<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en"><script id="tinyhippos-injected">if (window.top.ripple) { window.top.ripple("bootstrap").inject(window, document); }</script><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" ></script>
	
    <title></title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/jumbotron-narrow.css"/>" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
    		
    	var popupWindow=null;

    	function child_open()
    	{ 
	   		popupWindow=window.open('','_facebook','width=400,height=300,scrollbars=yes');
    		popupWindow.focus();
    	}
    	
    	function parent_disable() {
    		if(popupWindow && !popupWindow.closed)
    			popupWindow.focus();
    	}
    </script>
  </head>

  <body onFocus="parent_disable();" onclick="parent_disable();">

    <div class="container">
      <div class="header">
        <!-- <ul class="nav nav-pills pull-right">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Contact</a></li>
        </ul> -->
        <h3 class="text-muted">Course Evaluation</h3>
      </div>

      <div class="jumbotron">
        <h1>Course Evaluation</h1>
        <p class="lead">Group 1 COMP9323 - Mentor AMIN</p>
        <div>

	       <!--  <a class="btn btn-lg btn-success" href="<c:url value="/uni"/>">University</a>-->
    		<script type="text/javascript">
    		
    		
    		 <!-- Place this asynchronous JavaScript just before your </body> tag -->
    		      (function() {
    		       var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    		       po.src = 'https://apis.google.com/js/client:plusone.js';
    		       var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
    		     })();
    		    
  		
  		/*	function myRender() {
  			    gapi.signin.render('customBtn', {
  			      'callback': onSignInCallback,
  			      'clientid': '913622401500-odpadjc4kekc6ugqvtohv2ia3budo8bh.apps.googleusercontent.com',
  			      'cookiepolicy': 'single_host_origin',
  			      'requestvisibleactions': 'http://schemas.google.com/AddActivity',
  			      'scope': 'https://www.googleapis.com/auth/plus.login'
  			    });
  			  }*/
  			  $(document).ready(function(){
  			  $('#customBtn').click(function (event){
  				 event.preventDefault();
  				gapi.auth.authorize({
  					'client_id':'913622401500-odpadjc4kekc6ugqvtohv2ia3budo8bh.apps.googleusercontent.com',
  					'immediate' : 'false',
  					'response_type' : 'code',
  					'scope':'https://www.googleapis.com/auth/plus.login' 					
  				}, onSignInCallback);
  			  });
  			});
  </script>

 
    <button id="customBtn"  class="btn btn-lg btn-success customGPlusSignIn" >University</button>
  
  		
  		<script type="text/javascript">
var helper = (function() {
  var authResult = undefined;

  return {
    /**
     * Hides the sign-in button and connects the server-side app after
     * the user successfully signs in.
     *
     * @param {Object} authResult An Object which contains the access token and
     *   other authentication information.
     */
    onSignInCallback: function(authResult) {
   		this.authResult = authResult;
   		//  		alert(JSON.stringify(this.authResult));
        helper.connectServer();
        window.location.href = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '')+"/ces-1.0-SNAPSHOT/uni";
        
     
    },
    /**
     * Retrieves and renders the authenticated user's Google+ profile.
     */
    renderProfile: function() {
      
    },

    /**
     * Calls the server endpoint to connect the app for the user. The client
     * sends the one-time authorization code to the server and the server
     * exchanges the code for its own tokens to use for offline API access.
     * For more information, see:
     *   https://developers.google.com/+/web/signin/server-side-flow
     */
    connectServer: function() {
      console.log(this.authResult.code);
      $.ajax({
        type: 'POST',
        async: false,
        url: window.location.href + '/googleLogin',
        contentType: 'application/octet-stream; charset=utf-8',
        success: function(result) {
          console.log(result);
          alert(result);
          //helper.people();
        },
        processData: false,
        data:  this.authResult.code 
      });
    },
    
  };
})();



/**
 * Calls the helper method that handles the authentication flow.
 *
 * @param {Object} authResult An Object which contains the access token and
 *   other authentication information.
 */
function onSignInCallback(authResult) {
	// alert("lalala");
  helper.onSignInCallback(authResult);
}
</script>
	        <a class="btn btn-lg btn-success" onclick="child_open()" href="https://graph.facebook.com/oauth/authorize?client_id=575842165811798&amp;redirect_uri=${pageContext.request.getScheme()}://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.getContextPath()}/FBLogin/FBLoginTest&amp;scope=email,user_education_history,publish_actions&amp;display=popup&amp;response_type=token" target="_facebook">Student</a>

	
        </div>
      </div>

      <!-- <div class="row marketing">
        <div class="col-lg-6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="col-lg-6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
      </div>
 -->
      <div class="footer">
        <p>� Group 1 Comp9323</p>
      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  

</body></html>