package edu.unsw.cse.comp9323.group1.models;

import java.util.List;

import com.restfb.types.User.Education;

public class StudentEduHistoryModel {
	
	//there are two types :  College, Graduate School
	//only postgraduate level has a degree
	//Both have : Concentrations (Can be more than one), 
	//            Classes (Can be more than one) (Not Needed), Year
	
	private String degree;
	private String school;
	private List<StudentConcentrationModel> lstConcentration;
	private String type;
	private String year;
	
	
	

	public void test(){
		
		
	}

}
