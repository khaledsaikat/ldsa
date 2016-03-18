package DataGenerator;
import javax.json.*;
import java.io.*;


/**
 * 
 * @author Florian Wenzel
 * @version 1.0
 *	This Class provides a random Data Generator.	
 *
 */
public class DataGenerator {
	private JsonArray jsonArrayOneHundred = null;
	public DataGenerator(){
		
	}
	/**
     * Returns a String with 100 JsonObjects.
     * The JsonObjects are random generated Comments.
     *
     * @return String
     */
	public String giveRandomData(){
		File file = new File("MOCK_DATA.json");
		try(InputStream in = new FileInputStream(file)){
			 JsonReader jr = Json.createReader(in);
			 JsonArray jsonArray = jr.readArray();
			 jr.close();
			 JsonArrayBuilder jab = Json.createArrayBuilder();
			 for (int i = 0; i < 100; i++){
			 int rnd = 0 + (int)(Math.random() * 999); 
			 JsonObject added = jsonArray.getJsonObject(rnd);
			 jab.add(added);
			 }
			 jsonArrayOneHundred=jab.build();
			 			
		}catch (IOException e){
			e.printStackTrace();
			
		}
		return jsonArrayOneHundred.toString();
	}

}
