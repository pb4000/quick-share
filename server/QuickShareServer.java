import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class QuickShareServer {
    // sockets to handle connections
    private ServerSocket listener; // socket used to listen for new connections

    // array to store client connections
    ArrayList<Thread> list;

    // extra variables
    private int port;

    public static void main(String[] args) {
        QuickShareServer q = new QuickShareServer(args);
        q.run();
    }

    /**
     * Constructor which handles args
     */
    public QuickShareServer(String[] args) {
        list = new ArrayList<Thread>();
        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }
        port = Integer.parseInt(args[0]);
    }

    public void run() {
        try {
            // initializes listener socket
            listener = new ServerSocket(port);
            // continue listening for new conntections while not done running
            boolean done = false;
            while (!done) {
                // listens for a new connection
                Socket clientSocket = listener.accept();
                print("New connection from: " + clientSocket.getInetAddress());
                // passes the new connection to a connection handler and starts it
                list.add(new Thread(new ConnectionHandler(clientSocket)));
                list.get(list.size() - 1).start();
            }
            print("\nProgram is closing...");
            exitClients();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void exitClients() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).interrupt();
        }
    }

    /**
     * Literally just prints to the console because I don't
     * want to type System.out.println() every time
     * @param output
     */
    private void print(String output) {
        System.out.println(output);
    }

    /**
     * Prints usage of this program to the command line
     */
    private void printUsage() {
        System.out.println("Usage:\njava QuickShareServer <port>");
    }
}