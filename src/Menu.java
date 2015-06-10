import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
	//TODO: Adapt sample menu to accept new menu options
	// Local variable
	String[] options;
	public Menu (String name) {
		switch(name){
		case "Main":
			this.options = new String[] {"Create a new Recipe", "List Recipe Titles", "Search for a Recipe by Title", "Quit"};
			break;
		case "E4dit":
			this.options = new String[] {"Change Title", "Edit Ingredients", "Edit Instructions", "Delete", "Return to Main Menu"};
			break;
		}
		displayMenu(name);
	}
	
    
    public void displayMenu(String name){
    // Display menu graphics
	    System.out.println("==================================================");
	    System.out.println("|                  "+name+" Menu                    |");
	    System.out.println("=================================================");
	    System.out.println(" Options:                          ");
	    for (int i = 0; i<options.length; i++){
	    	int opnum = i+1;
	    	System.out.println("        "+ opnum + ". " + options[i] );
	    }
	    System.out.println("=================================================");
    }
    
    public int getKey(){
    	int swValue=0;
    	try{
    		System.out.print("Please enter an option number:  ");
    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	    swValue = Integer.parseInt(bufferRead.readLine());
    
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	   

    // Switch construct

    
    return swValue;
    	}
}
