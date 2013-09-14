<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>

<html lang="en"><script id="tinyhippos-injected">if (window.top.ripple) { window.top.ripple("bootstrap").inject(window, document); }</script><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

    <title>Group1 Course Evaluation</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    

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
      	<table border="1">
	       <c:forEach items="${allQuestions}" var="element"> 
			  <tr>
			    <td><c:out value="${element.id}"/></td>
			    <td><c:out value="${element.subtype}"/></a></td>
			    <td><c:out value="${element.title}"/></td>
			  </tr>
			</c:forEach>
		</table>
		
		<form:form commandName="SURVEY">

				<table>
					<tr>
						<td>Question Type : </td>
						<td>
							<select id="questionType">
  								<option value="0"></option>
  								<option value="1">short text</option>
								<option value="2">long text</option>
								<option value="3">multiple choice</option>
								<option value="4">checkboxes</option>
								<option value="5">dropdown menu</option>
								<option value="6">slider</option>
								<option value="7">net promoter score</option>
								<option value="8">likert scale</option>
							</select>
						</td>
					</tr>
					<tr id="question_layout">
					</tr>
					<tr>
						<td>
							<input class="btn btn-success" type="submit" value="Add Question" />
						</td>
					</tr>
				</table>
				
			</form:form>
		
		
		
      </div>
	
		

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  	
  	<script>
  	
  	configuration = {};
	
	configuration.changeQuestionType= function(){
		alert('test');
		$('#questionType').change(function(){
			var questionTypeVal = $('#questionType').val();
			var questionLayout = $('#question_layout');
			if(questionTypeVal==1){
				questionLayout.html('<td>Question Title</td>');
			}
			else if(questionTypeVal==2){
				
			}
			else if(questionTypeVal==3){
				
			}
			else if(questionTypeVal==4){
				
			}
			else if(questionTypeVal==5){
				
			}
			else if(questionTypeVal==6){
				
			}
			else if(questionTypeVal==7){
				
			}
			else if(questionTypeVal==8){
				
			}
			else{
				questionLayout.html('');
			}
		})
	};
  	
    $( document ).ready(function() {
        
    	configuration.changeQuestionType();
    	
    });
    </script>

</body></html>