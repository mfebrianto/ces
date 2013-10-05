<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <html lang="en"><script id="tinyhippos-injected">
if (window.top.ripple) { window.top.ripple("bootstrap").inject(window, document); }</script><head>

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

    <div class="container">

     <%@include file="layouts/uniHeader.jsp" %>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Search</h1>
        	<button id="logout" >Logout</button>
        <p>
        <form method="post" action="/ces-1.0-SNAPSHOT/search">
        	<table>
				<tr>
					<td>Course name</td>
					<td><input type="text" name="searchedText"></td>
				</tr>
				<tr>
					<td>Average rating</td>
					<td>&nbsp Min<select name="average_rating_min">
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
				
			</table>
			<input type="submit" value="Search" name="submit" />
		</form>
        <table border="1">
	       <c:forEach items="${searchedCourses}" var="element"> 
			  <tr>
			    <td><c:out value="${element.name}"/></td>
			    <td><a class="btn btn-primary" href="<c:url value="/uni/survey?courseId=${element.name}"/>">View surveys �</a></td>
			  </tr>
			</c:forEach>
		</table>
        
      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  
				<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" ></script>
        		<script type="text/javascript">
		    			$(document).ready(function() {
		      			  $('#logout').click(function(event) {
		      				  event.preventDefault();
		      				//alert("disconnect~~~");
    				    	//alert(window.location.href);
    				        // Revoke the server tokens
    				        $.ajax({
    				          type: 'POST',
    				          url: location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '')+"/ces-1.0-SNAPSHOT" + '/googleLogout',
    				          async: false,
    				          success: function(result) {
    				      
    				        		alert(result);
    				        		 window.location.href = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '')+"/ces-1.0-SNAPSHOT";
    				          },
    				          error: function(e) {
    				            	alert(JSON.stringify(e));
    				          }
    				        });

		      			  });
		      			  
		  			    	
		  			  	});
    			
    			
        		


        	</script>

</body></html>