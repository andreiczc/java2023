package ro.ase.lab6;

import java.io.IOException;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.*;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        // UDP server
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Void> serverThread = () -> {
            UdpServer server = new UdpServer(7778);
            server.run();
            server.close();

            return null;
        };
        Future<Void> future = executorService.submit(serverThread);

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

        byte[] receivingBuffer = new byte[128];
        DatagramPacket receivingPacket = new DatagramPacket(
                receivingBuffer, receivingBuffer.length
        );
        client.receive(receivingPacket); // blocking call
        System.out.printf("Received from server: %s\n", new String(receivingPacket.getData()));

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }
}
