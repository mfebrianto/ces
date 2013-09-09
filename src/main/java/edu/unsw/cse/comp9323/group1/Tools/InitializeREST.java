package edu.unsw.cse.comp9323.group1.Tools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.google.gson.Gson;

import edu.unsw.cse.comp9323.group1.Tools.RestClient.OAuth2Response;
import edu.unsw.cse.comp9323.group1.Tools.RestClient.UserCredentials;

public class InitializeREST {
	private static String OAUTH_ENDPOINT = "/services/oauth2/token";
	private static String REST_ENDPOINT = "/services/data";
	
	private String restUri ="";
	private Header oauthHeader = null;
	Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
	
	Gson gson;
	//String restUri ="";
	
	public InitializeREST(){
		gson = new Gson();
		
	}
	
	public void Init() throws URISyntaxException, HttpException{
		UserCredentials userCred = this.getUserCredentials();
		this.oauth2Login(userCred);
		//return restUri;
		
		
	}
	
	public HttpResponse oauth2Login(UserCredentials userCred) throws URISyntaxException, HttpException {
	    HttpResponse response = null;
	    
	    UserCredentials userCredentials = userCred;
	    
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
	      
	      if (response.getStatusLine().getStatusCode() == 200 ) {
	        InputStreamReader inputStream = new InputStreamReader( 
	            response.getEntity().getContent() 
	        );
	        
	        OAuth2Response oauth2Response = gson.fromJson(inputStream, 
	            OAuth2Response.class );
	        
	        String uri = oauth2Response.instance_url + REST_ENDPOINT + 
	            "/v" + userCredentials.apiVersion +".0";
	        
	        this.setRestUri(uri);
	        
	        System.out.println("\nSuccessfully logged in to instance: " + 
	            restUri);
	        
	        Header Header = new BasicHeader("Authorization", "OAuth " + 
	            oauth2Response.access_token);
	        
	        this.setOauthHeader(Header);
	        
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

	private UserCredentials getUserCredentials() {
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
	
	class UserCredentials {
	    String grantType;
	    String userName;
	    String password;
	    String consumerKey;
	    String consumerSecret;
	    String loginInstanceDomain;
	    String apiVersion;
	  }
	
	class OAuth2Response {
	   
	    String id;
	    String issued_at;
	    String instance_url="";
	    String signature;
	    String access_token;
	  }

	
	public String getRestUri() {
		return restUri;
	}

	public void setRestUri(String restUri) {
		this.restUri = restUri;
	}

	public Header getOauthHeader() {
		return oauthHeader;
	}

	public void setOauthHeader(Header oauthHeader) {
		this.oauthHeader = oauthHeader;
	}

}
