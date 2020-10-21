import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 9999);

        try (BufferedReader in = new BufferedReader((
                new InputStreamReader(socket.getInputStream())));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String message;

            while (true) {
                System.out.println("Введите число для вычисления или  введите end для  выхода.");
                message = scanner.nextLine();
                out.println(message);
                if ("end".equals(message)) break;
                System.out.println("Ответ от сервера: " + in.readLine());
            }
        }
    }
}