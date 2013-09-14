package edu.unsw.cse.comp9323.group1.controllers.survey;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.QuestionDAO;
import edu.unsw.cse.comp9323.group1.DAOs.SurveyDAO;
import edu.unsw.cse.comp9323.group1.forms.QuestionForm;
import edu.unsw.cse.comp9323.group1.forms.SurveyForm;
import edu.unsw.cse.comp9323.group1.models.Question;
import edu.unsw.cse.comp9323.group1.models.Survey;

@Controller
@RequestMapping("/uni/survey/detail")
public class SurveyDetailController {

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";
	
	@RequestMapping(method = RequestMethod.GET)
	public String getSurvey(@RequestParam("surveyId") int surveyId, Model model) {
		
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
		
		
		
		model.addAttribute("allQuestions", allQuestions);
		model.addAttribute("surveyId", surveyId);
		
		return "surveyDetail";
 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="SHORT_Q") QuestionForm questionForm,BindingResult result){
		if(result.hasErrors()){
			return "surveyCreate";
		}else{
			
			Question question = new Question();
			question.setSurveyId(questionForm.getSurveyId());
			question.setTitle(questionForm.getTitle());
			
			QuestionDAO questionDAO = new QuestionDAO();
			questionDAO.createQuestion(question);
			
			return "redirect:/uni/survey/detail?surveyId="+questionForm.getSurveyId();
		}		
	}
}
