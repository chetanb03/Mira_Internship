<%@ page import = "java.sql.*"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Studnet Management </title>
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
                <a class="nav-link" aria-current="page" href="view.jsp">View</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active"  href="add.jsp">Add</a>
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
                        <input type="submit" onclick="return confirm('Are you sure you want to logout?');" class="btnlogout" value="Logout" name="btnLogout">
                    </div>
                  </form>
              </li>
            </ul>
          </div>
        </div>
      </nav>
        <div class="add container shadow-lg p-3 mb-5 y rounded">
                    <h1>Add</h1>
                    <form method="post">
                        <input type="number" name="rno" class="form-control text" placeholder="Enter Roll no." required min="1">
                        <br><br>
                        <input type="text" name="name" class="form-control text " placeholder="Enter name" required>
                        <br><br>
                        <input type="number" step="any" class="form-control text" name="marks" placeholder="Enter marks" max="100" required>
                        <br><br>
                        <input type="submit" name="btn" class="btn btn-primary">                    
                    </form>
    </div>
    <% 
    String un = (String)session.getAttribute("un");
    if(un == null){
        response.sendRedirect("index.jsp");
    }else {
        if(request.getParameter("btn") != null){
            int rno = Integer.parseInt(request.getParameter("rno"));
            String name = request.getParameter("name");
            double marks = Double.parseDouble(request.getParameter("marks"));

            try {

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/final_project23";
                Connection con = DriverManager.getConnection(url, "root", "abc123");
                String sql = "insert into student values (?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, rno);
                pst.setString(2, name);
                pst.setDouble(3,marks);
                pst.executeUpdate();
      %>
                    <script>
                        alert("Roll No " + "<%=rno%>" + " Added");
                    </script>
      <%
                con.close();
                
            } catch (SQLException e) {
      %>
                    <script>
                        alert("<%=e%>" + " issue ");
                    </script>
      <%
    }
  }
}
      
        
    %>
    <%
    if(request.getParameter("btnLogout") != null){
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    %>
</body>
</html>