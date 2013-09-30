<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script id="tinyhippos-injected">if (window.top.ripple) { window.top.ripple("bootstrap").inject(window, document); }</script>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

    <title>Group1 Course Detail Page</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="navbar.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.raty.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  </head>
<body>
<script>
 

 	//load Star Jquery
	
	$(function() {
		$('#UserStar').raty({ path: 'resources/imgs/', size: 24,target: '#UserRating',
		hints: [null, null, null, null, null],
		score: ${UserRating
		click: function(score, evt) {
		$.ajax({
		        type: "POST",
		        url: "./Rating/",
		        data: {courseName:'${course.getName()}'
		   	   ,studentID:${studentId}
		   	   ,rating:$("#UserRating").val()
		   	   ,category: 'category1'
		   	   },
		    	dataType: "text",
		        success: function(data) {
		          alert("Rating Updated !!");
		           
		        },
		        error: function(e) {
		          console.log(e);
		        }
		      });
			}
		});
	});
 	
	$(function() {
		$('#OverallStar').raty({ path: 'resources/imgs/', size: 24,target: '#OverallRating',
		hints: [null, null, null, null, null],
		score: ${OverallRating},
		readOnly: true
		});
	});
	
	
	$(function() {
		$('#enrol').click(function() {
			$.ajax({
		        type: "POST",
		        url: "./enrollment",
		        data: {course_id:"${course.getId()}"
		   	   ,student_id:"${studentId}"
		   	   },
		    	dataType: "text",
		        success: function(data) {
		          alert("Enrolled !!");
		        },
		        error: function(e) {
		          alert("Error !!");
		        }
		     });
		});
	});
</script>

<div class="container">

     <%@include file="layouts/studentHeader.jsp" %>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Welcome Student</h1>
        <p>This example is a quick exercise to illustrate how the default, static navbar and fixed to top navbar work. It includes the responsive CSS and HTML, so it also adapts to your viewport and device.</p>
        <p>Course ID   : ${course.getId()} <br></br>
           Course Name : ${course.getName()} <br></br>
		   Course Description : ${course.getDescription()}<br></br>
		   Course Start Date  :	${course.getStartDate()}<br></br>
		   Course Length  :	${course.getLength()}<br></br>
        </p>
        <p> Overall Rating : 
        <div id="OverallStar"></div>
        <input type="hidden" id="OverallRating">
        
        <p> Your Rating : 
        <div id="UserStar"></div>
        <input type="hidden" id="UserRating">
        
        <div><a class="btn btn-lg btn-success" id="enrol" name="enrol">Enrol</a></div>
        <div>
        	<table border="1">
				<c:forEach items="${allSurveys}" var="element">
					<tr>
						<%-- <form:hidden path="id" value="${element.id}"/> --%>
						<td><c:out value="${element.title}" /></td>
						<td><a class="btn btn-primary"
							href="<c:url value="/survey/start?surveyId=${element.id}&studentId=${studentId}&courseName=${course.name}"/>">Take
								Survey</a></td>
					</tr>
				</c:forEach>
			</table>
        </div>
        <p>
          <a href="#" 
  			 onclick="
  				 FB.ui({
    				  method: 'feed',
    				  link: 'http://localhost:8080/ces-1.0-SNAPSHOT/courseDetail/${course.getId()}',
    				  picture: 'http://www.unsw.edu.au/sites/default/files/UNSW.png',
    				  name: 'UNSW Open Courses',
    				  caption: '${course.getName()}',
    				  description: 'This is test Dialog to test facebook sharing.'
    				}, function(response){});">
  				
  				<img src="<c:url value="/resources/imgs/icon_fb.jpg"/>" border="0">
  				
		</a>
		<a href="https://twitter.com/share" class="twitter-share-button" data-url="http://localhost:8080/ces-1.0-SNAPSHOT/student" data-text="Hey, check out this cool Course Evaluation Website : http://localhost:8080/ces-1.0-SNAPSHOT/courseDetail/${course.getId()}" data-count="none" data-via="Faridaji" data-lang="en">Tweet</a>
		
		
		<g:plus action="share"></g:plus>
		<a href="https://plus.google.com/share?url=http://www.unsw.edu.au" onclick="javascript:window.open(this.href,
  '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"><img
  src="https://www.gstatic.com/images/icons/gplus-16.png" alt="Share on Google+"/></a>
		<!-- window.open(
      				'https://www.facebook.com/sharer/sharer.php?u=http://www.yahoo.com',
      				'facebook-share-dialog', 
      				'width=626,height=436'); 
    				return false; -->
		
		<div class="fb-comments" data-href="http://localhost:8080/ces-1.0-SNAPSHOT/courseDetail/${course.getId()}" data-width="470"></div>
        
        </p>
      </div>		
    </div> <!-- /container -->


    

</body>
</html>