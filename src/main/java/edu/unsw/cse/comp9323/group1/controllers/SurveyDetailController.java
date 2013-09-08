package edu.unsw.cse.comp9323.group1.controllers;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.models.Question;


@Controller
@RequestMapping("/uni/survey")
public class SurveyDetailController {
	

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String getAllAvailableSurvey(@RequestParam("surveyId") int surveyId, Model model) {
		
		List<Question> allQuestions = new ArrayList<Question>();
		
		/*
		 *oauth, doesn't need it, because need to fill in pop-uppages. 
		 */
		//authentication();
		
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
		
		
		
		model.addAttribute("allQuestions", allQuestions);
		
		return "surveyDetail";
 
	}
	

}
