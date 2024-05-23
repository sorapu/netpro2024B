import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;




public class Server {
    public static void main(String[] args) {
        try {
            // サーバーソケットを生成
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("サーバーを起動しました。");

            // クライアントからの接続を待機
            Socket socket = serverSocket.accept();
            System.out.println("クライアントが接続しました。");

            // クライアントからのデータを受信
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ITask task = (ITask) inputStream.readObject();
            System.out.println("クライアントからタスクを受信しました。");

            // タスクを実行
            task.exec();

            // 結果を送信
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeInt(task.getResult());
            outputStream.flush();
            System.out.println("結果をクライアントに送信しました。");

            // クローズ処理
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
