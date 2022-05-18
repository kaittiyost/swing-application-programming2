package kiattiyot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import dao.SupplierGoodDAO;
import database.Database;
import model.GoodModel;
import model.SupplierGoodModel;

public class SupplierGoodAdmin extends JPanel {

	JComboBox subIdCombobox;
	JComboBox goodIdComboBox;
	JScrollPane scrollPane;
	DefaultTableModel model;
	JTable table;
	JPanel pane;

	public SupplierGoodAdmin() {

		table();
		data();

		pane = new JPanel();
		pane.setSize(200, 350);
		pane.setBounds(0, 0, 250, 350);
		pane.setBackground(new Color(231, 75, 75));
		pane.setLayout(null);
		add(pane);

		JLabel title = new JLabel("Supplier Good Management");
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		title.setForeground(Color.WHITE);
		title.setBounds(20, 10, 200, 50);
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

		goodIdComboBox = new JComboBox(s.ShowGood());
		goodIdComboBox.setSelectedIndex(0);
		goodIdComboBox.setBounds(100, 120, 110, 20);
		pane.add(goodIdComboBox);

		JTextField txtSupId = new JTextField();
		txtSupId.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtSupId.setForeground(Color.BLACK);
		txtSupId.setBounds(100, 70, 110, 20);
		// pane.add(txtSupId);

		JLabel goodID = new JLabel("GOOD ID ");
		goodID.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		goodID.setForeground(Color.WHITE);
		goodID.setBounds(20, 120, 100, 20);
		pane.add(goodID);

		ImageIcon icon = new ImageIcon("img/add.png");
		Image Icon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnAdd = new JButton("Add", new ImageIcon(Icon));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(10, 220, 100, 50);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String supId = subIdCombobox.getSelectedItem().toString();
				String goodId = goodIdComboBox.getSelectedItem().toString();

				SupplierGoodModel supgoodModel = new SupplierGoodModel();
				SupplierGoodDAO goodDAO = new SupplierGoodDAO();

				String sid = supId.split("-")[0];
				String gid = goodId.split("-")[0];

				supgoodModel.setGoodId(gid);
				supgoodModel.setSupplierId(sid);

				int noti = JOptionPane.showConfirmDialog(null, "Sure?");
				// 0=yes, 1=no, 2=cancel
				if (noti == 0) {
					int status = goodDAO.Add(supgoodModel);
					System.out.println("Add ID: " + status);

					if (status == 1) {
						int n = JOptionPane.showConfirmDialog(null, "insert Successful!", "notificaion",
								JOptionPane.OK_OPTION);

						if (n == JOptionPane.OK_OPTION) {
							remove(scrollPane);
							table();
							data();
						}
					}

				}
			}
		});
		pane.add(btnAdd);

		ImageIcon iconRec = new ImageIcon("img/recycle.png");
		Image IconRec = iconRec.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnUpdate = new JButton("UPDATE", new ImageIcon(IconRec));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(110, 220, 100, 50);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {

					String supId = subIdCombobox.getSelectedItem().toString();
					String goodId = goodIdComboBox.getSelectedItem().toString();

					boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String supgId = table.getValueAt(i, 1).toString();
					
					String subId_update = table.getValueAt(i, 2).toString();
					String goodId_update = table.getValueAt(i, 4).toString();

					SupplierGoodModel supplergoodModel = new SupplierGoodModel();
					SupplierGoodDAO supplergoodDAO = new SupplierGoodDAO();

					if (chked) {
//						int noti = JOptionPane.showConfirmDialog(null, "Sure? update SupplierGoodID :" + supgId);

//						String sid = supId.split("-")[0];
//						String gid = goodId.split("-")[0];

						supplergoodModel.setId(supgId);

						// Dialog update
						JTextField supplier_id = new JTextField();
						JTextField good_id = new JTextField();
						JPanel panel = new JPanel(new GridLayout(0, 1));
						
						String sid = subId_update.split("-")[0];
						String gid = goodId_update.split("-")[0];
						
						supplier_id.setText(sid);
						good_id.setText(gid);
						
						panel.add(new JLabel("Supplier ID:"));
						panel.add(supplier_id);
						panel.add(new JLabel("Good ID:"));
						panel.add(good_id);
						
						int result = JOptionPane.showConfirmDialog(null, panel, "update id "+supgId, JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE);

						if (result == JOptionPane.OK_OPTION) {
							
							String txt_supplier_id = supplier_id.getText();
							String txt_good_id = good_id.getText();
							
							supplergoodModel.setGoodId(txt_good_id);
							supplergoodModel.setSupplierId(txt_supplier_id);
							
							int status = supplergoodDAO.Update(supplergoodModel);

							System.out.println("Update : " + status);
							if (status == 1) {
								int n = JOptionPane.showConfirmDialog(null, "Update " + status + "  Successful!",
										"notificaion", JOptionPane.OK_OPTION);

								if (n == JOptionPane.OK_OPTION) {
									// table===
									remove(scrollPane);
									table();
									data();
								}
							}
							
				        } else {
				            System.out.println("Cancelled");
				        }

					}
				}
			}

		});
		pane.add(btnUpdate);

		ImageIcon iconDel = new ImageIcon("img/delete.png");
		Image IconDel = iconDel.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnDelete = new JButton("DELETE", new ImageIcon(IconDel));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {

					boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String supgId = (table.getValueAt(i, 1).toString());
					if (chked) {
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Delete SupplierGoodID :" + supgId);
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {
							SupplierGoodModel supplergoodModel = new SupplierGoodModel();
							SupplierGoodDAO supplergoodDAO = new SupplierGoodDAO();

							supplergoodModel.setId(supgId);

							int status = supplergoodDAO.Delete(supplergoodModel);

							System.out.println("Delete : " + status);
							if (status == 1) {
								int n = JOptionPane.showConfirmDialog(null, "Delete " + status + "  Successful!",
										"notificaion", JOptionPane.OK_OPTION);

								if (n == JOptionPane.OK_OPTION) {
									// table===
									remove(scrollPane);
									table();
									data();
								}
							}

						}
					}
				}
			}

		});
		btnDelete.setBounds(10, 270, 100, 50);
		pane.add(btnDelete);

		JButton btnRefesh = new JButton("REFESH");
		btnRefesh.setBackground(Color.WHITE);
		btnRefesh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefesh.setBounds(110, 270, 100, 50);
		pane.add(btnRefesh);
		btnRefesh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeItem();
				SupplierCombobox();
				GoodCombobox();

				// table===
				remove(scrollPane);
				table();
				data();
			}
		});
		
		setPreferredSize(new Dimension(700, 350));
		setVisible(true);
		setBackground(new Color(153, 187, 198));
		setLayout(null);


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

	public void table() {
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
		model.addColumn("ID");
		model.addColumn("SUPPLIER_ID");
		model.addColumn("SUPPLIER_NAME");
		model.addColumn("GOOD_ID");
		model.addColumn("GOOD_NAME");
	}

	public void data() {
		// Data Row
		SupplierGoodDAO suppliergoodDAO = new SupplierGoodDAO();
		ArrayList<HashMap<String, Object>> suppliergoodList = suppliergoodDAO.FindAll();

		for (int i = 0; i < suppliergoodList.size(); i++) {
			SupplierGoodModel sg = new SupplierGoodModel();
			sg.setGoodId(suppliergoodList.get(i).get("good_id").toString());
			sg.setSupplierId(suppliergoodList.get(i).get("supplier_id").toString());
			sg.setId(suppliergoodList.get(i).get("id").toString());
			sg.setGoodName(suppliergoodList.get(i).get("gname").toString());
			sg.setSupplierName(suppliergoodList.get(i).get("sname").toString());

			model.addRow(new Object[0]);
			model.setValueAt(false, i, 0);
			model.setValueAt(sg.getId(), i, 1);
			model.setValueAt(sg.getSupplierId(), i, 2);
			model.setValueAt(sg.getSupplierName(), i, 3);
			model.setValueAt(sg.getGoodId(), i, 4);
			model.setValueAt(sg.getGoodName(), i, 5);
		}

	}

	public void clickInTable() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

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
