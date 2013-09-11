package edu.unsw.cse.comp9323.group1.Tools;

import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpPost;

/**
 * Extend the Apache HttpPost method to implement an HttpPost
 * method.
 */  
public class HttpPatch extends HttpPost {
  public HttpPatch(String uri) throws URISyntaxException {
    super(uri);
  }

  public String getMethod() {
    return "PATCH";
  }
}