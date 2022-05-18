package dao;

import java.util.ArrayList;
import java.util.HashMap;

import database.Database;
import model.CustomerModel;
import model.PurchaseDetModel;

public class PurchaseDetDAO {
	Database db;

	public PurchaseDetDAO() {
		db = new Database();
	}

	public int Add(PurchaseDetModel pd) {
		String sql ="INSERT INTO purchase_det(purchase_id,good_id,quantity) "
				+ "values("+pd.getPurchase_id()+","+pd.getGood_id()+""
						+ ","+pd.getQuantity()+")";
		//System.out.println(sql);
		return db.add(sql);
	}

	public int Delete(PurchaseDetModel pd) {
		String sql = "DELETE FROM purchase_det WHERE purchase_id = "+pd.getPurchase_id();
		//System.out.println(sql);
		return db.remove(sql);
	}

	public int Update() {
		String sql = null;
		// return db.update();
		return db.update(sql);
	}

	public ArrayList<CustomerModel> FindAll() {

		return null;
	}

	public HashMap<String, Object> FindByID(String id) {
		String sql = "SELECT * FROM purchase_det WHERE purchase_id = " + id;
		//System.err.println(sql);
		return db.querySingle(sql);
	}
	
	public HashMap<String, Object> FinPurchasedByID(String id) {
		String sql = "SELECT * FROM purchase WHERE id = " + id;
		System.err.println(sql);
		return db.querySingle(sql);
	}

}
