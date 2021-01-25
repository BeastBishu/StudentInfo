<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,com.beast.web.student_tracker.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<!DOCTYPE html>
<html>

<head>

	<link type="text/css" rel="stylesheet" href="css/style.css" >
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Beast University</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		<input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp';return false;" class="add-student-button" />
			<table>
				<tr>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<%-- <% for(Student temp:theStudents) {  %>
				
				<tr>
					<td><%=temp.getFirstName() %></td>
					<td><%=temp.getLastName() %></td>
					<td><%=temp.getEmail() %></td>
				</tr>
				
				<% } %> --%>
				<c:forEach var="temp" items="${students}">
				
				<c:url var="tempLink" value="StudentController">
					<c:param name="command" value="LOAD"></c:param>
					<c:param name="studentId" value="${temp.id}"></c:param>
				</c:url>	
				<c:url var="deleteLink" value="StudentController">
					<c:param name="command" value="DELETE"></c:param>
					<c:param name="studentId" value="${temp.id}"></c:param>
				</c:url>
				<tr>
					
						<td>${temp.firstName}</td>
						<td>${temp.lastName}</td>
						<td>${temp.email}</td>
						<td><a href="${tempLink}">Update</a>
							|
							<a href = "${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete?'))) return false">Delete</a>
						</td>
					
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
</body>
</html>