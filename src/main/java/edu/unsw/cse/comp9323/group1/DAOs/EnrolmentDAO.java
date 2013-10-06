package edu.unsw.cse.comp9323.group1.DAOs;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.unsw.cse.comp9323.group1.Tools.RestDelete;
import edu.unsw.cse.comp9323.group1.models.Enrolment;

public class EnrolmentDAO {
	protected static RestClient client = new RestClient();
	
	public boolean updateEnrollment(String studentId, String courseId, String command) throws URISyntaxException, HttpException, UnsupportedEncodingException {
		RestClient client = new RestClient();
	  	client.oauth2Login( client.getUserCredentials());
		String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT s_id__c, c_id__c, id__c  FROM enrollment__c WHERE s_id__c = '"+studentId+"' and c_id__c = " + courseId,"UTF-8");
		
		String response = client.restGet(newRestUri);
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(response);
		JsonObject  jobject = jsonElement.getAsJsonObject();
		
		int total = jobject.get("totalSize").getAsInt();
		if (command.equals("Delete")) {
			JsonArray jarray = jobject.getAsJsonArray("records");
	    	JsonObject jobjectFirstRecord = jarray.get(0).getAsJsonObject();
	    	String url = jobjectFirstRecord.getAsJsonObject("attributes").get("url").getAsString();
	 	    //System.out.println("url : " + url);
	 	    String[] urlArr = url.split("/");
	 	    String id = Arrays.asList(urlArr).get(urlArr.length-1);
	 	    //System.out.println("id : " + id);
	 	    
	 	    RestDelete restDelete = new RestDelete();
	 	    restDelete.delete(newRestUri, client.oauthHeader);
	 	    System.out.println("id : " + id + " is deleted");
	 	    return true;
		}
		else {
			if (total == 0) {
				JsonObject a = new JsonObject();
		    	a.addProperty("s_id__c", studentId);
		    	a.addProperty("c_id__c", courseId);
		    	response = client.restPost("/sobjects/enrollment__c/", a.toString());
		    	return true;
			}
		}
		return false;
	}
	
	public List<Enrolment> getEnrollmentBasedOnStudentId(String studentId) throws URISyntaxException, HttpException, UnsupportedEncodingException {
		List<Enrolment> enrolments = new ArrayList<Enrolment>();
		RestClient client = new RestClient();
	  	client.oauth2Login( client.getUserCredentials());
		String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT s_id__c, c_id__c, id__c  FROM enrollment__c WHERE s_id__c = '"+studentId+"'","UTF-8");
		
		String response = client.restGet(newRestUri);
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(response);
		JsonObject  jobject = jsonElement.getAsJsonObject();
				
		JsonArray records = (JsonArray) jobject.get("records");
		Iterator<JsonElement> recordsItr = records.iterator();
		while(recordsItr.hasNext()){
			Enrolment enrolment = new Enrolment();
			JsonElement recordJsonElement = recordsItr.next();
			JsonObject recordJsonObject = recordJsonElement.getAsJsonObject();
			enrolment.setC_id(Integer.toString(recordJsonObject.get("c_id__c").getAsInt()));
			enrolment.setS_id(Integer.toString(recordJsonObject.get("s_id__c").getAsInt()));
			enrolments.add(enrolment);
		}
		
		return enrolments;
		
	}
}
