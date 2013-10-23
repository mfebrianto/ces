package edu.unsw.cse.comp9323.group1.controllers.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.SurveyDAO;
import edu.unsw.cse.comp9323.group1.forms.SurveyForm;
import edu.unsw.cse.comp9323.group1.models.Question;
import edu.unsw.cse.comp9323.group1.models.Survey;

/**
 * This is the main class of admin page.
 * 
 * @author z3378491.group1.comp9323-2013s1
 */

@Controller
@RequestMapping("/uni/survey")
public class SurveyController {

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";

	/**
	 * This method will receive parameter from the web and call DAO.
	 * DAO then will open the connection to database.com and surveygizmo.com
	 * to query all available survey. After it gets all the courses the list 
	 * then will be return to the web
	 * 
	 * @param courseId
	 * @param model
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllAvailableSurvey(@RequestParam("courseId") String courseId,ModelMap model) {
		
		 SurveyDAO surveyDAO = new SurveyDAO();
		
		 List<Survey> allSurveys = new ArrayList<Survey>();
		 allSurveys = surveyDAO.getAllAvailableSurvey();
		
		 /*
		  * get spesifically for the current courseID
		  */
		 List<Survey> listOfSurveys = surveyDAO.getSurveyWithCourseId(courseId);
		 
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
		
		
		model.addAttribute("allSurveys", returnSurveys);
		model.addAttribute("courseId",courseId);
		
		return "survey";
 
	}

	/**
	 * This method receives surveyId that coming from the web when user click delete button
	 * It will open connection to surveygizmo.com to remove the survey then return 
	 * to the course detail page
	 * 
	 * @param surveyId
	 * @param courseId
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteSurvey(@RequestParam("surveyId") int surveyId,
			@RequestParam("courseId") String courseId, Model model) {

		System.out.println("Delete id :" + surveyId);

		SurveyDAO surveyDAO = new SurveyDAO();
		surveyDAO.deleteSurvey(surveyId);

		return "redirect:/uni/survey?courseId=" + courseId;
	}

}
