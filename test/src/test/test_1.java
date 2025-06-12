package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// --------------------------------------
// ゴルフコースのパー情報を管理するクラス
// --------------------------------------
 class Course {
    private final int[] pars;

    public Course() {
        this.pars = new int[] {4, 4, 3, 4, 5, 4, 5, 3, 4, 4, 3, 4, 5, 4, 3, 4, 5, 4};
    }

    // 指定ホールのパー数を取得（0〜17）
    public int getPar(int holeIndex) {
        if (holeIndex >= 0 && holeIndex < pars.length) {
            return pars[holeIndex];
        }
        return 0; // 範囲外アクセス時は0を返す
    }

    // ホール数（18）を返す
    public int getHoleCount() {
        return pars.length;
    }
}

// スコア情報を保持・計算するクラス
class ScoreCard {
    private List<Integer> strokes; // 各ホールの打数を保持

    public ScoreCard() {
        this.strokes = new ArrayList<>();
    }

    // 打数を追加
    public void addStroke(int stroke) {
        strokes.add(stroke);
    }

    // 入力済ホール数を返す
    public int getHoleCount() {
        return strokes.size();
    }

    // Course情報に基づいてスコア（±値）を計算
    public int calculateScore(Course course) {
        int score = 0;
        int holeCount = Math.min(strokes.size(), course.getHoleCount());

        for (int i = 0; i < holeCount; i++) {
            score += strokes.get(i) - course.getPar(i); // 打数 - パー
        }

        return score;
    }

    // 入力がまだないかチェック
    public boolean isEmpty() {
        return strokes.isEmpty();
    }

    // スコアをリセット（再入力のため）
    public void clear() {
        strokes.clear();
    }
}

// メインクラス： 入力・全体制御
public class test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // 入力用スキャナ
        Course course = new Course();               // コース情報生成
        ScoreCard scoreCard = new ScoreCard();      // スコアカード生成

        // 入力処理ループ
        while (true) {
            System.out.print("Input > ");
            String input = scanner.nextLine().trim();  // 前後の空白除去

            // 空入力のチェック
            if (input.isEmpty()) {
                System.out.println("空の入力は無効です。再入力してください。");
                continue;
            }

            // スペース削除（カンマ区切り対応）
            input = input.replace(" ", "");

            // 使用可能な文字（数字とカンマ）かチェック
            if (!input.matches("^[0-9,]+$")) {
                System.out.println("無効な文字が含まれています。数字とカンマのみを使用してください。");
                continue;
            }

            // カンマで分割し、各ホールの打数を処理
            String[] tokens = input.split(",");
            boolean valid = true;

            for (String token : tokens) {
                if (token.isEmpty()) continue; // 空要素は無視

                try {
                    int stroke = Integer.parseInt(token);

                    if (stroke <= 0) {
                        System.out.println("0以下の数値が含まれています。再入力してください。");
                        valid = false;
                        break;
                    }

                    scoreCard.addStroke(stroke); // 有効な打数を記録

                } catch (NumberFormatException e) {
                    System.out.println("数値として認識できない値があります。再入力してください。");
                    valid = false;
                    break;
                }
            }

            // 入力不正だったらやり直し
            if (!valid || scoreCard.isEmpty()) {
                scoreCard.clear(); // 一度リセット
                continue;
            }

            // 正常終了 → スコア出力
            int holesPlayed = Math.min(scoreCard.getHoleCount(), course.getHoleCount());
            int finalScore = scoreCard.calculateScore(course);

            System.out.printf("Output: %dホール終了して、%+d\n", holesPlayed, finalScore);
            break;
        }
    }
}
