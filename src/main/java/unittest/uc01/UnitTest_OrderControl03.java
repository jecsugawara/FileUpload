package unittest.uc01;
import action.CustomerSearchAction;
// 単体番号３　ドライバクラス（CustomerSearchActionクラス）
public class UnitTest_OrderControl03 {
	public static void main(String[] args) {
		CustomerSearchAction action = new CustomerSearchAction();
		try {
			// 項番１
			System.out.println("--項番１----------------------------------");
			String[] data1 = { "09045678123", "" };
			String[][] tableData1 = action.execute(data1);
			System.out.println(tableData1[0][0]);
			System.out.println(tableData1[0][1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番２
			System.out.println("--項番２----------------------------------");
			String[] data2 = { "", "タカハシミホ" };
			String[][] tableData2 = action.execute(data2);
			System.out.println(tableData2[0][0]);
			System.out.println(tableData2[0][1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番３
			System.out.println("--項番３----------------------------------");
			String[] data3 = { "0314142135", "ワタナベ" };
			String[][] tableData3 = action.execute(data3);
			System.out.println(tableData3[0][0]);
			System.out.println(tableData3[0][1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番４
			System.out.println("--項番４----------------------------------");
			String[] data4 = { "0120500500", "" };
			String[][] tableData4 = action.execute(data4);
			System.out.println(tableData4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番５
			System.out.println("--項番５----------------------------------");
			String[] data5 = { "", "イワサキキョウコ" };
			String[][] tableData5 = action.execute(data5);
			System.out.println(tableData5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番６
			System.out.println("--項番６----------------------------------");
			String[] data6 = { "", "" };
			String[][] tableData6 = action.execute(data6);
			System.out.println(tableData6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
