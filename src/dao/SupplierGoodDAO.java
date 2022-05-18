package dao;

import java.util.ArrayList;
import java.util.HashMap;

import database.Database;
import model.CustomerModel;
import model.SupplierGoodModel;

public class SupplierGoodDAO {
	Database db;

	public SupplierGoodDAO() {
		db = new Database();
	}

	public int Add(SupplierGoodModel s) {
		String sql ="INSERT INTO supplier_good(supplier_id , good_id) VALUES (" + s.getSupplierId() + ", " + s.getGoodId() + ")";
		//System.out.println(sql);
		return db.add(sql);
	}

	public int Delete(SupplierGoodModel s) {
		String sql_delete = "DELETE FROM supplier_good WHERE ID = " + s.getId();
		return db.remove(sql_delete);
	}

	public int Update(SupplierGoodModel s) {
		String sql = "UPDATE supplier_good SET supplier_id = '"+s.getSupplierId()+"' , good_id = '"+s.getGoodId()+"' WHERE id ="+s.getId();
		System.out.println(sql);
		return db.update(sql);
	}

	public ArrayList<HashMap<String, Object>> FindAll() {
		String sql = "SELECT a.* FROM (\n" +
				"SELECT supplier_good.id , supplier_good.supplier_id , supplier_good.good_id,good.name as gname   , supplier.name   as sname\n" +
				"FROM supplier_good,supplier,good \n" +
				"WHERE supplier_good.good_id = good.id\n" +
				"AND supplier_good.supplier_id = supplier.ID\n" +
				") as a";
		//System.err.println(sql);
		return db.queryList(sql);
	}

	public HashMap<String, Object> FindByID(int id) {
		String sql = "SELECT * FROM customer WHERE id = " + id;
		return db.querySingle(sql);
	}

	public ArrayList<HashMap<String, Object>> DataSupplierJoinGood() {

		String sql = "SELECT * FROM supplier_good,supplier,good WHERE\n" + "supplier_good.good_id = good.id \n"
				+ "AND supplier_good.supplier_id = supplier.ID";
		ArrayList<HashMap<String, Object>> data = db.queryList(sql);
		//System.out.println(data);
		return data;
	}

	public String[] ShowSupplier() {
		String[] Allsubject = new String[50];
		try {
			Database db = new Database();
			String sql = "SELECT * FROM supplier";
		
			ArrayList<HashMap<String, Object>> querylist = db.queryList(sql);
			
			int i = 0;
			for (i = 0; i <= querylist.size(); i++) {
				Allsubject[i] = (String) querylist.get(i).get("id") + "-" + (String) querylist.get(i).get("name");
				
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			e.getMessage();
		}
		return Allsubject;
	}

	public String[] ShowGood() {
		String[] Allgood = new String[50];
		try {
			Database db = new Database();
			String sql = "SELECT * FROM good";
			ArrayList<HashMap<String, Object>> querylist = db.queryList(sql);
			int i = 0;
			for (i = 0; i <= querylist.size(); i++) {
				Allgood[i] = (String) querylist.get(i).get("id") + "-" + (String) querylist.get(i).get("name");
				
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			e.getMessage();
		}
		return Allgood;
	}

}
