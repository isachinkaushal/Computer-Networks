import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for client connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            Thread receiveThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        String message;
                        while ((message = input.readLine()) != null) {
                            System.out.println("Client: " + message);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading from client: " + e.getMessage());
                    }
                }
            });

            Thread sendThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        String message;
                        while (true) {
                            message = consoleInput.readLine();
                            output.println(message);
                        }
                    } catch (IOException e) {
                        System.out.println("Error writing to client: " + e.getMessage());
                    }
                }
            });

            receiveThread.start();
            sendThread.start();

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
