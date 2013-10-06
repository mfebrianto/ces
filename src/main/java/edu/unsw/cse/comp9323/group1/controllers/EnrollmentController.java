package edu.unsw.cse.comp9323.group1.controllers;

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

	@RequestMapping(method = RequestMethod.POST)
	public String enrolACourse(@RequestParam("course_id") String courseId, @RequestParam("student_id") String studentId) throws UnsupportedEncodingException, URISyntaxException, HttpException {
		EnrolmentDAO eDao = new EnrolmentDAO();
		
		if (eDao.updateEnrollment(studentId, courseId, "new"))
			return "enrollment";
		else
			return "enrollment";
	}

}
