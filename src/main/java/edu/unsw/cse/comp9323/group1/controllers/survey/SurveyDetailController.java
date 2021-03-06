package edu.unsw.cse.comp9323.group1.controllers.survey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.QuestionDAO;
import edu.unsw.cse.comp9323.group1.forms.QuestionForm;
import edu.unsw.cse.comp9323.group1.forms.QuestionFormOption;
import edu.unsw.cse.comp9323.group1.models.Question;
import edu.unsw.cse.comp9323.group1.models.QuestionOption;

/**
 * 
 * This class is controller class of survey detail.
 * 
 * @author z3378491.group1.comp9323-2013s1
 *
 */

@Controller
@RequestMapping("/uni/survey/detail")
public class SurveyDetailController {

	/**
	 * This method will open blank page. User can put in the question type and question title they would like to have.
	 * 
	 * @param surveyId
	 * @param model
	 * @return surveyDetail
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String getSurvey(@RequestParam("surveyId") int surveyId, Model model) {
		
		List<Question> allQuestions = new ArrayList<Question>();
		
		QuestionDAO questionDAO = new QuestionDAO();
		allQuestions = questionDAO.getAllQuestion(surveyId);
		
		if (allQuestions.size() > 0){
			allQuestions.remove(allQuestions.size()-1);
		}
		
		QuestionForm questionForm = new QuestionForm();
		List<QuestionFormOption> questionFormOptions = new ArrayList<QuestionFormOption>();
		questionFormOptions.add(new QuestionFormOption("",""));
		questionForm.setQuestionFormOptions(questionFormOptions);
		
		model.addAttribute("allQuestions", allQuestions);
		model.addAttribute("surveyId", surveyId);
		model.addAttribute("questionForm", questionForm);
		
		return "surveyDetail";
 
	}
	
	/**
	 * 
	 * This method handle post HTTP request from user.
	 * It will be invoke when university submit the survey detail.
	 * It opens communication to surveygizmo.com to submit user response.
	 * 
	 * @param questionForm
	 * @param result
	 * @return courseDetail
	 */
	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="questionForm") QuestionForm questionForm,BindingResult result){
		if(result.hasErrors()){
			return "surveyCreate";
		}else{
			
			Question question = new Question();
			question.setSurveyId(questionForm.getSurveyId());
			question.setTitle(questionForm.getTitle());
			question.setSubtype(questionForm.getQuestionType());
			
			if(questionForm.getQuestionFormOptions() != null){
				if(questionForm.getQuestionFormOptions().size() > 0){
					List<QuestionOption> questionOptions = new ArrayList<QuestionOption>();
					Iterator<QuestionFormOption> questionFormItr = questionForm.getQuestionFormOptions().iterator();
					while(questionFormItr.hasNext()){
						QuestionFormOption questionFormOption = questionFormItr.next();
						QuestionOption questionOption = new QuestionOption(questionFormOption.getTitle(), questionFormOption.getValue());
						questionOptions.add(questionOption);
					}
					question.setQuestionOptions(questionOptions);
				}
			}
			
			
			
			QuestionDAO questionDAO = new QuestionDAO();
			questionDAO.createQuestion(question);
			
			return "redirect:/uni/survey/detail?surveyId="+questionForm.getSurveyId();
		}		
	}
}
