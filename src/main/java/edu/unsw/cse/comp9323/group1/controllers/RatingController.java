package edu.unsw.cse.comp9323.group1.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.unsw.cse.comp9323.group1.DAOs.QuestionDAO;
import edu.unsw.cse.comp9323.group1.DAOs.RatingDAO;
import edu.unsw.cse.comp9323.group1.forms.QuestionForm;
import edu.unsw.cse.comp9323.group1.models.Question;
import edu.unsw.cse.comp9323.group1.models.Rating;

@Controller
@RequestMapping("/Rating")
public class RatingController {

	@RequestMapping(value = "/", method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="rating") Rating rating,BindingResult result) throws UnsupportedEncodingException{
		if(result.hasErrors()){
			return "studentHome";
		}else{
			
			RatingDAO ratingDAO = new RatingDAO();
			boolean success = ratingDAO.updateRating(rating.getStudentID(), rating.getCourseName(), rating.getRating(), rating.getCategory());
			
			
			System.out.println("StudentID : " + rating.getStudentID());
			System.out.println("Course Name : " + rating.getCourseName());
			System.out.println("Rating : " + rating.getRating());
			System.out.println("Category : " + rating.getCategory());
			
			
			return "studentHome";
		}		
	}
	
	
}
