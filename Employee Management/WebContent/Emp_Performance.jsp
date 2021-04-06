<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
	String id = request.getParameter("userid");
	String driver = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/";
	String database = "mydb";
	String userid = "root";
	String password = "root";
	try {
		Class.forName(driver);
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	Connection connection = null;
	ResultSet resultSet = null;
	java.sql.PreparedStatement statement=null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Retrieve data from database in jsp</h1>
	<table border="1">
	<tr>
	<td>first name</td>
	<td>last name</td>
	<td>City name</td>
	<td>Email</td>
	
	</tr>
	<%
			int a,b,c,d,e;
		try{
			connection = DriverManager.getConnection(connectionUrl+database, userid, password);	
			statement=connection.prepareStatement("select * from performance where id=?");
			
	        statement.setInt(1, 1555);
	        resultSet=statement.executeQuery();
	        
	             
	        if(resultSet.next()) {
	        	a=resultSet.getInt(2);
	        	b=resultSet.getInt(3);
	        	c=resultSet.getInt(4);
	        	d=resultSet.getInt(5);
	        	e=resultSet.getInt(6);
	        	System.out.println("Employee: "+a);
		        System.out.println("Employee : "+b);
		        System.out.println("Employee : "+c);
		        System.out.println("Employee : "+d);
		        System.out.println("Employee : "+e);
		        System.out.println("Employee id : "+resultSet.getInt(1));
		        
	          System.out.println("-------***-------");
	        }
	        else
	          System.out.println("No Record found");
			
	        
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	%>	
	
	
	
	<br>
    <h1 style="padding: 10px;">Employee Performace Analysis</h1>
    
    <div id="piechart" class="text-center"></div>
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    
    <script type="text/javascript">
    // Load google charts
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    
    // Draw the chart and set the chart values
    function drawChart() {
    	var aa= '${a}';
    	var bb= '${b}';
    	var cc= '${c}';
    	var dd= '${d}';
    	var ee= '${e}';
      var data = google.visualization.arrayToDataTable([
      ['Task', 'Hours per Month'],
      ['Quality of work', aa],
      ['Level of creativity', bb],
      ['Level of execution  ', cc],
      ['Consistency', dd],
      ['Customer and peer feedback', ee]
      
    ]);
    
      // Optional; add a title and set the width and height of the chart
      var options = {'title':'Performance Analysis', 'width':700, 'height':600,};
    
      // Display the chart inside the <div> element with id="piechart"
      var chart = new google.visualization.PieChart(document.getElementById('piechart'));
      chart.draw(data, options);
    }
    </script>
    
    
</body>
</html>