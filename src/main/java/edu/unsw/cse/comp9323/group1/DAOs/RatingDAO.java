package edu.unsw.cse.comp9323.group1.DAOs;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.http.HttpException;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.unsw.cse.comp9323.group1.Tools.InitializeREST;
import edu.unsw.cse.comp9323.group1.Tools.RestPatch;


public class RatingDAO {
	protected static RestClient client = new RestClient();
	
	public boolean updateRating(int studentID, String courseName, int rating) throws UnsupportedEncodingException{
		
		try {
			
			//TODO: need to put in DAO
			client.oauth2Login( client.getUserCredentials());
			
			String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT studentID__c, courseName__c,courseRating__c  FROM StudentUniRating__c WHERE studentID__c = '"+studentID+"' and courseName__c = '" + courseName +"'","UTF-8");
			
			String response = client.restGet(newRestUri);
			System.out.println(response);
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(response);
			JsonObject  jobject = jsonElement.getAsJsonObject();
			
			int total = jobject.get("totalSize").getAsInt();
		    
			if (total == 0){
				//do insert
				//insert to database.com
				System.out.println("Inserting.....");
    	    	JsonObject newRatingJSONObj = new JsonObject();
    	    	newRatingJSONObj.addProperty("studentID__c", studentID);
    	    	newRatingJSONObj.addProperty("courseName__c", courseName);
    	    	newRatingJSONObj.addProperty("courseRating__c", rating);
    	    	
    	    	
    	    	
    	    	client.oauth2Login( client.getUserCredentials());
    	    	response = client.restPost("/sobjects/StudentUniRating__c/", newRatingJSONObj.toString());
    	    	System.out.println(response);
    	    	
    	    	
			}else if(total > 0){
				
				System.out.println("Updating.....");
				JsonArray jarray = jobject.getAsJsonArray("records");
    	    	System.out.println("size of Array :" + jarray.size());
    	    	
        	    JsonObject jobjectFirstRecord = jarray.get(0).getAsJsonObject();
        	    
    	    	String url = jobjectFirstRecord.getAsJsonObject("attributes").get("url").getAsString();
         	    System.out.println("url : " + url);
         	    String[] urlArr = url.split("/");
         	    
         	   String id = Arrays.asList(urlArr).get(urlArr.length-1);
         	   System.out.println("ID : " + id);
         	   
				//do update
         	  //InitializeREST initREST = new InitializeREST();
         	 
         	  //newRestUri = initREST.getRestUri() +"/sobjects/StudentUniRating__c/" +id;
         	    //System.out.println("newRestUri : " + newRestUri);
				JsonObject newRatingJSONObj = new JsonObject();
    	    	newRatingJSONObj.addProperty("studentID__c", studentID);
    	    	newRatingJSONObj.addProperty("courseName__c", courseName);
    	    	newRatingJSONObj.addProperty("courseRating__c", rating);
    	    	
    	    	
    	    	
    	    	client.oauth2Login( client.getUserCredentials());
    	    	System.out.println("/sobjects/StudentUniRating__c/"+id);
    	    	response = client.restPatch("/sobjects/StudentUniRating__c/"+id, newRatingJSONObj.toString());
    	    	
			}
		    
		    
			return true;
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		} catch (HttpException e) {
			e.printStackTrace();
			return false;
		}
	
	
	}

}
