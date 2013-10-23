<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" ></script>    
</head>
<body>
<div id="fb-root"></div>
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
  	    
  	    
  	  	$.ajax({
          type: 'POST',
          url: '/ces-1.0-SNAPSHOT/FBLogin/checkToken',
          contentType: 'application/octet-stream; charset=utf-8',
          success: function(result) {
            console.log(result);
            if(result == 'success'){
            	window.opener.location.href="/ces-1.0-SNAPSHOT/student";
            	self.close();
            }else if(result == 'uni'){
            	alert('You need to logout from uni account first and login using student account')
            	window.opener.location.href="/ces-1.0-SNAPSHOT/";
            	self.close();
            }else{
            
            	alert(result);
            	window.opener.location.href="/ces-1.0-SNAPSHOT/student";
            	self.close();
            }
            //helper.people();
          },error: function(request,error) {
              alert('An error occurred');
              console.log(request, error);
          },
          processData: false,
          data:  accessToken 
        });
  	    
  	  } else if (response.status === 'not_authorized') {
  	    // the user is logged in to Facebook, 
  	    // but has not authenticated your app
  	    window.opener.location.href="/ces-1.0-SNAPSHOT/";
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

</script>
Loading Data...
</body>           
</html>