package jointest.uc01;
import action.CustomerSearchAction;
// 結合番号３　ドライバクラス
public class JoinTest_OrderControl03 {
	public static void main(String[] args) {
		try {
			// 項番１，２，３
			CustomerSearchAction action1 = new CustomerSearchAction();
			String[] data1 = { "08012345678", "" };
			String[][] tableData1 = action1.execute(data1);
			for (String[] row : tableData1) {
				for (String col : row) {
					System.out.print(col + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番４
			CustomerSearchAction action2 = new CustomerSearchAction();
			String[] data2 = { "", "シバタリュウイチ" };
			String[][] tableData2 = action2.execute(data2);
			for (String[] row : tableData2) {
				for (String col : row) {
					System.out.print(col + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番５
			CustomerSearchAction action3 = new CustomerSearchAction();
			String[] data3 = { "0314142135", "セト" };
			String[][] tableData3 = action3.execute(data3);
			for (String[] row : tableData3) {
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
