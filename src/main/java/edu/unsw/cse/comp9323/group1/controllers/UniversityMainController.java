package edu.unsw.cse.comp9323.group1.controllers;

import java.net.URISyntaxException;

import org.apache.http.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/uni")
public class UniversityMainController {
	
	protected static RestClient client = new RestClient();

		@RequestMapping(method = RequestMethod.GET)
		public String printWelcome(ModelMap model) {
			
			
			try {
				String response = client.restGet(client.restUri + "/query/?q=SELECT+name__c+FROM+course_detail__");
				
				System.out.println(response);
				
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (HttpException e) {
				e.printStackTrace();
			}
	 
			model.addAttribute("message", "Spring 3 MVC Hello World");
			return "universityHome";
	 
		}
}
