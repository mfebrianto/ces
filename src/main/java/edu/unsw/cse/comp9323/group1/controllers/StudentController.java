package edu.unsw.cse.comp9323.group1.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
 
@Controller
@RequestMapping("/student")
public class StudentController {
 
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		//model.addAttribute("message", "Spring 3 MVC Hello World");
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession session = attr.getRequest().getSession(true);
	    String accesstoken = "";
	    if (session.getAttribute("token") != null){
	    	if(session.getAttribute("loginRole") !=null){
	    		if(session.getAttribute("loginRole").toString() =="uni"){
	    			return "hello";
	    		}
	    		accesstoken = session.getAttribute("token").toString();
		    }
	    	
	    }else{
	    	return "hello";
	    }
	    
	    
		FacebookClient facebookClient = new DefaultFacebookClient(accesstoken);
		System.out.println(accesstoken);
		User user = facebookClient.fetchObject("me", User.class);
		
		if(user==null){
			return "hello";
		}else{
			return "studentHome";
		}
 
	}
 
}
