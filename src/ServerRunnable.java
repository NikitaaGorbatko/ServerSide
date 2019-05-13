import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunnable implements Runnable {

    private static ServerSocket serverSocket;
    private static BufferedReader incomingReader;
    private static BufferedWriter outgoingWriter;

    @Override
    public synchronized void run() {
        try {
            serverSocket = new ServerSocket(4004);
            System.out.println("Сервер запущен!");
            while (true) {
                Socket socket = serverSocket.accept();
                incomingReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outgoingWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String word = incomingReader.readLine();
                System.out.println(word);
                Runtime.getRuntime().exec(word);
                outgoingWriter.write("Client: " + word + "\n");
                outgoingWriter.flush();
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            System.out.println("Сервер закрыт!");
            try {
                serverSocket.close();
            } catch (IOException ex) {

            }

        }
    }
}
