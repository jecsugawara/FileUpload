package jointest.uc01;
import action.CustomerSearchAction;
// 結合番号５　ドライバクラス
public class JoinTest_OrderControl05 {
	public static void main(String[] args) {
		try {
			// 項番１，２
			CustomerSearchAction action1 = new CustomerSearchAction();
			String[] data = { "", "タカハシミホ" };
			String[][] tableData = action1.execute(data);
			for (String[] row : tableData) {
				for (String col : row) {
					System.out.print(col + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
