
public class Recipe {
	//TODO:save title, ingredients and instructions as recipe object.  
	//functions to edit, delete and save to file
	private String title;
	private String ingredients; //change to an ArrayList?
	private String instructions;//change to an ArrayList?
	
	public Recipe(){
		title="New Recipe";
		ingredients="Ingredients here";
		instructions="Instructions here";
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}	
	

}
