package dao;

import java.util.ArrayList;
import java.util.HashMap;

import database.Database;
import model.CustomerModel;
import model.SupplierModel;

public class SupplierDAO  {

	Database db;

	public SupplierDAO() {
		db = new Database();
	}

	public int Add(SupplierModel s) {
		String sql ="INSERT INTO supplier(name,address)  VALUES ('" + s.getName()  + "', "
				+ "'" + s.getAddress() + "')";
		return db.add(sql);
	}

	public int Delete(SupplierModel s) {
		String sql_delete = "DELETE FROM supplier WHERE ID = " + s.getId();
		return db.remove(sql_delete);
	}

	public int Update(SupplierModel s) {
		String sql = "UPDATE supplier SET name = '" + s.getName() + "'," + "address = '" + s.getAddress() + "'"
				+ "WHERE id = '" + s.getId() + "'";
		return db.update(sql);
	}

	public ArrayList<HashMap<String, Object>> FindAll() {
		String sql ="SELECT * FROM supplier";
		return db.queryList(sql);
	}

	public HashMap<String, Object> FindByID(int id) {
		String sql = "SELECT * FROM customer WHERE id = " + id;
		return db.querySingle(sql);
	}


}
