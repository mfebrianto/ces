package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import java.util.List;

import edu.unsw.cse.comp9323.group1.DAOs.CourseDAO;
import edu.unsw.cse.comp9323.group1.DAOs.EnrolmentDAO;
import edu.unsw.cse.comp9323.group1.models.Course;
import edu.unsw.cse.comp9323.group1.models.Enrolment;
import edu.unsw.cse.comp9323.group1.models.StudentModel;

/**
 * 
 * This class is session controller after successfully login using facebook.
 * 
 * @author z3399627.group1.comp9323-2013s1
 *
 */

@Controller
@RequestMapping("/student")
public class StudentController {
	
	/**
	 * This method will open student home page.
	 * 
	 * @param model
	 * @return
	 */
 
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		//model.addAttribute("message", "Spring 3 MVC Hello World");
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession session = attr.getRequest().getSession(true);
	    String accesstoken = "";
	    if (session.getAttribute("token") != null){
	    	if(session.getAttribute("loginRole") !=null){
	    		if(session.getAttribute("loginRole").toString() =="uni"){
	    			return "hello";
	    		}
	    		accesstoken = session.getAttribute("token").toString();
		    }
	    	
	    }else{
	    	return "hello";
	    }
	    
	    
		FacebookClient facebookClient = new DefaultFacebookClient(accesstoken);
		System.out.println(accesstoken);
		User user = facebookClient.fetchObject("me", User.class);
		
		if(user==null){
			return "hello";
		}else{
			
			StudentModel studentModel = (StudentModel)session.getAttribute("student");
			
			/*
	    	 * getEnrollment
	    	 */
	    	try {
		    	EnrolmentDAO enrollmentDAO = new EnrolmentDAO();
				List<Enrolment> enrolmentList = enrollmentDAO.getEnrollmentBasedOnStudentId(studentModel.getId());
				
				if (enrolmentList.size() > 0){
					CourseDAO crsDAO = new CourseDAO();
			    	session.setAttribute("courses", crsDAO.getAllIDNameCoursesBasedOnMultipleCourseId(enrolmentList));
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
			
			return "studentHome";
		}
 
	}
 
}
