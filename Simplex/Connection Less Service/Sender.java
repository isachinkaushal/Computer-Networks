import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress receiverAddress = InetAddress.getByName("localhost");
            byte[] buffer = "Hello this is a checking message for simplex communication using UDP (connection less service).".getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, 9876);
            socket.send(packet);
            socket.close();
            System.out.println("Message sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
