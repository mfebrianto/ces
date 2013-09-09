package edu.unsw.cse.comp9323.group1.models;

import java.util.List;

import com.restfb.types.User.Education;

public class StudentEduHistoryModel {
	
	//there are two types :  College, Graduate School
	//only postgraduate level has a degree
	//Both have : Concentrations (Can be more than one), 
	//            Classes (Can be more than one) (Not Needed), Year
	private Integer eduID;
	private String degree;
	private String school;
	private List<StudentConcentrationModel> lstConcentration;
	private String type;
	private String year;
	
	public Integer getEduID() {
		return eduID;
	}
	public void setEduID(Integer eduID) {
		this.eduID = eduID;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public List<StudentConcentrationModel> getLstConcentration() {
		return lstConcentration;
	}
	public void setLstConcentration(List<StudentConcentrationModel> lstConcentration) {
		this.lstConcentration = lstConcentration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

}
