import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in, "UTF-8");

            while (true) {
                System.out.print("素数を求める数値を入力してください（1以下の値で終了）: ");
                int number = scanner.nextInt();

                if (number <= 1) {
                    System.out.println("1以下の値が入力されたため、クライアントを終了します。");
                    break;
                }

                Socket socket = new Socket("localhost", 12345);
                System.out.println("サーバーに接続しました。");

                ITask task = new PrimeTask();
                task.setExecNumber(number);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(task);
                outputStream.flush();
                System.out.println("タスクをサーバーに送信しました。");

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                int result = inputStream.readInt();
                System.out.println("サーバーから結果を受信しました。");
                System.out.println(number + "以下の最大素数は " + result + " です。");

                inputStream.close();
                outputStream.close();
                socket.close();
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
