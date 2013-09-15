package edu.unsw.cse.comp9323.group1.models;

public class Survey {
	
	private String id;
	private String title;
	private String created_on;
	private String courseId;
	
	public Survey(){
		
	}
	
	public Survey(String id, 
				  String title,
				  String created_on){
		this.id = id;
		this.title = title;
		this.created_on = created_on;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
}
