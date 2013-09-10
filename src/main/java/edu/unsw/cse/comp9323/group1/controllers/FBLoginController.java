package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;
import com.restfb.types.User.Education;

import edu.unsw.cse.comp9323.group1.Tools.InitializeREST;
import edu.unsw.cse.comp9323.group1.Tools.RestGet;
import edu.unsw.cse.comp9323.group1.models.StudentConcentrationModel;
import edu.unsw.cse.comp9323.group1.models.StudentEduHistoryModel;
import edu.unsw.cse.comp9323.group1.models.StudentModel;
import edu.unsw.cse.comp9323.group1.models.fqlUser;

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
		
		//Alternative by using Facebook Query Language (FQL)
		
		/*String query = "SELECT uid,username,first_name,middle_name,last_name,sex,interests FROM user WHERE uid = me()";
		System.out.println(query);
		List<fqlUser> users = facebookClient.executeFqlQuery(query, fqlUser.class);
		fqlUser user = users.get(0);*/
		
		//Another alternative is using fetchConnection
		/* Connection<Education> myEducations = facebookClient.fetchConnection("me?fields=education", Education.class); 
		List<Education> lstEdux = myEducations.getData();*/
		
		System.out.println("User name: " + user.getFirstName());
		model.addAttribute("Uname", user.getName());
		
		StudentModel student = new StudentModel();
		
		student.setFirstName(user.getFirstName());
		student.setMiddleName(user.getMiddleName());
		student.setLastName(user.getLastName());
		
		student.setGender(user.getGender());
		student.setId(user.getId());
		student.setInterests(user.getInterestedIn());
		
		
		List<Education> lstEdu = user.getEducation();
		
		System.out.println("Type Of Education : " + lstEdu.get(0).getType());
		
		
		
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
		
		InitializeREST initREST = new InitializeREST();
		try {
			initREST.Init();
			String tableName = "course_detail__c";
			
			//space character translated into %20
			String newRestUri = initREST.getRestUri() + "/query/?q=" + URLEncoder.encode("SELECT id__c FROM course_detail__c WHERE id__c =","UTF-8");//"/sobjects/"+tableName+"/describe/";
			
			RestGet restGet = new RestGet();
			String result = restGet.getUsingQuery(newRestUri, initREST.getOauthHeader(), "");
			System.out.println(result);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "FBUserDetail";
 
	}
 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {
		return "FBLogin";
 
	}
	
	@RequestMapping(value = "/FBLoginTest", method = RequestMethod.GET)
	public String test(ModelMap model) {
		//System.out.println(surveyId);
		return "FBLoginTest";
 
	}
	
	
}


