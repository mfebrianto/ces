package edu.unsw.cse.comp9323.group1.DAOs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.unsw.cse.comp9323.group1.models.Course;
import edu.unsw.cse.comp9323.group1.models.Survey;

public class SurveyDAO {

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";

	public String createSurvey(Survey survey) {

		String surveyId=null;
		
		System.out.println("Title of the survey :" + survey.getTitle());
		String url = "https://restapi.surveygizmo.com/head/survey";

		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		method.setQueryString(new NameValuePair[] {
				new NameValuePair("user:pass", userPass),
				new NameValuePair("_method", "PUT"),
				new NameValuePair("title", survey.getTitle()),
				new NameValuePair("type", "survey") });

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			String responseBodyStr = new String(responseBody);

			

			 JSONParser parser = new JSONParser();
			 Object obj = parser.parse(responseBodyStr);
			 JSONObject jsonObject = (JSONObject) obj;
			
			 jsonObject = (JSONObject) jsonObject.get("data");
			 surveyId = (String) jsonObject.get("id");
			 
			 System.out.println("new surveyId :"+surveyId);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

		return surveyId;
	}

	public void deleteSurvey(int surveyId) {

		System.out.println("ID of the survey that will be deleted: " + Integer.toString(surveyId));
		String url = "https://restapi.surveygizmo.com/head/survey/" + Integer.toString(surveyId);

		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		method.setQueryString(new NameValuePair[] {
				new NameValuePair("user:pass", userPass),
				new NameValuePair("_method", "DELETE"), });

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			String responseBodyStr = new String(responseBody);

			System.out.println(responseBodyStr);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

	}
	
	
	public void saveSurvey(Survey survey){
		
		RestClient client = new RestClient();
		
		try {
			
			//TODO: need to put in DAO
			JSONObject newReview = new JSONObject();
			
			client.oauth2Login( client.getUserCredentials());
			newReview.put("id__c", survey.getId());
			newReview.put("courseId__c", survey.getCourseId());
			client.oauth2Login( client.getUserCredentials());
			String response = client.restPost("/sobjects/Review__c/", newReview.toString());
			
			System.out.println(">>>>>"+response);
		    
		    
			
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		}
	
	}
}
