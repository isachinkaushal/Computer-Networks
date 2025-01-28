//  Program in Java for simplex communication using Connection Oriented service.

// ---------------Server side--------------


import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            
            String clientMessage;
            while (!(clientMessage = in.readUTF()).equals("exit")) {
                System.out.println("Received from client: " + clientMessage);
                out.writeUTF("Echo: " + clientMessage);
            }
            
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Server connection closed.");
            
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
