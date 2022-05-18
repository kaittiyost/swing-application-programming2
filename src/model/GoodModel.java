package model;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GoodModel {
	private String id;
	private String name;
	private String unitcost;
	private String unitprice;

//	public GoodModel(String id, String name, String unitcost, String unitprice) {
//		this.id = id;
//		this.name = name;
//		this.unitcost = unitcost;
//		this.unitprice = unitprice;
//
//	}
//
//	public GoodModel(String name, String unitcost, String unitprice) {
//		this.name = name;
//		this.unitcost = unitcost;
//		this.unitprice = unitprice;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(String unitcost) {
		this.unitcost = unitcost;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

}
