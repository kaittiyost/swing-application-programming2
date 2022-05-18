package kiattiyot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import dao.GoodDAO;
import model.CustomerModel;
import model.GoodModel;

public class CustomerAdmin extends JPanel {

	JTable table;
	DefaultTableModel model;
	JScrollPane scrollPane;

	JTextField txtcusName;
	JTextField txtcusSurname;
	JTextField txtcusUsername;
	JTextField txtcusPassword;
	JTextField txtcusAddress;
	JTextField txtcusPostcode;
	JTextField txtcusBankAcct;
	JTextField txtcusBalance;

	String nameStr;
	String surnameStr;
	String usernameStr;
	String passStr;
	String addressStr;
	String postcodeStr;
	String bankacctStr;
	String balanceStr;

	public CustomerAdmin() {

		JPanel pane = new JPanel();
		pane.setSize(200, 350);
		pane.setBounds(0, 0, 700, 200);
		pane.setBackground(new Color(99, 42, 126));
		pane.setLayout(null);
		add(pane);

		JLabel title = new JLabel("Customers Management");
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		title.setForeground(Color.WHITE);
		title.setBounds(100, 10, 500, 50);
		pane.add(title);

		JLabel cusName = new JLabel("NAME ");
		cusName.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		cusName.setForeground(Color.WHITE);
		cusName.setBounds(20, 70, 100, 20);
		pane.add(cusName);

		txtcusName = new JTextField();
		txtcusName.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusName.setForeground(Color.BLACK);
		txtcusName.setBounds(100, 70, 110, 20);
		pane.add(txtcusName);

		JLabel cusSurame = new JLabel("SURNAME ");
		cusSurame.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		cusSurame.setForeground(Color.WHITE);
		cusSurame.setBounds(20, 100, 100, 20);
		pane.add(cusSurame);

		txtcusSurname = new JTextField();
		txtcusSurname.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusSurname.setForeground(Color.BLACK);
		txtcusSurname.setBounds(100, 100, 110, 20);
		pane.add(txtcusSurname);

		JLabel cusUsername = new JLabel("USER ");
		cusUsername.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		cusUsername.setForeground(Color.WHITE);
		cusUsername.setBounds(20, 130, 100, 20);
		pane.add(cusUsername);

		txtcusUsername = new JTextField();
		txtcusUsername.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusUsername.setForeground(Color.BLACK);
		txtcusUsername.setBounds(100, 130, 110, 20);
		pane.add(txtcusUsername);

		JLabel cusPassword = new JLabel("PASS ");
		cusPassword.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		cusPassword.setForeground(Color.WHITE);
		cusPassword.setBounds(20, 160, 100, 20);
		pane.add(cusPassword);

		txtcusPassword = new JTextField();
		txtcusPassword.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusPassword.setForeground(Color.BLACK);
		txtcusPassword.setBounds(100, 160, 110, 20);
		pane.add(txtcusPassword);

		// col2
		JLabel cusAddress = new JLabel("ADDR ");
		cusAddress.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		cusAddress.setForeground(Color.WHITE);
		cusAddress.setBounds(220, 70, 100, 20);
		pane.add(cusAddress);

		txtcusAddress = new JTextField();
		txtcusAddress.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusAddress.setForeground(Color.BLACK);
		txtcusAddress.setBounds(310, 70, 110, 20);
		pane.add(txtcusAddress);

		JLabel cusPostcode = new JLabel("POSTCODE ");
		cusPostcode.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		cusPostcode.setForeground(Color.WHITE);
		cusPostcode.setBounds(220, 100, 100, 20);
		pane.add(cusPostcode);

		txtcusPostcode = new JTextField();
		txtcusPostcode.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusPostcode.setForeground(Color.BLACK);
		txtcusPostcode.setBounds(310, 100, 110, 20);
		pane.add(txtcusPostcode);

		JLabel cusBankAcct = new JLabel("BANKACCT ");
		cusBankAcct.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		cusBankAcct.setForeground(Color.WHITE);
		cusBankAcct.setBounds(220, 130, 100, 20);
		pane.add(cusBankAcct);

		txtcusBankAcct = new JTextField();
		txtcusBankAcct.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusBankAcct.setForeground(Color.BLACK);
		txtcusBankAcct.setBounds(310, 130, 110, 20);
		pane.add(txtcusBankAcct);

		JLabel cusBalance = new JLabel("BALANCE ");
		cusBalance.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		cusBalance.setForeground(Color.WHITE);
		cusBalance.setBounds(220, 160, 100, 20);
		pane.add(cusBalance);

		txtcusBalance = new JTextField();
		txtcusBalance.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtcusBalance.setForeground(Color.BLACK);
		txtcusBalance.setBounds(310, 160, 110, 20);
		pane.add(txtcusBalance);

		String[] verified = { "verified", "0", "1" };
		final JComboBox Selectverified = new JComboBox(verified);
		Selectverified.setSelectedIndex(0);
		Selectverified.setBounds(450, 70, 110, 20);
		pane.add(Selectverified);

		ImageIcon icon = new ImageIcon("img/add.png");
		Image Icon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnAdd = new JButton("Add",new ImageIcon(Icon));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(450, 100, 100, 50);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					nameStr = txtcusName.getText();
					surnameStr = txtcusSurname.getText();
					usernameStr = txtcusUsername.getText();
					passStr = txtcusPassword.getText();
					addressStr = txtcusAddress.getText();
					postcodeStr = txtcusPostcode.getText();
					bankacctStr = txtcusBankAcct.getText();
					balanceStr = txtcusBalance.getText();

					if (nameStr.equals("") && surnameStr.equals("") && usernameStr.equals("") && passStr.equals("")) {
						System.out.println("กรุณากรอกข้อมูล");

					} else {
						int noti = JOptionPane.showConfirmDialog(null, "Sure?");
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {
							CustomerDAO cusDAO = new CustomerDAO();
							CustomerModel cusModel = new CustomerModel();

							cusModel.setName(nameStr);
							cusModel.setSurname(surnameStr);
							cusModel.setAddress(addressStr);
							cusModel.setBank_acct(bankacctStr);
							cusModel.setBalance(balanceStr);
							cusModel.setPostcode(postcodeStr);
							cusModel.setUsername(usernameStr);
							cusModel.setPassword(passStr);

							int cus = cusDAO.Add(cusModel);

							System.out.println(cus);

							if (cus == 1) {
								int n = JOptionPane.showConfirmDialog(null, "insert " + nameStr + "  Successful!",
										"notificaion", JOptionPane.OK_OPTION);

								if (n == JOptionPane.OK_OPTION) {
									System.out.println("refesh");
									remove(scrollPane);
									table();
									data();
									clickInTable();

									txtcusName.setText("");
									txtcusSurname.setText("");
									txtcusUsername.setText("");
									txtcusPassword.setText("");
									txtcusAddress.setText("");
									txtcusPostcode.setText("");
									txtcusBankAcct.setText("");
									txtcusBalance.setText("");
								}
							}

						} else {
							System.err.println("Something Wrong !");
						}
					}

				} catch (Exception e1) {
					e1.getMessage();
				}

			}
		});
		pane.add(btnAdd);

		ImageIcon iconRec = new ImageIcon("img/recycle.png");
		Image IconRec = iconRec.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnUpdate = new JButton("UPDATE", new ImageIcon(IconRec));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(550, 100, 100, 50);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				nameStr = txtcusName.getText();
				surnameStr = txtcusSurname.getText();
				usernameStr = txtcusUsername.getText();
				passStr = txtcusPassword.getText();
				addressStr = txtcusAddress.getText();
				postcodeStr = txtcusPostcode.getText();
				bankacctStr = txtcusBankAcct.getText();
				balanceStr = txtcusBalance.getText();
				String verified = Selectverified.getSelectedItem().toString(); 
				//clickInTable();
				for (int i = 0; i < table.getRowCount(); i++) {

					boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String id = table.getValueAt(i, 1).toString();
					if (chked) {
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Update GoodID :" + id);
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {
							CustomerDAO cusDAO = new CustomerDAO();
							CustomerModel cModel = new CustomerModel();

							cModel.setId(id);
							cModel.setName(nameStr);
							cModel.setSurname(surnameStr);
							cModel.setUsername(usernameStr);
							cModel.setPassword(passStr);
							cModel.setAddress(addressStr);
							cModel.setPostcode(postcodeStr);
							cModel.setBank_acct(bankacctStr);
							cModel.setBalance(balanceStr);
							cModel.setVerified(verified);
							int status = cusDAO.Update(cModel);
							System.out.println("Updated ID: " + status);

							remove(scrollPane);
							table();
							data();
							clickInTable();

							txtcusName.setText("");
							txtcusSurname.setText("");
							txtcusUsername.setText("");
							txtcusPassword.setText("");
							txtcusAddress.setText("");
							txtcusPostcode.setText("");
							txtcusBankAcct.setText("");
							txtcusBalance.setText("");
						}
					}
				}
			}
		});
		pane.add(btnUpdate);

		table();
		data();
		clickInTable();

		ImageIcon iconDel = new ImageIcon("img/delete.png");
		Image IconDel = iconDel.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnDelete = new JButton("DELETE",new ImageIcon(IconDel));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String id = table.getValueAt(i, 1).toString();
					if (chked) {
						System.out.println(id);
						CustomerDAO cusDAO = new CustomerDAO();
						CustomerModel cModel = new CustomerModel();

						cModel.setId(id);

						int noti = JOptionPane.showConfirmDialog(null, "Sure? Delete CusId :" + id);
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {
							int status = cusDAO.Delete(cModel);
							System.out.println("Delete ID: " + status);

							txtcusName.setText("");
							txtcusSurname.setText("");
							txtcusUsername.setText("");
							txtcusPassword.setText("");
							txtcusAddress.setText("");
							txtcusPostcode.setText("");
							txtcusBankAcct.setText("");
							txtcusBalance.setText("");

							remove(scrollPane);
							table();
							data();
							clickInTable();
						}
					}
				}
			}

		});
		btnDelete.setBounds(450, 150, 100, 50);
		pane.add(btnDelete);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setBackground(Color.WHITE);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setBounds(550, 150, 100, 50);
		pane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtcusName.setText("");
				txtcusSurname.setText("");
				txtcusUsername.setText("");
				txtcusPassword.setText("");
				txtcusAddress.setText("");
				txtcusPostcode.setText("");
				txtcusBankAcct.setText("");
				txtcusBalance.setText("");
			}
		});
		
		setPreferredSize(new Dimension(700, 350));
		setVisible(true);
		setBackground(new Color(99, 42, 126));
		setLayout(null);
	}

	public void table() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 670, 100);
		add(scrollPane);
		
		table = new JTable();
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel() {

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				default:
					return String.class;
				}
			}
		};
		table.setModel(model);

		model.addColumn("Select");
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("SURNAME");
		model.addColumn("USERNAME");
		model.addColumn("PASSWORD");
		model.addColumn("ADDRESS");
		model.addColumn("POSTCODE");
		model.addColumn("BANK_ACCT");
		model.addColumn("BALANCE");
		model.addColumn("VERIFIED");
	}

	public void data() {
		// Data Row
		CustomerDAO customerDAO = new CustomerDAO();
		ArrayList<HashMap<String, Object>> customerList = customerDAO.FindAll();
		for (int i = 0; i < customerList.size(); i++) {
			try {
				CustomerModel customerModel = new CustomerModel();
				customerModel.setId(customerList.get(i).get("id").toString());
				customerModel.setName(customerList.get(i).get("name").toString());
				customerModel.setSurname(customerList.get(i).get("surname").toString());
				customerModel.setUsername(customerList.get(i).get("username").toString());
				customerModel.setPassword(customerList.get(i).get("password").toString());
				customerModel.setAddress(customerList.get(i).get("address").toString());
				customerModel.setPostcode(customerList.get(i).get("postcode").toString());
				customerModel.setBank_acct(customerList.get(i).get("bank_acct").toString());
				customerModel.setBalance(customerList.get(i).get("balance").toString());
				customerModel.setVerified(customerList.get(i).get("verified").toString());

				model.addRow(new Object[0]);
				model.setValueAt(false, i, 0);
				model.setValueAt(customerModel.getId(), i, 1);
				model.setValueAt(customerModel.getName(), i, 2);
				model.setValueAt(customerModel.getSurname(), i, 3);
				model.setValueAt(customerModel.getUsername(), i, 4);
				model.setValueAt(customerModel.getPassword(), i, 5);
				model.setValueAt(customerModel.getAddress(), i, 6);
				model.setValueAt(customerModel.getPostcode(), i, 7);
				model.setValueAt(customerModel.getBank_acct(), i, 8);
				model.setValueAt(customerModel.getBalance(), i, 9);
				model.setValueAt(customerModel.getVerified(), i, 10);
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	public void clickInTable() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String cusId = table.getValueAt(i, 1).toString();
					String cusName = table.getValueAt(i, 2).toString();
					String cusSurname = table.getValueAt(i, 3).toString();
					String cusUsername = table.getValueAt(i, 4).toString();
					String cusPassword = table.getValueAt(i, 5).toString();
					String cusAddress = table.getValueAt(i, 6).toString();
					String cusPostcode = table.getValueAt(i, 7).toString();
					String cusBankAcct = table.getValueAt(i, 8).toString();
					String cusBalance = table.getValueAt(i, 9).toString();

					if (chked) {

						txtcusName.setText(cusName);
						txtcusSurname.setText(cusSurname);
						txtcusUsername.setText(cusUsername);
						txtcusPassword.setText(cusPassword);
						txtcusAddress.setText(cusAddress);
						txtcusPostcode.setText(cusPostcode);
						txtcusBankAcct.setText(cusBankAcct);
						txtcusBalance.setText(cusBalance);

					}
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

	}
}
