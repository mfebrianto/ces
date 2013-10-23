package edu.unsw.cse.comp9323.group1.controllers;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * 
 * This class is controller class of survey detail.
 * 
 * @author z3402013.group1.comp9323-2013s1
 *
 */

public class RestDB extends Object{
	
	static class OAuth2Response {
		
		/**
		 * This is model for OAuth2Response
		 */
		
	    public OAuth2Response() {
	    }
	    String id;
	    String issued_at;
	    String instance_url;
	    String signature;
	    String access_token;
	  }
	  
	/**
	 * 
	 * This is login information to database.com
	 *
	 */
	
	  public class UserCredentials {
	    String userName = "michaelfebrianto@gmail.com";
	    String password = "C0urs3Evalu@t10n!MzSFewuyg8pDCV1FwvhjJIOr1";
	    String consumerKey = "3MVG9Y6d_Btp4xp5bNQoPmoPqiSlgy5bVm3ApEsx_k.yOSmp.Qu6x.4jQilEtjrbqZyHCn_FSH7ITxvEbm_Tb";
	    String consumerSecret = "3532962961768066117";
	  
	  }
	
	  /**
	   * this will get the gson
	   */
	  
	  public RestDB(){
		  gson = new Gson();
	  }
	  
	  
	private static String OAUTH_ENDPOINT = "/services/oauth2/token";
	  private static String REST_ENDPOINT = "/services/data";
	  UserCredentials userCredentials = new UserCredentials();
	  String restUri;
	  Header oauthHeader;
	  Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
	  Gson gson;
	  OAuth2Response oauth2Response;
	  
	  /**
	   * 
	   * This method will communicate with database.com
	   * 
	   * @param inputStream
	   * @return
	   */
	  
	  private String getBody(InputStream inputStream) {
		    String result = "";
		    try {
		        BufferedReader in = new BufferedReader(
		            new InputStreamReader(inputStream)
		        );
		        String inputLine;
		        while ( (inputLine = in.readLine() ) != null ) {
		          result += inputLine;
		          result += "\n";
		        }
		        in.close();
		    } catch (IOException ioe) {
		      ioe.printStackTrace();
		    }
		    return result;
		  }
	  
	  /**
	   * 
	   * This method will communicate with database.com for get information
	   * 
	   * @param uri
	   * @return
	   */
	  
	  public String restGet(String uri) {
		    String result = "";
		    try {
		      HttpClient httpClient = new DefaultHttpClient();
		      HttpGet httpGet = new HttpGet(uri);
		      httpGet.addHeader(oauthHeader);
		      httpGet.addHeader(prettyPrintHeader);
		      HttpResponse response = httpClient.execute(httpGet);
		      result = getBody( response.getEntity().getContent() );
		    } catch (IOException ioe) {
		      ioe.printStackTrace();
		    } catch (NullPointerException npe) {
		      npe.printStackTrace();
		    }
		    return result;
		  }
	  
	  /**
	   * 
	   * this method will get all university name that already inserted to database.com
	   * 
	   * @param university
	   * @return
	   */
	  
	  public String getUniLoginData(UniversityModel university) {
		  String reponseBody = restGet(restUri + "/sobjects/UniversityAccounts__c/name/");		  
		  return reponseBody;
	  }
	  
	  
	  /**
	   * 
	   * This method will update record in database.com
	   * 
	   * @param uri
	   * @param requestBody
	   * @return
	   */
	  
	  public String restPost(String uri, String requestBody) {
		    String result = null;
		    try {
		      HttpClient httpClient = new DefaultHttpClient();
		      HttpPost httpPost = new HttpPost(uri);
		      httpPost.addHeader(oauthHeader);
		      httpPost.addHeader(prettyPrintHeader);
		      StringEntity body = new StringEntity(requestBody);
		      body.setContentType("application/json");
		      httpPost.setEntity(body);
		      HttpResponse response = httpClient.execute(httpPost);
		      result = getBody( response.getEntity().getContent() );
		    } catch (IOException ioe) {
		      ioe.printStackTrace();
		    } catch (NullPointerException npe) {
		      npe.printStackTrace();
		    }
		    return result;
	  }
	  
	  /**
	   * 
	   * This method will authenticate system to database.com
	   * 
	   * @param userCredentials
	   * @return
	   */
	  
	  public HttpResponse oauth2Login(UserCredentials userCredentials) {
		    HttpResponse response = null;
		    this.userCredentials = userCredentials;
		    String loginHostUri = "https://login.database.com" + OAUTH_ENDPOINT;
		    try {
		      HttpClient httpClient = new DefaultHttpClient();
		      HttpPost httpPost = new HttpPost(loginHostUri);
		      StringBuffer requestBodyText = 
		          new StringBuffer("grant_type=password");
		      requestBodyText.append("&username=");
		      requestBodyText.append(userCredentials.userName);
		      requestBodyText.append("&password=");
		      requestBodyText.append(userCredentials.password);
		      requestBodyText.append("&client_id=");
		      requestBodyText.append(userCredentials.consumerKey);
		      requestBodyText.append("&client_secret=");
		      requestBodyText.append(userCredentials.consumerSecret);
		      StringEntity requestBody = 
		          new StringEntity(requestBodyText.toString());
		      requestBody.setContentType("application/x-www-form-urlencoded");
		      httpPost.setEntity(requestBody);
		      httpPost.addHeader(prettyPrintHeader);
		      response = httpClient.execute(httpPost);
		      if (  response.getStatusLine().getStatusCode() == 200 ) {
		        InputStreamReader inputStream = new InputStreamReader( 
		            response.getEntity().getContent() 
		        );
		        oauth2Response = gson.fromJson( inputStream, 
		            OAuth2Response.class );
		        restUri = oauth2Response.instance_url + REST_ENDPOINT + 
		            "/v" + 24 +".0";
		        System.out.println("\nSuccessfully logged in to instance: " + 
		            restUri);
		        oauthHeader = new BasicHeader("Authorization", "OAuth " + 
		            oauth2Response.access_token);
		      } else {
		    	  HttpEntity entity = response.getEntity();
		    	  String responseString = EntityUtils.toString(entity, "UTF-8");

		    	  System.out.println(responseString);
		        System.out.println("An error has occured.");
		      }
		    } catch (UnsupportedEncodingException uee) {
		      uee.printStackTrace();
		    } catch (IOException ioe) {
		      ioe.printStackTrace();
		    } catch (NullPointerException npe) {
		      npe.printStackTrace();
		    }
		    return response;
		  }
	  
	  /**
	   * 
	   * This method will save university data to database.com
	   * 
	   * @param university
	   * @return
	   */
	  
	  public String postUniLoginData(UniversityModel university) {
		  String reponseBody = restPost(restUri + 
			        "/sobjects/UniversityAccounts__c/", gson.toJson(university)+"\n\n");
		 
		  
		  return reponseBody;
	  }
}
