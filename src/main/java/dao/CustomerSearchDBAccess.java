/**
 * クラス名：	CustomerSearchDBAccess
 * 概要　　：	顧客情報検索DAO
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerSearchDBAccess {

	private Connection createConnection() throws Exception {
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://10.64.144.5/24jy0200",
					"24jy0200", "24jy0200");
		} catch(SQLException e) {
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。");
		}
		return con;
	}

	// try-with-resource化したことで不要になった
	/*
	private void closeConnection(Connection con) throws Exception {
		try{
			if(con != null){
				con.close();
			}
		} catch(SQLException e) {
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。");
		}
	}
	*/

	public ArrayList<Customer> searchCustomerByTel(String tel) throws Exception {
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try ( 
			Connection con = createConnection();
			PreparedStatement pstmt = con.prepareStatement(
				"SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM CUSTOMER WHERE TEL = ?");
			)
		{
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				int custId = rs.getInt("CUSTID");
				String custName = rs.getString("CUSTNAME");
				String kana = rs.getString("KANA");
				String address = rs.getString("ADDRESS");
				Customer customer = new Customer(custId, custName, kana, tel, address);
				list.add(customer);
			}
		} catch (SQLException e) {
			throw new Exception("顧客情報検索処理に失敗しました！管理者に連絡してください。");
		} 
		return list;
	}

	public ArrayList<Customer> searchCustomerByKana(String kana) throws Exception {
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try (
			Connection con = createConnection();
			PreparedStatement pstmt = con.prepareStatement(
				"SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS FROM CUSTOMER WHERE KANA like ?");
			)
		{
			pstmt.setString(1, "%" + kana + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int custId = rs.getInt("CUSTID");
				String custName = rs.getString("CUSTNAME");
				String custKana = rs.getString("KANA");
				String tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				Customer customer = new Customer(custId, custName, custKana, tel, address);
				list.add(customer);
			}
		} catch (SQLException e) {
			throw new Exception("顧客情報検索処理に失敗しました！管理者に連絡してください。");
		} 
		return list;
	}

	public ArrayList<Customer> searchCustomer(String tel, String kana) throws Exception {
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try (
			Connection con = createConnection();
			PreparedStatement pstmt = con.prepareStatement(
				"SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM CUSTOMER WHERE TEL = ? AND KANA like ?");
			)
		{
			pstmt.setString(1, tel);
			pstmt.setString(2, "%" + kana + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int custId = rs.getInt("CUSTID");
				String custName = rs.getString("CUSTNAME");
				String custKana = rs.getString("KANA");
				String address = rs.getString("ADDRESS");
				Customer customer
					= new Customer(custId, custName, custKana, tel, address);
				list.add(customer);
			}
		} catch (SQLException e) {
			throw new Exception("顧客情報検索処理に失敗しました！管理者に連絡してください。");
		} 
		return list;
	}
}
