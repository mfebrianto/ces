package edu.unsw.cse.comp9323.group1.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;


public class CourseExtraction {
	protected static String linkString = "http://www.mooc-list.com";
	
 
	public static JsonObject getCourseDetail(String link) throws IOException {
		JsonObject newCourse = new JsonObject();
		Document doc;
		doc = Jsoup.connect(link).timeout(60000).get();
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
		newCourse.addProperty("name__c", courseName);
	    newCourse.addProperty("categories__c", itemList.get(3));
	    newCourse.addProperty("certificates__c", itemList.get(9));
	    newCourse.addProperty("country__c", itemList.get(16));
	    newCourse.addProperty("course_link__c", link);
	    newCourse.addProperty("description__c", itemList.get(4));
	    newCourse.addProperty("estimate_effort__c", itemList.get(7));
	    newCourse.addProperty("forum__c", itemList.get(15));
	    newCourse.addProperty("exam__c", itemList.get(12));
	    newCourse.addProperty("language__c", itemList.get(17));
	    newCourse.addProperty("instructors__c", itemList.get(2));
	    newCourse.addProperty("length__c", itemList.get(6));
	    newCourse.addProperty("peer_assessment__c", itemList.get(10));
	    newCourse.addProperty("prerequesite__c", itemList.get(8));
	    newCourse.addProperty("start_date__c", itemList.get(5));
	    newCourse.addProperty("tags__c", itemList.get(18));
	    newCourse.addProperty("team_project__c", itemList.get(11));
	    newCourse.addProperty("textbook_materials__c", itemList.get(14));
	    newCourse.addProperty("uni__c", itemList.get(1));
	    newCourse.addProperty("video_lecturer__c", itemList.get(13));
//		for (int i = 0; i <= 18; i++) {
//			System.out.println(labelList.get(i) + ":" + itemList.get(i));
//		}
		return newCourse;
	}
	
	public static ArrayList<JsonObject> getListCourses(String link) throws IOException {
		ArrayList<JsonObject> allCourses = new ArrayList<JsonObject>();
		Document doc;
		doc = Jsoup.connect(link).timeout(60000).get();
		Elements main_divs = doc.select("div.view-content");
		if (main_divs.size() == 1)
			return allCourses;
		Elements a_tags = main_divs.get(1).select("a[href]");
		for (Element a : a_tags) {
			
			String str = linkString + a.attr("href");
			allCourses.add(getCourseDetail(str));
		}
		return allCourses;
	}
	
	public static ArrayList<JsonObject> getAllCourses() {
		ArrayList<JsonObject> allCourses = new ArrayList<JsonObject>();
		Document doc;
		try {
			doc = Jsoup.connect(linkString + "/tags/").timeout(60000).get();
			
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
					allCourses.addAll(getListCourses(linkString + a.attr("href")));
					break;
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allCourses;
	}
 
}