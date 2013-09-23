package edu.unsw.cse.comp9323.group1.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import org.apache.http.HttpException;
import org.json.simple.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import edu.unsw.cse.comp9323.group1.models.FeedMessage;
import edu.unsw.cse.comp9323.group1.Tools.RestClient;

public class RSSFeedParser {
	static final String TITLE = "title";
	static final String ITEM = "item";
	static final String DESCRIPTION = "description";
	static final String LANGUAGE = "language";
	static final String CREATOR = "creator";
	static final String LINK = "link";
	static final String RELATION = "relation";
	static final String SUBJECT = "subject";
	static final String DATE = "date";
	static final String PUBLISHER = "publisher";
	static final String RIGHTS = "rights";

	final URL url;

	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<FeedMessage> readFeed() {
		List<FeedMessage> result = new ArrayList<FeedMessage>();
		try {
			String description = "";
			String title = "";
			String link = "";
			String creator = "";
			String date = "";
			String relation = "";
			String language = "";
			String subject = "";
			String publisher = "";
			String rights = "";
			
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				// Set header values intial to the empty string
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName()
							.getLocalPart();
					if (localPart.compareTo(TITLE) == 0)
						title = getCharacterData(event, eventReader);
					else if (localPart.compareTo(DESCRIPTION) == 0)
						description = getCharacterData(event, eventReader);
					else if (localPart.compareTo(LINK) == 0)
						link = getCharacterData(event, eventReader);
					else if (localPart.compareTo(CREATOR) == 0)
	    			  	creator += getCharacterData(event, eventReader) + " ";
					else if (localPart.compareTo(LANGUAGE) == 0)
						language = getCharacterData(event, eventReader);
					else if (localPart.compareTo(DATE) == 0)
						date = getCharacterData(event, eventReader);
					else if (localPart.compareTo(RELATION) == 0)
						relation += getCharacterData(event, eventReader) + " ";
					else if (localPart.compareTo(RIGHTS) == 0)
						rights = getCharacterData(event, eventReader);
					else if (localPart.compareTo(SUBJECT) == 0)
						subject += getCharacterData(event, eventReader) + " ";
					else if (localPart.compareTo(PUBLISHER) == 0)
						publisher = getCharacterData(event, eventReader);
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().compareTo(ITEM) == 0) {
						int pos = title.indexOf(" ");
						title = title.substring(pos + 1);
						FeedMessage message = new FeedMessage();
						message.setCreator(creator);
						message.setDate(date);
						message.setDescription(description);
						message.setLanguage(language);
						message.setLink(link);
						message.setPublisher(publisher);
						message.setRelation(relation);
						message.setRights(rights);
						message.setSubject(subject);
						message.setTitle(title);
						result.add(message);
						event = eventReader.nextEvent();
						subject = "";
						creator = "";
						relation = "";
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
      throws XMLStreamException {
    String result = "";
    event = eventReader.nextEvent();
    if (event instanceof Characters) {
      result = event.asCharacters().getData();
    }
    return result;
  }

  private InputStream read() {
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  
  @SuppressWarnings("unchecked")
	public void getRSSMitCourse() throws URISyntaxException, HttpException {
	  	RestClient client = new RestClient();
		String linkString = "http://ocw.mit.edu/rss/new/mit-newcourses.xml";
		//Login to the system.
		client.oauth2Login( client.getUserCredentials());
			
	    RSSFeedParser parser = new RSSFeedParser(linkString);
	    List<FeedMessage> allFeeds = parser.readFeed();
	    for (FeedMessage message : allFeeds) {
		    JSONObject newCourse = new JSONObject();
		    newCourse.put("name__c", message.getTitle());
		    newCourse.put("categories__c", message.getSubject());
		    newCourse.put("certificates__c", "Not given");
		    newCourse.put("country__c", "Not given");
		    newCourse.put("course_link__c", message.getLink());
		    newCourse.put("description__c", message.getDescription());
		    newCourse.put("estimate_effort__c", "Not given");
		    newCourse.put("forum__c", "Not given");
		    newCourse.put("exam__c", "Not given");
		    newCourse.put("language__c", message.getLanguage());
		    newCourse.put("instructors__c", "Not given");
		    newCourse.put("length__c", "Not given");
		    newCourse.put("peer_assessment__c", "Not given");
		    newCourse.put("prerequesite__c", "Not given");
		    newCourse.put("start_date__c", message.getDate());
		    newCourse.put("tags__c", message.getRelation());
		    newCourse.put("team_project__c", "Not given");
		    newCourse.put("textbook_materials__c", "Not given");
		    newCourse.put("uni__c", message.getPublisher());
		    newCourse.put("video_lecturer__c", "Not given");
		    
		    String courseName = message.getTitle();
		    courseName = courseName.replaceAll("\"", "");
		    courseName = courseName.replaceAll("\'", "");
		    courseName = courseName.replace(' ', '+');
		    
		    String response = client.restGet(client.getRestUri() + "/query/?q=SELECT+name__c+FROM+course_detail__c+WHERE+name__c+=+\'"+ courseName + "\'");
		    if (response.contains("errorCode")) {
		    	System.out.println(courseName + " has errors: " + response);
		    	return;
		    }
		    JsonParser jsonParser = new JsonParser();
		    JsonElement jsonElement = jsonParser.parse(response);
	    
		    String total = jsonElement.getAsJsonObject().get("totalSize").getAsString();
		    if (total.compareTo("0") == 0) {
		    	response = client.restPost(client.getRestUri() + "/sobjects/course_detail__c/", newCourse.toString());
		    	if (!response.contains("errorCode"))
		    		System.out.println("Inserted new course: " + courseName);
		    	else {
		    		System.out.println(courseName + " has errors: " + response);
				}
		    }
	    }
	}
} 