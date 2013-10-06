package edu.unsw.cse.comp9323.group1.DAOs;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.unsw.cse.comp9323.group1.models.Course;
import edu.unsw.cse.comp9323.group1.models.Enrolment;



public class CourseDAO {

	protected static RestClient client = new RestClient();
	
	public List<Course> getAllCourses(){
		
		List<Course> listOfCourses = new ArrayList<Course>();
		
		try {
			
			//TODO: need to put in DAO
			client.oauth2Login( client.getUserCredentials());
			String response = client.restGet("/query/?q=SELECT+id__c+,+name__c+,+uni__c+,+categories__c+FROM+course_detail__c");
			
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
		    	course.setUni((String)jsonAttribute.get("uni__c"));
		    	course.setCategories((String)jsonAttribute.get("categories__c"));
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
	
	public List<Course> getAllIDNameCourses(){
		
		List<Course> listOfCourses = new ArrayList<Course>();
		
		try {
			
			//TODO: need to put in DAO
			client.oauth2Login( client.getUserCredentials());
			String response = client.restGet("/query/?q=SELECT+id__c+,+name__c+FROM+course_detail__c");
			
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
		    	course.setId(jsonAttribute.get("id__c").toString());
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
	
	
	public List<Course> getAllIDNameCoursesBasedOnMultipleCourseId(List<Enrolment> enrolmentList){
		
		List<Course> listOfCourses = new ArrayList<Course>();
		
		try {
			
			//TODO: need to put in DAO
			client.oauth2Login( client.getUserCredentials());
			StringBuffer urlBuffer = new StringBuffer();
			urlBuffer.append("/query/?q=SELECT+id__c+,+name__c+FROM+course_detail__c+where+id__c+in+(");
			int iteratorNumber=0;
			Iterator<Enrolment> enrolmentListItr = enrolmentList.iterator();
			while(enrolmentListItr.hasNext()){
				
				if(iteratorNumber==0){
					urlBuffer.append("'"+Integer.toString(Integer.parseInt(enrolmentListItr.next().getC_id()))+"'");
				}
				else{
					urlBuffer.append(",'"+Integer.toString(Integer.parseInt(enrolmentListItr.next().getC_id()))+"'");
				}
				iteratorNumber++;
				
			}
			urlBuffer.append(")");
			System.out.println(">>>>>>"+urlBuffer.toString());
			String response = client.restGet(urlBuffer.toString());
			
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
		    	course.setId(jsonAttribute.get("id__c").toString());
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
	
	public Course getCourseByID(String id) throws UnsupportedEncodingException{
		
		 Course course = new Course();
		
		try {
			
			//TODO: need to put in DAO
			client.oauth2Login( client.getUserCredentials());
			String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT id__c, name__c, description__c, " +
					"start_date__c, length__c, uni__c, categories__c FROM course_detail__c " +
					"WHERE id__c = ","UTF-8") 
					+ "\'"+id+"\'";
			
			String response = client.restGet(newRestUri);
			
			JSONParser parser = new JSONParser();
		    Object obj = parser.parse(response);
		    JSONObject jsonObject = (JSONObject) obj;
		      
		    JSONArray listOfRecords = (JSONArray) jsonObject.get("records");
		    
		   
		    
		    if(listOfRecords.size() > 0){
		    	JSONObject jsonAttribute = (JSONObject) listOfRecords.get(0);
		    
		    	course.setId(jsonAttribute.get("id__c").toString());
		    	course.setName((String)jsonAttribute.get("name__c"));
		    	course.setDescription(jsonAttribute.get("description__c").toString());
		    	course.setLength(jsonAttribute.get("length__c").toString());
		    	course.setStartDate(jsonAttribute.get("start_date__c").toString());
		    }
		    
		    
		    
		    
		    
			
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return course;
	
	}

	public Course getCourseByName(String courseName) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub 
		Course course = new Course();
		
		try {
			
			//TODO: need to put in DAO
			client.oauth2Login( client.getUserCredentials());
			String newRestUri = "/query/?q=" + URLEncoder.encode("SELECT id__c, name__c, description__c, " +
					"start_date__c, length__c, uni__c, categories__c FROM course_detail__c " +
					"WHERE name__c = '"+courseName.replace("'", "\\'")+"'","UTF-8") ;
			
			String response = client.restGet(newRestUri);
			System.out.println(response);
			JSONParser parser = new JSONParser();
		    Object obj = parser.parse(response);
		    JSONObject jsonObject = (JSONObject) obj;
		      
		    JSONArray listOfRecords = (JSONArray) jsonObject.get("records");
		    
		   
		    
		    if(listOfRecords.size() > 0){
		    	JSONObject jsonAttribute = (JSONObject) listOfRecords.get(0);
		    
		    	course.setId(jsonAttribute.get("id__c").toString());
		    	course.setName((String)jsonAttribute.get("name__c"));
		    	course.setDescription(jsonAttribute.get("description__c").toString());
		    	course.setLength(jsonAttribute.get("length__c").toString());
		    	course.setStartDate(jsonAttribute.get("start_date__c").toString());
		    }
		    
		    
		    
		    
		    
			
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return course;
	}


}
