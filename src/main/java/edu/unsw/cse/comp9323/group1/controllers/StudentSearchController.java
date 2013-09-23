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


@Controller
@RequestMapping("/studentSearch")
public class StudentSearchController {

	@RequestMapping(method = RequestMethod.GET)
	public String getAllCourses(@RequestParam("studentId") String studentId, ModelMap model) {
		//CourseDAO courseDAO = new CourseDAO();
		//model.addAttribute("courses", courseDAO.getAllCourses());
		
		model.addAttribute("studentId", studentId);
		return "studentSearch";
 
	}
	
	//Search courses based on uni name or course name
	@RequestMapping(method = RequestMethod.POST)
	public String postSearchedCourses(@RequestParam("searchedText") String searchedText, @RequestParam("studentId") String studentId, ModelMap model) {
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
		model.addAttribute("studentId", studentId);
		return "studentSearch";
	}

}
