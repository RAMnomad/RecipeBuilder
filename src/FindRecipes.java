import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindRecipes {
	// List Recipe titles alphabetically
	//Find Recipe by title
	
	public ArrayList<String> titles;
	public JSONArray array;
	public File recipeFile;
	public JSONObject recipeObj;
	
	
	public File getRecipeFile() throws IOException{
		Scanner file = null;
		recipeFile = new File("C://temp/recipes.json");
		try{
			file = new Scanner(recipeFile);
			
		}
		catch(FileNotFoundException e){
			System.out.println("Recipe file does not exist.");
			boolean fileCreated = recipeFile.createNewFile();
			if (fileCreated){
				System.out.println("Created new recipe file.");//change to pop up alert?
				recipeFile=getRecipeFile();
			}
			
		}
		return recipeFile;
	}
	
	public ArrayList<String> listAllRecipes() throws IOException{
		System.out.println("step1");
		titles=new ArrayList<String>();
		String nextTitle;
		getRecipeArray();
		if(!getRecipeArray()){
			ArrayList<String> noRecipes = new ArrayList<String>();
			noRecipes.add("No Recipes in File");
			return noRecipes;
		}else{
			for(int index=0; index<array.size(); index++){
				recipeObj = (JSONObject) array.get(index);
				nextTitle = (String) recipeObj.get("Title");
				System.out.println(nextTitle);
				titles.add(nextTitle);
				System.out.println(titles);
			}
			System.out.println(titles);
			java.util.Collections.sort(titles);//arrange titles by alphabetical order
			System.out.println(titles);
			return titles;
		}
	}
	public boolean getRecipeArray() throws IOException{
		recipeFile=getRecipeFile();
		Scanner file=new Scanner(recipeFile);
		JSONParser recipeParser=new JSONParser();
		boolean fileHasRecipes=false;
		array=new JSONArray();
		try{
			System.out.println("step3");
			
			if(!file.hasNext()){
				System.out.println("step4");
				file.close();
				return fileHasRecipes;
			}else{
				//while (file.hasNext()){
					array.addAll((JSONArray)recipeParser.parse(file.nextLine()));
				//}
				fileHasRecipes=true;
			}
		
		}
		catch (ParseException e) {
	        e.printStackTrace();
	    }
		file.close();
		return fileHasRecipes;
	}
	//public Recipe nextRecipe(){
//		JSONObject recipeObj = (JSONObject) parsedData;
	//	title = (String) recipeObj.get("title");
		//ingred = (String) recipeObj.get("ingredients");
		//instr = (String) recipeObj.get("instructions");
		//Recipe recipe = new Recipe(title,ingred,instr);
		//return recipe;
		
		//System.out.println(title);
		//System.out.println("Ingredients: ");
		//Iterator<String> iterator = ingred.iterator();
		//while(iterator.hasNext()) {
			//System.out.println(iterator.next());
		//}
		//System.out.println("Instructions: ");
		//Iterator<String>iterator = instr.iterator();
		//while(iterator.hasNext()) {
			//System.out.println(iterator.next());
		//}
		
	//}
	
	
		
	
	
	public Recipe getRecipeByTitle(String title) throws IOException{
		
		//getRecipeArray();
		Recipe recipe= new Recipe();
		if(!getRecipeArray()){
			recipe.setTitle("No Recipes in File");
			return recipe;
		}else{
			//Iterator<String> iterator=titles.iterator();
			//while(iterator.hasNext()){
				//if(title.equalsIgnoreCase(iterator.next())){
					//recipe.setTitle(title);
					//recipe.setIngredients(ingredient);
				//}
			//}
			for(int index=0; index<array.size(); index++){
				recipeObj = (JSONObject) array.get(index);
				if(title.equalsIgnoreCase((String)recipeObj.get("Title"))){
					recipe.setTitle(title);
					System.out.println("found in json array");
					System.out.println(title+"1");
					recipe.setIngredients((String)recipeObj.get("Ingredients"));
					recipe.setInstructions((String)recipeObj.get("Instructions"));
					break;
				}
	
			}
			return recipe;
		}
	}
}
