import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindRecipes {
	// List Recipe titles alphabetically
	//Turn existing recipe into Recipe object
	
	private int index = 0 ;
	public String title, ingred, instr;
	
	public ArrayList<String> titles;
	public JSONParser recipeParser = new JSONParser();
	public JSONArray array= new JSONArray();
	
	public File recipeFile;
	public JSONObject recipeObj;
	public Object parsedData=new Object();
	public String nextTitle="";
	
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
		recipeFile = getRecipeFile();
		Scanner file=new Scanner(recipeFile);
		titles=new ArrayList<String>();
		titles.add("");
		System.out.println("step2");
		try{
			System.out.println("step3");
			
			if(!file.hasNext()){
				System.out.println("step4");
				ArrayList<String> noRecipes = new ArrayList<String>();
				noRecipes.add("No Recipes in File");
				file.close();
				return noRecipes;
			}else{
				while (file.hasNext()){
					array.addAll((JSONArray)recipeParser.parse(file.nextLine()));
				}
				for(int index=0; index<array.size(); index++){
					recipeObj = (JSONObject) array.get(index);
					nextTitle = (String) recipeObj.get("Title");
					titles.add(nextTitle);
				}
				
			file.close();
			}
		}
		catch (ParseException e) {
	        e.printStackTrace();
	    }
	
		java.util.Collections.sort(titles);//arrange titles by alphabetical order
		
		return titles;
			
		
	}
	public Recipe grabRecipe(Object parsedData){
		JSONObject recipeObj = (JSONObject) parsedData;
		title = (String) recipeObj.get("title");
		ingred = (String) recipeObj.get("ingredients");
		instr = (String) recipeObj.get("instructions");
		Recipe recipe = new Recipe(title,ingred,instr);
		return recipe;
		
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
		
	}
	
	public void getRecipeByIndex() throws IOException{
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
		
		title=titles.get(index-1);
		//Object parsedData = null;
		Scanner file = new Scanner(getRecipeFile());
		try{
			while (file.hasNext()){
				Object parsedData = recipeParser.parse(file.nextLine());
				JSONObject recipeObj = (JSONObject) parsedData;
				String nextTitle = (String) recipeObj.get("title");
				if(title.equals(nextTitle)){
					grabRecipe(parsedData);
					break;
				
				}
				
			}
			
		}
		catch (ParseException e) {
	        e.printStackTrace();
	    }
		

		
	}
	
	
	public Recipe getRecipeByTitle(String title) throws IOException{
		String fileTitle = null;
		Object parsedData = null;
		Recipe recipe;
		//while (title == null){
			/*try{
	    		System.out.print("Please enter the title of the recipe you wish to view: ");
	    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    	    userTitle = bufferRead.readLine();
	    
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}*/
			Scanner file =new Scanner( getRecipeFile());
			while (title!=fileTitle&&file.hasNextLine()){
				
				try {
					parsedData = recipeParser.parse(file.nextLine());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject recipeObj = (JSONObject) parsedData;
				fileTitle = (String) recipeObj.get("title");	
			}
			if	(title==fileTitle){
				recipe=grabRecipe(parsedData);
				
			}else{
				recipe=new Recipe("File not found","","");
			}
		//}
			return recipe;
	}
	
}
