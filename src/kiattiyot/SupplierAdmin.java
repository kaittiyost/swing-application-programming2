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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.SupplierDAO;
import model.CustomerModel;
import model.SupplierModel;

public class SupplierAdmin extends JPanel {

	DefaultTableModel model;
	JScrollPane scrollPane;
	JTextField txtaddress;
	JTextField txtname;
	JTable table;
	SupplierDAO supplierDAO;

	SupplierModel supplierModel;
	boolean chked;

	public SupplierAdmin() {
		
		JPanel pane = new JPanel();
		pane.setSize(200, 350);
		pane.setBounds(0, 0, 250, 350);
		pane.setBackground(new Color(231, 75, 75));
		pane.setLayout(null);
		add(pane);

		JLabel title = new JLabel("Supplier Management");
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		title.setForeground(Color.WHITE);
		title.setBounds(20, 10, 200, 50);
		pane.add(title);

		JLabel name = new JLabel("NAME ");
		name.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		name.setForeground(Color.WHITE);
		name.setBounds(20, 70, 100, 20);
		pane.add(name);

		txtname = new JTextField();
		txtname.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtname.setForeground(Color.BLACK);
		txtname.setBounds(100, 70, 110, 20);
		pane.add(txtname);

		JLabel addr = new JLabel("ADDRESS");
		addr.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		addr.setForeground(Color.WHITE);
		addr.setBounds(20, 120, 100, 20);
		pane.add(addr);

		txtaddress = new JTextField();
		txtaddress.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtaddress.setForeground(Color.BLACK);
		txtaddress.setBounds(100, 120, 110, 20);
		pane.add(txtaddress);

		table();
		Data();
		clickInTable();

		ImageIcon icon = new ImageIcon("img/add.png");
		Image Icon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnAdd = new JButton("Add",new ImageIcon(Icon));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(10, 220, 100, 50);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtname.getText();
				String addr = txtaddress.getText();

				SupplierModel supplierModel = new SupplierModel();
				SupplierDAO supplierDAO = new SupplierDAO();

				supplierModel.setName(name);
				supplierModel.setAddress(addr);

				int status = supplierDAO.Add(supplierModel);
				System.out.println("Add ID: " + status);

				int noti = JOptionPane.showConfirmDialog(null, "Sure?");
				// 0=yes, 1=no, 2=cancel
				if (noti == 0) {
					if (status == 1) {
						int n = JOptionPane.showConfirmDialog(null, "Insert Successful!", "notificaion",
								JOptionPane.OK_OPTION);

						if (n == JOptionPane.OK_OPTION) {

							remove(scrollPane);
							table();
							Data();
							clickInTable();

							txtname.setText("");
							txtaddress.setText("");
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

				String name = txtname.getText();
				String address = txtaddress.getText();

				clickInTable();
				for (int i = 0; i < table.getRowCount(); i++) {

					chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String supid = table.getValueAt(i, 1).toString();
					if (chked) {
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Update Supplier :" + supid);
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {

							supplierModel = new SupplierModel();
							supplierDAO = new SupplierDAO();

							supplierModel.setId(supid);
							supplierModel.setName(name);
							supplierModel.setAddress(address);

							int status = supplierDAO.Update(supplierModel);
							System.out.println("Updated status: " + status);

							if (status == 1) {
								int n = JOptionPane.showConfirmDialog(null, "Update Successful!", "notificaion",
										JOptionPane.OK_OPTION);

								if (n == JOptionPane.OK_OPTION) {

									remove(scrollPane);
									table();
									Data();
									clickInTable();

									txtname.setText("");
									txtaddress.setText("");
								}
							}

						}
					}
				}
			}
		});
		pane.add(btnUpdate);

		ImageIcon iconDel = new ImageIcon("img/delete.png");
		Image IconDel = iconDel.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnDelete = new JButton("DELETE",new ImageIcon(IconDel));
		//btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {

					chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String supid = table.getValueAt(i, 1).toString();
					if (chked) {
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Delete SupplierID :" + supid);
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {

							supplierModel = new SupplierModel();
							supplierDAO = new SupplierDAO();

							supplierModel.setId(supid);

							int status = supplierDAO.Delete(supplierModel);
							System.out.println("Delete ID status : " + status);
							if (status == 1) {
								int n = JOptionPane.showConfirmDialog(null, "Delete Successful!", "notificaion",
										JOptionPane.OK_OPTION);

								if (n == JOptionPane.OK_OPTION) {

									remove(scrollPane);
									table();
									Data();
									clickInTable();

									txtname.setText("");
									txtaddress.setText("");
								}
							} else {
								
								System.out.println("Delete Error!");
								JOptionPane.showMessageDialog(null, "Cannet Delete!");
							}

						}
					}
				}
			}

		});
		btnDelete.setBounds(10, 270, 100, 50);
		pane.add(btnDelete);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setBackground(Color.WHITE);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setBounds(110, 270, 100, 50);
		pane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtaddress.setText("");
			}
		});
		
		setPreferredSize(new Dimension(700, 350));
		setBackground(new Color(153, 187, 198));
		setLayout(null);
		setVisible(true);

	}

	// -------------------------------------------
	public void clearDataInJtable() {

		remove(scrollPane);
		SupplierAdmin s = new SupplierAdmin();
		setVisible(false);
		s.setVisible(true);

		DefaultTableModel dm = (DefaultTableModel) model;
		int rowCount = dm.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
	}

	public void Data() {
		// Data Row
		supplierDAO = new SupplierDAO();
		ArrayList<HashMap<String, Object>> supplierList = supplierDAO.FindAll();

		for (int i = 0; i < supplierList.size(); i++) {

			supplierModel = new SupplierModel();

			supplierModel.setId(supplierList.get(i).get("id").toString());
			supplierModel.setName(supplierList.get(i).get("name").toString());
			supplierModel.setAddress(supplierList.get(i).get("address").toString());

			model.addRow(new Object[0]);
			model.setValueAt(false, i, 0);
			model.setValueAt(supplierModel.getId(), i, 1);
			model.setValueAt(supplierModel.getName(), i, 2);
			model.setValueAt(supplierModel.getAddress(), i, 3);
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
		model.addColumn("SupplierID");
		model.addColumn("SupplierName");
		model.addColumn("SupplierAddress");
	}

	public void clickInTable() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());

					String name = table.getValueAt(i, 2).toString();
					String addr = table.getValueAt(i, 3).toString();

					if (chked) {
						txtname.setText(name);
						txtaddress.setText(addr);

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
