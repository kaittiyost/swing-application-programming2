package dao;

import java.util.ArrayList;
import java.util.HashMap;

import database.Database;
import model.CustomerModel;

public class CustomerDAO {
	Database db;

	public CustomerDAO() {
		db = new Database();
	}

	public int Add(CustomerModel cus) {
		String sql = "INSERT INTO customer(name,surname,username,password,address,postcode,bank_acct,balance,verified)"
				+ "VALUES ('" + cus.getName() + "','" + cus.getSurname() + "','" + cus.getUsername() + "','"
				+ cus.getPassword() + "','" + cus.getAddress() + "','" + cus.getPostcode() + "','" + cus.getBank_acct()
				+ "','" + cus.getBalance() + "',0)";
	//	System.out.println(sql);
		return db.add(sql);
	}

	public int Delete(CustomerModel c) {
		String sql_delete = "DELETE FROM customer WHERE id = " + c.getId();
		return db.remove(sql_delete);
	}

	public int Update(CustomerModel bean) {
		String sql = "UPDATE customer SET name='" + bean.getName() + "', surname='" + bean.getSurname()
		+ "', username ='" + bean.getUsername() + "', password = '" + bean.getPassword() + "', address = "
		+ bean.getAddress() + ", postcode =" + bean.getPostcode() + ", bank_acct = " + bean.getBank_acct()
		+ ", balance =" + bean.getBalance() + ", verified = '"+bean.getVerified()+"' WHERE id = "
		+ bean.getId();
		return db.update(sql);
	}

	public ArrayList<HashMap<String, Object>> FindAll() {
		String sql = "SELECT * FROM customer";
		return db.queryList(sql);
	}

	public HashMap<String, Object> FindByID(int id) {
		String sql = "SELECT * FROM customer WHERE id = " + id;
		return db.querySingle(sql);
	}

}
