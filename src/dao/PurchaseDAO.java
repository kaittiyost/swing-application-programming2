package dao;

import java.util.ArrayList;
import java.util.HashMap;

import database.Database;
import model.CustomerModel;
import model.PurchaseModel;

public class PurchaseDAO {
	Database db;

	public PurchaseDAO() {
		db = new Database();
	}

	public int Add(PurchaseModel p) {
		String sql = "INSERT INTO `purchase` (`supplier_id`,  `status`, `total_amount`) \n" +
				"VALUES ('"+p.getSupplier_id()+"','0', '"+p.getTotal_amount()+"');";
		
		return db.add(sql);
	}

	public int Delete(PurchaseModel p) {
		String sql = "DELETE FROM purchase WHERE id = "+p.getId();
		return db.remove(sql);
	}
	public int DeleteDet(PurchaseModel p) {
		String sql = "DELETE FROM purchase_det WHERE purchase_id = "+p.getId();
		return db.remove(sql);
	}

	public int Update() {
		String sql = null;
		// return db.update();
		return db.update(sql);
	}

	public ArrayList<HashMap<String, Object>> FindAll() {
		String sql = "SELECT p.* FROM(\n" +
				"SELECT purchase.id as id , purchase.purchase_date , purchase.supplier_id\n" +
				", supplier.`name` AS sname , purchase_det.good_id , good.`name` AS gname , purchase_det.quantity , purchase.total_amount\n" +
				",purchase.`status` , purchase.receive_date\n" +
				"FROM purchase , purchase_det , good , supplier\n" +
				"WHERE purchase_det.purchase_id = purchase.id\n" +
				"AND purchase.supplier_id = supplier.id\n" +
				"AND purchase_det.good_id = good.id  \n" +
				")  AS p ORDER BY p.id ";
		//System.out.println(sql);
		return db.queryList(sql);
	}

	public HashMap<String, Object> FindByID(String id) {
		String sql = "SELECT * FROM purchase WHERE id = " + id;
		return db.querySingle(sql);
	}

	public HashMap<String, Object> FindLastID() {
		String sql = "SELECT MAX(id) AS lastId\n" + 
				"FROM purchase ";
		return db.querySingle(sql);
	}
}
