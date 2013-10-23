package edu.unsw.cse.comp9323.group1.controllers;

/**
 * 
 * This class is controller class for student enrollment
 * 
 * @author z3402013.group1.comp9323-2013s1
 *
 */

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.json.Json;
import com.google.gson.JsonObject;

import edu.unsw.cse.comp9323.group1.DAOs.EnrolmentDAO;
import edu.unsw.cse.comp9323.group1.models.Enrolment;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {
	
	/**
	 * 
	 * This method will send POST request to database.com to add record for student id and the course id.
	 * 
	 * @param courseId
	 * @param studentId
	 * @return courseDetail
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 * @throws HttpException
	 */

	@RequestMapping(method = RequestMethod.POST)
	public String enrolACourse(@RequestParam("course_id") String courseId, @RequestParam("student_id") String studentId) throws UnsupportedEncodingException, URISyntaxException, HttpException {
		EnrolmentDAO eDao = new EnrolmentDAO();
		
		if (eDao.updateEnrollment(studentId, courseId, "new"))
			return "enrollment";
		else
			return "enrollment";
	}

}
