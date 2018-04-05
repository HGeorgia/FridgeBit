import java.util.*;

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

	// "predict the contents of an image" (https://github.com/Clarifai/clarifai-java) 
	Model<Concept> generalModel = client.getDefaultModels().generalModel();

	PredictRequest<Concept> request = generalModel.predict().withInputs(
	        ClarifaiInput.forImage("https://samples.clarifai.com/metro-north.jpg")
	    );
	List<ClarifaiOutput<Concept>> result = request.executeSync().get();
	
	System.out.println(result.toString());
	}
	
}
