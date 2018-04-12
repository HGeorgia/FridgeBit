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

		
		private final ClarifaiClient client = new ClarifaiBuilder("b97e7d97f24d47fdbc8789e36ddc1f70").buildSync();
   
		// variables to hold the values of the top 3 items recognized in the image 
		private String food1; 
		private String food2; 
		private String food3; 
		
		public Photo ()
		/** No-arg constructor 
		 *  @param 
		 */
		{
			food1="water"; 
			food2=null;
			food3=null;
		}
		
		public String getFood1() {
			return food1;
		}

		public void setFood1(String food1) {
			this.food1 = food1;
		}

		public String getFood2() {
			return food2;
		}

		public void setFood2(String food2) {
			this.food2 = food2;
		}

		public String getFood3() {
			return food3;
		}

		public void setFood3(String food3) {
			this.food3 = food3;
		}


		public ArrayList<String> fromFridge() {
			
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
			
			return foodOutput;
		}



	
}
