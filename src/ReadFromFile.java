
import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class ReadFromFile
{
	@SuppressWarnings("unchecked")//not sure what this does
	public static void Read()
	{
		String recipeFile="recipes.JSON";//name of file with path
		JSONParser parser = new JSONParser();
		try
		{
			Object obj = parser.parse(new FileReader(recipeFile));
			JSONObject recipe = (JSONObject) obj;

			//need while loop? to check for next recipe in file
			String title = (String) recipe.get("Title");
			JSONArray ingredients = (JSONArray) recipe.get("Ingredients");
			JSONArray instructions = (JSONArray) recipe.get("Instructions");

			System.out.println("Title: " + title);
			System.out.println("Ingredients: ");
			Iterator<String> iterator = ingredients.iterator();
			while (iterator.hasNext())
			{
				System.out.println(iterator.next());
			}
			System.out.println("Instructions: ");
			iterator = instructions.iterator();
			while (iterator.hasNext())
			{
				System.out.println(iterator.next());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}