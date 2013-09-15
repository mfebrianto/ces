package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.CourseDAO;
import edu.unsw.cse.comp9323.group1.models.Course;

@Controller
@RequestMapping("/courseDetail")
public class CourseDetailController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCourseDetail(@RequestParam("courseId") String courseId, ModelMap model) throws UnsupportedEncodingException {
		
		CourseDAO crsDAO = new CourseDAO();
		Course course = new Course();
		course = crsDAO.getCourseByID(courseId);
		model.addAttribute("course", course);
		return "courseDetail";
	
	}

}
