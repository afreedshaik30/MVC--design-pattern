package in.sp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.sp.dbConn.DbConnection;
import in.sp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginForm")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String myEmail=request.getParameter("email1");
		String myPassword=request.getParameter("password1");
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher rd=null;
		
		try 
		{
			Connection conn=DbConnection.getConnection();
			String selectQuery="SELECT * FROM register WHERE email=? AND password=?";
			PreparedStatement ps=conn.prepareStatement(selectQuery);
			ps.setString(1,myEmail);
			ps.setString(2,myPassword);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				User user=new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("city"));
				
				HttpSession session=request.getSession();
				session.setAttribute("session_user",user);
				rd=request.getRequestDispatcher("/profile.jsp");
				rd.forward(request, response);
			}
			else
			{
			   out.println("<h2 style='color:red'>Invalid Email and Password</h2>");
			   rd=request.getRequestDispatcher("/login.html");
			   rd.include(request, response);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
