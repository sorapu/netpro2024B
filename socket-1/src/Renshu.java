public class Renshu {

    // xを2倍にして返す関数
    public int doubleValue(int x) {
        return x * 2;
    }

    // 1からnまでの整数の合計値を返す関数
    public int sumUpToN(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // pからqまでの整数の合計値を返す関数
    public int sumFromPtoQ(int p, int q) {
        if (p > q) {
            return -1; // pがqより大きい場合は -1 を返す
        int sum = 0;
        for (int i = p; i <= q; i++) {
            sum += i;
        }
        return sum;
    }

    // 配列a[]の指定されたindexから以降の要素の合計値を返す。不正なindexの場合は-1を返す。
    public int sumFromArrayIndex(int[] a, int index) {
        if (index < 0 || index >= a.length)
            return -1;

        int sum = 0;
        for (int i = index; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    // 配列aの中で最大の値を返す関数
    public int selectMaxValue(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int value : a) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // 配列aの中で最大の値が入っている要素の位置（index）を返す関数。最大値が複数の場合は最小のindexを返す。
    public int selectMaxIndex(int[] a) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= max) {
                max = a[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // 配列pのi番目とj番目の要素を入れ替える関数
    public void swapArrayElements(int[] p, int i, int j) {
        if (i >= 0 && i < p.length && j >= 0 && j < p.length) {
            int temp = p[i];
            p[i] = p[j];
            p[j] = temp;
        }
    }

    // 同じ長さの二つの配列aとbの内容を入れ替える。返り値は交換が成功したかどうかを示すブール値。
    public boolean swapTwoArrays(int[] a, int[] b) {
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            a[i] = b[i];
            b[i] = temp;
        }
        return true;
    }

    public static void main(String[] args) {
        // テストを行う場合は、JUnitを使用してテストクラスを作成することが一般的です。
        // したがって、メインメソッドでは通常、テストの起動ではなく、他のクラスやライブラリの利用例を記述します。
        // ただし、メインメソッドが必要な場合は、ここに追加できます。
    }
}
