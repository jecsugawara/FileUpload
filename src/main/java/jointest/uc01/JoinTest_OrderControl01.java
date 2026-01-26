package jointest.uc01;
import java.util.ArrayList;
import model.Customer;
import model.OrderControlUtility;
// 結合番号１　ドライバクラス
public class JoinTest_OrderControl01 {
	public static void main(String[] args) {
		// 項番１～５
		ArrayList<Customer> list = new ArrayList<Customer>();
		Customer customer = new Customer(1, "西島康介", "ニシジマコウスケ", "01234567890", "東京都荒川区西日暮里");
		list.add(customer);
		String[][] tableData = OrderControlUtility.customerToArray(list);
		System.out.println("項番１～５");
		System.out.println(tableData[0][0]); // 項番２
		System.out.println(tableData[0][1]); // 項番３
		System.out.println(tableData[0][2]); // 項番４
		System.out.println(tableData[0][3]); // 項番５
	}
}
