import java.io.*;
import java.net.*;


public class Client {

    private static final String SERVER_IP = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, 9090);

        BufferedReader input = new BufferedReader((new InputStreamReader(socket.getInputStream())));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true );

        while (true) {
            System.out.println(">");
            String command = keyboard.readLine();

            if (command.equals("quit")) break;

            out.println(command);

            String serverResponse = input.readLine();
            System.out.println("Server says: " + serverResponse);
        }

        socket.close();
        System.exit(0);

    }
}