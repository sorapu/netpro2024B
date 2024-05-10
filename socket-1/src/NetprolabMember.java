
import java.util.Random;

public class NetprolabMember {
    

        public static final int years = 15;
        public static final int columns = 3;
    
        public static void main(String[] args) {
            int[][] members = new int[years][columns];
            Random random = new Random();
            double totalRatio = 1;
            for (int i = 0; i < years; i++) {
                //学生の総数
                members[i][0] = 120 + (random.nextInt(20) - 10);
                //女性の割合(%)
                members[i][1] = 20 + i; // 初年度から毎年1%上昇
                //岩井研の人数
                members[i][2] = 10 + (random.nextInt(7) - 3); // 岩井研の定員は10人で、+-3人のランダム性
    
                // 男性数を求める
                int men = members[i][0] - (int) (members[i][0] * ((float) members[i][1] / 100));
                // 総数から岩井研の人数を取り出す組み合わせ
                long cpsRatio = combination(members[i][0], members[i][2]);
                // 男性の中から岩井研のメンバーを取り出す組み合わせ
                long menRatio = combination(men, members[i][2]);
                
                // 岩井研の人数が0以下の場合は組み合わせを0として扱う
                if (members[i][2] <= 0) {
                    cpsRatio = 1;
                }
                
                // 岩井研の人数に男性しか入らない割合
                totalRatio *= (double) menRatio / cpsRatio;
            }
            double noFemaleProbability = Math.pow(totalRatio, years); // 15年間の確率を計算
            System.out.println("15年間岩井研に女性の学生が来ない確率: " + noFemaleProbability);
        }
    
        // Combinationを計算するメソッド
        public static final long combination(final int n, int r) {
            long numerator = factorial(n);
            long denominator = factorial(r) * factorial(n - r);
            // 分母が0になる場合は1を返す
            if (denominator == 0) {
                return 1;
            }
            return numerator / denominator;
        }
    
        // 階乗を計算するメソッド
        public static final long factorial(int n) {
            if (n == 0) return 1;
            long result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }
    