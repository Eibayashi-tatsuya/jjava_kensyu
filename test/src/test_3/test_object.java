package test_3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class test_object {


	protected int key = 8012;
	protected int bank = 300000;
	protected int number, number2;
	protected int draw = 0, leav = 0;

	// 暗証番号認証処理
	public boolean pass() {
		
		keisan_class ke = new keisan_class();

		Scanner scan = new Scanner(System.in);

		
		int num =0;//カウント
		boolean test ;

		while (num < 3) {

			try {

				System.out.print("暗証番号を入力してください：");

				number = scan.nextInt();
				
				if(key == number) {
					System.out.println("認証成功");
					break;
				}
				
			} catch (InputMismatchException e) {

				//InputMismatchExceptionエラーが発生したら以下の処理を行う
				
				System.out.println("\n正しい番号を入力してください\n");

				scan.nextLine(); // 入力バッファをクリア
			}
			num++;
		}
		
		if(num == 3) {
			test = false;
			return test;
		}
		
		
		test = true;
		return test;
	}
	

	// 操作処理
	public void handling() {
		keisan_class ke = new keisan_class();

		
		Scanner scan = new Scanner(System.in);


		while (true) {

			System.out.println("\n操作メニュー");

			System.out.println("0 終了\n1 残高照会\n2 引き出し\n3 預け入れ");

			try {

				System.out.print("選択：");

				number2 = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\n正しい番号を入力してください\n");
				scan.nextLine();
				continue;
			}

			switch (number2) {

			case 0:
				System.out.println("\n終了します");
				break;

			case 1:
				// 残高照会
				ke.zanndaka();
				break;

			case 2:
				// 引き出し
				System.out.print("\n引き出し金額：");

				try {
					draw = scan.nextInt();

				} catch (InputMismatchException e) {
					System.out.println("\n正しい金額を入力してください\n操作メニューへ戻ります\n");
					scan.nextLine();//バッファリセット
					continue;
				}
				ke.hikidasi(draw);//引き出し処理
				break;

			case 3:

				System.out.print("\n預け入れ金額:");

				try {
					leav = scan.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\n正しい金額を入力してください\n操作メニューへ戻ります\n");
					scan.nextLine();
					continue;
				}

				ke.azuke(leav);//預入処理のメソッドを呼び出し実行
				break;
			}

			if (number2 == 0) {//処理終了させるための処理
				break;
			}
		}
	}

}
