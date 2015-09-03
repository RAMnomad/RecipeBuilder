import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Recipe {
	//TODO:save title, ingredients and instructions as recipe object.  
	//functions to edit, delete and save to file
	private String title; 
	private String ingredients;
	private String instructions;
	
	public Recipe(){
		title="Enter new recipe title here";
		ingredients = "Ingredients here";
		instructions="Instructions go here\nClick Save when finished";
		
	}
	

	public Recipe(String title, String ingredients, String instructions) {
		super();
		this.title=title;
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
	
	
	 @SuppressWarnings("unchecked")
		public void writeRecipeFile(Recipe recipe){
			File recipeFile=null;
			FileWriter filewriter = null;
			try {
				recipeFile = new File("C://temp/recipes.json");
				if (!recipeFile.exists()){
					boolean fileCreated = recipeFile.createNewFile();
					if (fileCreated){
						System.out.println("Created new recipe file.");//change to pop up alert?
					}
				
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject addRecipe = new JSONObject();
			addRecipe.put("Title", recipe.getTitle());
			addRecipe.put("Ingredients", recipe.getIngredients());
			addRecipe.put("Instructions", recipe.getInstructions());
			JSONArray array=new JSONArray();
			JSONParser recipeParser=new JSONParser();
			Scanner file = null;
			try {
				file = new Scanner(recipeFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				while(file.hasNext()){
				array.addAll((JSONArray)recipeParser.parse(file.nextLine()));
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			array.add(addRecipe);
			try {
				recipeFile.setWritable(true);		
				filewriter=new FileWriter(recipeFile,false);
	            filewriter.write(array.toJSONString());
	            
				
	            System.out.println("Successfully Copied JSON Object to File...");
	            System.out.println("\nJSON Object: " + addRecipe);
	            filewriter.flush();
	            filewriter.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
		
		
		}
	 public void removeRecipe(Recipe recipe){
		 //TODO write code to remove recipe from file
		
	 }
}
