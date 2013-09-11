package edu.unsw.cse.comp9323.group1.DAOs;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.unsw.cse.comp9323.group1.models.Course;



public class CourseDAO {

	protected static RestClient client = new RestClient();
	
	public List<Course> getAllCourses(){
		
		List<Course> listOfCourses = new ArrayList<Course>();
		
		try {
			
			//TODO: need to put in DAO
			client.oauth2Login( client.getUserCredentials());
			String response = client.restGet("/query/?q=SELECT+name__c+FROM+course_detail__c");
			
			JSONParser parser = new JSONParser();
		    Object obj = parser.parse(response);
		    JSONObject jsonObject = (JSONObject) obj;
		      
		    JSONArray listOfRecords = (JSONArray) jsonObject.get("records");
		    
		    @SuppressWarnings("unchecked")
		    Iterator<JSONObject> iteratorRecords = listOfRecords.iterator();
		    while(iteratorRecords.hasNext()){
		    	JSONObject jsonAttribute = (JSONObject)iteratorRecords.next();
		    	Course course = new Course();
		    	course.setName((String)jsonAttribute.get("name__c"));
		    	listOfCourses.add(course);
		    }
		    
		    
			
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return listOfCourses;
	
	}
}
