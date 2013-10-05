package edu.unsw.cse.comp9323.group1.models;

public class CourseAverageRating {
	private int id;
	private int course_id;
	private double rating;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public double getAverage_rating() {
		return rating;
	}
	public void setAverage_rating(double rating) {
		this.rating = rating;
	}
	public CourseAverageRating(int id, int course_id, double rating) {
		super();
		this.id = id;
		this.course_id = course_id;
		this.rating = rating;
	}
	public CourseAverageRating(int course_id, double rating) {
		super();
		this.course_id = course_id;
		this.rating = rating;
	}
	public CourseAverageRating() {
		super();
	}
}
