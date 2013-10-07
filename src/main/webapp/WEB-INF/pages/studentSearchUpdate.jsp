<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="updated_result">
	<table border="1">
       	<c:forEach items="${searchedCourses}" var="element"> 
		  	<tr>
				<td><c:out value="${element.name}"/></td>
		    	<td><a class="btn btn-primary" href="<c:url value="/courseDetail?courseName=${element.name}&studentId=${student.id}"/>">View surveys »Check Detail And Do Evaluation »</a></td>
		  	</tr>
		</c:forEach>
	</table>
</div>
