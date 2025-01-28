// Program in Java for simplex communication using Connection Oriented service.

// Client Side

import java.io.*;
import java.net.*;

public class MyClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to server...");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            
            String message;
            while (true) {
                System.out.print("Enter message (type 'exit' to quit): ");
                message = consoleInput.readLine();
                
                out.writeUTF(message);
                
                if (message.equals("exit")) {
                    break;
                }
                
                String serverResponse = in.readUTF();
                System.out.println("Server response: " + serverResponse);
            }
            
            in.close();
            out.close();
            consoleInput.close();
            System.out.println("Client disconnected.");
            
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
