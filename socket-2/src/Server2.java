import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("サーバーを起動しました。");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("クライアントが接続しました。");

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ITask task = (ITask) inputStream.readObject();
                System.out.println("クライアントからタスクを受信しました。");

                task.exec();

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeInt(task.getResult());
                outputStream.flush();
                System.out.println("結果をクライアントに送信しました。");

                inputStream.close();
                outputStream.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

