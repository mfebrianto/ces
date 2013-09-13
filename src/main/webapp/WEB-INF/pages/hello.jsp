<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en"><script id="tinyhippos-injected">if (window.top.ripple) { window.top.ripple("bootstrap").inject(window, document); }</script><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">
    <script type="text/javascript" src="https://apis.google.com/js/plusone.js">
		{"parsetags": "explicit"}
	</script>
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
  </head>

  <body>

    <div class="container">
      <div class="header">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
        <h3 class="text-muted">VInCi Course Evaluation</h3>
      </div>

      <div class="jumbotron">
        <h1>Course Evaluation</h1>
        <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <div>

	       <!--  <a class="btn btn-lg btn-success" href="<c:url value="/uni"/>">University</a>-->
    		<script type="text/javascript">
  			(function() {
    			var po = document.createElement('script');
    			po.type = 'text/javascript'; po.async = true;
    			po.src = 'https://apis.google.com/js/client:plusone.js?onload=render';
    			var s = document.getElementsByTagName('script')[0];
    			s.parentNode.insertBefore(po, s);
  			})();

  			function myRender() {
    			gapi.signin.render('customBtn', {
      				'callback': onSignInCallback,
      				'clientid': '913622401500-odpadjc4kekc6ugqvtohv2ia3budo8bh.apps.googleusercontent.com',
      				'cookiepolicy': 'single_host_origin',
      				'scope': 'https://www.googleapis.com/auth/plus.login'
    			});
  			}
  
  </script>
    <button id="customBtn" class="btn btn-lg btn-success" onclick="myRender()">University</button>
  
  		
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
      $('#authResult').html('Auth Result:<br/>');
   		this.authResult = authResult;
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
	        <a class="btn btn-lg btn-success" onclick="var gw=window.open('','_facebook','width=400,height=300,scrollbars=yes');gw.focus();" href="https://graph.facebook.com/oauth/authorize?client_id=575842165811798&amp;redirect_uri=http://localhost:8080/ces-1.0-SNAPSHOT/FBLogin/FBLoginTest&amp;scope=email,user_education_history,publish_actions&amp;display=popup&amp;response_type=token" target="_facebook">Student</a>

<!--         <a class="btn btn-lg btn-success" href="<c:url value="/uni"/>">University</a>
	        <a class="btn btn-lg btn-success" onclick="var gw=window.open('','_facebook','width=400,height=300,scrollbars=yes');gw.focus();" href="https://graph.facebook.com/oauth/authorize?client_id=575842165811798&amp;redirect_uri=http://localhost:8080/ces-1.0-SNAPSHOT/FBLogin/FBLoginTest&amp;scope=email,user_education_history,publish_actions,user_interests&amp;display=popup&amp;response_type=token" target="_facebook">Student</a>
>>>>>>> 44e8b3233dc3de9279dd2b049483409fd93fa7d0 -->	
        </div>
      </div>

      <div class="row marketing">
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

      <div class="footer">
        <p>© Company 2013</p>
      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  

</body></html>