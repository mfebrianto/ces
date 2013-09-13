package edu.unsw.cse.comp9323.group1.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProfanityController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfanityController.class);
	
	public static String endpoint = "http://www.wdyl.com/profanity";

	
	@RequestMapping(value = "/containProfanity", method = RequestMethod.POST)
	@ResponseBody
	public String containProfanity(@RequestBody String data) throws IOException {
		URL url = new URL(endpoint+"?q="+ URLEncoder.encode(data));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		
		String output = "";
		String line;
		while ((line = br.readLine()) != null) {
			//System.out.println(output);
			output += line;
			
		}
 
		conn.disconnect();
		return output;
		
	}
	
}