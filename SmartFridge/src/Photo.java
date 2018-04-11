import java.util.*;
import java.lang.*; 

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.request.model.PredictRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;



public class Photo {

	public static void main (String [] args)
	{
	final ClarifaiClient client = new ClarifaiBuilder("b97e7d97f24d47fdbc8789e36ddc1f70").buildSync();
   
   String food1; 
   String food2; 
   String food3; 
   
   ArrayList<String>foodOutput = new ArrayList<String>(); 


	// "predict the contents of an image" (https://github.com/Clarifai/clarifai-java) 
	Model<Concept> foodModel = client.getDefaultModels().foodModel();

	PredictRequest<Concept> request = foodModel.predict().withInputs(
	        ClarifaiInput.forImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrRTfEVXgDmlyDh__nlrx881Ny5WrCxeB6SGPT1WJQtEicRlIFvA")
	    );
	
	List<ClarifaiOutput<Concept>> result = request.executeSync().get();
   
   // right now we will use 0 since we are only making 1 call
  
   food1 = result.get(0).data().get(0).name();
   food2 = result.get(0).data().get(1).name();
   food3 = result.get(0).data().get(2).name(); 
   
   foodOutput.add(food1);
   foodOutput.add(food2);
   foodOutput.add(food3);
   
   System.out.println(foodOutput);

   
	
	}
	
}
