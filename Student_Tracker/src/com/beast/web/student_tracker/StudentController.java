package com.beast.web.student_tracker;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

		private StudentDbUtil studentDbUtil;
	
		@Resource(name="jdbc/student_tracker")
		private DataSource dataSource;
		
		
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			super.init();
			
			try {
				studentDbUtil = new StudentDbUtil(dataSource);
			}
			catch(Exception e) {
				throw new ServletException(e);
			}
			
		}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String theCommand = request.getParameter("command");
			
			if(theCommand==null) {
				theCommand = "LIST";
			}
			
			switch(theCommand) {
			
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudents(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request,response);
				break;
			default :
				listStudents(request, response);
				
			}
			
			
		listStudents(request,response);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}



	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String theStudentId = request.getParameter("studentId");
		
		studentDbUtil.deleteStudent(theStudentId);
		
		listStudents(request, response);
		
	}



	private void updateStudent(HttpServletRequest request, HttpServletResponse response)throws Exception {
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student theStudent = new Student(id, firstName, lastName, email);
		
		studentDbUtil.updateStudent(theStudent);
		
		listStudents(request,response);
		
	}



	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theStudentId = request.getParameter("studentId");
		
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		request.setAttribute("The_student", theStudent);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-student.jsp");
		dispatcher.forward(request, response);
		
	}



	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student theStudents = new Student(firstName, lastName, email);
		studentDbUtil.addStudents(theStudents);
		
		listStudents(request, response);
		
	}



	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> students = studentDbUtil.getStudents();
		
		request.setAttribute("students", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/studentlist.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
