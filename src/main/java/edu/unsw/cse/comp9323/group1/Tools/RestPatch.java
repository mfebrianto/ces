package edu.unsw.cse.comp9323.group1.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import edu.unsw.cse.comp9323.group1.Tools.HttpPatch;

public class RestPatch {
	
	public void update(String restUri, Header oauthHeader, String requestBody) throws URISyntaxException{
		//String result = "";
	   
	    try {
	      
	    	HttpClient httpClient = new DefaultHttpClient();
	      
	    	HttpPatch httpPatch = new HttpPatch(restUri);
	      
	    	httpPatch.addHeader(oauthHeader);
	      
	    	Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
	    	httpPatch.addHeader(prettyPrintHeader);
	      
	    	StringEntity body = new StringEntity(requestBody);	      
	    	body.setContentType("application/json");	      
	    	httpPatch.setEntity(body);
	      
	    	httpClient.execute(httpPatch);
	      
	    	/*result = response.getEntity() != null ? 
	          getBody( response.getEntity().getContent() ) : "";*/
	          
	    } catch (IOException ioe) {
	      
	    	ioe.printStackTrace();
	    
	    } catch (NullPointerException npe) {
	      
	    	npe.printStackTrace();
	    
	    }
	    
	    //return result;
		
	}
	
	private String getBody(InputStream inputStream) {
		
		String result = "";
		
		try {
			BufferedReader in = new BufferedReader(
            new InputStreamReader(inputStream));
        
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

}
