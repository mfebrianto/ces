<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>

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

		<%@include file="layouts/studentHeader.jsp"%>

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">

			<form:form action="/ces-1.0-SNAPSHOT/survey/start" method="post" modelAttribute="responseListForm">
				
				<input type="hidden" id="courseName" name="courseName"
							value="${responseListForm.courseName}" />
				<input type="hidden" id="studentId" name="studentId"
							value="${responseListForm.studentId}" />
				<input type="hidden" id="surveyId" name="surveyId"
							value="${responseListForm.surveyId}" />	
						

				<table border="1" id="responseTable">
					<c:forEach items="${responseListForm.questionForm}" var="contact"
						varStatus="status">
						<input type="hidden" name="questionForm[${status.index}].surveyId"
						id="questionForm[${status.index}].surveyId"
							value="${contact.surveyId}" />
						<input type="hidden" name="questionForm[${status.index}].id"
						id="questionForm[${status.index}].id"
							value="${contact.id}" />
						
						
						<tr>
							<td>${contact.title}</td>
							<%-- <td>${contact.questionType}</td> --%>
							<td>
								${contact.responseContainer}
							</td>
							<%-- <form:radiobutton path="questionForm[${status.index}].response" /> --%>
						</tr>
					</c:forEach>
				</table>

				<br /> <input id="submitButton" class="btn btn-success" type="submit"
					value="Submit Response" />

			</form:form>

		</div>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

	<script>
		configuration = {};

		configuration.submitListener = function() {

			
			
			$('#submitButton').click(function(event) {

				
				var response="";
				var result=0;
				var questionNumber=0;
				
				// event.preventDefault();
				
				while($('#questionForm'+questionNumber).length){
					response = response +" "+$('#questionForm'+questionNumber).val();
					questionNumber++;
				}
				
				
				$.ajax({
			        type: "POST",
			        url: "../containProfanity/",
			        data: response,
			        dataType: "text",
			        async: false,
			        success: function(data) {
			        	  var obj = jQuery.parseJSON(data);
			        	  if(obj.response=="true"){
			        	  	result = 1;
			        	  }
				           
				       },
				        error: function(e) {
				          console.log(e);
				        }
			      });
				
				
				if (result) {
					alert('Cannot submit feedback, please be polite');
					return false;
				}
				
			});
		};
		
		
		
		

		$(document).ready(function() {

			configuration.submitListener();

		});
	</script>

</body>
</html>