package edu.unsw.cse.comp9323.group1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unsw.cse.comp9323.group1.DAOs.CourseDAO;

@Controller
@RequestMapping("/uni")
public class UniversityMainController {
	
	

		@RequestMapping(method = RequestMethod.GET)
		public String printWelcome(ModelMap model) {
			
			CourseDAO courseDAO = new CourseDAO();
			
	 
			model.addAttribute("courses", courseDAO.getAllCourses());
			
			return "universityHome";
	 
		}
}
