package kiattiyot;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dao.GoodDAO;
import graph.testPainGraph;

public class main extends JFrame {

	static testPainGraph graph;

	public static void main(String[] args) {

		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		graph = new testPainGraph();
		addDataInGraph();

		GoodAdmin goodAdmin = new GoodAdmin();
		SupplierAdmin supAdmin = new SupplierAdmin();
		SupplierGoodAdmin supGoodAdmin = new SupplierGoodAdmin();
		CustomerAdmin cusAdmin = new CustomerAdmin();

		Purchasing purchase = new Purchasing();
		Reciving revice = new Reciving();

		JMenu jMenu = new JMenu("Management");
		JMenu help = new JMenu("Help");
		JMenu procurement = new JMenu("Procurement");

		JMenuBar jMenuBar = new JMenuBar();

		JMenuItem GoodAdminItem = new JMenuItem("Good-Admin");
		JMenuItem SupplierAdminItem = new JMenuItem("Supplier-Admin");
		JMenuItem SupplierGoodItem = new JMenuItem("Supplier-Good");
		JMenuItem CustomerAdminItem = new JMenuItem("Customer-Admin");

		JMenuItem purchaseItem = new JMenuItem("Purchasing");
		JMenuItem reciveItem = new JMenuItem("Reciving");
		JMenuItem graphItem = new JMenuItem("Stock Graph");

		final CardLayout cardLayout = new CardLayout();
		final String StrGood = "goodAdmin";
		final String StrSupplier = "supAdmin";
		final String StrSupGood = "supGoodAdmin";
		final String StrCustomer = "cusAdmin";

		final String StrPurchase = "Purchasing";
		final String StrRecive = "Reciving";

		final String Strgraph = "Graph";

		frame.getContentPane().setLayout(cardLayout);
		cardLayout.addLayoutComponent(goodAdmin, StrGood);
		cardLayout.addLayoutComponent(supAdmin, StrSupplier);
		cardLayout.addLayoutComponent(supGoodAdmin, StrSupGood);
		cardLayout.addLayoutComponent(cusAdmin, StrCustomer);

		cardLayout.addLayoutComponent(purchase, StrPurchase);
		cardLayout.addLayoutComponent(revice, StrRecive);

		cardLayout.addLayoutComponent(graph, Strgraph);

		jMenu.add(GoodAdminItem);
		jMenu.add(SupplierAdminItem);
		jMenu.add(SupplierGoodItem);
		jMenu.add(CustomerAdminItem);

		procurement.add(purchaseItem);
		procurement.add(reciveItem);

		procurement.add(graphItem);

		jMenuBar.add(jMenu);
		jMenuBar.add(procurement);
		jMenuBar.add(help);

		graphItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Graph_page_request");
				testPainGraph graph = new testPainGraph();
				cardLayout.removeLayoutComponent(graph);
				//graph.setVisible(true);
				cardLayout.show(frame.getContentPane(), Strgraph);
			}
		});

		// Management------------------------
		GoodAdminItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Good_Admin_page_request");
				cardLayout.show(frame.getContentPane(), StrGood);

			}
		});

		SupplierAdminItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Supplier_Admin_page_request");
				cardLayout.show(frame.getContentPane(), StrSupplier);

			}
		});

		SupplierGoodItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Supplier_good_Admin_request");

				cardLayout.show(frame.getContentPane(), StrSupGood);
			}
		});

		CustomerAdminItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Customer_Admin_request");
				cardLayout.show(frame.getContentPane(), StrCustomer);

			}
		});

		// Procrument------------------------
		purchaseItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Purchase_Admin_request");
				cardLayout.show(frame.getContentPane(), StrPurchase);

			}
		});

		reciveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reciving_Admin_request");
				cardLayout.show(frame.getContentPane(), StrRecive);

			}
		});

		frame.getContentPane().add(goodAdmin);
		frame.getContentPane().add(supAdmin);
		frame.getContentPane().add(supGoodAdmin);
		frame.getContentPane().add(cusAdmin);

		frame.getContentPane().add(purchase);
		frame.getContentPane().add(revice);

		frame.getContentPane().add(graph);

		frame.pack();
		frame.setJMenuBar(jMenuBar);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static void addDataInGraph() {
	
		GoodDAO goodDAO = new GoodDAO();
		ArrayList<HashMap<String, Object>> goodList = goodDAO.StockAll();

		Random rand = new Random();

		for (int i = 0; i < goodList.size(); i++) {

			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();

			Color randomColor = new Color(r, g, b);
			graph.addBarColumn(goodList.get(i).get("name").toString(),
					Integer.parseInt(goodList.get(i).get("replenish").toString()), randomColor);
		}
		graph.layoutBarChart();

	}
}
