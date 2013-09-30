package edu.unsw.cse.comp9323.group1.models;

import java.util.List;

public class StudentModel {
	public String id;
	//The user's first name.
	public String FirstName;
	//The user's middle name.
	public String MiddleName;
	//The user's last name.
	public String LastName;
	//The user's gender.
	public String gender;
	//The genders the user is interested in.
	public List<String> Interests;
	
	public List<StudentEduHistoryModel> Educations;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public List<String> getInterests() {
		return Interests;
	}
	public void setInterests(List<String> interests) {
		Interests = interests;
	}
	public List<StudentEduHistoryModel> getEducations() {
		return Educations;
	}
	public void setEducations(List<StudentEduHistoryModel> lstStuHistory) {
		this.Educations = lstStuHistory;
	}
	
	

}
