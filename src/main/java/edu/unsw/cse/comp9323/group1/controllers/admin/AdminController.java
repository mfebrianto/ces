package edu.unsw.cse.comp9323.group1.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is the main class of admin page.
 * 
 * @author z3378491.group1.comp9323-2013s1
 */

@Controller
@RequestMapping("/console")
public class AdminController {
 
	
	 /**
	  * @return admin page address
	  */
	 
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 		
		return "adminLogin";
 
	}
 
}
