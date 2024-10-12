package in.sp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.sp.dbConn.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerForm")
public class Register  extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String myName=request.getParameter("name1");
		String myEmail=request.getParameter("email1");
		String myPassword=request.getParameter("password1");
		String myCity=request.getParameter("city1");
		
		PrintWriter out= response.getWriter();
		response.setContentType("text/html");

		try
		{
			Connection connection=DbConnection.getConnection();
			String insertQuery="INSERT INTO register(name,email,password,city) VALUES(?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(insertQuery);
			ps.setString(1,myName);
			ps.setString(2,myEmail);
			ps.setString(3,myPassword);
			ps.setString(4,myCity);
			int rowsAffected=ps.executeUpdate();
			if(rowsAffected>0)
			{
				out.println("<h2 style='color:blue'> Registeration Successfull </h2>");
			    RequestDispatcher rd=request.getRequestDispatcher("/login.html");
			    rd.include(request, response);
			}
			else
			{
				out.println("<h2 style='color:red'> Registeration Failed </h2>");
			    RequestDispatcher rd=request.getRequestDispatcher("/register.html");
			    rd.include(request, response);
			}
		}
		catch (Exception e) 
		{
         e.printStackTrace();
		}
	}
}
