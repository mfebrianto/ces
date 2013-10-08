<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>

<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" />
	
	<script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

</head>
<script>
	var reportData = ${reportData};
	$(document).ready(function() {
		$('')
		$.each(reportData['data'], function(index, stat){
			var text = "";
			if (stat['_subtype'] == 'checkbox' || stat['_subtype'] == 'radio'){
				text += "<tr><th>Label</th><th>Count</th><th>Percentage</th></tr>";
				$.each(stat['Breakdown'], function(index, breakdown){
					text += "<tr><td>"+breakdown['label']+"</td><td>"+breakdown['count']+"</td><td>"+breakdown['percentage']+"</td></tr>";
				});
				var matches = stat['id'].match(/\[(.*?)\]/);
				var idText = matches[1];
				matches = idText.match(/\((.*?)\)/);
				var id = matches[1];
				$('#question_'+id+'_table').html(text);
			}
		});
	});	
	</script>
<body>
<div class="rows" >
<div class="col-lg-2">
</div>
<div class="well col-lg-8" style="margin: 20px;">
	<h2>Statistic for survey ${ surveyID }</h2>
	<div id="statSummary">
		Number of questions: <span class="badge">${ numOfQuestion }</span>
	</div>
	<div id="statDetail">
		<c:forEach items="${questionList}" var="question">
			<h3>${ question.title } <span class="badge">${ question.subtype }</span></h3>
			<table class="table table-bordered" id="question_${question.id}_table">
				<c:if test='${question.subtype == "textbox" || question.subtype == "essay"}'>
					<tr><th>Response Answer</th><th>Count</th></tr>
					<c:set var="questionID" value="[question(${question.id})]" />
					<c:forEach items="${ textTable[questionID] }" var="entry">
						<tr><td>${ entry.key }</td>
						<td>${ entry.value }</td></tr>
					</c:forEach>
				</c:if>
			</table>
		</c:forEach>
	</div>
	
</div>
</div>
</body>
</html>