package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ゴルフコース情報（パー値）



// 選手情報とスコア管理
class Player {
    private String name;
    private List<Integer> strokes;

    public Player(String name) {
        this.name = name;
        this.strokes = new ArrayList<>();
    }

    public void addStroke(int stroke) {
        strokes.add(stroke);
    }

    public int calculateScore(Course course) {
        int score = 0;
        for (int i = 0; i < course.getHoleCount(); i++) {
            score += (strokes.get(i) - course.getPar(i));
        }
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean isValid() {
        return strokes.size() == 18;
    }
}

public class test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Course course = new Course();

        while (true) {
            System.out.print("Input > ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("空の入力は無効です。再入力してください。");
                continue;
            }

            input = input.replace(" ", "");

            // カンマと数字のみ許可（日本語文字が含まれていても最初は許容）
            if (!input.matches("^[^,]+,[^,]+(,[0-9]+){36}$")) {
                System.out.println("形式が正しくありません。名前1,名前2,スコア36個（カンマ区切り）で入力してください。");
                continue;
            }

            String[] tokens = input.split(",");

            // 名前とスコア分割
            String name1 = tokens[0];
            String name2 = tokens[1];

            Player player1 = new Player(name1);
            Player player2 = new Player(name2);

            try {
                for (int i = 0; i < 18; i++) {
                    int stroke = Integer.parseInt(tokens[2 + i]);
                    player1.addStroke(stroke);
                }
                for (int i = 0; i < 18; i++) {
                    int stroke = Integer.parseInt(tokens[20 + i]);
                    player2.addStroke(stroke);
                }
            } catch (NumberFormatException e) {
                System.out.println("数値に変換できないスコアがあります。再入力してください。");
                continue;
            }

            if (!player1.isValid() || !player2.isValid()) {
                System.out.println("両選手とも18ホールのスコアが必要です。");
                continue;
            }

            // スコア計算・出力
            int score1 = player1.calculateScore(course);
            int score2 = player2.calculateScore(course);

            System.out.printf("%s のスコア: %+d\n", player1.getName(), score1);
            System.out.printf("%s のスコア: %+d\n", player2.getName(), score2);

            // 勝敗判定
            if (score1 < score2) {
                System.out.printf("勝者: %s\n", player1.getName());
            } else if (score1 > score2) {
                System.out.printf("勝者: %s\n", player2.getName());
            } else {
                System.out.println("引き分けです！");
            }

            break; // 正常終了
        }
    }
}
