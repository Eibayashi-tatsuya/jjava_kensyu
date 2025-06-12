package test_3;

import java.util.Scanner;

//aaaaaaaaaaaaaaaaaaa
public class test3_2 {

	public static void main(String[] args) {

		test_object obu = new test_object();
		Scanner scan = new Scanner(System.in);

		// ---------------------------------
		// ATMの一連の処理
		// --------------------------------
		
		// 暗証番号認証する処理
		boolean test = obu.pass();
		
		//戻り値がtrueなら続きの処理
		if (test == true) {
			
				obu.handling();
			
		} else {
			
			System.out.println("\n３回間違えた為、強制終了します");
			
		}
		

	}

}
