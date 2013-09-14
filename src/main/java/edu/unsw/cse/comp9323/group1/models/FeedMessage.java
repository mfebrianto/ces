package edu.unsw.cse.comp9323.group1.models;

/*
 * Represents one RSS message
 */
public class FeedMessage {

	String title;
	String description;
	String link;
	String creator;
	String date;
	String relation;
	String language;
	String subject;
	String publisher;
	String rights;
  
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public String getCreator() {
		return creator;
	}



	public void setCreator(String creator) {
		this.creator = creator;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getRelation() {
		return relation;
	}



	public void setRelation(String relation) {
		this.relation = relation;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getPublisher() {
		return publisher;
	}



	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}



	public String getRights() {
		return rights;
	}



	public void setRights(String rights) {
		this.rights = rights;
	}



	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", description=" + description
				+ ", link=" + link + ", creator=" + creator + ", date=" + date
				+ ", relation=" + relation + ", language=" + language
				+ ", subject=" + subject + ", publisher=" + publisher
				+ ", rights=" + rights + "]";
	}
} 
