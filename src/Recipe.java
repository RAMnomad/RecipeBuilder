import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Recipe {
	//TODO:save title, ingredients and instructions as recipe object.  
	//functions to edit, delete and save to file
	private String title;
	//private JSONArray ingredients; 
	private String ingredients;
	private String instructions;
	
	public Recipe(){
		title="Enter new recipe title here";
		//ingredients=new JSONArray();
		ingredients = "Ingredients here";
		instructions="Instructions go here\nClick Save when finished";
		
	}

	public Recipe(String title, String ingredients, String instructions) {
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

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredient) {
		this.ingredients=ingredient;
		
	}
	
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instruction) {
		this.instructions=instruction;
	}	
	/*public void getTitleFromUser() {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("====== Build a new Recipe Here =======");
		System.out.println("Title of Recipe:");
		try {
			this.setTitle(bufferRead.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getIngredientsFromUser();
	}
	
	public void getIngredientsFromUser(){	
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String ingredient = null;
		System.out.println("Enter ingredients here. Type 'quit' when finished.");
		
		do {
		System.out.println("Ingredient:");
		try {
			ingredient = bufferRead.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIngredients(ingredient);
		} while (!ingredient.equalsIgnoreCase("quit"));
		this.getInstructionsFromUser();
	}
	
	public void getInstructionsFromUser(){	
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String instruction = null;
		System.out.println("Enter instructions here. Type 'quit' when finished.");
		
		do {
		System.out.println("Instruction:");
		try {
			instruction = bufferRead.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setInstructions(instruction);
		} while (!instruction.equalsIgnoreCase("quit"));
		this.writeRecipeFile(this);
	}*/
	
	//@SuppressWarnings("unchecked")
	 @SuppressWarnings("unchecked")
		public void writeRecipeFile(Recipe recipe){
			File file=null;
			FileWriter filewriter = null;
			try {
				file = new File("C://temp/recipes.json");
				if (!file.exists()){
					boolean fileCreated = file.createNewFile();
					if (fileCreated){
						System.out.println("Created new recipe file.");//change to pop up alert?
					}
				//file.setWritable(true);
				//filewriter = new FileWriter("C://temp/recipes.json", true);	
					}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject addRecipe = new JSONObject();
			addRecipe.put("Title", recipe.getTitle());
			addRecipe.put("Ingredients", recipe.getIngredients());
			addRecipe.put("Instructions", recipe.getInstructions());
			try {
				file.setWritable(true);
				filewriter=new FileWriter("C://temp/recipes.json", true);
	            filewriter.write(addRecipe.toJSONString());
	            System.out.println("Successfully Copied JSON Object to File...");
	            System.out.println("\nJSON Object: " + addRecipe);
	            filewriter.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
		
		
		}
}
