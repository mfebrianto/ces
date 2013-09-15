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

     <%@include file="layouts/studentHeader.jsp" %>

      <!-- Main component for a primary marketing message or call to action -->
      <div data-action="share" class="g-plus" id="plusOne"></div>
  <button onClick="gapi.plus.render('plusOne', getParamBag('http://localhost:8080/ces-1.0-SNAPSHOT/'))"></button>
  
      <div class="jumbotron">
        <h1>Welcome Student</h1>
        
          <a href="#" 
  			 onclick="
  				 FB.ui({
    				  method: 'feed',
    				  link: 'http://localhost:8080/ces-1.0-SNAPSHOT/',
    				  picture: 'http://www.unsw.edu.au/sites/default/files/UNSW.png',
    				  name: 'UNSW Open Courses',
    				  caption: 'COMP 9323 - E-Enterprise Project',
    				  description: 'This is test Dialog to test facebook sharing.'
    				}, function(response){});">
  				<img src="<c:url value="/resources/imgs/icon_fb.jpg"/>" border="0">
  				
		</a>
		<a href="https://twitter.com/share" class="twitter-share-button" data-url="http://localhost:8080/ces-1.0-SNAPSHOT/student" data-text="Hey, check out this cool Course Evaluation Website : http://localhost:8080/ces-1.0-SNAPSHOT/student" data-count="none" data-via="Faridaji" data-lang="en">Tweet</a>
		
		
		<!--  <g:plus action="share"></g:plus>
		<a href="https://plus.google.com/share?url=http://www.unsw.edu.au" onclick="javascript:window.open(this.href,
  '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"><img
  src="https://www.gstatic.com/images/icons/gplus-16.png" alt="Share on Google+"/></a>-->
		<!-- window.open(
      				'https://www.facebook.com/sharer/sharer.php?u=http://www.yahoo.com',
      				'facebook-share-dialog', 
      				'width=626,height=436'); 
    				return false; -->
		
		<div class="fb-comments" data-href="http://localhost:8080/ces-1.0-SNAPSHOT/student" data-width="470"></div>
        
        <table border="1">
	       <c:forEach items="${courses}" var="element"> 
			  <tr>
			  <td><c:out value="${element.name}"/></td>
			    <td><c:out value="${element.getId()}"/></td>
			    <td><a class="btn btn-primary" href="<c:url value="/courseDetail?courseId=${element.id}"/>">Check Detail And Feedback �</a></td>
			  </tr>
			</c:forEach>
		</table>
		
      </div>
      

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
     <script src="<c:url value="/resources/js/jquery.raty.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  

</body></html>