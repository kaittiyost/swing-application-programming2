package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import kiattiyot.main;

public class testPainGraph extends JPanel {

	JPanel barPanel;
	JPanel labelPanel;
	private int histogramHeight = 200;
	private int barWidth = 50;
	int bargap = 10; // ระยะห่างของ bar = 10

	private List<Bar> bars = new ArrayList<Bar>();

	public testPainGraph() {
		
		setPreferredSize(new Dimension(700, 350));
		setVisible(true);
		setBackground(new Color(99, 42, 126));
	
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());

		barPanel = new JPanel(new GridLayout(1, 0, bargap, 0));

		Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK); // create border
		Border inner = new EmptyBorder(10, 10, 0, 10); // inner border
		Border compound = new CompoundBorder(outer, inner); // ขอบทั้งหมด
		barPanel.setBorder(compound);

		labelPanel = new JPanel(new GridLayout(1, 0, bargap, 0));
		//labelPanel.setBorder(new EmptyBorder(5, 1, 0, 10));

		add(barPanel, BorderLayout.CENTER);
		add(labelPanel, BorderLayout.PAGE_END);
		
		JButton btnRef = new JButton("Refesh Graph");
		btnRef.setSize(50, 50);
		add(btnRef ,BorderLayout.BEFORE_FIRST_LINE );
		btnRef.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("refesh graph");
			}
		});

	}

	public void layoutBarChart() {
		int maxValue = 0;
		for (Bar bar : bars)
			maxValue = Math.max(maxValue, bar.getValue());

		for (Bar bar : bars) {
			JLabel label = new JLabel(bar.getValue() + ""); // ข้อความบนแท่งกราฟ  text on bar graph

			label.setHorizontalTextPosition(JLabel.CENTER); // set position label center on top bar graph
			label.setHorizontalAlignment(JLabel.CENTER); // set position label center botton graph ข้อความใต้กราฟ
			label.setVerticalTextPosition(JLabel.TOP); // set position label on top bar graph
			label.setVerticalAlignment(JLabel.BOTTOM);

			int barHeight = (bar.getValue() * histogramHeight) / maxValue; // graph height
			Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight); // graph
			label.setIcon(icon);
			barPanel.add(label);

			JLabel barLabel = new JLabel(bar.getLabel()); // text bottom bar graph
			barLabel.setHorizontalAlignment(JLabel.CENTER);
			labelPanel.add(barLabel);

		}
	}

	public void addBarColumn(String label, int value, Color color) {
		Bar bar = new Bar(label, value, color);
		bars.add(bar);

	}

//	public static void main(String[] args) {
//		testPainGraph g = new testPainGraph();
//		// add data to graph
//		g.addBarColumn("A", 300, Color.BLUE);
//		g.addBarColumn("B", 200, Color.green);
//		g.layoutBarChart();
//
//		JFrame frame = new JFrame("Stock Bar");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add(g);
//		frame.setLocationByPlatform(true);
//		frame.pack();
//		frame.setVisible(true);
//	}
	
	class Bar {
		private String label;
		private int value;
		private Color color;

		public Bar(String label, int value, Color color) {
			this.label = label;
			this.value = value;
			this.color = color;
		}

		public String getLabel() {
			return label;
		}

		public int getValue() {
			return value;
		}

		public Color getColor() {
			return color;
		}
	}

	class ColorIcon implements Icon {
		
		private Color color;
		private int width;
		private int height;

		public ColorIcon(Color color, int width, int height) {
			this.color = color;
			this.width = width;
			this.height = height;
		}

		public int getIconWidth() {
			return width;
		}

		public int getIconHeight() {
			return height;
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
			g.setColor(Color.GRAY);
			g.fillRect(x + width, y , 0, height);
		}
	}
}


