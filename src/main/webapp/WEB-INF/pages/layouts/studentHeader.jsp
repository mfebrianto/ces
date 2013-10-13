 <!-- Static navbar -->
<!-- Facebook,google, twitter Part -->
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
  	    
  	    //user is connected
  	  } else if (response.status === 'not_authorized') {
  	    // the user is logged in to Facebook, 
  	    // but has not authenticated your app
  	   
  		alert("Application Not Yet Authorized");
  		window.location.href="http://localhost:8080/ces-1.0-SNAPSHOT/";
  	    
  	  } else {
  	    // the user isn't logged in to Facebook.
  		alert("User Has not Login");
  		window.location.href="http://localhost:8080/ces-1.0-SNAPSHOT/";
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
 <!-- Custom styles for this template -->
      <div class="navbar navbar-default" style="margin-top:10px">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/ces-1.0-SNAPSHOT">Group1 Course Evaluation</a>
        </div>
        <div class="navbar-collapse collapse">
          <!-- <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul> -->
          <ul class="nav navbar-nav navbar-right">
            <!-- <li class="active"><a href="./">Default</a></li>
            <li><a href="../navbar-static-top/">Static top</a></li>
            <li><a href="../navbar-fixed-top/">Fixed top</a></li> -->
            <li><a href="/ces-1.0-SNAPSHOT/student">Home</a></li>
            <li><a href="/ces-1.0-SNAPSHOT/studentSearch?studentId=${student.id}">Search</a></li>
            <li><a href="/ces-1.0-SNAPSHOT/FBLogin/logout">Logout</a></li>
            <!-- <li><a href="/ces-1.0-SNAPSHOT/studentSearch">Search</a></li> -->
          </ul>
        </div><!--/.nav-collapse -->
      </div>