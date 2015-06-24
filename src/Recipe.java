import java.util.ArrayList;


public class Recipe {
	//TODO:save title, ingredients and instructions as recipe object.  
	//functions to edit, delete and save to file
	private String title;
	private ArrayList<String> ingredients; //change to an ArrayList?
	private ArrayList<String> instructions;//change to an ArrayList?
	
	public Recipe(){
		title="New Recipe";
		ingredients=null;
		instructions=null;
		
	}

	public Recipe(String title, ArrayList<String> ingredients, ArrayList<String> instructions) {
		super();
		this.title = title;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(ArrayList<String> instructions) {
		this.instructions = instructions;
	}	
	

}
