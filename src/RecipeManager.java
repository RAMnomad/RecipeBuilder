
public class RecipeManager {
	public static void main(String[] args){
		//TODO:  Instantiate new menu
		Recipe recipe = null;
		Menu mainMenu = new Menu("Main");
		int selected = mainMenu.getKey();
		switch (selected){
		case 1: recipe = new Recipe(); break;
		case 2: Menu editMenu = new Menu("Edit");
		case 4: System.exit(0);
		default: System.out.println("Option " + selected + " selected.");
		}
	}
	
}
