package edu.unsw.cse.comp9323.group1.DAOs;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.unsw.cse.comp9323.group1.Tools.InitializeREST;
import edu.unsw.cse.comp9323.group1.Tools.RestPatch;

import edu.unsw.cse.comp9323.group1.models.CourseAverageRating;

/**
 * 
 * This class will be automatically invoked when user entry the rating for courses
 * 
 * @author group1.comp9323-2013s1
 *
 */

public class CourseAverageRatingDAO {
	protected static RestClient client = new RestClient();
	
	/**
	 * This method will get user session
	 * 
	 * @throws URISyntaxException
	 * @throws HttpException
	 */
	
	public CourseAverageRatingDAO() throws URISyntaxException, HttpException {
		client.oauth2Login( client.getUserCredentials());
	}
	
	/**
	 * 
	 * This method will connect to database.com,
	 * then get all existing rating.
	 * 
	 * @return
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	
	public List<CourseAverageRating> getAllCourseAverageRating() throws URISyntaxException, HttpException, UnsupportedEncodingException, ParseException {
		List<CourseAverageRating> result = new ArrayList<CourseAverageRating>();
		
//		client.oauth2Login( client.getUserCredentials());
		String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT RateCategory__c, courseName__c, rating__c FROM course_average_rating__c","UTF-8");
		
		String response = client.restGet(newRestUri);
		JSONParser parser = new JSONParser();
	    Object obj = parser.parse(response);
	    JSONObject jsonObject = (JSONObject) obj;
	    
	    JSONArray listOfRecords = (JSONArray) jsonObject.get("records");
	    
	    @SuppressWarnings("unchecked")
	    Iterator<JSONObject> iteratorRecords = listOfRecords.iterator();
	    while(iteratorRecords.hasNext()){
	    	JSONObject jsonAttribute = (JSONObject)iteratorRecords.next();
	    	CourseAverageRating rate = new CourseAverageRating();
	    	rate.setRateCategory((String)jsonAttribute.get("RateCategory__c"));
	    	rate.setCourse_name((String)jsonAttribute.get("courseName__c"));
	    	rate.setRating((Double)jsonAttribute.get("rating__c"));
	    	result.add(rate);
	    }
	    
		return result;
	}
	
	/**
	 * 
	 * This method will get existing rating based on course id
	 * then calculate the average based on the new rating.
	 * 
	 * @param category
	 * @return
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	
	public List<CourseAverageRating> getAllCourseAverageRatingByCategory(String category) throws URISyntaxException, HttpException, UnsupportedEncodingException, ParseException {
		List<CourseAverageRating> result = new ArrayList<CourseAverageRating>();
		
//		client.oauth2Login( client.getUserCredentials());
		String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT RateCategory__c, courseName__c, " +
				"rating__c FROM course_average_rating__c WHERE RateCategory__c = '" + category + "'","UTF-8");
		
		String response = client.restGet(newRestUri);
		JSONParser parser = new JSONParser();
	    Object obj = parser.parse(response);
	    JSONObject jsonObject = (JSONObject) obj;
	    
	    JSONArray listOfRecords = (JSONArray) jsonObject.get("records");
	    
	    @SuppressWarnings("unchecked")
	    Iterator<JSONObject> iteratorRecords = listOfRecords.iterator();
	    while(iteratorRecords.hasNext()){
	    	JSONObject jsonAttribute = (JSONObject)iteratorRecords.next();
	    	CourseAverageRating rate = new CourseAverageRating();
	    	rate.setRateCategory((String)jsonAttribute.get("RateCategory__c"));
	    	rate.setCourse_name((String)jsonAttribute.get("courseName__c"));
	    	rate.setRating((Double)jsonAttribute.get("rating__c"));
	    	result.add(rate);
	    }
	    
		return result;
	}
	
	/**
	 * 
	 * This search function is using this method to get the course
	 * with spesific rating based on the range.
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	public List<CourseAverageRating> getCoursesByRating(int min, int max) throws URISyntaxException, HttpException, UnsupportedEncodingException, ParseException {
		List<CourseAverageRating> result = new ArrayList<CourseAverageRating>();
		
//		client.oauth2Login( client.getUserCredentials());
		String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT RateCategory__c, courseName__c, rating__c " +
				"FROM course_average_rating__c" +
				"WHERE rating__c <= " + max + " AND rating__c >= " + min,"UTF-8");
		String response = client.restGet(newRestUri);

		JSONParser parser = new JSONParser();
	    Object obj = parser.parse(response);
	    JSONObject jsonObject = (JSONObject) obj;
	      
	    JSONArray listOfRecords = (JSONArray) jsonObject.get("records");
	    
	    @SuppressWarnings("unchecked")
	    Iterator<JSONObject> iteratorRecords = listOfRecords.iterator();
	    while(iteratorRecords.hasNext()){
	    	JSONObject jsonAttribute = (JSONObject)iteratorRecords.next();
	    	CourseAverageRating rate = new CourseAverageRating();
	    	rate.setRateCategory((String)jsonAttribute.get("RateCategory__c"));
	    	rate.setCourse_name((String)jsonAttribute.get("coursename__c"));
	    	rate.setRating((Double)jsonAttribute.get("rating__c"));
	    	result.add(rate);
	    }
	    
		return result;
	}
	
	/**
	 * 
	 * This method will get the course rating based on course name
	 * 
	 * @param course_name
	 * @return
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	public double getCourseRatingByName(String course_name) throws URISyntaxException, HttpException, UnsupportedEncodingException, ParseException {
//		client.oauth2Login( client.getUserCredentials());
		String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT rating__c " +
				"FROM course_average_rating__c WHERE courseName__c='" + course_name + "'","UTF-8");
		
		String response = client.restGet(newRestUri);
		JSONParser parser = new JSONParser();
	    Object obj = parser.parse(response);
	    JSONObject jsonObject = (JSONObject) obj;
	    
	    JSONArray listOfRecords = (JSONArray) jsonObject.get("records");
	    if(listOfRecords.size() > 0){
	    	JSONObject jsonAttribute = (JSONObject) listOfRecords.get(0);
	    	return Double.parseDouble(jsonAttribute.get("rating__c").toString());
	    }
		
		return 0;
	}
	
	/**
	 * 
	 * This is the main method of this class to test functional of this class
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws ParseException
	 */
	
	public void main() throws UnsupportedEncodingException, URISyntaxException, HttpException, ParseException {
		CourseAverageRatingDAO carDao = new CourseAverageRatingDAO();
		carDao.getCoursesByRating(0, 5);
	}
}
