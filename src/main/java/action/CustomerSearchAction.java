/**
 * クラス名：	CustomerSearchAction
 * 概要　　：	顧客情報検索アクション
 * 作成者名：	丸山
 * 作成日　：	20XX/06/20
 * 修正者名：
 * 修正日　：
 */
package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class CustomerSearchAction {

	public String[][] execute(String[] data) throws Exception {
		CustomerSearchDBAccess csDao = new CustomerSearchDBAccess();
		ArrayList<Customer> list = null;
		String[][] customerData = null;

		data[0] = data[0].replaceAll(" ", "");
		data[0] = data[0].replaceAll("　", "");
		data[1] = data[1].replaceAll(" ", "");
		data[1] = data[1].replaceAll("　", "");

		if (!data[0].equals("") && data[1].equals("")) {
			list = csDao.searchCustomerByTel(data[0]);
		} else if (data[0].equals("") && !data[1].equals("")) {
			list = csDao.searchCustomerByKana(data[1]);
		} else if (!data[0].equals("") && !data[1].equals("")) {
			list = csDao.searchCustomer(data[0], data[1]);
		}

		if(list != null && list.size() != 0) {
			customerData = OrderControlUtility.customerToArray(list);
		}

		return customerData;
	}
}
