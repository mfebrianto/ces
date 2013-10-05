package edu.unsw.cse.comp9323.group1.models;

public class CourseAverageRating {
	private int id;
	private String course_name;
	private double rating;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public CourseAverageRating(int id, String course_name, double rating) {
		super();
		this.id = id;
		this.course_name = course_name;
		this.rating = rating;
	}
	public CourseAverageRating(String course_name, double rating) {
		super();
		this.course_name = course_name;
		this.rating = rating;
	}
	public CourseAverageRating() {
		super();
	}
	
	
}
