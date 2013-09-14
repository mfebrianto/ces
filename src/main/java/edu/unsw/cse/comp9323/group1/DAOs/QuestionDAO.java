package edu.unsw.cse.comp9323.group1.DAOs;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import edu.unsw.cse.comp9323.group1.models.Question;

public class QuestionDAO {

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";

	public void createQuestion(Question question) {

		
		String url = "https://restapi.surveygizmo.com/head/survey/"+question.getSurveyId()+"/surveypage/1/surveyquestion";

		HttpClient client = new HttpClient();

		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		method.setQueryString(new NameValuePair[] {
				new NameValuePair("user:pass", userPass),
				new NameValuePair("_method", "PUT"),
				new NameValuePair("surveypage", "1"),
				new NameValuePair("type", "text"),
				new NameValuePair("title", question.getTitle())});

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

			System.out.println(responseBodyStr);


		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

	}

}
