<%@ page import = "java.sql.*"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="view.jsp">View</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="add.jsp">Add</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="update.jsp">Update</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="delete.jsp">Delete</a>
              </li>
              <li class="nav-item">
                <form>
                    <div class="logout_nav">
                        <input type="submit"  class="btnlogout" value="Logout" name="btnLogout">
                    </div>
                  </form>
              </li>
            </ul>
          </div>
        </div>
      </nav>

    

    <% 
     String un = (String)session.getAttribute("un");
    if(un == null){
        response.sendRedirect("index.jsp");
    }
    %>
       <div class="table ">
        <table border="5" class="shadow p-3 mb-5  rounded">
            <tr>
            <th class="main-head">Roll No.</th>
            <th class="main-head">Name</th>
            <th class="main-head">Marks</th>
        </tr>
        <% 
            try {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    String url = "jdbc:mysql://localhost:3306/final_project23";
                    Connection con = DriverManager.getConnection(url, "root", "abc123");
        
                    String sql = "select * from student";
                    PreparedStatement pst =  con.prepareStatement(sql);
                    ResultSet rs =  pst.executeQuery();
                    while(rs.next()){
        %>
                        
                        <tr>
                            <td><%= rs.getInt(1) %></td>
                            <td><%= rs.getString(2) %></td>
                            <td><%= rs.getDouble(3) %>%</td>
                        </tr>
        
        <%
        
                    }
                    
                    con.close();
                    
                } catch (SQLException e) {
        %>
                    <script>
                        alert("<%=e%>" + " issue ");
                    </script>
        <%
                }
                if(request.getParameter("btnLogout")!= null){
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
        %>
        </table>
    </div>   
</body>
</html> 