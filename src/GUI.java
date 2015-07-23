import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.json.simple.JSONArray;

import java.io.*;
import java.util.*;

public class GUI {

	private JFrame frame;
	private JPanel contentPane;
	private JTextArea instructionsText, ingredientsText;
	private JTextField titleText;
	//private JScrollPane ingredientsText;
	private String title;
	private JSONArray ingredients;
	private JSONArray instructions;
	
	public void buildRecipeCard() {
		frame = new JFrame("Recipe Card");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		makeMenus();
		makeDisplayArea();
		frame.pack();
		frame.setSize(900, 700);
		frame.setVisible(true);
		
	}
	private void makeMenus(){
		JMenuBar menuBar;
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.add(makeRecipeMenu());
	}
	private JMenu makeRecipeMenu(){
		JMenu menu;
		JMenuItem menuItem;
		
		
		//set up Recipe menu
		menu = new JMenu("Recipe");
		menu.setMnemonic(KeyEvent.VK_R);
		
		//add Recipe menu items
		menuItem = new JMenuItem("New");
		menu.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(new newListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open...");
		menu.setMnemonic(KeyEvent.VK_O);
		menuItem.addActionListener(new openListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save");
		menu.setMnemonic(KeyEvent.VK_S);
		menuItem.addActionListener(new saveListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("List");
		menu.setMnemonic(KeyEvent.VK_L);
		menuItem.addActionListener(new listListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menu.addSeparator();
		menuItem = new JMenuItem("Exit");
		menu.setMnemonic(KeyEvent.VK_X);
		menuItem.addActionListener(new exitListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		menu.add(menuItem);
		
		return menu;
		
	}
	private void makeDisplayArea(){
		contentPane = (JPanel)frame.getContentPane();
		contentPane.setLayout(new BorderLayout(6,6));
		contentPane.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		
		makeNorthRegion(); //title area
		makeWestRegion(); //img area
		//makeCenterRegion(); //ing and instr 
		makeEastRegion();   //ing and instr text areas
		makeSouthRegion();  //buttons for nav
	}
	private void makeNorthRegion(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Title:"));
		//panel.setPreferredSize(new Dimension(150,0));
		titleText = new JTextField("");
		titleText.setEditable(true);
		titleText.setPreferredSize(new Dimension(300,30));
		panel.add(titleText, BorderLayout.CENTER);
		//TODO add button for search feature here
		contentPane.add(panel, BorderLayout.NORTH);
	}
	/*private void makeCenterRegion(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		
		panel.add(new JLabel("Ingredients:"));
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.add(new JLabel("Instructions:"));
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);
		contentPane.add(panel, BorderLayout.CENTER);
	}*/
	private void makeEastRegion(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		
		JPanel smallPanel = new JPanel();
		smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
		smallPanel.setBorder(BorderFactory.createTitledBorder("Ingredients:"));
		ingredientsText = new JTextArea("");
		ingredientsText.setEditable(true);
		ingredientsText.setPreferredSize(new Dimension(300,300));
		
		smallPanel.add(ingredientsText);
		panel.add(smallPanel);
		
		smallPanel = new JPanel();
		smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
		smallPanel.setBorder(BorderFactory.createTitledBorder("Instructions:"));
		
		instructionsText = new JTextArea("");
		instructionsText.setEditable(true);
		instructionsText.setPreferredSize(new Dimension(400,400));
		smallPanel.add(instructionsText);
	
		//smallPanel.add(ingredientsText);
		//smallPanel.add(instructionsText);
		panel.add(smallPanel);
		contentPane.add(panel, BorderLayout.EAST);
		
	}
	private void makeWestRegion(){
		//get and display image
		JLabel imgLabel = new JLabel(new ImageIcon("recipe.jpg"), JLabel.CENTER);
		imgLabel.setPreferredSize(new Dimension(400,100));
		contentPane.add(imgLabel, BorderLayout.WEST);
	}
	private void makeSouthRegion(){
		//TODO buttons for prev, next, edit, save, send shopping list?
	}
	private class newListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//TODO new recipe 
		}
	}
	private class openListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//TODO open recipe
		}
	}
	private class saveListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//TODO save recipe
		}
	}
	private class listListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//TODO list titles
		}
	}
	private class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
}
