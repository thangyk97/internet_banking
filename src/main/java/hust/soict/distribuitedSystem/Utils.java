package hust.soict.distribuitedSystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Utils {
	public static String creatResponseJson(String title, Object object) {
		
		JsonObject response = new JsonObject();
		response.addProperty("title", title);
		
		Gson gson = new GsonBuilder().create();
		JsonElement element = gson.toJsonTree(object,object.getClass());
		response.add("content", element);
		
		return gson.toJson(response);
	}
}
