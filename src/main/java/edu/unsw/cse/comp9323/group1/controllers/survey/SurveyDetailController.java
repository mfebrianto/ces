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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.QuestionDAO;
import edu.unsw.cse.comp9323.group1.DAOs.SurveyDAO;
import edu.unsw.cse.comp9323.group1.forms.QuestionForm;
import edu.unsw.cse.comp9323.group1.forms.SurveyForm;
import edu.unsw.cse.comp9323.group1.models.Question;
import edu.unsw.cse.comp9323.group1.models.Survey;

@Controller
@RequestMapping("/uni/survey/detail")
public class SurveyDetailController {

	private String userPass = "michaelfebrianto@gmail.com:C0urs3Evalu@t10n!";
	
	@RequestMapping(method = RequestMethod.GET)
	public String getSurvey(@RequestParam("surveyId") int surveyId, Model model) {
		
		List<Question> allQuestions = new ArrayList<Question>();
		
		QuestionDAO questionDAO = new QuestionDAO();
		allQuestions = questionDAO.getAllQuestion(surveyId);
		
		if (allQuestions.size() > 0){
			allQuestions.remove(allQuestions.size()-1);
		}
		
		model.addAttribute("allQuestions", allQuestions);
		model.addAttribute("surveyId", surveyId);
		
		return "surveyDetail";
 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="SHORT_Q") QuestionForm questionForm,BindingResult result){
		if(result.hasErrors()){
			return "surveyCreate";
		}else{
			
			Question question = new Question();
			question.setSurveyId(questionForm.getSurveyId());
			question.setTitle(questionForm.getTitle());
			
			QuestionDAO questionDAO = new QuestionDAO();
			questionDAO.createQuestion(question);
			
			return "redirect:/uni/survey/detail?surveyId="+questionForm.getSurveyId();
		}		
	}
}
