package edu.unsw.cse.comp9323.group1.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unsw.cse.comp9323.group1.controllers.crawler.CourseExtraction;
//import edu.unsw.cse.comp9323.group1.controllers.crawler.RSSFeedParser;

/**
 * 
 * This class is controller class of survey detail.
 * 
 * @author z3402013.group1.comp9323-2013s1
 *
 */

@Controller
@RequestMapping("/courseExtraction")
public class CourseExtractionController {

	/**
	 * This method will parse all available courses that coming from RSS mit-newcourses.xml
	 * 
	 * @return
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws ParseException
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	
	public List<JSONObject> courseExtract() throws URISyntaxException, HttpException, ParseException{
	    List<JSONObject> result = new ArrayList<JSONObject>();
//	    JSONObject tmp = new JSONObject();
//	    tmp.put("alll", "aaa");
//	    result.add(tmp);
		RSSFeedParser rss = new RSSFeedParser("http://ocw.mit.edu/rss/new/mit-newcourses.xml");
		result.addAll(rss.getRSSMITCourses());
		
//		result.addAll(CourseExtraction.getAllCourses());
		
		return result;
//	    return "{\"response\": \"true\"}";
	}

}
