package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.CourseDAO;
import edu.unsw.cse.comp9323.group1.DAOs.RatingDAO;
import edu.unsw.cse.comp9323.group1.DAOs.SurveyDAO;
import edu.unsw.cse.comp9323.group1.models.Course;
import edu.unsw.cse.comp9323.group1.models.Survey;

@Controller
@RequestMapping("/courseDetail")
public class CourseDetailController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCourseDetail(@RequestParam("courseName") String courseName,@RequestParam("studentId") String studentId, ModelMap model) throws UnsupportedEncodingException, URISyntaxException, HttpException {
		
		CourseDAO crsDAO = new CourseDAO();
		Course course = new Course();
		course = crsDAO.getCourseByName(courseName);
		
		
		SurveyDAO surveyDAO = new SurveyDAO();
		
		 List<Survey> allSurveys = new ArrayList<Survey>();
		 allSurveys = surveyDAO.getAllAvailableSurvey();
		
		 /*
		  * get spesifically for the current courseID
		  */
		 List<Survey> listOfSurveys = surveyDAO.getSurveyWithCourseId(courseName);
		 
		 /*
		  * filter the result before show it to view
		  */
		 List<Survey> returnSurveys = new ArrayList<Survey>();
		 Iterator<Survey> listOfSurveysItr = listOfSurveys.iterator();
		 while(listOfSurveysItr.hasNext()){
			 String surveyIdRslt = (String)listOfSurveysItr.next().getId();
			 Iterator<Survey> allSurveysItr = allSurveys.iterator();
			 while(allSurveysItr.hasNext()){
				 Survey surveyTemp = allSurveysItr.next();
				 if(surveyIdRslt.equals(surveyTemp.getId())){
					 returnSurveys.add(surveyTemp);
				 }
			 }
		 }
		 
		 RatingDAO ratingDAO = new RatingDAO();
		 
		 int overallRatingCourse = ratingDAO.getOverAllRating(courseName);
		 int userRatingCourse = ratingDAO.getUserRating(studentId, courseName);
		 
		 model.addAttribute("UserRating", userRatingCourse);
		 model.addAttribute("OverallRating", overallRatingCourse);
		 model.addAttribute("allSurveys", returnSurveys);
		 model.addAttribute("course", course);
		 model.addAttribute("studentId", studentId);
		return "courseDetail";
	
	}

}
