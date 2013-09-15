package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unsw.cse.comp9323.group1.DAOs.CourseDAO;
import edu.unsw.cse.comp9323.group1.models.Course;

@Controller
@RequestMapping("/courseDetail")
public class CourseDetailController {
	
	@RequestMapping(value = "/{courseID}", method = RequestMethod.GET)
	public String getCourseDetail(@PathVariable String courseID, ModelMap model) throws UnsupportedEncodingException {
		
		CourseDAO crsDAO = new CourseDAO();
		Course course = new Course();
		course = crsDAO.getCourseByID(courseID);
		model.addAttribute("course", course);
		return "courseDetail";
	
	}

}
