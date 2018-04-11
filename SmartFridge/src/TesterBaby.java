//import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import org.apache.commons.io.IOUtils;
import com.google.gson.*;


public class TesterBaby {

	
	public static void main(String[] args)
	{
		//Currently receiving strings as user input to test Yummly API
		String french = " ";
		String toast = " ";
		String cinnamon = " ";
		String whiskey = " ";
		
		//Scanner objects for user input.
		Scanner keyboard = new Scanner(System.in);
		french = keyboard.next();
		toast = keyboard.next();
		cinnamon = keyboard.next();
		whiskey = keyboard.next();
		keyboard.close();
		
		//Opening a connection to Yummly.
		try {
		//Creating a URL connection
		URL connected =
		//Information placed into the URL comes from the Scanner input above.
		//Yummly allows for user input just like a search query.
		new URL("http://api.yummly.com/v1/api/recipes?_app_id=ff685a45&_app_key=3ee0e0125e7c0acdc06aa2c4c1f91758&q=" +
		french + "+" + toast  + "&allowedIngredient[]=" + cinnamon + "&allowedIngredient[]=" + whiskey);
		
		//Opens a URL connection.
		URLConnection myConnected = connected.openConnection();
		myConnected.connect();
		//Creating a receiver of input from the URL Connectiton.
		InputStream in = myConnected.getInputStream();
		//receiving and recognizing the JSON as UTF-8
		String encoding = myConnected.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		//Converting the contents of the given URL to a String.
		String recipeListing = IOUtils.toString(in, encoding);
		System.out.println(recipeListing);
		//Working on this part.
		//Encoding the parsed JSON data into an Array List
		//This is the returned recipe ID.
		//It will be used to access recipes in Yummly.
		//ArrayList<String> recipes = new ArrayList<String>();
		//recipes = parse(recipeListing);
		
		}
		//required exceptions
		catch(MalformedURLException e) 
		{
			System.out.println("Failed connection1");
		}
		catch(IOException e)
		{
			System.out.println("Failed connection2");
		}
	}
	//Methods for obtaining the JSON objects.
	/*public static ArrayList<String> parse(String recipeListing)
	{
		//Declaring a JSON object named searchTerm
		JsonObject searchTerm = new JsonObject();
		searchTerm = searchTerm.getAsJsonObject("q");
		//Polymorph of a JsonElement to a JsonParser
		//This is not working properly
		//It will only allow for a character array of 1024 characters
		//I have not been able to determine a workaround for this yet.
		JsonElement jelement = new JsonParser().parse(recipeListing);
		//Creating a JSON object
		JsonObject jobject = jelement.getAsJsonObject();
		//Here I am attempting to get anything with the "id" tag
		//from the Yummly JSON to be put into jobject
		jobject = jobject.getAsJsonObject("id");
		//creating an array for the ingredients.
		JsonArray jarray = jobject.getAsJsonArray("ingredients");
		jobject = jarray.get(0).getAsJsonObject();
		//An array list for all of the recipe IDs.
		ArrayList<String> recipes = new ArrayList<String>();
		//theoretically adds the returned recipe ID to the Array List
		recipes.add(jobject.get("id").getAsString());
		return recipes;
	}*/
	
}

