package edu.unsw.cse.comp9323.group1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;
import com.restfb.types.User.Education;

import edu.unsw.cse.comp9323.group1.models.StudentConcentrationModel;
import edu.unsw.cse.comp9323.group1.models.StudentEduHistoryModel;
import edu.unsw.cse.comp9323.group1.models.StudentModel;

@Controller
@RequestMapping("/FBLogin")
@SessionAttributes({"review", "externalReview"})
public class FBLoginController {
	@RequestMapping(value = "/{accesstoken}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String accesstoken, ModelMap model) {
 
		
		model.addAttribute("accesstoken", accesstoken);
		System.out.println(accesstoken);
		FacebookClient facebookClient = new DefaultFacebookClient(accesstoken);
		
		User user = facebookClient.fetchObject("me", User.class);
		//Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		//String query = "SELECT * FROM user WHERE uid = "+user.getId();
		
		//user = facebookClient.executeFqlQuery(query, User.class);
		//System.out.println(query);
		//Page page = facebookClient.fetchObject("cocacola", Page.class);

		System.out.println("User name: " + user.getName());
		model.addAttribute("Uname", user.getName());
		
		StudentModel student = new StudentModel();
		
		student.setFirstName(user.getFirstName());
		student.setMiddleName(user.getMiddleName());
		student.setLastName(user.getLastName());
		
		student.setGender(user.getGender());
		student.setId(user.getId());
		student.setInterests(user.getInterestedIn());
		
		
		List<Education> lstEdu = user.getEducation();//facebookClient.executeFqlQuery(query, FqlUser.class);
		
		System.out.println("Number Of Education : " + lstEdu.get(0).getType());
		
		//Connection<Education> myEducations = facebookClient.fetchConnection("me?fields=education", Education.class); 
		//List<Education> lstEdux = myEducations.getData();
		
		List<StudentEduHistoryModel> lstStuHistory = new ArrayList<StudentEduHistoryModel>();
		
		for(Education edu : lstEdu){
			StudentEduHistoryModel StuHistory = new StudentEduHistoryModel();
			if(edu.getDegree()!=null){
			StuHistory.setDegree(edu.getDegree().getName());}
			StuHistory.setSchool(edu.getSchool().getName());
			StuHistory.setType(edu.getType());
			if(edu.getYear()!=null){
			StuHistory.setYear(edu.getYear().getName());
				}
			List<StudentConcentrationModel> lstConcentration = new ArrayList<StudentConcentrationModel>();
			for(NamedFacebookType x : edu.getConcentration()){
				StudentConcentrationModel concentration = new StudentConcentrationModel();
				concentration.setId(x.getId());
				concentration.setName(x.getName());
				lstConcentration.add(concentration);
			}
			
			StuHistory.setLstConcentration(lstConcentration);
			lstStuHistory.add(StuHistory);
			
		}
		student.setEducations(lstStuHistory);
		model.addAttribute("student", student);
		return "FBUserDetail";
 
	}
 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {
		return "FBLogin";
 
	}
	
	/*public class FqlUser {
		  @Facebook
		  String uid;
		  
		  @Facebook
		  String name;

		  @Override
		  public String toString() {
		    return String.format("%s (%s)", name, uid);
		  }
		}*/

}

