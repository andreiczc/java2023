package ro.ase.lab6;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer implements Closeable {

    private final DatagramSocket server;

    public UdpServer(int port) throws SocketException {
        this.server = new DatagramSocket(port);
        System.out.printf("UDP server bound to port %d\n", port);
    }

    public void run() throws IOException {
        while(true) {
            try {
                serverLogic();
            } catch (CloseUdpServer e) {
                break;
            }
        }
    }

    private void serverLogic() throws IOException, CloseUdpServer {
        // prepare receiving packet
        byte[] receivingBuffer = new byte[128];
        DatagramPacket receivingPacket = new DatagramPacket(
                receivingBuffer, receivingBuffer.length
        );

        // wait for a message - blocking call
        server.receive(receivingPacket);

        // process message from sender
        String parsedResponse = new String(receivingPacket.getData());
        System.out.printf("Received from client %s\n",
                    parsedResponse);
        if(parsedResponse.contains("CLOSE")) {
            throw new CloseUdpServer();
        }

        // send response back
        String responseString = "OK";
        DatagramPacket responsePacket = new DatagramPacket(
                        responseString.getBytes(), responseString.length(),
                        receivingPacket.getSocketAddress());

        server.send(responsePacket);
    }

    @Override
    public void close() throws IOException {
        server.close();
    }
}
