package edu.unsw.cse.comp9323.group1.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.unsw.cse.comp9323.group1.DAOs.QuestionDAO;
import edu.unsw.cse.comp9323.group1.models.Question;



@Controller
@RequestMapping("/reporting")
public class SurveyReportingController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@RequestParam("surveyId") String surveyId, ModelMap model) {
		String url = "https://restapi.surveygizmo.com/head/survey/"+surveyId+"/surveystatistic";
		String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";

		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		method.setQueryString(new NameValuePair[] {
				new NameValuePair("user:pass", userPass),
				new NameValuePair("_method", "Get")});
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			String responseBodyStr = new String(responseBody);

			

			 JSONParser parser = new JSONParser();
			 Object obj = parser.parse(responseBodyStr);
			 JSONObject jsonObject = (JSONObject) obj;
			
			 model.addAttribute("reportData", jsonObject.toJSONString());
			 model.addAttribute("surveyID", surveyId);
			 
			 
			// System.out.println(jsonObject.toJSONString());
			 QuestionDAO questionDAO = new QuestionDAO();
			 List<Question> questionList = questionDAO.getAllQuestion(Integer.parseInt(surveyId));
			 questionList.remove(questionList.size()-1);
			 model.addAttribute("questionList", Lists.reverse(questionList));
			 model.addAttribute("numOfQuestion", questionList.size());
			 
			 Hashtable<String, Hashtable<String, Integer>> answerTextTable = new Hashtable<String, Hashtable<String, Integer>>();
			 for (Question question : questionList) {
				if (question.getSubtype().equals("textbox") || question.getSubtype().equals("essay")) {
					answerTextTable.put("[question("+question.getId()+")]", getTextResponse(Integer.parseInt(surveyId), "[question("+question.getId()+")]"));
					
				}
			 }
			 model.addAttribute("textTable", answerTextTable);
			 
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
			
		}
		return "reporting";
		
		
	}
	
	public Hashtable<String, Integer> getTextResponse(int surveyId, String questionId) {
		String url = "https://restapi.surveygizmo.com/head/survey/"+surveyId+"/surveyresponse";
		String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";

		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		method.setQueryString(new NameValuePair[] {
				new NameValuePair("user:pass", userPass),
				new NameValuePair("_method", "Get")});
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

			

			JsonElement jelement = new JsonParser().parse(responseBodyStr);
		    JsonObject  jobject = jelement.getAsJsonObject();
		    JsonArray data = jobject.getAsJsonArray("data");
			Iterator<JsonElement> dataIterator =  data.iterator(); 
			Hashtable<String,Integer> stringTable = new Hashtable<String,Integer>();
		    while (dataIterator.hasNext()) {
				JsonObject element = dataIterator.next().getAsJsonObject();
				if (element.get(questionId) != null) {
					String answer = element.get(questionId).getAsString();
					if (Strings.isNullOrEmpty(answer)) {
						continue;
					}
					if (stringTable.get(answer) == null) {
						stringTable.put(answer, 1);
					}else{
						stringTable.put(answer, stringTable.get(answer)+1);
					}
					
				}
				
			}
		    
		    /*for (String string : stringSet) {
				System.out.println(string);
			}*/
			 
			return stringTable;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return null;
	}

}
