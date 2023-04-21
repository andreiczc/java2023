package ro.ase.lab6;

import java.io.IOException;
import java.net.*;

public class Application {

    public static void main(String[] args) throws IOException {
        // UDP client
        DatagramSocket client = new DatagramSocket();
        String sendingMessage = "Hello";
        DatagramPacket sendingPacket =
                new DatagramPacket(
                        sendingMessage.getBytes(),
                        sendingMessage.length(),
                        InetAddress.getByName("localhost"),
                        7778
                );
        client.send(sendingPacket);
    }
}
