package edu.unsw.cse.comp9323.group1.models;

import com.restfb.Facebook;

public class fqlUser {
	
		  @Facebook
		  public String uid;

		  @Facebook
		  public String username;
		  
		  @Facebook
		  private String first_name;

		  @Facebook
		  public String middle_name;
		  
		  @Facebook
		  public String last_name;
		  
		  @Facebook
		  public String sex;
		  
		  @Facebook
		  public String interests;

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		  
		 // @Facebook
		 // Object[] education;
		  
		
}
