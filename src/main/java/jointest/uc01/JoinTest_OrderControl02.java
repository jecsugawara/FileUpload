package jointest.uc01;
import java.util.ArrayList;
import dao.CustomerSearchDBAccess;
import model.Customer;
// 結合番号２　ドライバクラス
public class JoinTest_OrderControl02 {
	public static void main(String[] args) {
		try {
			// 項番１，２
			CustomerSearchDBAccess dao1 = new CustomerSearchDBAccess();
			ArrayList<Customer> list1 = dao1.searchCustomerByTel("09012345678");
			for (Customer customer : list1) {
				System.out.println(customer.getCustId());
				System.out.println(customer.getCustName());
				System.out.println(customer.getKana());
				System.out.println(customer.getTel());
				System.out.println(customer.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番３
			CustomerSearchDBAccess dao2 = new CustomerSearchDBAccess();
			ArrayList<Customer> list2 = dao2.searchCustomerByKana("イトウハナエ");
			for (Customer customer : list2) {
				System.out.println(customer.getCustId());
				System.out.println(customer.getCustName());
				System.out.println(customer.getKana());
				System.out.println(customer.getTel());
				System.out.println(customer.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 項番４
			CustomerSearchDBAccess dao3 = new CustomerSearchDBAccess();
			ArrayList<Customer> list3 = dao3.searchCustomer("0314142135", "ワタナベ");
			for (Customer customer : list3) {
				System.out.println(customer.getCustId());
				System.out.println(customer.getCustName());
				System.out.println(customer.getKana());
				System.out.println(customer.getTel());
				System.out.println(customer.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
