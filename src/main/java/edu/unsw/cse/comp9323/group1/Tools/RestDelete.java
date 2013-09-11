package edu.unsw.cse.comp9323.group1.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

public class RestDelete {
	
	public void delete(String restUri, Header oauthHeader){
String result = "";
	    
	    try {
	      HttpClient httpClient = new DefaultHttpClient();
	      
	      HttpDelete httpDelete = new HttpDelete(restUri);
	      httpDelete.addHeader(oauthHeader);
	      
	      Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
	      httpDelete.addHeader(prettyPrintHeader);
	      
	      HttpResponse response = httpClient.execute(httpDelete);
	      
	      //result = getBody(response.getEntity().getContent());
	      
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

}
