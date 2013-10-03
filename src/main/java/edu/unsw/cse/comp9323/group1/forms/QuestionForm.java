package edu.unsw.cse.comp9323.group1.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class QuestionForm {
	
	private String title;
	private int surveyId;
	private String id;
	private String response;
	private String questionType;
	
	public QuestionForm(){
		
	}
	
	public QuestionForm(String id, String title, String response, int surveyId, String questionType){
		this.id = id;
		this.surveyId = surveyId;
		this.response = response;
		this.title = title;
		this.questionType = questionType;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
}
