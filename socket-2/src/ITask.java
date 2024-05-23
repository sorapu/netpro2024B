import java.io.Serializable;

// 計算タスクのインターフェース
interface ITask extends Serializable {
    void setExecNumber(int x); // クライアントで最初に計算させる数字を入力しておく関数
    void exec(); // サーバで計算を実行させる関数...計算アルゴリズムが記載される。
    int getResult(); // クライアントで結果を取り出す関数
}

// 最大素数を求めるタスクの実装
class PrimeTask implements ITask {
    private int execNumber; // クライアントから送られてくる数字
    private int result; // 計算結果

    // クライアントで最初に計算させる数字を設定
    public void setExecNumber(int x) {
        execNumber = x;
    }

    // サーバで最大素数を計算
    public void exec() {
        result = findLargestPrime(execNumber);
    }

    // クライアントで結果を取得
    public int getResult() {
        return result;
    }

    // 最大素数を求めるメソッド
    private int findLargestPrime(int x) {
        for (int i = x; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 0; // 素数が見つからなかった場合は0を返す
    }

    // 素数判定メソッド
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}