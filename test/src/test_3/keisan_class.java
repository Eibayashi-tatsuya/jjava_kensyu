package test_3;

public class keisan_class extends test_object {

	public void zanndaka() {
		System.out.println("残高：" + bank);
	}

	public void hikidasi(int price) {

		if (bank - price < 0) {

			System.out.println("残高がありません。");

		} else {
			bank = bank - price;
			System.out.println("お金を引き出しました。");
			return;
		}

	}

	public void azuke(int price) {
		bank += price;
		System.out.println("入金しました。");
		System.out.println("入金後残高：" + bank);
		return;
	}

}
