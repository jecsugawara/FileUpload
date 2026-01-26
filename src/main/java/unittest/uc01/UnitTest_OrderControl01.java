package unittest.uc01;
import model.Customer;
// 単体番号１　ドライバクラス（Customerクラス）
public class UnitTest_OrderControl01 {
	public static void main(String[] args) {
		// 項番１，５，９，13，17，21
		Customer customer1 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
		System.out.println("--項番１，５，９，13，17，21--------------");
		System.out.println(customer1.getCustId()); // 項番５
		System.out.println(customer1.getCustName()); // 項番９
		System.out.println(customer1.getKana()); // 項番13
		System.out.println(customer1.getTel()); // 項番17
		System.out.println(customer1.getAddress()); // 項番21
		// 項番２，６，10，14，18，22
		Customer customer2 = new Customer(0, null, null, null, null);
		System.out.println("--項番２，６，10，14，18，22--------------");
		System.out.println(customer2.getCustId()); // 項番６
		System.out.println(customer2.getCustName()); // 項番10
		System.out.println(customer2.getKana()); // 項番14
		System.out.println(customer2.getTel()); // 項番18
		System.out.println(customer2.getAddress()); // 項番22
		// 項番３
		Customer customer3 = new Customer();
		System.out.println("--項番３----------------------------------");
		customer3.setCustId(2);
		System.out.println(customer3.getCustId());
		// 項番４
		Customer customer4 = new Customer();
		System.out.println("--項番４----------------------------------");
		customer4.setCustId(0);
		System.out.println(customer4.getCustId());
		// 項番７
		Customer customer7 = new Customer();
		System.out.println("--項番７----------------------------------");
		customer7.setCustName("伊藤華英");
		System.out.println(customer7.getCustName());
		// 項番８
		Customer customer8 = new Customer();
		System.out.println("--項番８----------------------------------");
		customer8.setCustName(null);
		System.out.println(customer8.getCustName());
		// 項番11
		Customer customer11 = new Customer();
		System.out.println("--項番11----------------------------------");
		customer11.setKana("イトウハナエ");
		System.out.println(customer11.getKana());
		// 項番12
		Customer customer12 = new Customer();
		System.out.println("--項番12----------------------------------");
		customer12.setKana(null);
		System.out.println(customer12.getKana());
		// 項番15
		Customer customer15 = new Customer();
		System.out.println("--項番15----------------------------------");
		customer15.setTel("09023456781");
		System.out.println(customer15.getTel());
		// 項番16
		Customer customer16 = new Customer();
		System.out.println("--項番16----------------------------------");
		customer16.setTel(null);
		System.out.println(customer16.getTel());
		// 項番19
		Customer customer19 = new Customer();
		System.out.println("--項番19----------------------------------");
		customer19.setAddress("東京都千代田区神田小川町2-1-1");
		System.out.println(customer19.getAddress());
		// 項番20
		Customer customer20 = new Customer();
		System.out.println("--項番20----------------------------------");
		customer20.setAddress(null);
		System.out.println(customer20.getAddress());
	}
}
