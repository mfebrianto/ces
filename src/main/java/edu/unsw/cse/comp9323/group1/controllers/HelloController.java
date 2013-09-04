package edu.unsw.cse.comp9323.group1.controllers;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import java.io.IOException;
import java.util.Date; 

public class HelloController implements Controller { 
    protected final Log logger = LogFactory.getLog(getClass()); 
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        logger.info("Returning hello view"); 
        
        String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);
<<<<<<< HEAD
        //I am Joe Farid
=======
        //I am Farid
>>>>>>> 51ed3d4dfae92a18a6adee48d52bf041a43225c4
        return new ModelAndView("hello", "now", now);
    } 
}
