package edu.unsw.cse.comp9323.group1.controllers;

import java.net.URISyntaxException;

import org.apache.http.HttpException;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unsw.cse.comp9323.group1.controllers.crawler.CourseExtraction;
public class CourseExtractionController {

	@RequestMapping(value = "/courseExtraction", method = RequestMethod.GET)
	@ResponseBody
	
	public String courseExtract() throws URISyntaxException, HttpException, ParseException{
		RSSFeedParser rss = new RSSFeedParser("");
		rss.getRSSMitCourse();
		
		CourseExtraction ex = new CourseExtraction();
		ex.getAllCourses();
		
		return "adminHome";
	}

}
