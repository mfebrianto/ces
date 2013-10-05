package edu.unsw.cse.comp9323.group1.models;

public class QuestionOption {
	
	private String id;
	private String title;
	private String value;

	public QuestionOption(){

	}
	
	public QuestionOption(String title, String value){
		this.title = title;
		this.value = value;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
