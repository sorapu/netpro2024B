import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  
public class HowOldAreYou {






    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println("現在の年齢を入力してください（qまたはeで終了）:");
                String input = reader.readLine();

                if (input.equals("q") || input.equals("e")) {
                    System.out.println("終了します。");
                    break;
                }

                int age = Integer.parseInt(input);
                if (age < 0 || age >= 120) {
                    System.out.println("年齢が不正です。再入力してください。");
                    continue;
                }

                // 2030年の年齢を計算
                int age2030 = age + 9;

                // 誕生年の元号を求める
                int birthYear = 2022 - age; // 2022年時点での年齢から誕生年を推定
                String era = "";
                if (birthYear >= 2019) {
                    era = "令和";
                    birthYear -= 2018; // 令和元年からの経過年数に変換
                } else if (birthYear >= 1989) {
                    era = "平成";
                    birthYear -= 1988; // 平成元年からの経過年数に変換
                } else if (birthYear >= 1927) {
                    era = "昭和";
                    birthYear -= 1925; // 昭和元年からの経過年数に変換
                } else if (birthYear >= 1912) {
                    era = "大正";
                    birthYear -= 1911; // 大正元年からの経過年数に変換
                } else {
                    era = "明治";
                    birthYear -= 1867; // 明治元年からの経過年数に変換
                }

                System.out.println("2030年のあなたの年齢は " + age2030 + " 歳です。");
                System.out.println("あなたの誕生年は" + era + birthYear + "年です。");
            }
        } catch (IOException e) {
            System.out.println("入出力エラーが発生しました: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("入力が数値ではありません。再入力してください。");
        }
    }


    
}
