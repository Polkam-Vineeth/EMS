import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;
@WebServlet("/Admin_login")


public class Admin_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		Scanner sc=new Scanner(System.in);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Loaded");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root", "root");
			System.out.println("Connected");
			response.setContentType("text/html");
			String uname=request.getParameter("uname");
			String password = request.getParameter("pswd");
			Statement st = con.createStatement();
			//pw.println(uname+" "+password );
			ResultSet rs = st.executeQuery("select * from admin");
			while(rs.next())
			{
				//pw.println(rs.getString(2)+" "+rs.getString(3));
				if((uname.equals(rs.getString(2))) && (password.equals(rs.getString(3))))
				{
					//pw.println(uname+" "+" "+password+" "+rs.getString(2)+" "+rs.getString(3));
					response.sendRedirect("admin.html");
					
				}
			}
			
				//pw.println("Wrong Admin credentails  !! ");
				pw.println("<a href=\"AdminLogin.html\">Wrong credentials , Please Go to Login Page</a>");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
