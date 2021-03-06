import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class GUI {

	private JFrame frame;
	private JPanel contentPane;
	private JTextArea instructionsText, ingredientsText, listTitles;
	private JTextField titleText;
	private JScrollPane scroll;
	public String title;
	public String titles="";
	public ArrayList <String> titlesArray=new ArrayList<String>();
	public JButton prev, next, edit, save, send, search, newRecipe;
	public Recipe recipe=new Recipe();
	public FindRecipes findRecipe=new FindRecipes();
	
	public void buildRecipeCard() throws IOException {
		frame = new JFrame("Recipe Card");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Container contentPane = frame.getContentPane();
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
	private void makeDisplayArea() throws IOException{
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
		//search button
		search = new JButton("Search");
		search.addActionListener(new openListener());
		panel.add(search, BorderLayout.EAST);
		contentPane.add(panel, BorderLayout.NORTH);
	}
	private void makeEastRegion() throws IOException{
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("All Recipes:"));
		
		//FindRecipes findRecipes = new FindRecipes();
		
		System.out.println("a");
		//method call to return titles
		titlesArray = findRecipe.listAllRecipes();
		System.out.println("b");
		Iterator<String> iterator = titlesArray.iterator();
				while(iterator.hasNext()){
						title=iterator.next();
						titles = (titles + "\n" + title);
					}
			
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
		//buttons for prev, next, edit, save, send shopping list
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		newRecipe= new JButton("New");
		newRecipe.addActionListener(new newListener());
		panel.add(newRecipe);
		prev = new JButton("<-Prev");
		prev.addActionListener(new prevListener());
		panel.add(prev);
		next = new JButton("Next->");
		next.addActionListener(new nextListener());
		panel.add(next);
		edit = new JButton("Edit");
		edit.addActionListener(new editListener());
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
			titleText.setEditable(true);
			ingredientsText.setEditable(true);
			instructionsText.setEditable(true);
		}
	}
	private class prevListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//loads previous recipe in list
			title = titleText.getText();
			FindRecipes findRecipes=new FindRecipes();
			ListIterator<String> iterator = titlesArray.listIterator();
			while(iterator.hasNext()){
				if(iterator.next().equalsIgnoreCase(title)){
					title=iterator.previous();
					if(iterator.hasPrevious()){
						title=iterator.previous();
					}
					break;
				}
			}
			try {
				recipe=findRecipes.getRecipeByTitle(title);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			titleText.setText(recipe.getTitle());
			ingredientsText.setText(recipe.getIngredients());
			instructionsText.setText(recipe.getInstructions());
		}
	}
	private class nextListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//loads next recipe in list
			title = titleText.getText();
			FindRecipes findRecipes=new FindRecipes();
			Iterator<String> iterator=titlesArray.iterator();
			while(iterator.hasNext()){
				if(iterator.next().equalsIgnoreCase(title)){
					if(iterator.hasNext()){
					title=iterator.next();
					}
					try {
						recipe=findRecipes.getRecipeByTitle(title);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
			titleText.setText(recipe.getTitle());
			ingredientsText.setText(recipe.getIngredients());
			instructionsText.setText(recipe.getInstructions());
		}
	}
	private class openListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// open recipe
			recipe=new Recipe();//should we construct with given title instead?
			recipe.setTitle(titleText.getText());
			//compare title to titles (look for existing in ArrayList)
			FindRecipes findRecipes=new FindRecipes();
			boolean foundRecipe=false;
			try {
				titlesArray=findRecipes.listAllRecipes();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//System.out.println(titlesArray);
			Iterator<String> iterator=titlesArray.iterator();
			while(iterator.hasNext()){
				if(iterator.next().equalsIgnoreCase(recipe.getTitle())){
					//System.out.println("title");
					try {
						recipe=findRecipes.getRecipeByTitle(recipe.getTitle());
						//System.out.println(recipe.getTitle());
						foundRecipe=true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			if(foundRecipe){
				titleText.setText(recipe.getTitle());
				ingredientsText.setText(recipe.getIngredients());
				instructionsText.setText(recipe.getInstructions());
				titleText.setEditable(false);
			    ingredientsText.setEditable(false);
			    instructionsText.setEditable(false);
			}else{
				titleText.setText("File not found");
			}
			}
		}
	}
	public boolean currentlyEditing = false;
	private class editListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// make fields editable
			titleText.setEditable(true);
			ingredientsText.setEditable(true);
			instructionsText.setEditable(true);
			currentlyEditing = true;
		
		}
	}
	private class saveListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//save recipe
			recipe=new Recipe();
			recipe.setTitle(titleText.getText());
			recipe.setIngredients(ingredientsText.getText());
		    recipe.setInstructions(instructionsText.getText());
		    if(currentlyEditing){
		    	FindRecipes.array.remove(FindRecipes.indexOfEditableRecipe);
		    	System.out.println(FindRecipes.array);
		    	currentlyEditing = false;
		    	recipe.writeRecipeFile(FindRecipes.array);
		    }
		    recipe.writeRecipeFile(recipe);		    
		    titleText.setEditable(false);
		    ingredientsText.setEditable(false);
		    instructionsText.setEditable(false);
		    FindRecipes findRecipes=new FindRecipes();
		    try {
		    	
		    titles=new String();
		    titlesArray=findRecipes.listAllRecipes();
		    Iterator<String> iterator = titlesArray.iterator();
			while(iterator.hasNext()){
					title=iterator.next();
					titles = (titles + "\n" + title);
				}
		    	listTitles.setText(titles);
		    	//makeEastRegion();//does not work to refresh list!!!!
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
		   
	}
	private class listListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//TODO list titles
			try {
				makeEastRegion();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//will this work to refresh the list of titles???
		}
	}
	private class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
}
