package edu.unsw.cse.comp9323.group1.controllers.survey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unsw.cse.comp9323.group1.DAOs.QuestionDAO;
import edu.unsw.cse.comp9323.group1.forms.QuestionForm;
import edu.unsw.cse.comp9323.group1.forms.QuestionListForm;
import edu.unsw.cse.comp9323.group1.models.Question;

@Controller
@RequestMapping("/survey/start")
public class SurveyStartController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getSurvey(@RequestParam("surveyId") int surveyId,@RequestParam("studentId") int studentId, Model model) {
	
		List<Question> allQuestions = new ArrayList<Question>();
		
		QuestionDAO questionDAO = new QuestionDAO();
		allQuestions = questionDAO.getAllQuestion(surveyId);
		
		if (allQuestions.size() > 0){
			allQuestions.remove(allQuestions.size()-1);
		}
		
		QuestionListForm responseListForm = new QuestionListForm();
		List<QuestionForm> questionListTmp = new ArrayList<QuestionForm>();
		Iterator<Question> allQuestionsItr = allQuestions.iterator();
		while(allQuestionsItr.hasNext()){
			Question questionTemp = allQuestionsItr.next();
			QuestionForm questionForm = new QuestionForm(questionTemp.getId().toString(), questionTemp.getTitle(),"",surveyId);
			questionListTmp.add(questionForm);
		}
		
		
		responseListForm.setQuestionForm(questionListTmp);
		
		model.addAttribute("responseListForm", responseListForm);
		model.addAttribute("surveyId", surveyId);
		
		return "surveyStart";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@Valid QuestionForm questionForm, @ModelAttribute(value="SHORT_Q_RESPONSE") QuestionListForm questionListForm,BindingResult result){
		if(result.hasErrors()){
			return "surveyCreate";
		}else{
			
			System.out.println(">>>>>SIZE>>>>"+questionListForm.getQuestionForm().size());
			
			return "redirect:/courseDetail?courseName=Category Theory for Scientists (MIT)&studentId=594128691";
		}		
	}

}
