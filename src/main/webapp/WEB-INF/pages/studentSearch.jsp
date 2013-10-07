<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<script id="tinyhippos-injected">if (window.top.ripple) { window.top.ripple("bootstrap").inject(window, document); }</script>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

    <title>Group1 Course Evaluation</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="navbar.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body style="">
  <script>
  window.fbAsyncInit = function() {
  FB.init({
    appId      : '575842165811798', // App ID
    channelUrl : '/channel.html', // Channel File
    status     : true, // check login status
    cookie     : true, // enable cookies to allow the server to access the session
    xfbml      : true  // parse XFBML
  });
  
  FB.getLoginStatus(function(response) {
  	if (response.status === 'connected') {
  	    
  	    var uid = response.authResponse.userID;
  	    var accessToken = response.authResponse.accessToken;
  	    console.log("Your Access Token : " + accessToken);
  	    
  	    //window.location.href = "http://localhost:8080/ces-1.0-SNAPSHOT/FBLogin/"+accessToken;
  	    //window.opener.location.href="http://localhost:8080/ces-1.0-SNAPSHOT/FBLogin/"+accessToken;
  	    //self.close();
  	  } else if (response.status === 'not_authorized') {
  	    // the user is logged in to Facebook, 
  	    // but has not authenticated your app
  	    self.close();
  		window.opener.alert("Application Not Yet Authorized");
  	    
  	    
  	  } else {
  		self.close();
  	    // the user isn't logged in to Facebook.
  	  }
  
  });

  
  };

  // Load the SDK asynchronously
  (function(d){
   var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
   if (d.getElementById(id)) {return;}
   js = d.createElement('script'); js.id = id; js.async = true;
   js.src = "//connect.facebook.net/en_US/all.js";
   ref.parentNode.insertBefore(js, ref);
  }(document));
  
  window.___gcfg = {
	  lang: 'en-US',
      parsetags: 'explicit'
  };

  (function() {
	  var po = document.createElement('script'); 
	  po.type = 'text/javascript'; 
	  po.async = true;
	  po.src = 'https://apis.google.com/js/plusone.js';
	  var s = document.getElementsByTagName('script')[0]; 
	  s.parentNode.insertBefore(po, s);
  })();
  
  !function(d,s,id){
	  var js,fjs=d.getElementsByTagName(s)[0];
	  if(!d.getElementById(id)){
		  js=d.createElement(s);
		  js.id=id;
		  js.src="https://platform.twitter.com/widgets.js";
		  fjs.parentNode.insertBefore(js,fjs);
		  }
	  }(document,"script","twitter-wjs");
	  
	  function getParamBag(url){
		    return {
		      action: "share",
		      href: url
		    };
		  }
	  

</script>


<div class="container">
	<%-- <%@include file="layouts/studentHeader.jsp" %> --%>
     <jsp:include page="layouts/studentHeader.jsp">
     	<jsp:param name="studentId" >  
	      <jsp:attribute name="value" >  
	         <c:out value="${student.id}"/>  
	      </jsp:attribute>  
	  	</jsp:param>
     </jsp:include>

      <!-- Main component for a primary marketing message or call to action -->
     <div class="jumbotron">
        <h1>Search</h1>
        	<button id="logout" >Logout</button>
        <p>
        <form method="post" action="/ces-1.0-SNAPSHOT/studentSearch?studentId=${student.id}">
			<table>
				<tr>
					<td style="padding: 10px 0px">Course name</td>
					<td style="padding: 10px 0px"><input type="text" name="searchedText" value="${searchedText}"></td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Uni name</td>
					<td style="padding: 10px 0px"><input type="text" name="uni_name"></td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Category</td>
					<td style="padding: 10px 0px"><input type="text" name="category"></td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Uni Average rating</td>
					<td style="padding: 10px 0px">&nbsp Min<select name="average_rating_min">
			  			<option value="0" selected>0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5">5</option>
						</select>
						&nbsp Max
						<select name="average_rating_max">
			  			<option value="0">0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5" selected>5</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Uni Reputation Average rating</td>
					<td style="padding: 10px 0px">&nbsp Min<select name="average_reputation_rating_min">
			  			<option value="0" selected>0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5">5</option>
						</select>
						&nbsp Max
						<select name="average_reputation_rating_max">
			  			<option value="0">0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5" selected>5</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Uni Teaching Average rating</td>
					<td style="padding: 10px 0px">&nbsp Min<select name="average_teaching_rating_min">
			  			<option value="0" selected>0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5">5</option>
						</select>
						&nbsp Max
						<select name="average_teaching_rating_max">
			  			<option value="0">0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5" selected>5</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Uni Research Average rating</td>
					<td style="padding: 10px 0px">&nbsp Min<select name="average_research_rating_min">
			  			<option value="0" selected>0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5">5</option>
						</select>
						&nbsp Max
						<select name="average_research_rating_max">
			  			<option value="0">0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5" selected>5</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Uni Administrators Average rating</td>
					<td style="padding: 10px 0px">&nbsp Min<select name="average_administrators_rating_min">
			  			<option value="0" selected>0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5">5</option>
						</select>
						&nbsp Max
						<select name="average_administrators_rating_max">
			  			<option value="0">0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5" selected>5</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding: 10px 0px">Uni Lecture Notes Average rating</td>
					<td style="padding: 10px 0px">&nbsp Min<select name="average_lecture_notes_rating_min">
			  			<option value="0" selected>0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5">5</option>
						</select>
						&nbsp Max
						<select name="average_lecture_notes_rating_max">
			  			<option value="0">0</option>
			  			<option value="1">1</option>
					  	<option value="2">2</option>
			  			<option value="3">3</option>
			  			<option value="4">4</option>
			  			<option value="5" selected>5</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="submit" value="Search" name="submit" />
		</form>
		<%-- <form method="post" action="/ces-1.0-SNAPSHOT/studentSearchAjax?studentId=${student.id}" 
			name="search_ajax_form" id="search_ajax_form">
			<div id="search_filter">
			<h3>Search filter</h3>
			<p>Universities</p>
				<c:forEach items="${list_unis}" var="element"> 
					<c:choose>
	      				<c:when test="${element==uni_name}">
	      					<input type="checkbox" value="${element}" name="uni_name" checked onchange="this.form.submit();">
	      				</c:when>
						<c:otherwise>
							<input type="checkbox" value="${element}" name="uni_name"  onchange="this.form.submit();">
	      				</c:otherwise>
					</c:choose>
				 	<c:out value="${element}"/>
				</c:forEach>
			<br/>
			<p>Categories</p>	
				<c:forEach items="${list_categories}" var="element"> 
					<c:choose>
	      				<c:when test="${element==category}">
	      					<input type="checkbox" value="${element}" name="category" checked  onchange="this.form.submit();">
	      				</c:when>
						<c:otherwise>
							<input type="checkbox" value="${element}" name="category"  onchange="this.form.submit();">
	      				</c:otherwise>
					</c:choose>
				 	<c:out value="${element}"/>
				</c:forEach>
			</div>
			<input type="hidden" name="searchedText" value="${searchedText}">
			<input type="hidden" name="average_rating_min" value="${average_rating_min}">
			<input type="hidden" name="average_rating_max" value="${average_rating_max}">
			<input type="hidden" name="average_reputation_rating_min" value="${average_reputation_rating_min}">
			<input type="hidden" name="average_reputation_rating_max" value="${average_reputation_rating_max}">
			<input type="hidden" name="average_teaching_rating_min" value="${average_teaching_rating_min}">
			<input type="hidden" name="average_teaching_rating_max" value="${average_teaching_rating_max}">
			<input type="hidden" name="average_research_rating_min" value="${average_research_rating_min}">
			<input type="hidden" name="average_research_rating_max" value="${average_research_rating_max}">
			<input type="hidden" name="average_administrators_rating_min" value="${average_administrators_rating_min}">
			<input type="hidden" name="average_administrators_rating_max" value="${average_administrators_rating_max}">
			<input type="hidden" name="average_lecture_notes_rating_min" value="${average_lecture_notes_rating_min}">
			<input type="hidden" name="average_lecture_notes_rating_max" value="${average_lecture_notes_rating_max}">
			
		</form> --%>
		<div id="updated_result">
	        <table border="1">
		       <c:forEach items="${searchedCourses}" var="element"> 
				  <tr>
				    <td><c:out value="${element.name}"/></td>
				    <td><a class="btn btn-primary" href="<c:url value="/courseDetail?courseName=${element.name}&studentId=${student.id}"/>">View surveys »Check Detail And Do Evaluation »</a></td>
				  </tr>
				</c:forEach>
			</table>
        </div>
      </div>
      

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
     <script src="<c:url value="/resources/js/jquery.raty.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  

</body></html>