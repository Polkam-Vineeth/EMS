<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<html>
  <head>
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
    <%
int id=Integer.parseInt(request.getParameter("id"));
int a,b,c,d;
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root", "root");
PreparedStatement stmt=con.prepareStatement("select * from performance where id = ?");
stmt.setInt(1, id);
ResultSet rs = stmt.executeQuery();
while(rs.next())
{
	a=rs.getInt(2);
	b=rs.getInt(3);
	c=rs.getInt(4);
	d=rs.getInt(5);
	
}

%>
  </body>
     <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      drawChart();

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
         
          ['Work',  id ],
          ['Eat',    a],
          ['Commute',b ],
          ['Watch TV',c],
          ['Sleep',d ]
        ]);

        var options = {
          title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
</html>