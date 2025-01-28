import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String receivedMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received message: " + receivedMessage);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
