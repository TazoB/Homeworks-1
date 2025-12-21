import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatRoom extends StringReader {
    static List<ClientHandler> sockets = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println(sockets.size());
        ServerSocket serverSocket = null;
        boolean wantsToStop = false;

        while(true) {
            String input = readString("""
                    Hello !!!
                    Enter <port> in order to start the chat room.
                    Enter 'exit' to leave the chat.
                    """);
            if(input.equalsIgnoreCase("exit")) {
                wantsToStop = true;
                break;
            }
            try {
                serverSocket = new ServerSocket(Integer.parseInt(input));
                System.out.println("Server has been established.");

                ServerSocket finalServerSocket = serverSocket;
                Thread getSocket = new Thread(() -> {
                    while(true) {
                        try {
                            Socket socket = finalServerSocket.accept();
                            if(! socket.isClosed()) {
                                ClientHandler client = new ClientHandler(socket);
                                new Thread(client).start();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                getSocket.start();
                break;
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if(wantsToStop) {
            System.out.println("The Server is closing...");
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Goodbye!...");
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static void broadcast(String message) {
        System.out.println(message);
        synchronized (sockets) {
            for (ClientHandler client : sockets) {
                client.getOut().println(message);
            }
        }
    }


    static void sendPrivateMessage(String sender, String receiver, String message) {
        boolean delivered = false;

        synchronized (sockets) {
            for (ClientHandler client : sockets) {
                if (client.getUsername().equals(receiver)) {
                    client.getOut().println(
                            "PM from " + sender + ": " + message
                    );
                    delivered = true;
                    break;
                }
            }
        }

        if (!delivered) {
            synchronized (sockets) {
                for (ClientHandler client : sockets) {
                    if (client.getUsername().equals(sender)) {
                        client.getOut().println(
                                "User '" + receiver + "' not found."
                        );
                        break;
                    }
                }
            }
        }
    }

    static void removeClient(ClientHandler client) {
        sockets.remove(client);
        broadcast(client.getUsername() + " left the chat");
    }
}