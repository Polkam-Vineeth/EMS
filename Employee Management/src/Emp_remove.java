

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Emp_remove")
public class Emp_remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public Emp_remove() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Scanner sc= new Scanner(System.in);
			int id=Integer.parseInt(request.getParameter("id"));
			//pw.println("Id : "+id);
			
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root", "root");
			PreparedStatement stmt=con.prepareStatement("delete from employee where id = ? ");
			stmt.setInt(1, id);
			try {
				int i=stmt.executeUpdate();
				if(i!=0)
				{	PreparedStatement stmt1=con.prepareStatement("delete from performance where id = ? ");
					stmt1.setInt(1, id);
					int j=stmt1.executeUpdate();
					pw.println("<a href=\"admin.html\"> Employee Record is Deleted !!, Please Go to Admin Page</a>");
				}
				else
					pw.println("<a href=\"admin.html\"> Employee Record is not Found !!, Please Go to Admin Page</a>");
			}
			catch(Exception e)
			{
				pw.println("<a href=\"admin.html\"> Employee Record is not  Deleted !!, Please Go to Admin Page</a>");
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
