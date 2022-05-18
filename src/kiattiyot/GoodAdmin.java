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

import dao.GoodDAO;
import model.GoodModel;

public class GoodAdmin extends JPanel {

	GoodModel goodModel;
	GoodDAO goodDAO;
	JScrollPane scrollPane;
	JTable table;
	DefaultTableModel model;
	JTextField txtname;
	JTextField txtunitC;
	JTextField txtunitP;

	Boolean chked = false;

	public GoodAdmin() {

		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setSize(200, 350);
		pane.setBackground(new Color(231, 75, 75));
		pane.setBounds(0, 0, 250, 350);
		add(pane);

		JLabel title = new JLabel("Good Management");
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

		JLabel unitcost = new JLabel("UNITCOST ");
		unitcost.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		unitcost.setForeground(Color.WHITE);
		unitcost.setBounds(20, 110, 100, 20);
		pane.add(unitcost);

		txtunitC = new JTextField();
		txtunitC.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtunitC.setForeground(Color.BLACK);
		txtunitC.setBounds(100, 110, 110, 20);
		pane.add(txtunitC);

		JLabel unitprice = new JLabel("UNITPRICE ");
		unitprice.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
		unitprice.setForeground(Color.WHITE);
		unitprice.setBounds(20, 150, 100, 20);
		pane.add(unitprice);

		txtunitP = new JTextField();
		txtunitP.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		txtunitP.setForeground(Color.BLACK);
		txtunitP.setBounds(100, 150, 110, 20);
		pane.add(txtunitP);

		ImageIcon icon = new ImageIcon("img/add.png");
		Image Icon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnAdd = new JButton("Add",new ImageIcon(Icon));
	
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBounds(10, 220, 100, 50);
		pane.add(btnAdd);

		// JTable
		Table();
		Data();
		clickInTable();

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name;
				String unitcost;
				String unitprice;
				try {
					name = txtname.getText();
					unitcost = txtunitC.getText();
					unitprice = txtunitP.getText();

					if (name.equals("") && unitprice.equals("") && unitcost.equals("")) {
						System.out.println("กรุณากรอกข้อมูล");

					} else {
						int noti = JOptionPane.showConfirmDialog(null, "Sure?");
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {
							GoodDAO goodDAO = new GoodDAO();
							goodModel = new GoodModel();
							goodModel.setName(name);
							goodModel.setUnitcost(unitcost);
							goodModel.setUnitprice(unitprice);
							
							int goodDetail = goodDAO.Add(goodModel);

							System.out.println(goodDetail);

							txtname.setText("");
							txtunitC.setText("");
							txtunitP.setText("");

							if (goodDetail == 1) {
								int n = JOptionPane.showConfirmDialog(null, "insert " + name + "  Successful!",
										"notificaion", JOptionPane.OK_OPTION);

								if (n == JOptionPane.OK_OPTION) {
									//System.out.println("refesh");
									clearDataInJtable();
									Data();

									txtname.setText("");
									txtunitC.setText("");
									txtunitP.setText("");
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
		
		ImageIcon iconRec = new ImageIcon("img/recycle.png");
		Image IconRec = iconRec.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnUpdate = new JButton("UPDATE", new ImageIcon(IconRec));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBounds(110, 220, 100, 50);
		pane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = goodModel.getId();
				String name = txtname.getText();
				String unitcost = txtunitC.getText();
				String unitprice = txtunitP.getText();
				clickInTable();
				for (int i = 0; i < table.getRowCount(); i++) {

					chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String goodId = table.getValueAt(i, 1).toString();
					if (chked) {
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Update GoodID :" + goodId);
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {
							
							goodModel = new GoodModel();
							goodDAO = new GoodDAO();
							
							goodModel.setId(goodId);
							goodModel.setName(name);
							goodModel.setUnitcost(unitcost);
							goodModel.setUnitprice(unitprice);
							
							int status = goodDAO.Update(goodModel);
							System.out.println("Updated ID: " + status);

							clearDataInJtable();
							Data();

							txtname.setText("");
							txtunitC.setText("");
							txtunitP.setText("");
						}
					}
				}
			}
		});

		
		ImageIcon iconDel = new ImageIcon("img/delete.png");
		Image IconDel = iconDel.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnDelete = new JButton("DELETE",new ImageIcon(IconDel));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < table.getRowCount(); i++) {

					chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String goodId = table.getValueAt(i, 1).toString();
					if (chked) {
						int noti = JOptionPane.showConfirmDialog(null, "Sure? Delete GoodID :" + goodId);
						// 0=yes, 1=no, 2=cancel
						if (noti == 0) {
							GoodModel goodModel = new GoodModel();
							GoodDAO goodDAO = new GoodDAO();
							
							goodModel.setId(goodId);
							
							int tbmId = goodDAO.Delete(goodModel);

							System.out.println("Delete ID: " + tbmId);
							
							if(tbmId == 1) {
								clearDataInJtable();
								Data();

								txtname.setText("");
								txtunitC.setText("");
								txtunitP.setText("");
							}else {
								JOptionPane.showMessageDialog(null, "Cannot Delete!");
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
				txtunitC.setText("");
				txtunitP.setText("");
			}
		});
				
		setPreferredSize(new Dimension(700, 350));
		setVisible(true);
		setBackground(new Color(153, 187, 198));
		setLayout(null);

	}

//-------------------------------------------
	public void clearDataInJtable() {

		remove(scrollPane);
		GoodAdmin g = new GoodAdmin();
		setVisible(false);
		g.setVisible(true);

		DefaultTableModel dm = (DefaultTableModel) model;
		int rowCount = dm.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
	}

	public void Data() {

		goodDAO = new GoodDAO();
		ArrayList<HashMap<String, Object>>  goodList = goodDAO.FindAll();

		for (int i = 0; i < goodList.size(); i++) {
			goodModel = new GoodModel();
			
			goodModel.setId(goodList.get(i).get("id").toString());
			goodModel.setName(goodList.get(i).get("name").toString());
			goodModel.setUnitcost(goodList.get(i).get("unitcost").toString());
			goodModel.setUnitprice(goodList.get(i).get("unitprice").toString());
			
			model.addRow(new Object[0]);
			model.setValueAt(false, i, 0);
			model.setValueAt(goodModel.getId(), i, 1);
			model.setValueAt(goodModel.getName(), i, 2);
			model.setValueAt(goodModel.getUnitcost(), i, 3);
			model.setValueAt(goodModel.getUnitprice(), i, 4);
		}
		add(scrollPane);
	}

	public void Table() {
		// ScrollPane for Table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 20, 400, 200);
		add(scrollPane);

		// Table
		table = new JTable();
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		// table.setBackground(Color.red);

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
		model.addColumn("GoodID");
		model.addColumn("GoodName");
		model.addColumn("UnitCost");
		model.addColumn("UnitPrice");

	}

	public void clickInTable() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String GoodId = null;
				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());

					GoodId = table.getValueAt(i, 1).toString();
					String GoodName = table.getValueAt(i, 2).toString();
					String UnitCost = table.getValueAt(i, 3).toString();
					String UnitPrice = table.getValueAt(i, 4).toString();

					if (chked) {
						goodModel.setId(GoodId);
						txtname.setText(GoodName);
						txtunitC.setText(UnitCost);
						txtunitP.setText(UnitPrice);

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
