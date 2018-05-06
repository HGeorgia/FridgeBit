import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.net.*;


import com.google.gson.*;


public class ShelfLife extends Photo
{
	private String foodItem1 = " ";
	private String foodItem2 = " ";
	private String foodItem3 = " ";
	private String foodId = " ";
	private String foodName = " ";
	private double hours = 0.0;
	
	public ShelfLife(String foodItem1, String foodItem2, String foodItem3)
	{
		this.foodItem1 = foodItem1;
		this.foodItem2 = foodItem2;
		this.foodItem3 = foodItem3;
	}
	
	
	
	public ShelfLife ()
	{
		 foodItem1 = "carrot";
		 foodItem2 = "celery";
		 foodItem3 = "salt";
	
	}
	
	public TreeMap <Double, String> GenerateRecipeIngredients()
	{
		 TreeMap<Double, String> lifeSpan = new TreeMap<Double, String>();
		 ArrayList<String> idNumbers = new ArrayList<String>();
		 Photo p = new Photo();
		 String[] ingredientArray = new String[p.fromFridge().size()];
		 p.fromFridge().toArray(ingredientArray);
		 
		 foodItem1 = p.getFood1();
		 foodItem2 = p.getFood2();
		 foodItem3 = p.getFood3();
		 
		 System.out.println(p.toString());
		
			
		
		try 
		{
		for(int i = 0; i <=2; i++)
		{
		//Creating a URL connection
		URL connected =
		//Information placed into the URL comes from the Scanner input above.
		//Shelf Life API Connection
		new URL("https://shelf-life-api.herokuapp.com/search/?q=" + ingredientArray[i]);
		
		//Opens a URL connection.
		URLConnection myConnected = connected.openConnection();
		myConnected.connect();
		//Creating a receiver of input from the URL Connectiton.
		InputStream in = myConnected.getInputStream();
		
		/*
		 * JSON Parsing Tool
		 * Parses the JSON
		 */
		JsonParser parser = new JsonParser();
		JsonArray jArray = (JsonArray) parser.parse(
				new InputStreamReader(in, "UTF-8"));
		
		for(JsonElement jm : jArray)
		{
			JsonObject thing = jm.getAsJsonObject();
			JsonPrimitive id = thing.getAsJsonPrimitive("id");
			JsonPrimitive name = thing.getAsJsonPrimitive("name");
			
				foodId = id.toString();
				foodName = ingredientArray[i];
		}
	
		System.out.println(foodId);
		System.out.println(foodName);
		
		/*
		 * JSON Array of recipe search matches.
		 */
		
		}
		}
		
		catch(MalformedURLException me) 
		{
			System.out.println("Failed connection1");
		}
		catch(IOException ioe)
		{
			System.out.println("Failed connection2");
		}
		
	
		
		try {
			//Creating a URL connection
			URL connected2 =
			//Information placed into the URL comes from the Scanner input above.
			//Yummly allows for user input just like a search query.
			new URL("https://shelf-life-api.herokuapp.com/guides/"+ foodId);
			
			//Opens a URL connection.
			URLConnection myConnected2 = connected2.openConnection();
			myConnected2.connect();
			//Creating a receiver of input from the URL Connectiton.
			InputStream in2 = myConnected2.getInputStream();
			
			/*
			 * JSON Parsing Tool
			 * Parses the JSON that is returned by Yummly.
			 */
			
			JsonParser parser2 = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser2.parse(
					new InputStreamReader(in2, "UTF-8"));
			
			JsonArray methods = jsonObject.getAsJsonArray("methods");
			for(JsonElement jm : methods)
			{
				JsonObject location = jm.getAsJsonObject();
				JsonPrimitive expirationTime = location.getAsJsonPrimitive("expirationTime");
				hours = Double.parseDouble(expirationTime.toString());
				hours = (hours/60)/60;
				lifeSpan.put(hours, foodName);
			}
			System.out.println(lifeSpan.toString());
			
			
			
			
			
			
			
		}
		
		
			catch(MalformedURLException me2) 
			{
				System.out.println("Failed connection1");
			}
			catch(IOException ioe2)
			{
				System.out.println("Failed connection2");
			}	
	return lifeSpan;
	}

	



	public String getFoodItem1() {
		return foodItem1;
	}



	public void setFoodItem1(String foodItem1) {
		this.foodItem1 = foodItem1;
	}



	public String getFoodItem2() {
		return foodItem2;
	}



	public void setFoodItem2(String foodItem2) {
		this.foodItem2 = foodItem2;
	}



	public String getFoodItem3() {
		return foodItem3;
	}



	public void setFoodItem3(String foodItem3) {
		this.foodItem3 = foodItem3;
	}



	public String getFoodId() {
		return foodId;
	}



	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}



	public String getFoodName() {
		return foodName;
	}



	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}



	@Override
	public String toString() {
		return "ShelfLife [foodItem1=" + foodItem1 + ", foodItem2=" + foodItem2 + ", foodItem3=" + foodItem3
				+ ", foodId=" + foodId + ", foodName=" + foodName + "]";
	}
	
	
}
