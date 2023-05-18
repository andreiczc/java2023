import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable server = () -> {
            TcpServer tcpServer = null;
            try {
                tcpServer = new TcpServer(8085);
                tcpServer.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    tcpServer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable client = () -> {
            try(Socket socketClient = new Socket("localhost", 8085)) {
                System.out.println(socketClient.isConnected());
                System.out.println("Connected to the server");

                // send a request
                OutputStream outputStream = socketClient.getOutputStream();
                outputStream.write("Hello from client".getBytes());
                outputStream.flush();

                // receive a response
                InputStream inputStream = socketClient.getInputStream();
                var response = inputStream.readAllBytes();
                var responseString = new String(response);
                System.out.println("Received from Server " + responseString);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        var threadServer = new Thread(server);
        threadServer.start();

        var threadClient = new Thread(client);
        threadClient.start();

        threadClient.join();
        threadServer.join();
    }
}