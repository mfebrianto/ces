package edu.unsw.cse.comp9323.group1.models;

public class Enrolment {
	int id;
	String s_id;
	String c_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public Enrolment(String s_id, String c_id) {
		super();
		this.s_id = s_id;
		this.c_id = c_id;
	}
	public Enrolment() {
		super();
	}
	

}
