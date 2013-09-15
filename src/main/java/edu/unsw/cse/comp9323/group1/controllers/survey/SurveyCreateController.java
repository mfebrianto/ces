package edu.unsw.cse.comp9323.group1.controllers.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unsw.cse.comp9323.group1.DAOs.SurveyDAO;
import edu.unsw.cse.comp9323.group1.forms.QuestionForm;
import edu.unsw.cse.comp9323.group1.forms.SurveyForm;
import edu.unsw.cse.comp9323.group1.models.Survey;

@Controller
@RequestMapping(value="/uni/survey/create")
public class SurveyCreateController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(ModelMap model){
		SurveyForm survey = new SurveyForm();
		model.addAttribute("SURVEY", survey);
		return "surveyCreate";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="SURVEY") SurveyForm surveyForm,BindingResult result){
		if(result.hasErrors()){
			return "surveyCreate";
		}else{
			
			System.out.println("Controller - title of the survey :"+surveyForm.getTitle());
			
			SurveyDAO surveyDAO = new SurveyDAO();
			
			Survey survey = new Survey();
			survey.setTitle(surveyForm.getTitle());
			survey.setCourseId(surveyForm.getCourseId());
			
			survey.setId(surveyDAO.createSurvey(survey));
			surveyDAO.saveSurvey(survey);
			
			
			return "redirect:/uni/survey?courseId="+surveyForm.getCourseId();
		}		
	}
}
