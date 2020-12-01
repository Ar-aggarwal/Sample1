import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static String[] names = {"Willy", "Felix", "Carlsbad", "Hobob"};
    public static String[] adjs = {"the gentle", "the un-gentle", "the overwrought", "the urbane"};

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);

        while (true) {
            System.out.println("[SERVER] Waiting for client connection ...");
            Socket client = listener.accept();
            System.out.println("[SERVER] Connected to Client !!");
            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }

    public static String getRandomName() {
        String name = names[(int) (Math.random() * names.length)];
        String adj = adjs[(int) (Math.random() * adjs.length)];
        return (name + "" + adj);
    }
}

