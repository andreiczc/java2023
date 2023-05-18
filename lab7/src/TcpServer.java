import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TcpServer implements Closeable {

    private ServerSocket server;
    private ExecutorService executorService;
    private boolean isRunning;

    public TcpServer(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.executorService = Executors.newFixedThreadPool(10);
        this.isRunning = true;
        System.out.println("Started TCP server on port " + port);
    }

    public void run() throws Exception {
        while(isRunning) {
            Socket client = server.accept();
            executorService.submit(() -> {
                try {
                    handleRequest(client);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void handleRequest(Socket client) throws Exception {
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();

        // receiving a request
        var request = inputStream.readAllBytes();
        var stringRequest = new String(request);
        System.out.printf("Received from %s: %s\n",
                client.getInetAddress().getHostAddress(), stringRequest);

        // sending back response: Hello World!
        outputStream.write("Hello World!".getBytes());
        outputStream.flush();

        // close the server
        if(stringRequest.equals("CLOSE")) {
            close();
        }
    }

    @Override
    public void close() throws IOException {
        server.close();
        this.isRunning = false;
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
