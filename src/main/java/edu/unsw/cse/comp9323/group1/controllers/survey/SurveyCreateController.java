package edu.unsw.cse.comp9323.group1.controllers.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unsw.cse.comp9323.group1.forms.Survey;

@Controller
@RequestMapping(value="/uni/survey/create")
public class SurveyCreateController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(ModelMap model){
		Survey survey = new Survey();
		model.addAttribute("SURVEY", survey);
		return "surveyCreate";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="SURVEY") Survey survey,BindingResult result){
		if(result.hasErrors()){
			return "surveyCreate";
		}else{
			System.out.println("User values is : " + survey.getTitle());
			return "surveyCreate";
		}		
	}
}
