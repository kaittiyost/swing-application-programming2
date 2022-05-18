package dao;

import java.util.HashMap;

import database.Database;
import model.StockModel;

public class StockDAO {
	Database db;

	public StockDAO() {
		db = new Database();
	}
	
	public int InsertSTOCK(StockModel s) {
		String sql = "INSERT INTO stock (good_id,quantity,replenish) VALUES ("+s.getGood_id()+","+s.getQuantity()+","+s.getReplenish()+") ";
	//	System.out.println(sql);
		return db.update(sql);
	}
	
	public HashMap<String, Object> CheckGoodIdInStock(StockModel s) {
		String sql = "SELECT COUNT(good_id) as c , good_id , quantity , replenish FROM stock WHERE good_id = "+s.getGood_id();
	//	System.out.println(sql);
		return db.querySingle(sql);
	}
	
	public int UpdateSTOCK( int good_id , double quantity , double replenish) {
		String sql = "UPDATE stock SET quantity = "+quantity +" , replenish = "+replenish +" WHERE good_id ="+good_id;
		System.out.println(sql);
		return db.update(sql);
	}
}
