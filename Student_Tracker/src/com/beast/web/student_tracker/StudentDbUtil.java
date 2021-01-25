package com.beast.web.student_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql="select * from student order by last_name";
			
			st=conn.createStatement();
			
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				students.add(tempStudent);
				
			}
		return students;
		}
		finally {
			close(conn,st,rs);
		}
	}

	private void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(st!=null) {
				st.close();
			}
			
			
			if(conn!=null) {
				conn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudents(Student theStudents) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn= dataSource.getConnection();
			String sql="insert into student"
					+"(first_name,last_name,email)"
					+"values(?,?,?)";
			st=conn.prepareStatement(sql);
			st.setString(1, theStudents.getFirstName());
			st.setString(2, theStudents.getLastName());
			st.setString(3, theStudents.getEmail());
			
			st.execute();
		}
		finally {
			close(conn,st,null);
		}
	}

	public Student getStudent(String theStudentId) throws SQLException,Exception {
		Student theStudent = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int studentId;
		try {
		studentId = Integer.parseInt(theStudentId);
		
		conn = dataSource.getConnection();
		
		String sql = "select * from student where id=?";
		
		st=conn.prepareStatement(sql);
		
		st.setInt(1, studentId);
		
		rs = st.executeQuery();
		
		if(rs.next()) {
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			
			 theStudent = new Student(studentId, firstName, lastName, email);
		}
		
		else {
			throw new Exception("could not find studentId "+studentId);
		}
		return theStudent;
		}finally {
			close(conn,st,rs);
		}
	}

	public void updateStudent(Student theStudent) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = dataSource.getConnection();
		String sql="update student "
					+"set first_name=?, last_name=?, email=? "
					+ "where id=?";
		st=conn.prepareStatement(sql);
		
		st.setString(1, theStudent.getFirstName());
		st.setString(2, theStudent.getLastName());
		st.setString(3, theStudent.getEmail());
		st.setInt(4, theStudent.getId());
		
		st.execute();
		}finally {
			close(conn,st,null);
		}
		
	}

	public void deleteStudent(String theStudentId)throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			int studentId = Integer.parseInt(theStudentId);
			
			conn = dataSource.getConnection();
			
			String sql = "delete from student where id=?";
			
			st= conn.prepareStatement(sql);
			
			st.setInt(1, studentId);
			
			st.execute();
			
		}
		finally {
			close(conn, st, null);
		}
		
	}
}
