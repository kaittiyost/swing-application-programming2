package model;

public class StockModel {
	private String id;
	private String good_id;
	private String quantity;
	private String replenish;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getReplenish() {
		return replenish;
	}
	public void setReplenish(String replenish) {
		this.replenish = replenish;
	}
	
}
