<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css" >
<link type="text/css" rel="stylesheet" href="css/add-student-style.css" >
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
		<div id="header">
			<h2>Beast University</h2>
		</div>
	</div>
	<h2>Add Student</h2>
	<div id="container">
	<form action="StudentController" method="get">
		<input type="hidden" name="command" value="ADD">
		<table>
			<tbody>
				<tr>
					<td><label>FirstName: </label></td>
					<td><input type="text" name="firstName" /></td>
				</tr>
				<tr>
					<td><label>LastName: </label></td>
					<td><input type="text" name="lastName" /></td>
				</tr>
				<tr>
					<td><label>Email: </label></td>
					<td><input type="text" name="email" /></td>
				</tr>
					<td><input type="submit" name="save" class="save" /></td>
					
				
			</tbody>
		</table>
	</form>
	<p>
		<a href="StudentController">back to the list.</a>
	</p>
	</div>
</body>
</html>