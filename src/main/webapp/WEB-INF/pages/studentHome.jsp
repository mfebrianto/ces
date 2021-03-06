<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <html lang="en"><script id="tinyhippos-injected">
if (window.top.ripple) { window.top.ripple("bootstrap").inject(window, document); }</script>
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
  


<div class="container">
	<%-- <%@include file="layouts/studentHeader.jsp" %> --%>
    <jsp:include page="layouts/studentHeader.jsp">
     	<jsp:param name="studentId" >  
	      <jsp:attribute name="value" >  
	         <c:out value="${student.id}"/>  
	      </jsp:attribute>  
	  	</jsp:param>
	  	<jsp:param name="studentFN" >  
	      <jsp:attribute name="value" >  
	         <c:out value="${student.firstName}"/>  
	      </jsp:attribute>  
	  	</jsp:param>  
     </jsp:include>

      <!-- Main component for a primary marketing message or call to action -->
      
  
      <div class="jumbotron">
        <h2>Welcome <c:out value="${student.firstName} ${student.middleName} ${student.lastName}"/> </h2>
        <table border="1">
	       <c:forEach items="${courses}" var="element"> 
			  <tr>
			  	<td><c:out value="${element.name}"/></td>
			    <%-- <td><c:out value="${element.getId()}"/></td> --%>
			    <td><a class="btn btn-primary" href="<c:url value="/courseDetail?courseName=${element.name}&studentId=${student.id}"/>">Check Detail And Do Evaluation �</a></td>
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