package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * 
 * This class return detail for search page
 * 
 * @author z3402013.group1.comp9323-2013s1
 *
 */

@Controller
@RequestMapping("/studentSearch")
public class StudentSearchController {
	static List<String> list_unis = Arrays.asList("ALL", "MIT", "Berkeley", "Saylor", "OpenSecurityTraining",
		"Macquarie", "Georgetown", "DTU", "British", "Washington", "College", "Harvard", "RMIT");
	static List<String> list_categories = Arrays.asList("ALL", "Chemistry", "Humanities", "Computer Science", "Biology",
		"Energy", "Health", "Chemistry", "Art", "Economics", "Finance", "Music", "Mathematics", 
		"Medicine", "Physics", "Education");
	
	/**
	 * 
	 * This method will add value to all search attributes on the search screen
	 * 
	 * @param studentId
	 * @param model
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllCourses(@RequestParam("studentId") String studentId, ModelMap model) {
		//CourseDAO courseDAO = new CourseDAO();
		//model.addAttribute("courses", courseDAO.getAllCourses());
		
		model.addAttribute("studentId", studentId);
		model.addAttribute("searchedText", "");
		model.addAttribute("uni_name", "");
		model.addAttribute("category", "");
		model.addAttribute("average_rating_min", "0");
		model.addAttribute("average_rating_max", "5");
		model.addAttribute("average_reputation_rating_min", "0");
		model.addAttribute("average_reputation_rating_max", "5");
		model.addAttribute("average_teaching_rating_min", "0");
		model.addAttribute("average_teaching_rating_max", "5");
		model.addAttribute("average_research_rating_min", "0");
		model.addAttribute("average_research_rating_max", "5");
		model.addAttribute("average_administrators_rating_min", "0");
		model.addAttribute("average_administrators_rating_max", "5");
		model.addAttribute("average_lecture_notes_rating_min", "0");
		model.addAttribute("average_lecture_notes_rating_max", "5");
		return "studentSearch";
	}
	
	/**
	 * 
	 * This method recieve parameter from the screen and parse it before send it
	 * to database.com
	 * 
	 * @param searchedText
	 * @param model
	 * @param uni_name
	 * @param category
	 * @param average_rating_min
	 * @param average_rating_max
	 * @param average_reputation_rating_min
	 * @param average_reputation_rating_max
	 * @param average_teaching_rating_min
	 * @param average_teaching_rating_max
	 * @param average_research_rating_min
	 * @param average_research_rating_max
	 * @param average_administrators_rating_min
	 * @param average_administrators_rating_max
	 * @param average_lecture_notes_rating_min
	 * @param average_lecture_notes_rating_max
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws ParseException
	 */
	
	//Search courses based on uni name or course name
	@RequestMapping(method = RequestMethod.POST)
	public String postSearchedCourses(@RequestParam("searchedText") String searchedText, 
			ModelMap model, 
			@RequestParam("uni_name") String uni_name,
			@RequestParam("category") String category,
			@RequestParam("average_rating_min") int average_rating_min, 
			@RequestParam("average_rating_max") int average_rating_max,
			@RequestParam("average_reputation_rating_min") int average_reputation_rating_min, 
			@RequestParam("average_reputation_rating_max") int average_reputation_rating_max,
			@RequestParam("average_teaching_rating_min") int average_teaching_rating_min, 
			@RequestParam("average_teaching_rating_max") int average_teaching_rating_max,
			@RequestParam("average_research_rating_min") int average_research_rating_min, 
			@RequestParam("average_research_rating_max") int average_research_rating_max,
			@RequestParam("average_administrators_rating_min") int average_administrators_rating_min, 
			@RequestParam("average_administrators_rating_max") int average_administrators_rating_max,
			@RequestParam("average_lecture_notes_rating_min") int average_lecture_notes_rating_min, 
			@RequestParam("average_lecture_notes_rating_max") int average_lecture_notes_rating_max
			) throws UnsupportedEncodingException, URISyntaxException, HttpException, ParseException {
		CourseDAO courseDAO = new CourseDAO();
		List<Course> searchedCourses = new ArrayList<Course>();
		
		searchedText.trim();
		//If searchedText is not empty (searchedText is course name)
		if (searchedText.length() > 0) {
			List<Course> list = courseDAO.getAllCourses();
			for (Course c : list) {
				if (c.getName().toLowerCase().contains(searchedText.toLowerCase())) {
					searchedCourses.add(c);
				}
			}
		}
		else {
			searchedCourses = courseDAO.getAllCourses();
		}
		
		//If uni_name is not empty
		if (uni_name.length() > 0) {
			List<String> uniList = new ArrayList<String>();
			if (uni_name.contains(",")) {
				uniList = Arrays.asList(uni_name.split(","));
			}
			else 
				uniList.add(uni_name);
			
			List<Course> tmpList = new ArrayList<Course>();
			for (String str: uniList) {
				Iterator<Course> course_it = searchedCourses.iterator();
				while (course_it.hasNext()) {
					Course c = course_it.next();
					if (c.getUni().toLowerCase().contains(str.toLowerCase().trim()))
						if (!tmpList.contains(c))
							tmpList.add(c);
				}
			}
			searchedCourses = tmpList;
		}
		
		
		//If category is not empty
		if (category.length() > 0) {
			List<String> categoryList = new ArrayList<String>();
			if (category.contains(",")) {
				categoryList = Arrays.asList(category.split(","));
			}
			else 
				categoryList.add(category);
			
			List<Course> tmpList = new ArrayList<Course>();
			for (String str: categoryList) {
				Iterator<Course> course_it = searchedCourses.iterator();
				while (course_it.hasNext()) {
					Course c = course_it.next();
					if (c.getCategories().toLowerCase().contains(str.toLowerCase().trim()))
						if (!tmpList.contains(c))
							tmpList.add(c);
				}
			}
			searchedCourses = tmpList;
		}
		
		
		CourseAverageRatingDAO carDAO = new CourseAverageRatingDAO();
		//If average_rating_min, max is [0,5]
		if (average_rating_max <= 5 && average_rating_min >= 0) {
			if (average_rating_max != 5 || average_rating_min != 0)
				searchedCourses = refineSearchedCourse("ALL", carDAO, 
					average_rating_min, average_rating_max, searchedCourses);
		}
		//If average_reputation_rating_min, max is [0,5]
		if (average_reputation_rating_max <= 5 && average_reputation_rating_min >= 0) {
			if (average_reputation_rating_max != 5 || average_reputation_rating_min != 0)
				searchedCourses = refineSearchedCourse("Reputation", carDAO, 
					average_reputation_rating_min, average_reputation_rating_max, searchedCourses);
		}
		//If average_teaching_rating_min, max is [0,5]
		if (average_teaching_rating_max <= 5 && average_teaching_rating_min >= 0) {
			if (average_teaching_rating_max != 5 || average_teaching_rating_min != 0)
				searchedCourses = refineSearchedCourse("Teaching", carDAO, 
					average_teaching_rating_min, average_teaching_rating_max, searchedCourses);
		}
		//If average_research_rating_min, max is [0,5]
		if (average_research_rating_max <= 5 && average_research_rating_min >= 0) {
			if (average_research_rating_max != 5 || average_research_rating_min != 0)
				searchedCourses = refineSearchedCourse("Research", carDAO, 
					average_research_rating_min, average_research_rating_max, searchedCourses);
		}
		//If average_administrators_rating_min, max is [0,5]
		if (average_administrators_rating_max <= 5 && average_administrators_rating_min >= 0) {
			if (average_administrators_rating_max != 5 || average_administrators_rating_min != 0)
				searchedCourses = refineSearchedCourse("Admin", carDAO, 
					average_administrators_rating_min, average_administrators_rating_max, searchedCourses);
		}
		//If average_lecture_notes_rating_min, max is [0,5]
		if (average_lecture_notes_rating_max <= 5 && average_lecture_notes_rating_min >= 0) {
			if (average_lecture_notes_rating_max != 5 || average_lecture_notes_rating_min != 0)
			searchedCourses = refineSearchedCourse("LectureNotes", carDAO, 
					average_lecture_notes_rating_min, average_lecture_notes_rating_max, searchedCourses);
		}
		
		model.addAttribute("searchedCourses", searchedCourses);
		model.addAttribute("searchedText", searchedText);
		model.addAttribute("uni_name", uni_name);
		model.addAttribute("category", category);
		model.addAttribute("average_rating_min", average_rating_min);
		model.addAttribute("average_rating_max", average_rating_max);
		model.addAttribute("average_reputation_rating_min", average_reputation_rating_min);
		model.addAttribute("average_reputation_rating_max", average_reputation_rating_max);
		model.addAttribute("average_teaching_rating_min", average_teaching_rating_min);
		model.addAttribute("average_teaching_rating_max", average_teaching_rating_max);
		model.addAttribute("average_research_rating_min", average_research_rating_min);
		model.addAttribute("average_research_rating_max", average_research_rating_max);
		model.addAttribute("average_administrators_rating_min", average_administrators_rating_min);
		model.addAttribute("average_administrators_rating_max", average_administrators_rating_max);
		model.addAttribute("average_lecture_notes_rating_min", average_lecture_notes_rating_min);
		model.addAttribute("average_lecture_notes_rating_max", average_lecture_notes_rating_max);
//		model.addAttribute("list_categories", list_categories);
//		model.addAttribute("list_unis", list_unis);
		
		return "studentSearchUpdate";
	}

	public List<Course> refineSearchedCourse(String category, CourseAverageRatingDAO carDAO, 
			int min, int max, List<Course> searchedCourses) throws UnsupportedEncodingException, URISyntaxException, HttpException, ParseException {
		List<Course> result = searchedCourses;
		List<CourseAverageRating> list = carDAO.getAllCourseAverageRatingByCategory(category);
		Iterator<CourseAverageRating> carIterator = list.iterator();
		while (carIterator.hasNext()) {
			CourseAverageRating tmp = carIterator.next();
			if (tmp.getRating() > max || tmp.getRating() < min)
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
			if (!averageCourseNameList.contains(st) && min > 0)
				it.remove();
		}
		
		Iterator<Course> course_it = searchedCourses.iterator();
		while (course_it.hasNext()) {
			if (!searchedCourseNameList.contains(course_it.next().getName()))
				course_it.remove();
		}
		return result;
	}
	
}
