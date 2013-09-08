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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unsw.cse.comp9323.group1.models.Survey;


@Controller
@RequestMapping("/uni/survey")
public class SurveyController {
	
//	private String oauth_callback_confirmed;
//	private String oauth_token;
//	private String oauth_token_secret;
//	private int xoauth_token_ttl;

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllAvailableSurvey(ModelMap model) {
		
		List<Survey> allSurveys = new ArrayList<Survey>();
		
		/*
		 *oauth, doesn't need it, because need to fill in pop-uppages. 
		 */
		//authentication();
		
		String url = "https://restapi.surveygizmo.com/head/survey";
		 
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
		      
		      JSONParser parser = new JSONParser();
		      Object obj = parser.parse(responseBodyStr);
		      JSONObject jsonObject = (JSONObject) obj ;
		      
		      JSONArray listOfSurvey = (JSONArray) jsonObject.get("data");
		      
		      /*
		       * put all results to list
		       */
		      @SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = listOfSurvey.iterator();
		      while (iterator.hasNext()) {
		    	  JSONObject jsonObject2 = (JSONObject) iterator.next();
		    	  Survey survey = new Survey((String)jsonObject2.get("id"),
		    			  (String)jsonObject2.get("title"),
		    			  (String)jsonObject2.get("created_on"));
		    	  allSurveys.add(survey); 
		    	  
//		    	  System.out.println((String)jsonObject2.get("id"));
//		    	  System.out.println((String)jsonObject2.get("title"));
//		    	  System.out.println((String)jsonObject2.get("created_on"));
		    	  
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
		
		
		
		model.addAttribute("allSurveys", allSurveys);
		
		return "survey";
 
	}
	
//	private void authentication(){
//		authentication1();
//	}
//	
//	private void authentication1(){
//		//String url = "https://restapi.surveygizmo.com/v2/survey?user:md5=michaelfebrianto@gmail.com:md5C0urs3Evalu@t10n!";
//		String url = "http://restapi.surveygizmo.com/head/oauth/request_token";
//		 
//		HttpClient client = new HttpClient();
//		
//		GetMethod method = new GetMethod(url);
//		
//		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
//	    		new DefaultHttpMethodRetryHandler(3, false));
//		
//		method.setQueryString(new NameValuePair[] { 
//			    new NameValuePair("oauth_consumer_key", "0f7fcaa7c7c30219b3b6d3c82a7e961b0522b2eb5"),
//			    new NameValuePair("oauth_nonce", "1231qwe123"),
//			    new NameValuePair("oauth_signature_method", "PLAINTEXT"),
//			    new NameValuePair("oauth_signature", "PLAINTEXT"),
//			    new NameValuePair("oauth_version", "1.0")
//			});
//		
//		 try {
//		      // Execute the method.
//		      int statusCode = client.executeMethod(method);
//
//		      if (statusCode != HttpStatus.SC_OK) {
//		        System.err.println("Method failed: " + method.getStatusLine());
//		      }
//
//		      // Read the response body.
//		      byte[] responseBody = method.getResponseBody();
//
//		      // Deal with the response.
//		      // Use caution: ensure correct character encoding and is not binary data
//		      String responseBodyStr = new String(responseBody);
//		      
//		      
//		      
//		      String[] splited =  responseBodyStr.split("&");
//		      
//		      //get oauth_callback_confirmed
//		      String[] oauth_callback_confirmed_ori = splited[0].split("=");
//		      this.oauth_callback_confirmed = oauth_callback_confirmed_ori[1];
//		      //get oauth_token
//		      String[] oauth_token_ori = splited[1].split("=");
//		      this.oauth_token = oauth_token_ori[1];
//		      //get oauth_token_secret
//		      String[] oauth_token_secret_ori = splited[2].split("=");
//		      this.oauth_token_secret = oauth_token_secret_ori[1];
//		      //get oauth_token_secret
//		      String[] xoauth_token_ttl_ori = splited[3].split("=");
//		      this.xoauth_token_ttl = Integer.parseInt(xoauth_token_ttl_ori[1]);   
//		      
//
//		    } catch (HttpException e) {
//		      System.err.println("Fatal protocol violation: " + e.getMessage());
//		      e.printStackTrace();
//		    } catch (IOException e) {
//		      System.err.println("Fatal transport error: " + e.getMessage());
//		      e.printStackTrace();
//		    } finally {
//		      // Release the connection.
//		      method.releaseConnection();
//		    }  		
//	}
}
