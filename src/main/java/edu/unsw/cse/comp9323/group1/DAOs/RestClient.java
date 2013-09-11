package edu.unsw.cse.comp9323.group1.DAOs;

//import java.awt.Desktop;
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.net.URI;
import java.net.URISyntaxException;
//import java.net.URLEncoder;
//import java.util.ArrayList;


import org.apache.http.Header;
//import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.util.EntityUtils;


import com.google.gson.Gson;
//import org.json.*;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.json.simple.JSONObject;

import edu.unsw.cse.comp9323.group1.controllers.CourseExtraction;

//Vu Quang Hoa - Matilda

public class RestClient extends Object {
  private static BufferedReader reader = 
      new BufferedReader(new InputStreamReader(System.in));
  private static String OAUTH_ENDPOINT = "/services/oauth2/token";
  private static String REST_ENDPOINT = "/services/data";
  UserCredentials userCredentials;
  private String restUri;
  Header oauthHeader;
  Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
  Gson gson;
  OAuth2Response oauth2Response;

//  public static void main(String[] args) throws URISyntaxException, HttpException {
//    RestClient client = new RestClient();
//    client.oauth2Login( client.getUserCredentials());
//    client.testRestData();
//  }

  public RestClient() {
    gson = new Gson();
  }

  public HttpResponse oauth2Login(UserCredentials userCredentials) throws URISyntaxException, HttpException {
    HttpResponse response = null;
    this.userCredentials = userCredentials;
    String loginHostUri = "https://" + 
        userCredentials.loginInstanceDomain + OAUTH_ENDPOINT;
    System.out.println(loginHostUri);
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
      requestBodyText.append("&redirect_uri=https://no_redirect_uri");
      StringEntity requestBody = 
          new StringEntity(requestBodyText.toString());
      requestBody.setContentType("application/x-www-form-urlencoded");
      httpPost.setEntity(requestBody);
      httpPost.addHeader(prettyPrintHeader);
      response = httpClient.execute(httpPost);
//      System.out.println(response.getStatusLine().getStatusCode());
//      HttpEntity entity = response.getEntity();
//      String responseString = EntityUtils.toString(entity, "UTF-8");
//      System.out.println(responseString);
      if (  response.getStatusLine().getStatusCode() == 200 ) {
        InputStreamReader inputStream = new InputStreamReader( 
            response.getEntity().getContent() 
        );
        oauth2Response = gson.fromJson( inputStream, 
            OAuth2Response.class );
        restUri = oauth2Response.instance_url + REST_ENDPOINT + 
            "/v" + this.userCredentials.apiVersion +".0";
        System.out.println("\nSuccessfully logged in to instance: " + 
            restUri);
        oauthHeader = new BasicHeader("Authorization", "OAuth " + 
            oauth2Response.access_token);
      } else {
        System.out.println("An error has occured.");
        System.exit(-1);
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

  public void testRestData() throws URISyntaxException, HttpException {
    String responseBody = restGet(restUri);
    //System.out.println(responseBody);
//    List<course_detail__c> objs = new List<course_detail__c>();
//    objs = [Select o.name__c From course_detail__c o];
//    for (course_detail__c obj : objs){
//    delete obj;
//    }
//    responseBody = restGet(restUri + "/sobjects/course_detail__c/describe/");
    CourseExtraction list = new CourseExtraction();
//    ArrayList<JSONObject> allCourses = list.getAllCourses();
//    if (allCourses.size() != 0) {
//    	for (JSONObject newCourse: allCourses) {
////    		responseBody = restGet(restUri + "/query/?q=SELECT+name__c+FROM+course_detail__c+WHERE+name__c+=+\'2D+Design+(WEU)\'");
////    		JsonParser parser = new JsonParser();
////    	    JsonElement jsonElement = parser.parse(responseBody);
////    	    int total = jsonElement.getAsJsonObject().get("totalSize").getAsInt();
////    	    if (total == 0) {
//    	    	responseBody = restPost(restUri + "/sobjects/course_detail__c/", newCourse.toString());
//    	    	System.out.println(responseBody);
////    	    }
//    	}
//    }    
//    JsonParser jsonParser = new JsonParser();
//    JsonElement jsonElement = jsonParser.parse(responseBody);
//    String id = jsonElement.getAsJsonObject().get("id").getAsString();
//    responseBody = restGet(restUri +
//        "/sobjects/Merchandise__c/" + id);
//    System.out.println(responseBody);
//    responseBody = restPost(restUri + 
//        "/sobjects/Merchandise__c/", "{ \"Name\" : \"Zeppelin GmbH\" }\n\n");
//    System.out.println(responseBody);
//    responseBody = restGet(restUri + 
//        "/query/?q=SELECT+Name+FROM+Merchandise__c");
//    System.out.println(responseBody);
//    responseBody = restPatch(restUri + 
//        "/sobjects/Merchandise__c/" + id, "{ \"Name\" : \"Dry Twig.\" }\n\n");
//    System.out.println(responseBody);
//    responseBody = restGet(restUri + 
//        "/sobjects/Merchandise__c/" + id);
//    System.out.println(responseBody);
  }

  public String restGet(String uri) throws URISyntaxException, HttpException {
    String result = "";
    uri = restUri+uri;
//    printBanner("GET", uri);
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
  

  public String restPatch(String uri, String requestBody) throws HttpException, URISyntaxException {
    String result = "";
    printBanner("PATCH", uri);
    try {
      HttpClient httpClient = new DefaultHttpClient();
      HttpPatch httpPatch = new HttpPatch(uri);
      httpPatch.addHeader(oauthHeader);
      httpPatch.addHeader(prettyPrintHeader);
      StringEntity body = new StringEntity(requestBody);
      body.setContentType("application/json");
      httpPatch.setEntity(body);
      HttpResponse response = httpClient.execute(httpPatch);
      result = response.getEntity() != null ? 
          getBody( response.getEntity().getContent() ) : "";
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (NullPointerException npe) {
      npe.printStackTrace();
    }
    return result;
	}

  public String restPatchXml(String uri, String requestBody) throws HttpException, URISyntaxException {
    String result = "";
    printBanner("PATCH", uri);
    try {
      HttpClient httpClient = new DefaultHttpClient();
      HttpPatch httpPatch = new HttpPatch(uri);
      httpPatch.addHeader(oauthHeader);
      httpPatch.addHeader(prettyPrintHeader);
      httpPatch.addHeader( new BasicHeader("Accept", "application/xml") );
      StringEntity body = new StringEntity(requestBody);
      body.setContentType("application/xml");
      httpPatch.setEntity(body);
      HttpResponse response = httpClient.execute(httpPatch);
      result = getBody( response.getEntity().getContent() );
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (NullPointerException npe) {
      npe.printStackTrace();
    }
    return result;
	}

  public String restPost(String uri, String requestBody) throws HttpException, URISyntaxException {
    String result = null;
//    printBanner("POST", uri);
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
   * Extend the Apache HttpPost method to implement an HttpPost
   * method.
   */  public static class HttpPatch extends HttpPost {
    public HttpPatch(String uri) throws URISyntaxException {
      super(uri);
    }

    public String getMethod() {
      return "PATCH";
    }
  }

  static class OAuth2Response {
    public OAuth2Response() {
    }
    String id;
    String issued_at;
    String instance_url="";
    String signature;
    String access_token;
  }
  
  class UserCredentials {
    String grantType;
    String userName;
    String password;
    String consumerKey;
    String consumerSecret;
    String loginInstanceDomain;
    String apiVersion;
  }

  // private methods  
  public String getUserInput(String prompt) {
    String result = "";
    try {
      System.out.print(prompt);
      result = reader.readLine();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return result;
  }

  public void printBanner(String method, String uri) {
    System.out.println("\n--------------------------------------------------------------------\n");
    System.out.println("HTTP Method: " + method);
    System.out.println("REST URI: " + uri);
    System.out.println("\n--------------------------------------------------------------------\n");
  }

  public String getBody(InputStream inputStream) {
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

  public UserCredentials getUserCredentials() {
    UserCredentials userCredentials = new UserCredentials();
    userCredentials.loginInstanceDomain = "login.salesforce.com";
    userCredentials.apiVersion = "28";
    userCredentials.userName = "michaelfebrianto@gmail.com";
    userCredentials.password = "C0urs3Evalu@t10n!MzSFewuyg8pDCV1FwvhjJIOr1";
    userCredentials.consumerKey = "3MVG9Y6d_Btp4xp5bNQoPmoPqiSlgy5bVm3ApEsx_k.yOSmp.Qu6x.4jQilEtjrbqZyHCn_FSH7ITxvEbm_Tb";
    userCredentials.consumerSecret = "3532962961768066117";
    userCredentials.grantType = "password";
    return userCredentials;
  }
}


