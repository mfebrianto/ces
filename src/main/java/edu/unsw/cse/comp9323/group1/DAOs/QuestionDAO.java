package edu.unsw.cse.comp9323.group1.DAOs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.unsw.cse.comp9323.group1.models.Question;

public class QuestionDAO {

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";

	public void createQuestion(Question question) {

		
		String url = "https://restapi.surveygizmo.com/head/survey/"+question.getSurveyId()+"/surveypage/1/surveyquestion";

		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		method.setQueryString(new NameValuePair[] {
				new NameValuePair("user:pass", userPass),
				new NameValuePair("_method", "PUT"),
				new NameValuePair("surveypage", "1"),
				new NameValuePair("type", "text"),
				new NameValuePair("title", question.getTitle())});

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
	
	public List<Question> getAllQuestion(int surveyId){
		
		List<Question> allQuestions = new ArrayList<Question>();
		
		String url = "https://restapi.surveygizmo.com/head/survey/"+surveyId;
		 
		HttpClient client = new HttpClient();
		
		GetMethod method = new GetMethod(url);
		
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		new DefaultHttpMethodRetryHandler(3, false));
		
		method.setQueryString(new NameValuePair[] { 
			    new NameValuePair("user:pass", userPass)
			});
		
		 try {
		      // Execute the method.
		      int statusCode = client.executeMethod(method);

		      if (statusCode != HttpStatus.SC_OK) {
		        System.err.println("Method failed: " + method.getStatusLine());
		      }

		      // Read the response body.
		      byte[] responseBody = method.getResponseBody();

		      // Deal with the response.
		      // Use caution: ensure correct character encoding and is not binary data
		      String responseBodyStr = new String(responseBody);
		      
		      
//		      System.out.println(responseBodyStr);
		      

		      JSONParser parser = new JSONParser();
		      Object obj = parser.parse(responseBodyStr);
		      JSONObject jsonObject = (JSONObject) obj;
		      
		      JSONObject jsonObjectData = (JSONObject) jsonObject.get("data");
		      JSONArray listOfPages = (JSONArray) jsonObjectData.get("pages");
		      
		      @SuppressWarnings("unchecked")
		      Iterator<JSONObject> iteratorPages = listOfPages.iterator();
		      while(iteratorPages.hasNext()){
		    	  JSONObject jsonPage = (JSONObject) iteratorPages.next();
		    	  JSONArray listOfQuestions = (JSONArray) jsonPage.get("questions");
		    	  
		    	  @SuppressWarnings("unchecked")
		    	  Iterator<JSONObject> iteratorQuestions = listOfQuestions.iterator();
		    	  while (iteratorQuestions.hasNext()) {
		    		  JSONObject jsonQuestion = (JSONObject) iteratorQuestions.next();
		    		  
		    		  JSONObject jsonQuestionEnglish = (JSONObject)jsonQuestion.get("title");
		    		  
		    		  
		    		  Question question = new Question((Long)jsonQuestion.get("id"),
		    				  (String)jsonQuestion.get("_subtype"),
		    				  (String)jsonQuestionEnglish.get("English"));
		    		  
		    
		    		  
			    	  allQuestions.add(question);
		    	  }
		      }

		    } catch (HttpException e) {
		      System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		    } catch (IOException e) {
		      System.err.println("Fatal transport error: " + e.getMessage());
		      e.printStackTrace();
		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		      // Release the connection.
		      method.releaseConnection();
		    }  
		 
		 return allQuestions;
	}

}
