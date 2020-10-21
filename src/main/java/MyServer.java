import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {

                String message;

                while ((message = in.readLine()) != null) {
                    if (message.equals("end")) break;
                    try {
                        int number = Integer.parseInt(message);
                        out.println(calcFibonacci(number));
                    } catch (NumberFormatException e) {
                        out.println("неверный формат ввода...");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static long calcFibonacci(int number) {
        long[] fibonacci = new long[number + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i < number; ++i) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci[number];
    }

}