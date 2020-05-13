import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionHandler implements Runnable {
    private Socket clientSocket; // client socket
    private InputStream input;  // input stream to take input from client
    private OutputStream output;    // output stream to send data to client
    private Scanner scan;   // scanner to read from input
    private PrintWriter printer;    // PrintWriter to print to output

    public ConnectionHandler(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {
        boolean done = false;
        initStreams();
        // requires client to login/create an account first
        verify();
        // once logged in, send inital shared links
        sendLinks();
        // then, listen for client input
        while (!done && scan.hasNextLine()) {
            if (Thread.interrupted()) {
                return;
            }
            String line = scan.nextLine();
            /**
             * DO THINGS
             * TODO
             */
        }
    }

    /**
     * Initializes IO streams with client
     */
    private void initStreams() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
            scan = new Scanner(input);
            printer = new PrintWriter(new OutputStreamWriter(output), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends previously shared links to client
     */
    private void sendLinks() {
        // TODO
    }

    /**
     * Verifies that the connected client is actually a client. Asks
     * for encrypted email/password to run against databse
     */
    private void verify() {
        // TODO
    }

}