<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<script id="tinyhippos-injected">
	if (window.top.ripple) {
		window.top.ripple("bootstrap").inject(window, document);
	}
</script>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.png">

<title>Group1 Course Evaluation</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">



<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body style="">

	<div class="container">

		<%@include file="layouts/uniHeader.jsp"%>

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">

			<table border="1">
				<c:forEach items="${allSurveys}" var="element">
					<tr>
						<%-- <form:hidden path="id" value="${element.id}"/> --%>
						<td><c:out value="${element.id}" /></td>
						<td><a
							href="<c:url value="/uni/survey/detail?surveyId=${element.id}"/>"><c:out
									value="${element.title}" /></a></td>
						<td><c:out value="${element.created_on}" /></td>
						<td><a class="btn btn-warning"
							href="<c:url value="/uni/survey/delete?surveyId=${element.id}"/>">Delete
								Survey</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>

		<a class="btn btn-lg btn-primary"
			href="<c:url value="/uni/survey/create"/>">Create Survey</a>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


</body>
</html>