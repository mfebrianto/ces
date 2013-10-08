package edu.unsw.cse.comp9323.group1;

import java.util.ArrayList;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.unsw.cse.comp9323.group1.DAOs.QuestionDAO;
import edu.unsw.cse.comp9323.group1.DAOs.ResponseDAO;

public class ReportingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String url = "https://restapi.surveygizmo.com/head/survey/1371398/surveystatistic";
		String url = "https://restapi.surveygizmo.com/head/survey/1371398/surveyquestion/27";
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

			

			 JSONParser parser = new JSONParser();
			 Object obj = parser.parse(responseBodyStr);
			 JSONObject jsonObject = (JSONObject) obj;
			
			 
			 System.out.println(jsonObject.toJSONString());
			 
			 getTextResponse(1371398, "[question(7)]");

			 
			 
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	public static Set<String> getTextResponse(int surveyId, String questionId) {
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
			Set<String> stringSet = new TreeSet<String>();
		    while (dataIterator.hasNext()) {
				JsonObject element = dataIterator.next().getAsJsonObject();
				if (element.get(questionId) != null) {
					String answer = element.get(questionId).getAsString();
					if (Strings.isNullOrEmpty(answer)) {
						continue;
					}
					
					stringSet.add(answer);
					
				}
				
			}
		    
		    for (String string : stringSet) {
				System.out.println(string);
			}
			 
			

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
