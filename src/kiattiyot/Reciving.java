package kiattiyot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.PurchaseDAO;
import dao.PurchaseDetDAO;
import dao.ReceiveDAO;
import dao.StockDAO;
import model.PurchaseDetModel;
import model.PurchaseModel;
import model.StockModel;

public class Reciving extends JPanel {
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollPane;

	public Reciving() {

		JLabel title = new JLabel("Reciving Management");
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		title.setForeground(Color.BLACK);
		title.setBounds(10, 10, 500, 50);
		add(title);

		table();
		data();

		ImageIcon iconReceive = new ImageIcon("img/inbox.png");
		Image IconReceive = iconReceive.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnReceive = new JButton("RECEIVE", new ImageIcon(IconReceive));
		btnReceive.setBackground(Color.WHITE);
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String id = table.getValueAt(i, 1).toString();
					if (chked) {
						System.out.println(id);
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Receive PurchaseId :" + id);
						if (noti == 0) {
							PurchaseDetDAO pdDAO = new PurchaseDetDAO();
							PurchaseModel pModel = new PurchaseModel();
							ReceiveDAO receiveDAO = new ReceiveDAO();

							StockDAO stDAO = new StockDAO();
							StockModel stModel = new StockModel();
							pModel.setId(id);

								HashMap<String, Object> findbyId = pdDAO.FindByID(id);

								stModel.setGood_id(findbyId.get("good_id").toString());
								stModel.setQuantity(findbyId.get("quantity").toString());
								stModel.setReplenish(findbyId.get("quantity").toString());

								// check good in table stock
								HashMap<String, Object> statusCheckGoodInStock = stDAO.CheckGoodIdInStock(stModel);
								System.out.println(statusCheckGoodInStock);
								if (statusCheckGoodInStock.get("c").equals("1")) {
									
									//HashMap<String, Object> findpuchasebyId = pdDAO.FinPurchasedByID(id);
									
									double purchase_quantity = Double.parseDouble(findbyId.get("quantity").toString());
									double Stock_quantity = Integer
											.parseInt(statusCheckGoodInStock.get("quantity").toString());

									double purchase_Replenish = Double.parseDouble(findbyId.get("quantity").toString());
									double Stock_Replenish = Integer
											.parseInt(statusCheckGoodInStock.get("replenish").toString());

									double sum_quantity = purchase_quantity + Stock_quantity;
									double sum_replenish = purchase_Replenish + Stock_Replenish;
									int good_id = Integer.parseInt(statusCheckGoodInStock.get("good_id").toString());

									int updateStock = stDAO.UpdateSTOCK(good_id, sum_quantity, sum_replenish);
									System.out.println("updateStock :" + updateStock);

								} else {

									int insertStock = stDAO.InsertSTOCK(stModel);
									System.out.println("insertStock : " + insertStock);

								}
								
								int status = receiveDAO.UpdateStatus(pModel);
								if (status == 1) {
									System.out.println("Receive Success!");
								}else {
									System.out.println("Receive Error!");
								}
								remove(scrollPane);
								table();
								data();
							
						}
					}
				}
			}

		});
		btnReceive.setBounds(10, 270, 200, 50);
		btnReceive.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnReceive);

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
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Delete GoodID :" + id);
						if (noti == 0) {
							PurchaseDAO pDAO = new PurchaseDAO();
							PurchaseModel pModel = new PurchaseModel();

							pModel.setId(id);
							int statusDet = pDAO.DeleteDet(pModel);
							int status = pDAO.Delete(pModel);

							if (status == 1 && statusDet == 1) {
								System.out.println("Delete success!!");
								remove(scrollPane);
								table();
								data();

							} else {
								System.err.println("Delete Error!!");
							}

						}
					}
				}
			}

		});
		btnDelete.setBounds(210, 270, 200, 50);
		add(btnDelete);

		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.setBackground(Color.WHITE);
		btnRefesh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(scrollPane);
				table();
				data();
			}
		});
		btnRefesh.setBounds(410, 270, 200, 50);
		btnRefesh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnRefesh);
		
		setPreferredSize(new Dimension(700, 350));
		setVisible(true);
		setBackground(new Color(242, 171, 57));
		setLayout(null);
	}

	public void table() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 670, 200);
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
		model.addColumn("Supplier_id");
		model.addColumn("Purchase_date");
		model.addColumn("status");
		model.addColumn("Good_name");
		model.addColumn("Quantity");
		model.addColumn("Sup_name");
		model.addColumn("Total_amount");

	}

	public void data() {
		ReceiveDAO receiveDAO = new ReceiveDAO();
		PurchaseModel pModel = new PurchaseModel();
		PurchaseDetModel pdModel = new PurchaseDetModel();
		ArrayList<HashMap<String, Object>> suppliergoodList = receiveDAO.FindAll();

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
}
