package edu.unsw.cse.comp9323.group1.models;

import com.restfb.types.User.Education;

public class StudentEduHistory {
	
	//there are two types :  College, Graduate School
	//only postgraduate level has a degree
	//Both have : Concentrations (Can be more than one), 
	//            Classes (Can be more than one) (Not Needed), Year
	
	private String degree;
	private List<StudentConcentration> LstConcentration;
	private String type;
	
	
	
	
	public void test(){
		Education edu = new Education();
		edu.getDegree().getName();
		
		edu.getConcentration().isEmpty();
		
		edu.ge
		edu.getSchool()
		edu.getType()
		edu.getYear().getName();
		
	}

}
