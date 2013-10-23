package edu.unsw.cse.comp9323.group1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.CourseDAO;
import edu.unsw.cse.comp9323.group1.models.Course;


/**
 * 
 * This class controls all functions related to search function
 * 
 * @author z3402013.group1.comp9323-2013s1
 *
 */

@Controller
@RequestMapping("/search")
public class SearchController {

	/**
	 * This method will get all courses from database.com 
	 * 
	 * @param model
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllCourses(ModelMap model) {
		//CourseDAO courseDAO = new CourseDAO();
		//model.addAttribute("courses", courseDAO.getAllCourses());
		return "search";
 
	}
	
	
	/**
	 * 
	 * this method get courses from database.com based on inserted critera.
	 * 
	 * @param searchedText
	 * @param model
	 * @return
	 */
	
	//Search courses based on uni name or course name
	@RequestMapping(method = RequestMethod.POST)
	public String postSearchedCourses(@RequestParam("searchedText") String searchedText, ModelMap model) {
		CourseDAO courseDAO = new CourseDAO();
		List<Course> searchedCourses = new ArrayList<Course>();
		
		searchedText.trim();
		if (searchedText.length() > 0) {
			List<Course> list = courseDAO.getAllCourses();
			for (Course c : list) {
				if (c.getName().contains(searchedText) || c.getUni().contains(searchedText)) {
					searchedCourses.add(c);
				}
			}
		}
		model.addAttribute("searchedCourses", searchedCourses);
		return "search";
	}

}
