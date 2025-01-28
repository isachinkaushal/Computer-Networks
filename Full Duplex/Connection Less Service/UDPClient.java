import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";  
        int port = 1234;  
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);

            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            while (true) {
                System.out.print("Client: ");
                String clientMessage = clientInput.readLine();
                sendData = clientMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, port);
                socket.send(sendPacket);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client has exited.");
                    break;
                }

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + serverMessage);

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
