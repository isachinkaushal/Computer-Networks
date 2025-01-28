import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        int port = 1234;
        DatagramSocket socket = null;
        
        try {
            socket = new DatagramSocket(port);
            System.out.println("Server is running... Waiting for client messages.");

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            DatagramPacket receivePacket;
            InetAddress clientAddress;
            int clientPort;

            while (true) {
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                
                clientAddress = receivePacket.getAddress();
                clientPort = receivePacket.getPort();
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client has exited. Server shutting down.");
                    break;
                }

                System.out.print("Server: ");
                String serverMessage = serverInput.readLine();
                
                sendData = serverMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);  

                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server has exited.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
