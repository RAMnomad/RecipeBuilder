import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindRecipes {
    // List Recipe titles alphabetically
	//Turn existing recipe into Recipe object
	private int index = 0 ;
	private String title;
	private JSONArray ingred,instr;
	private ArrayList<String> titles;
	private JSONParser recipeParser = new JSONParser();
	
	public Scanner getRecipeFile(){
		Scanner file = null;
		File recipeFile = new File("C://recipes.json");
		try{
			file = new Scanner(recipeFile);
		}
		catch(FileNotFoundException e){
			System.out.println("Recipe file does not exist.");
			
			
		}
		return file;
	}
	
	public void listAllRecipes(){
		
		Scanner file = getRecipeFile();
		try{
			while (file.hasNext()){
				Object parsedData = recipeParser.parse(file.nextLine());
				JSONObject recipeObj = (JSONObject) parsedData;
				String nextTitle = (String) recipeObj.get("title");
				titles.add(nextTitle);
				
			}
			
		}
		catch (ParseException e) {
	        e.printStackTrace();
	    }
		int i = 0;
		
		for (String t:titles){
			i++;
			System.out.println(i + ". " + t);
		}
			
		
	}
	public void grabRecipe(Object parsedData){
		JSONObject recipeObj = (JSONObject) parsedData;
		title = (String) recipeObj.get("title");
		ingred = (JSONArray) recipeObj.get("ingredients");
		instr = (JSONArray) recipeObj.get("instructions");
		Recipe recipe = new Recipe(title,ingred,instr);
	}
	public void getRecipeByIndex(){
		while (index < 1){
			try{
	    		System.out.print("Please enter the number of the recipe you wish to view: ");
	    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    	    index = Integer.parseInt(bufferRead.readLine());
	    
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
			if (index > titles.size()){
				System.out.println("Invalid entry. Out of range.");
				index = 0;
			}
		}
			
		Scanner file = getRecipeFile();
		for(int i = 0; i<index;i++){
			file.nextLine();
		}
		Object parsedData = null;
		try {
			parsedData = recipeParser.parse(file.nextLine());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grabRecipe(parsedData);
	}
	
	
	public void getRecipeByTitle(){
		String userTitle = null, fileTitle = null;
		Object parsedData = null;
		while (userTitle == null){
			try{
	    		System.out.print("Please enter the title of the recipe you wish to view: ");
	    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    	    userTitle = bufferRead.readLine();
	    
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
			Scanner file = getRecipeFile();
			while (userTitle.toLowerCase()!=fileTitle.toLowerCase()&&file.hasNextLine()){
				
				try {
					parsedData = recipeParser.parse(file.nextLine());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject recipeObj = (JSONObject) parsedData;
				fileTitle = (String) recipeObj.get("title");	
			}
			if	(userTitle.toLowerCase()!=fileTitle.toLowerCase()){
				grabRecipe(parsedData);
			}
		}
	}
	
}
