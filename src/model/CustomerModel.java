package model;

public class CustomerModel {
	private String id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String address;
	private String postcode;
	private String bank_acct;
	private String balance;
	private String verified;
	
//	public CustomerModel(String id,String name, String surname, String username, String password, String address, String postcode,
//			String back_acc, String balance,String verified) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.surname = surname;
//		this.username = username;
//		this.password = password;
//		this.address = address;
//		this.postcode = postcode;
//		this.back_acc = back_acc;
//		this.balance = balance;
//		this.verified = verified;
//	}
//	
//	public CustomerModel() {
//		; 
//	}
//
//	public CustomerModel(String nameStr, String surnameStr, String usernameStr, String passStr, String addressStr,
//			String postcodeStr, String bankacctStr, String balanceStr) {
//		this.name = nameStr;
//		this.surname = surnameStr;
//		this.username = usernameStr;
//		this.password = passStr;
//		this.address = addressStr;
//		this.postcode = postcodeStr;
//		this.back_acc = bankacctStr;
//		this.balance = balanceStr;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getBank_acct() {
		return bank_acct;
	}
	public void setBank_acct(String bank_acct) {
		this.bank_acct = bank_acct;
	}

	
}
