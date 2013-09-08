package edu.unsw.cse.comp9323.group1.models;

import java.util.List;

import com.restfb.types.User.Education;

public class StudentEduHistory {
	
	//there are two types :  College, Graduate School
	//only postgraduate level has a degree
	//Both have : Concentrations (Can be more than one), 
	//            Classes (Can be more than one) (Not Needed), Year
	
	private String degree;
	private List<StudentConcentrationModel> LstConcentration;
	private String type;
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public List<StudentConcentrationModel> getLstConcentration() {
		return LstConcentration;
	}
	public void setLstConcentration(List<StudentConcentrationModel> lstConcentration) {
		LstConcentration = lstConcentration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
