import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.*; 

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.request.model.PredictRequest;
import clarifai2.dto.input.ClarifaiFileImage;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;



public class Photo
{

		
		
		
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
		
		public Photo(String food1, String food2, String food3)
		{
			this.food1 = food1;
			this.food2 = food2;
			this.food3 = food3;
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
			
			
			final ClarifaiClient client = new ClarifaiBuilder("a48bd8cc3ec34636be6f3426b4872119").buildSync();
			ArrayList<String>foodOutput = new ArrayList<String>();
			HashSet<String> temp = new HashSet<String>();
			// "predict the contents of an image" (https://github.com/Clarifai/clarifai-java) 
			Model<Concept> foodModel = client.getDefaultModels().foodModel();
			ArrayList<byte[]> imageArrays = new ArrayList<byte[]>();
			ImageSplit.run();
			BufferedImage clarifaiImg;
			
			try 
			{
			for(int i = 0; i <= 8; i++) 
			{
			clarifaiImg = ImageIO.read(new File(
					"img" + i + ".jpg"
					));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(clarifaiImg, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			imageArrays.add(imageInByte);
			baos.close();
			
			PredictRequest<Concept> request = foodModel
					.predict()
					.withInputs
					(
							ClarifaiInput.forImage(imageArrays.get(i))
					);

			
			List<ClarifaiOutput<Concept>> result = request.executeSync().get();
	   
			// right now we will use 0 since we are only making 1 call
	   
			food1 = result.get(0).data().get(0).name();
			//setFood1(food1);
			food2 = result.get(0).data().get(1).name();
			//setFood2(food2);
			food3 = result.get(0).data().get(2).name();
			//setFood3(food3);
			
			if(result.get(0).data().get(0).value() > 0.9)
				foodOutput.add(food1);
	   
		
			
			}
			}
			catch (IOException e)
			{
				System.out.println("Error with image input byte stream");
			}
			Collections.sort(foodOutput);
			temp.addAll(foodOutput);
			foodOutput.clear();
			foodOutput.addAll(temp);
			setFood1(foodOutput.get(0).toString());
			setFood2(foodOutput.get(1).toString());
			setFood3(foodOutput.get(2).toString());
			return foodOutput;
		}
			

		@Override
		public String toString() 
		{
			return "Photo [food1=" + food1 + ", food2=" + food2 + ", food3=" + food3 + "]";
		}
}



	
