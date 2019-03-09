import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiMsgMultiClientServer {

    private ServerSocket serverSocket;
    private QueueModel queue;
    public MultiMsgMultiClientServer(int serverPort , QueueModel queue) throws IOException {
        this.queue = queue;
        serverSocket = new ServerSocket(serverPort);
    }

    public void start() throws IOException {
        System.out.println("start listening for clients... ");
        while (true) {
            new MultiClientHandler(serverSocket.accept(),queue).start();
            System.out.println("Client connected");
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class MultiClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private QueueModel Queue;

        public MultiClientHandler(Socket socket, QueueModel Queue) throws IOException {
            this.Queue = Queue;
            this.clientSocket = socket;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

        public void run() {
            try {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("...")) {
                        System.out.println("sleep untill peers be ready");
                        sleep(5000);

                    }
                    System.out.println(inputLine);
                    while (!Queue.setMessage(inputLine)){//no space space
                        Peer.SendMessage("...");
                        sleep(6000);
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
