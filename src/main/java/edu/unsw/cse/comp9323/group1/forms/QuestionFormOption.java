package edu.unsw.cse.comp9323.group1.forms;

public class QuestionFormOption {
	
	private String title;
	private String value;
	
	public QuestionFormOption(){

	}
	
	public QuestionFormOption(String title, String value){
		this.title = title;
		this.value = value;
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
