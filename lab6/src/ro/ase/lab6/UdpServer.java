package ro.ase.lab6;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UdpServer implements Closeable {

    private final DatagramSocket server;
    private final ExecutorService executorService;
    private boolean run;

    public UdpServer(int port) throws SocketException {
        this.server = new DatagramSocket(port);
        this.executorService = Executors.newFixedThreadPool(2);
        this.run = true;
        System.out.printf("UDP server bound to port %d\n", port);
    }

    public void run() throws IOException {
        while(run) {
            // prepare receiving packet
            byte[] receivingBuffer = new byte[128];
            DatagramPacket receivingPacket = new DatagramPacket(
                    receivingBuffer, receivingBuffer.length
            );

            // wait for a message - blocking call
            server.receive(receivingPacket);

            executorService.submit(() -> {
                try {
                    serverLogic(receivingPacket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void serverLogic(DatagramPacket receivingPacket) throws IOException {
        // process message from sender
        String parsedResponse = new String(receivingPacket.getData());
        System.out.printf("Received from client %s\n",
                    parsedResponse);

        // send response back
        String responseString = "OK";
        DatagramPacket responsePacket = new DatagramPacket(
                        responseString.getBytes(), responseString.length(),
                        receivingPacket.getSocketAddress());

        server.send(responsePacket);

        // if msg == CLOSE, close server
        if(parsedResponse.contains("CLOSE")) {
            this.close();
        }
    }

    @Override
    public void close() throws IOException {
        this.run = false;
        server.close();
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
