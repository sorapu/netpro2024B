import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {

    public static void main(String[] args) {
        try {
            // サーバーに接続
            Socket socket = new Socket("localhost", 12345);
            System.out.println("サーバーに接続しました。");

            // タスクを作成
            ITask task = new PrimeTask();
            task.setExecNumber(100); // 例として100以下の最大素数を求める

            // タスクを送信
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(task);
            outputStream.flush();
            System.out.println("タスクをサーバーに送信しました。");

            // 結果を受信
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            int result = inputStream.readInt();
            System.out.println("サーバーから結果を受信しました。");
            System.out.println("100以下の最大素数は " + result + " です。");

            // クローズ処理
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
