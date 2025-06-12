package test_3;

public class keisan extends test_object {

	public void hikidasi(int x) {// 引き出す処理

		if (bank - x < 0) {

			System.out.println("\n残高が足りません。\n");

		} else if (bank - x >= 0) {

			bank = bank - x;

			System.out.println("\n引き出し後の残高:" + bank + "\n");
		}
	}

	public void azuke(int x) {
		bank = bank + x;

		System.out.println("\n入金後残高：" + bank + "\n");
	}

	public void zanndaka() {// 残高照会
		System.out.println("\n残高:" + bank);
	}

}
