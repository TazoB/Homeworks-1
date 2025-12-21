import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                out.println("Enter your username: ");
                String name = in.readLine();

                synchronized (ChatRoom.sockets) {
                    boolean exists = ChatRoom.sockets.stream()
                            .anyMatch(c -> c.username.equals(name));

                    if (!exists) {
                        username = name;
                        ChatRoom.sockets.add(this);
                        break;
                    }
                }
                out.println("Username already taken.");
            }

            ChatRoom.broadcast(username + " joined the chat");
            String msg;
            while ((msg = in.readLine()) != null) {
                msg = msg.trim();
                if (msg.isEmpty()) continue;

                if (msg.equalsIgnoreCase("exit")) {
                    break;
                }

                if (msg.startsWith("/pm ")) {
                    String[] parts = msg.split("\\s+", 3);

                    if (parts.length < 3) {
                        out.println("Usage: /pm <username> <message>");
                        continue;
                    }

                    String receiver = parts[1];
                    String privateMsg = parts[2];

                    if (receiver.equals(username)) {
                        out.println("You cannot PM yourself.");
                        continue;
                    }

                    ChatRoom.sendPrivateMessage(username, receiver, privateMsg);
                    continue;
                } else if(msg.equals("/username")) {
                    out.println("If you want to change the username");
                    while(true) {
                        out.println("Enter your username: ");
                        String name = in.readLine();
                        if(name.equals(username)) break;
                        synchronized (ChatRoom.sockets) {
                            boolean exists = ChatRoom.sockets.stream()
                                    .anyMatch(c -> c.username.equals(name));

                            if (!exists) {
                                username = name;
                                break;
                            }
                        }
                        out.println("Username already taken.");
                    }
                    out.println("You have successfully changed your username to: " + username);
                    continue;
                } else if(msg.equals("/leave")) {
                    out.println("Are you sure you want to leave the chat? (Y/N):");
                    String response = in.readLine();
                    if(response.equalsIgnoreCase("y")) {
                        out.println("You are leaving the chat");
                        ChatRoom.removeClient(this);
                        out.println("Goodbye!!!");
                        break;
                    }
                }
                ChatRoom.broadcast(username + ": " + msg);
            }

        } catch (IOException e) {
            System.out.println(username + " disconnected.");
        } finally {
            ChatRoom.removeClient(this);
            close();
        }
    }

    public PrintWriter getOut() {
        return out;
    }

    public String getUsername() {
        return username;
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
