import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;


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

	public JSONArray getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredient) {
		this.ingredients.add(ingredient);
		
	}

	public JSONArray getInstructions() {
		return instructions;
	}

	public void setInstructions(String instruction) {
		this.instructions.add(instruction);
	}	
	public void getTitleFromUser() {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("====== Build a new Recipe Here =======");
		System.out.println("Title of Recipe:");
		try {
			this.setTitle(bufferRead.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getIngredientsFromUser(){	
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String ingredient = null;
		System.out.println("Enter ingredients here. Type 'quit' when finished.");
		
		do {
		System.out.println("Ingredient:");
		ingredient = bufferRead.readLine();
		this.setIngredients(ingredient);
		} while (ingredient.toLowerCase() != "quit");
	}
	
	public void getInstructionsFromUser(){	
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String instruction = null;
		System.out.println("Enter instructions here. Type 'quit' when finished.");
		
		do {
		System.out.println("Instruction:");
		instruction = bufferRead.readLine();
		this.setInstructions(instruction);
		} while (instruction.toLowerCase() != "quit");
}
