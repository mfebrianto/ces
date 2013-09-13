package edu.unsw.cse.comp9323.group1.controllers.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unsw.cse.comp9323.group1.forms.Survey;

@Controller
@RequestMapping("/uni/survey/create")
public class SurveyCreateController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		// model.addAttribute("message", "Spring 3 MVC Hello World");

		return "surveyCreate";

	}

	
	@RequestMapping(value = "/addSurvey", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("survey")
                            Survey survey, BindingResult result) {
         
        System.out.println("Survey Title:" + survey.getTitle());
         
        //return "redirect:contacts.html";
        return "survey";
    }
}
