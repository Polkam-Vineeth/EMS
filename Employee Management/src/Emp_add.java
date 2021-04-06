

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Emp_add")
public class Emp_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Emp_add() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Scanner sc= new Scanner(System.in);
			String name=request.getParameter("name");
			int id=Integer.parseInt(request.getParameter("id"));
			String job = request.getParameter("job");
			String salary = request.getParameter("salary");
			String phno = request.getParameter("phno");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root", "root");
			PreparedStatement stmt=con.prepareStatement("insert into employee (name,id,job,salary,phno) values (?,?,?,?,?)");
			stmt.setString(1,name);
			stmt.setInt(2,id);
			stmt.setString(3,job);
			stmt.setString(4,salary);
			stmt.setString(5,phno);
			try {
				int i=stmt.executeUpdate();
				PreparedStatement stmt1=con.prepareStatement("insert into performance (id,wquality,creativity,execution,consistency,feedback) values (?,?,?,?,?,?)");
				stmt1.setInt(1,id);
				stmt1.setInt(2,0);
				stmt1.setInt(3,0);
				stmt1.setInt(4,0);
				stmt1.setInt(5,0);
				stmt1.setInt(6,0);
				int j=stmt1.executeUpdate();
				pw.println("<a href=\"admin.html\"> Employee Record is Added !!, Please Go to Admin Page</a>");
			}
			catch(Exception e)
			{
				pw.println("<a href=\"admin.html\"> Employee Record is not  Added !!, Please Go to Admin Page</a>");
			}
		}
		catch(Exception e)
		{
			pw.println("<a href=\"admin.html\"> Error in data base connection , Please Go to Admin Page</a>");
		}
			
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
