package kiattiyot;

import java.awt.Color;
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

import dao.GoodDAO;
import dao.PurchaseDAO;
import dao.PurchaseDetDAO;
import dao.SupplierGoodDAO;
import database.Database;
import model.PurchaseDetModel;
import model.PurchaseModel;

public class Purchasing extends JPanel {

	JPanel pane;
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollPane;

	JComboBox subIdCombobox;
	JComboBox goodIdComboBox;
	
	public Purchasing() {
		setPreferredSize(new Dimension(700, 350));
		setVisible(true);
		setBackground(new Color(153, 187, 198));
		setLayout(null);

		pane = new JPanel();
		pane.setLayout(null);
		pane.setSize(200, 350);
		pane.setBackground(new Color(231, 75, 75));
		pane.setBounds(0, 0, 250, 350);
		add(pane);

		JLabel title = new JLabel("Purchasing Management");
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		title.setForeground(Color.WHITE);
		title.setBounds(10, 10, 500, 50);
		pane.add(title);

		JLabel name = new JLabel("SUPPLIER");
		name.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		name.setForeground(Color.WHITE);
		name.setBounds(20, 70, 100, 20);
		pane.add(name);

		SupplierGoodDAO s = new SupplierGoodDAO();

		subIdCombobox = new JComboBox(s.ShowSupplier());
		subIdCombobox.setSelectedIndex(0);
		subIdCombobox.setBounds(100, 70, 110, 20);
		pane.add(subIdCombobox);

		JLabel goodID = new JLabel("GOOD ID ");
		goodID.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		goodID.setForeground(Color.WHITE);
		goodID.setBounds(20, 110, 100, 20);
		pane.add(goodID);

		goodIdComboBox = new JComboBox(s.ShowGood());
		goodIdComboBox.setSelectedIndex(0);
		goodIdComboBox.setBounds(100, 110, 110, 20);
		pane.add(goodIdComboBox);

		JLabel quantity = new JLabel("QUANTITY ");
		quantity.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		quantity.setForeground(Color.WHITE);
		quantity.setBounds(20, 150, 100, 20);
		pane.add(quantity);

		// Combobox
		String[] quan = { "Select", "50", "100", "150", "200", "250" };
		final JComboBox quancomboBox = new JComboBox(quan);
		quancomboBox.setSelectedIndex(0);
		quancomboBox.setBounds(100, 150, 110, 20);
		pane.add(quancomboBox);

		JLabel total = new JLabel("TOTAL ");
		total.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		total.setForeground(Color.WHITE);
		total.setBounds(20, 190, 110, 20);
		pane.add(total);

		final JLabel txttotal = new JLabel();
		txttotal.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txttotal.setForeground(Color.WHITE);
		txttotal.setBounds(85, 190, 110, 20);
		pane.add(txttotal);

		quancomboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(quancomboBox.getSelectedItem());
				double total = Double.parseDouble(quancomboBox.getSelectedItem().toString());

				String goodId = goodIdComboBox.getSelectedItem().toString();
				int gid = Integer.parseInt(goodId.split("-")[0].toString());

				GoodDAO goodDAO = new GoodDAO();
				HashMap<String, Object> goodModel = goodDAO.FindByID(gid);

				double goodPrice = Double.parseDouble(goodModel.get("unitprice").toString());

				double sumTotal = total * goodPrice;
				String sumTotalStr = Double.toString(sumTotal);
				txttotal.setText(sumTotalStr);
				txttotal.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
				txttotal.setForeground(Color.BLACK);

			}
		});
		;

		ImageIcon icon = new ImageIcon("img/add.png");
		Image Icon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnAdd = new JButton("Add", new ImageIcon(Icon));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(10, 220, 100, 50);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String supId = subIdCombobox.getSelectedItem().toString();
				String gId = goodIdComboBox.getSelectedItem().toString();
				String total = txttotal.getText().toString();
				String quantity = quancomboBox.getSelectedItem().toString();

				// split get id only
				String sid = supId.split("-")[0];
				String gid = gId.split("-")[0];

				System.out.println("Purchase : sID :" + sid + ",GId :" + gid + ",quantity :" + quantity);

				int noti = JOptionPane.showConfirmDialog(null, "Sure?");
				// 0=yes, 1=no, 2=cancel
				if (noti == 0) {
					PurchaseModel purchase = new PurchaseModel();
					PurchaseDetModel pd = new PurchaseDetModel();

					purchase.setSupplier_id(sid);
					purchase.setTotal_amount(total);

					pd.setGood_id(gid);
					pd.setQuantity(quantity);

					PurchaseDAO purchaseDAO = new PurchaseDAO();
					PurchaseDetDAO pDet = new PurchaseDetDAO();

					int puchaseId = purchaseDAO.Add(purchase);

					HashMap<String, Object> lastId = purchaseDAO.FindLastID();

					pd.setPurchase_id(lastId.get("lastId").toString());
					int pdetStatus = pDet.Add(pd);

					if (puchaseId == 1 && pdetStatus == 1) {
						JOptionPane.showMessageDialog(null, "Insert Success!");
						remove(scrollPane);
						table();
						data();
						clickInTable();
					} else {
						JOptionPane.showMessageDialog(null, "Insert Error!");
					}
				}
			}

		});
		pane.add(btnAdd);

		table();
		data();

		ImageIcon iconDel = new ImageIcon("img/delete.png");
		Image IconDel = iconDel.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnDelete = new JButton("DELETE", new ImageIcon(IconDel));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String id = table.getValueAt(i, 1).toString();
					if (chked) {

						System.out.println(id);
						int noti = JOptionPane.showConfirmDialog(null, "Delete Purchase id :" + id + " ?");
						if (noti == 0) {
							PurchaseModel purchase = new PurchaseModel();
							PurchaseDetModel pd = new PurchaseDetModel();
							PurchaseDAO purchaseDAO = new PurchaseDAO();
							PurchaseDetDAO pDet = new PurchaseDetDAO();

							purchase.setId(id);
							pd.setPurchase_id(id);
							int delpd = pDet.Delete(pd);
							int delp = purchaseDAO.Delete(purchase);
							System.out.println(delpd + "," + delp);
							if (delpd == 1 && delp == 1) {
								JOptionPane.showMessageDialog(null, "Delete Success!!");
								remove(scrollPane);
								table();
								data();
								clickInTable();
							}
						}
					}
				}
			}

		});
		btnDelete.setBounds(10, 270, 200, 50);
		pane.add(btnDelete);

		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.setBounds(520, 230, 150, 50);
		btnRefesh.setBackground(Color.WHITE);
		add(btnRefesh);
		btnRefesh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Refesh");
				
				removeItem();
				SupplierCombobox();
				GoodCombobox();
				
				remove(scrollPane);
				table();
				data();
				clickInTable();
			}
		});

	}
	
	public void removeItem() {

		subIdCombobox.removeAllItems();
		goodIdComboBox.removeAllItems();

	}

	
	public void SupplierCombobox() {

		SupplierGoodDAO s = new SupplierGoodDAO();
		String[] Allsubject = new String[100];
		try {
			Database db = new Database();
			String sql = "SELECT * FROM supplier";
			ArrayList<HashMap<String, Object>> querylist = db.queryList(sql);
			int i = 0;
			for (i = 0; i <= querylist.size(); i++) {
				Allsubject[i] = (String) querylist.get(i).get("id") + "-" + (String) querylist.get(i).get("name");
				// System.out.println(Allsubject[i]);
				subIdCombobox.addItem(Allsubject[i]);
			}
		} catch (Exception e) {

		}

	}

	public void GoodCombobox() {

		SupplierGoodDAO s = new SupplierGoodDAO();
		String[] Allgood = new String[100];
		try {
			Database db = new Database();
			String sql = "SELECT * FROM good";
			ArrayList<HashMap<String, Object>> querylist = db.queryList(sql);
			int i = 0;
			for (i = 0; i <= querylist.size(); i++) {
				Allgood[i] = (String) querylist.get(i).get("id") + "-" + (String) querylist.get(i).get("name");

				goodIdComboBox.addItem(Allgood[i]);
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			e.getMessage();
		}

	}

	public void Data() {

		PurchaseDAO pDAO = new PurchaseDAO();
		ArrayList<HashMap<String, Object>> pList = pDAO.FindAll();
		for (int i = 0; i < pList.size(); i++) {

		}
	}

	public void table() {
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(110, 220, 100, 50);
		pane.add(btnUpdate);

		// ScrollPane for Table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 20, 400, 200);
		add(scrollPane);

		// Table
		table = new JTable();
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		scrollPane.setViewportView(table);

		// Model for Table
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
		model.addColumn("Supplier_id");
		model.addColumn("Purchase_date");
		model.addColumn("status");
		model.addColumn("Good_name");
		model.addColumn("Quantity");
		model.addColumn("Sup_name");
		model.addColumn("Total_amount");
	}

	public void data() {
		// Data Row
		PurchaseDAO pDAO = new PurchaseDAO();
		PurchaseModel pModel = new PurchaseModel();
		PurchaseDetModel pdModel = new PurchaseDetModel();
		ArrayList<HashMap<String, Object>> suppliergoodList = pDAO.FindAll();

		for (int i = 0; i < suppliergoodList.size(); i++) {

			pModel.setId(suppliergoodList.get(i).get("id").toString());
			pModel.setPurchase_date(suppliergoodList.get(i).get("purchase_date").toString());
			pModel.setSupplier_id(suppliergoodList.get(i).get("supplier_id").toString());
			pModel.setSupplier_name(suppliergoodList.get(i).get("sname").toString());
			pdModel.setGood_id(suppliergoodList.get(i).get("good_id").toString());
			pModel.setGood_name(suppliergoodList.get(i).get("gname").toString());
			pdModel.setQuantity(suppliergoodList.get(i).get("quantity").toString());
			pModel.setTotal_amount(suppliergoodList.get(i).get("total_amount").toString());
			pModel.setStatus(suppliergoodList.get(i).get("status").toString());
			pModel.setReceive_date(suppliergoodList.get(i).get("receive_date").toString());

			model.addRow(new Object[0]);
			model.setValueAt(false, i, 0);
			model.setValueAt(pModel.getId(), i, 1);
			model.setValueAt(pModel.getPurchase_date(), i, 2);
			model.setValueAt(pModel.getStatus(), i, 3);
			model.setValueAt(pModel.getGood_name(), i, 4);
			model.setValueAt(pdModel.getQuantity(), i, 5);
			model.setValueAt(pModel.getSupplier_name(), i, 6);
			model.setValueAt(pModel.getTotal_amount(), i, 7);

		}

	}

	public void clickInTable() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());

					String id = table.getValueAt(i, 1).toString();
//					String addr = table.getValueAt(i, 3).toString();

					if (chked) {
						PurchaseModel pModel = new PurchaseModel();
						pModel.setId(id);
//						txtname.setText(name);
//						txtaddress.setText(addr);

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
