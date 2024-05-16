import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
import java.util.Scanner;

public class OsyougatuTCPClient {

    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            System.out.println("お年玉をあげます");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("私へのメッセージを入力してください(例:あなたはイケメンです。) ↓");
            String message = scanner.next();
            System.out.println("お年玉の金額を入力してください(例:100万円) ↓");
            String content = scanner.next();
            scanner.close();

            OsyougatuOtoshidama present = new OsyougatuOtoshidama();
            present.setMessage(message);
            present.setContent(content);

            oos.writeObject(present);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            OsyougatuOtoshidama okaeshiPresent = (OsyougatuOtoshidama) ois.readObject();

            String replayMsg = okaeshiPresent.getMessage();
            System.out.println("サーバからのメッセージは" + replayMsg);
            String replayContent = okaeshiPresent.getContent();
            System.out.println(replayContent + "をもらいました！");

            ois.close();
            oos.close();
            socket.close();

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}

