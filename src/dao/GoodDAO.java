package dao;

import java.util.ArrayList;
import java.util.HashMap;

import database.Database;
import model.CustomerModel;
import model.GoodModel;

public class GoodDAO {
	Database db;

	public GoodDAO() {
		db = new Database();
	}

	public int Add(GoodModel g) {
		String sql = "INSERT INTO good(name,unitcost,unitprice) VALUES ('" + g.getName() + "'," + g.getUnitcost() + ","
				+ g.getUnitprice() + ")";
		System.out.println(sql);
		return db.add(sql);
	}

	public int Delete(GoodModel g) {
		String sql_delete = "DELETE FROM good WHERE id = " + g.getId();
		return db.remove(sql_delete);
	}

	public int Update(GoodModel g) {
		String sql = "UPDATE good SET name = '" + g.getName() + "'," + "unitcost = '" + g.getUnitcost() + "',"
				+ "unitprice='" + g.getUnitprice() + "'" + "WHERE id = '" + g.getId() + "'";
		// System.out.println(sql);
		return db.update(sql);
	}

	public ArrayList<HashMap<String, Object>> FindAll() {
		String sql = "SELECT * FROM good";
		return db.queryList(sql);
	}

	public HashMap<String, Object> FindByID(int id) {
		String sql = "SELECT * FROM good WHERE id = " + id;
		return db.querySingle(sql);
	}

	public ArrayList<HashMap<String, Object>> StockAll() {
		String sql = "SELECT * FROM stock , good WHERE stock.good_id = good.id ";
		return db.queryList(sql);
	}

}
