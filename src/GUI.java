import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class GUI {

	private JFrame frame;
	private JPanel contentPane;
	private JTextArea instructionsText, ingredientsText, listTitles;
	private JTextField titleText;
	private JScrollPane scroll;
	private String title, titles;
	//private JSONArray ingredients;
	//private JSONArray instructions;
	private ArrayList <String> titlesArray;
	public JButton prev, next, edit, save, send;
	public Recipe recipe=new Recipe();
	//public Recipe recipe1;
	//public FindRecipes findRecipe=new FindRecipes();
	
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
		makeCenterRegion(); //ing and instr 
		makeEastRegion();   //list of titles
		makeSouthRegion();  //buttons for nav
	}
	private void makeNorthRegion(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Title:"));
		//panel.setPreferredSize(new Dimension(150,0));
		titleText = new JTextField(recipe.getTitle());
		titleText.setEditable(true);
		titleText.setPreferredSize(new Dimension(300,30));
		panel.add(titleText, BorderLayout.CENTER);
		//TODO add button for search feature here
		contentPane.add(panel, BorderLayout.NORTH);
	}
	@SuppressWarnings("null")
	private void makeEastRegion(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("All Recipes:"));
		
		FindRecipes findRecipes = new FindRecipes();
		
		System.out.println("a");
		//method call to return titles
		titlesArray = findRecipes.listAllRecipes();
		System.out.println("b");
		for(String title : titlesArray)
			titles = (titles + "\n" + title);
		listTitles = new JTextArea(titles);
		listTitles.setEditable(false);
		listTitles.setVisible(true);
		
		scroll = new JScrollPane(listTitles);
		scroll.setPreferredSize(new Dimension(200,400));
		panel.add(scroll);
		contentPane.add(panel, BorderLayout.EAST);
	}
	private void makeCenterRegion(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		
		JPanel smallPanel = new JPanel();
		smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
		smallPanel.setBorder(BorderFactory.createTitledBorder("Ingredients:"));
		ingredientsText = new JTextArea(recipe.getIngredients());
		ingredientsText.setEditable(true);
		ingredientsText.setLineWrap(true);
		ingredientsText.setVisible(true);
		
		
		scroll = new JScrollPane(ingredientsText);
		scroll.setPreferredSize(new Dimension(200,200));
		smallPanel.add(scroll);
		panel.add(smallPanel);
		
		smallPanel = new JPanel();
		smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
		smallPanel.setBorder(BorderFactory.createTitledBorder("Instructions:"));
		
		instructionsText = new JTextArea(recipe.getInstructions());
		instructionsText.setEditable(true);
		instructionsText.setLineWrap(true);
		instructionsText.setVisible(true);
				
		scroll = new JScrollPane(instructionsText);
		scroll.setPreferredSize(new Dimension(200,200));
		
		smallPanel.add(scroll);
		panel.add(smallPanel);
		panel.setVisible(true);
		contentPane.add(panel, BorderLayout.CENTER);
		
	}
	private void makeWestRegion(){
		//get and display image
		JLabel imgLabel = new JLabel(new ImageIcon("recipe.jpg"), JLabel.CENTER);
		imgLabel.setPreferredSize(new Dimension(400,100));
		contentPane.add(imgLabel, BorderLayout.WEST);
	}
	private void makeSouthRegion(){
		//TODO buttons for prev, next, edit, save, send shopping list?
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		prev = new JButton("<-Prev");
		panel.add(prev);
		next = new JButton("Next->");
		panel.add(next);
		edit = new JButton("Edit");
		panel.add(edit);
		save = new JButton("Save");
		save.addActionListener(new saveListener());
		panel.add(save);
		send = new JButton("Send Shopping List");
		panel.add(send);
		contentPane.add(panel, BorderLayout.SOUTH);
	}
	private class newListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//clears text fields so new recipe can be entered
			recipe=new Recipe();
			titleText.setText(recipe.getTitle());
			ingredientsText.setText(recipe.getIngredients());
			instructionsText.setText(recipe.getInstructions());
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
			recipe=new Recipe();
			recipe.setTitle(titleText.getText());
			recipe.setIngredients(ingredientsText.getText());
		    recipe.setInstructions(instructionsText.getText());
		    recipe.writeRecipeFile(recipe);
		    //System.out.println(recipe.getTitle());
		   // System.out.println(recipe.getIngredients());
		}
		   
	}
	private class listListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//TODO list titles
			makeEastRegion();//will this work to refresh the list of titles???
		}
	}
	private class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
}
