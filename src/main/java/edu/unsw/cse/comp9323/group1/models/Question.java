package edu.unsw.cse.comp9323.group1.models;

public class Question {
	
	private Long id;
	private int surveyId;
	private String subtype;
	private String title;

	public Question(){
		
	}
	
	public Question(Long id,
			String subtype,
			String title){
		this.id = id;
		this.subtype=subtype;
		this.title=title;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
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

}
