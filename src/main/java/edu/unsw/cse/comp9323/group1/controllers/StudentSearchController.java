package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.CourseAverageRatingDAO;
import edu.unsw.cse.comp9323.group1.DAOs.CourseDAO;
import edu.unsw.cse.comp9323.group1.models.Course;
import edu.unsw.cse.comp9323.group1.models.CourseAverageRating;


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
	public String postSearchedCourses(@RequestParam("searchedText") String searchedText, 
			ModelMap model, 
			@RequestParam("average_rating_min") int average_rating_min, 
			@RequestParam("average_rating_max") int average_rating_max) throws UnsupportedEncodingException, URISyntaxException, HttpException, ParseException {
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
		else {
			searchedCourses = courseDAO.getAllCourses();
		}
		
		
		if (average_rating_max <= 5 && average_rating_min >= 0) {
			CourseAverageRatingDAO carDAO = new CourseAverageRatingDAO();
			List<CourseAverageRating> list = carDAO.getAllCourseAverageRating();
			
			Iterator<CourseAverageRating> carIterator = list.iterator();
			while (carIterator.hasNext()) {
				CourseAverageRating tmp = carIterator.next();
				if (tmp.getRating() > average_rating_max || tmp.getRating() < average_rating_min)
					carIterator.remove();
			}
			
			List<String> searchedCourseNameList = new ArrayList<String>();
			for (Course c: searchedCourses) {
				searchedCourseNameList.add(c.getName());
			}
			List<String> averageCourseNameList = new ArrayList<String>();
			for (CourseAverageRating c: list) {
				averageCourseNameList.add(c.getCourse_name());
			}
			
			Iterator<String> it = searchedCourseNameList.iterator();
			while (it.hasNext()) {
				String st = it.next();
				if (!averageCourseNameList.contains(st) && average_rating_min > 0)
					it.remove();
			}
			
			Iterator<Course> course_it = searchedCourses.iterator();
			while (course_it.hasNext()) {
				if (!searchedCourseNameList.contains(course_it.next().getName()))
					course_it.remove();
			}
		}
		
		model.addAttribute("searchedCourses", searchedCourses);
		return "studentSearch";
	}

}
