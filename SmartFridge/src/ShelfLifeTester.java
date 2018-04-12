import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import org.json.JSONException;

import com.google.gson.*;


public class ShelfLifeTester 
{

	
	public static void main(String[] args) throws JSONException
	{
		String foodItem = " ";
		String foodId = " ";
		ArrayList<String> idNumbers = new ArrayList<String>();
		Scanner keyboard = new Scanner(System.in);
		foodItem = keyboard.next();
		//Currently receiving strings as user input to test Yummly API
		/*String french = " ";
		String toast = " ";
		String cinnamon = " ";
		String whiskey = " ";
		ArrayList<String> RECIPE_IDS = new ArrayList<String>();

		//Scanner objects for user input.
		Scanner keyboard = new Scanner(System.in);
		french = keyboard.next();
		toast = keyboard.next();
		cinnamon = keyboard.next();
		whiskey = keyboard.next();
		keyboard.close();
		*/
		
		//Opening a connection to Yummly.
		
		
		try {
		//Creating a URL connection
		URL connected =
		//Information placed into the URL comes from the Scanner input above.
		//Yummly allows for user input just like a search query.
		new URL("https://shelf-life-api.herokuapp.com/search/?q="+ foodItem);
		
		//Opens a URL connection.
		URLConnection myConnected = connected.openConnection();
		myConnected.connect();
		//Creating a receiver of input from the URL Connectiton.
		InputStream in = myConnected.getInputStream();
		
		/*
		 * JSON Parsing Tool
		 * Parses the JSON that is returned by Yummly.
		 */
		JsonParser parser = new JsonParser();
		JsonArray jArray = (JsonArray) parser.parse(
				new InputStreamReader(in, "UTF-8"));
		
		for(JsonElement jm : jArray)
		{
			JsonObject thing = jm.getAsJsonObject();
			JsonPrimitive id = thing.getAsJsonPrimitive("id");
			JsonPrimitive name = thing.getAsJsonPrimitive("name");
			System.out.println(id.toString());
			System.out.println(name.toString());
		}
	
		
		/*
		 * JSON Array of recipe search matches.
		 */
		
		}
		
		catch(MalformedURLException e) 
		{
			System.out.println("Failed connection1");
		}
		catch(IOException e)
		{
			System.out.println("Failed connection2");
		}
		
	
		
		try {
			//Creating a URL connection
			URL connected =
			//Information placed into the URL comes from the Scanner input above.
			//Yummly allows for user input just like a search query.
			new URL("https://shelf-life-api.herokuapp.com/guides/"+ foodId);
			
			//Opens a URL connection.
			URLConnection myConnected = connected.openConnection();
			myConnected.connect();
			//Creating a receiver of input from the URL Connectiton.
			InputStream in = myConnected.getInputStream();
			
			/*
			 * JSON Parsing Tool
			 * Parses the JSON that is returned by Yummly.
			 */
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(
					new InputStreamReader(in, "UTF-8"));
			
			JsonArray methods = jsonObject.getAsJsonArray("methods");
			System.out.println(methods.toString());
			
		}
		
		
			catch(MalformedURLException e) 
			{
				System.out.println("Failed connection1");
			}
			catch(IOException e)
			{
				System.out.println("Failed connection2");
			}
	}
}
		
		
		
		

