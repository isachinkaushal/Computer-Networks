import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            Thread receiveThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        String message;
                        while ((message = input.readLine()) != null) {
                            System.out.println("Server: " + message);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading from server: " + e.getMessage());
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
                        System.out.println("Error writing to server: " + e.getMessage());
                    }
                }
            });

            receiveThread.start();
            sendThread.start();

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
