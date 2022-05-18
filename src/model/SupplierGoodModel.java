package model;

public class SupplierGoodModel {
	private String id;
	private String supplierId;
	private String goodId;
	private String goodName;
	private String supplierName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

//	public SupplierGoodModel(int id, SupplierModel supplierModel, GoodModel goodModel) {
//		// TODO Auto-generated constructor stub
//		this.id = id;
//		this.supplier = supplierModel;
//		this.good = goodModel;
//	}

	
}
