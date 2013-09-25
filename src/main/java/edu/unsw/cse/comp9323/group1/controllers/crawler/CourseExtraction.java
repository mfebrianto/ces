package edu.unsw.cse.comp9323.group1.controllers.crawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.unsw.cse.comp9323.group1.DAOs.RestClient;


public class CourseExtraction {
	protected static String linkString = "http://www.mooc-list.com";
	protected static RestClient client = new RestClient();
	protected static List<JSONObject> listCourses;

	@SuppressWarnings("unchecked")
	public static void getCourseDetail(String link) throws IOException, HttpException, URISyntaxException, ParseException {
		JSONObject newCourse = new JSONObject();
		Document doc;
		doc = Jsoup.connect(link).timeout(0).get();
		Element titleElement = doc.select("h1[id^=page-title]").first();
		Element mainContent = doc.select("div.node-content").first();
		String courseName = titleElement.text();
		Elements labelElements = mainContent.select("h3.field-label, p.field-label");
		Elements itemElements = mainContent.select("div.field-items, ul.field-items");
		ArrayList<String> labelList = new ArrayList<String>(19);
		ArrayList<String> itemList = new ArrayList<String>(19);
		for (Element e: labelElements) {
			if (labelList.size() == 4)
				labelList.add("Description");
			labelList.add(e.text().substring(0, e.text().length() - 2));
			
		}
		
		for (Element e: itemElements) {
			if (itemList.size() == 19)
				break;
			itemList.add(e.text());
		}
//		System.out.println(courseName);
		int max = itemList.get(4).length() < 1000 ? itemList.get(4).length() : 1000;
		newCourse.put("name__c", courseName);
	    newCourse.put("categories__c", itemList.get(3));
	    newCourse.put("certificates__c", itemList.get(9));
	    newCourse.put("country__c", itemList.get(16));
	    newCourse.put("course_link__c", link);
	    newCourse.put("description__c", itemList.get(4).substring(0, max));
	    newCourse.put("estimate_effort__c", itemList.get(7));
	    newCourse.put("forum__c", itemList.get(15));
	    newCourse.put("exam__c", itemList.get(12));
	    newCourse.put("language__c", itemList.get(17));
	    newCourse.put("instructors__c", itemList.get(2));
	    newCourse.put("length__c", itemList.get(6));
	    newCourse.put("peer_assessment__c", itemList.get(10));
	    newCourse.put("prerequesite__c", itemList.get(8));
	    newCourse.put("start_date__c", itemList.get(5));
	    newCourse.put("tags__c", itemList.get(18));
	    newCourse.put("team_project__c", itemList.get(11));
	    newCourse.put("textbook_materials__c", itemList.get(14));
	    newCourse.put("uni__c", itemList.get(1));
	    newCourse.put("video_lecturer__c", itemList.get(13));
//		for (int i = 0; i <= 18; i++) {
//			System.out.println(labelList.get(i) + ":" + itemList.get(i));
//		}
	    courseName = courseName.replaceAll("\"", "");
	    courseName = courseName.replaceAll("\'", "");
	    courseName = courseName.replace(' ', '+');
	    String response = client.restGet("/query/?q=SELECT+name__c+FROM+course_detail__c+WHERE+name__c+=+\'"+ courseName + "\'");
	    if (response.contains("errorCode")) {
	    	System.out.println(courseName + " has errors: " + response);
	    	return;
	    }
	    JsonParser jsonParser = new JsonParser();
	    JsonElement jsonElement = jsonParser.parse(response);
    
	    String total = jsonElement.getAsJsonObject().get("totalSize").getAsString();
	    if (total.compareTo("0") == 0) {
	    	response = client.restPost("/sobjects/course_detail__c/", newCourse.toString());
//	    	jsonElement = jsonParser.parse(response);
//	    	String success = jsonElement.getAsJsonObject().get("success").getAsString();
//	    	String errors = jsonElement.getAsJsonObject().get("errors").getAsString();
	    	if (!response.contains("errorCode")) {
	    		listCourses.add(newCourse);
	    		System.out.println("Inserted new course: " + courseName);
	    	}
	    	else {
	    		System.out.println(courseName + " has errors: " + response);
			}
	    }
	}
	
	public static void getListCourses(String link) throws IOException, HttpException, URISyntaxException, ParseException {
		Document doc;
		doc = Jsoup.connect(link).timeout(0).get();
		Elements main_divs = doc.select("div.view-content");
		if (main_divs.size() == 1)
			return;
		Elements a_tags = main_divs.get(1).select("a[href]");
		for (Element a : a_tags) {
			
			String str = linkString + a.attr("href");
			getCourseDetail(str);
		}
	}
	
	public static List<JSONObject> getAllCourses() throws HttpException, URISyntaxException, ParseException {
		Document doc;
		try {


//			System.setProperty("http.proxyHost", "147.167.10.2");//replace with your proxy host
//			System.setProperty("http.proxyPort", "8080");//replace with your proxy port
			doc = Jsoup.connect(linkString + "/tags/").timeout(0).get();
			
			Element page_div = doc.select("div.item-list").first();
			Element page_a_tagsElement = page_div.select("a[href]").last();
			
			int index = page_a_tagsElement.attr("href").indexOf('=');
			int max_page = Integer.parseInt(page_a_tagsElement.attr("href").substring(index + 1));
			//System.out.println(max_page);
			for (int i = 0; i <= max_page; i++) {
				doc = Jsoup.connect(linkString + "/tags?page=" + i).get();
				Element main_div = doc.select("div.view-content").first();
				Elements a_tags = main_div.select("a[href]");
				for (Element a : a_tags) {
					getListCourses(linkString + a.attr("href"));
//					break;
				}
//				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listCourses;
	}
 

	public static void getMoocListCourse() throws URISyntaxException, HttpException, ParseException {
	    //RestClient client = new RestClient();
	    client.oauth2Login( client.getUserCredentials());
	    getAllCourses();
	}

//	public static void main(String[] args) throws URISyntaxException, HttpException, ParseException {
//	    //RestClient client = new RestClient();
//	    client.oauth2Login( client.getUserCredentials());
//	    getAllCourses();
//	}

}