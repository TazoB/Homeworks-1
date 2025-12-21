import java.io.*;
import java.net.Socket;

public class ChatUser extends StringReader {

    public static void main(String[] args) {
        Socket socket = null;

        while (true) {
            String input = readString("""
                    Hello !!!
                    Enter <host>:<port> to connect to running server.
                    Enter 'exit' to leave the chat.
                    Enter '/pm <receiver> <text>' to send private messages.
                    Enter '/username' to change username.
                    Enter '/leave' to leave the chat.
                    """);

            if (input.equalsIgnoreCase("exit")) return;

            if (input.contains(":")) {
                try {
                    String host = input.substring(0, input.indexOf(":"));
                    int port = Integer.parseInt(input.substring(input.indexOf(":") + 1));
                    socket = new Socket(host, port);
                    break;
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Thread reader = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });

            // Writer thread
            Thread writer = new Thread(() -> {
                while (true) {
                    String msg = readString("");
                    out.println(msg);
                    if (msg.equalsIgnoreCase("exit")) {
                        break;
                    }
                }
            });

            reader.start();
            writer.start();

            writer.join();   // wait until user exits
            socket.close();  // close everything cleanly

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
