package edu.unsw.cse.comp9323.group1.controllers;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.gson.Gson;

/**
 * 
 * This class is controller class control student authentication with google login
 * 
 * @author z3389874.group1.comp9323-2013s1
 *
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	   * Replace this with the client ID you got from the Google APIs console.
	   */
	  private static final String CLIENT_ID = "913622401500-odpadjc4kekc6ugqvtohv2ia3budo8bh.apps.googleusercontent.com";
	  /**
	   * Replace this with the client secret you got from the Google APIs console.
	   */
	  private static final String CLIENT_SECRET = "gEzD1LEGMUoxsLzB6i8Hdxds";
	  /**
	   * Optionally replace this with your application's name.
	   */

	  /**
	   * Default HTTP transport to use to make HTTP requests.
	   */
	  private static final HttpTransport TRANSPORT = new NetHttpTransport();
	  /**
	   * Default JSON factory to use to deserialize JSON.
	   */
	  private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	  /**
	   * Gson object to serialize JSON responses to requests to this servlet.
	   */
	  private static final Gson GSON = new Gson();

	
	  
	  /**
	   * Register all endpoints that we'll handle in our server.
	   * Simply selects the home view to render by returning its name.  
	   * 
	   * @param locale
	   * @param model
	   * @return
	   */

    
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		*/
		return "home";
	}

	/**
	 * 
	 * This method will start session with Google API to login as university
	 * 
	 * @param code
	 * @param redirectUrl
	 * @return
	 * @throws IOException
	 */
	
	
	@RequestMapping(value = "/googleLogin", method = RequestMethod.POST)
	@ResponseBody
	public String googleLogin(@RequestBody String code, String redirectUrl) throws IOException{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession session = attr.getRequest().getSession(true);
	    
		
		if (session.getAttribute("token") != null) {
			return "already login";
		}
		GoogleTokenResponse tokenResponse =
	              new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY,
	                  CLIENT_ID, CLIENT_SECRET, code, "postmessage").execute();

	          // You can read the Google user ID in the ID token.
	          // This sample does not use the user ID.
			String id_token = tokenResponse.getIdToken();
			String accessToken = tokenResponse.getAccessToken();
	        String responseToken = tokenResponse.toString();
			
			
	          // Store the token in the session for later use.
	        
	          session.setAttribute("token", tokenResponse.toString());
	          System.out.println(tokenResponse.toString());
	          
	          GoogleCredential credential = new GoogleCredential.Builder()
              .setJsonFactory(JSON_FACTORY)
              .setTransport(TRANSPORT)
              .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
              .setFromTokenResponse(tokenResponse);
	          
	          Plus service = new Plus.Builder(TRANSPORT, JSON_FACTORY, credential)
              .setApplicationName("CS9323Project")
              .build();
	          String name = service.people().get("me").execute().getDisplayName();
	          UniversityModel university = new UniversityModel();
	          university.access_token__c = accessToken;
	          university.id_token__c = id_token;
	          university.name__c = name;
	          university.responseToken__c = responseToken;
	        		  
	          //String testString = GSON.toJson(university);
	         // System.out.println(id_token.length()+"   "+responseToken.length()+"    "+testString);
	          RestDB rdb = new RestDB();
	          rdb.oauth2Login(rdb.userCredentials);
	          String result = rdb.postUniLoginData(university);
	          System.out.println(result);
	          session.setAttribute("loginRole", "uni");
	          return name+" Successfully Login";
		
	}
	
	/**
	 * This method will end google session and end session as university
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/googleLogout", method = RequestMethod.POST)
	@ResponseBody
	public String googleLogout(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
//		if (session.getAttribute("token") == null) {
//			return "already logout!!";
//		}
		String tokenData = (String) session.getAttribute("token");
		if (tokenData == null) {
			return "user has not login!";
		}
		try {
	          // Build credential from stored token data.
	          GoogleCredential credential = new GoogleCredential.Builder()
	              .setJsonFactory(JSON_FACTORY)
	              .setTransport(TRANSPORT)
	              .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
	              .setFromTokenResponse(JSON_FACTORY.fromString(
	                  tokenData, GoogleTokenResponse.class));
	          
	          Plus service = new Plus.Builder(TRANSPORT, JSON_FACTORY, credential)
              .setApplicationName("CS9323Project")
              .build();
	          String name = service.people().get("me").execute().getDisplayName();

	          
	          System.out.println("session token    "+session.getAttribute("token"));
	          System.out.println(name + "  out");
	          
	          session.removeAttribute("token");
	          session.removeAttribute("loginRole");
	          
	          return name+" Successfully logout.";
	        } catch (IOException e) {
	          // For whatever reason, the given token was invalid.
	          e.printStackTrace();
	          return GSON.toJson("Failed to revoke token for given user.");
	        }
		
		
	}
	
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	@ResponseBody
//	public String testRest() {
//		return GSON.toJson("ok");
//	}
	
	
	

	
	
}
