package dao;

import java.util.ArrayList;
import java.util.HashMap;

import database.Database;
import model.PurchaseModel;
import model.StockModel;
import model.SupplierGoodModel;

public class ReceiveDAO {
	Database db;

	public ReceiveDAO() {
		db = new Database();
	}
	public ArrayList<HashMap<String, Object>> FindAll() {
		String sql = "SELECT p.* FROM(\n" +
				"SELECT purchase.id , purchase.purchase_date , purchase.supplier_id\n" +
				", supplier.`name` AS sname , purchase_det.good_id , good.`name` AS gname , purchase_det.quantity , purchase.total_amount\n" +
				",purchase.`status` , purchase.receive_date\n" +
				"FROM purchase , purchase_det , good , supplier\n" +
				"WHERE purchase_det.purchase_id = purchase.id\n" +
				"AND purchase.supplier_id = supplier.id\n" +
				"AND purchase_det.good_id = good.id\n" +
				"AND purchase.`status` = 0\n" +
				") AS p";
		//System.err.println(sql);
		return db.queryList(sql);
	}
	
	public int UpdateStatus(PurchaseModel s) {
		String sql = "UPDATE purchase SET status = 1 ,  receive_date = CURRENT_TIMESTAMP WHERE id="+s.getId();
		//System.out.println(sql);
		return db.update(sql);
	}
	


}
