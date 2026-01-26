package jointest.uc01;
import action.CustomerSearchAction;
// 結合番号４　ドライバクラス
public class JoinTest_OrderControl04 {
	public static void main(String[] args) {
		try {
			// 項番１
			CustomerSearchAction action1 = new CustomerSearchAction();
			String[] data = { "09045678123", "" };
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
