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
   <h1 class="login-head">Student Management</h1>
    <div class="row login-row">
        <div class="login container shadow-lg p-3 mb-5 y rounded">
                <div class="col text-form">
                    <h1>Login</h1>
                    <form method="post">
                        <input type="text" name="un" class="form-control text " placeholder="Enter user name" pattern="[A-Za-z ]+" required>
                        <br><br>
                        <input type="password" class="form-control text" name="pw" placeholder="Enter password" required>
                        <br><br>
                        <input type="submit" class="btn btn-primary"  name="btn" value="Login">
                        <br><br>
                        <p>Don't have and Account? <a class="login_link" href="signup.jsp">Signup</a></p>
                    </form>
                </div>
        </div>
        <div class="col">
            <img src="images/login.png" class="img float-end"  alt="image">
          </div>
    </div>

    <% 
        if(request.getParameter("btn") != null){
            String un = request.getParameter("un");
            String pw = request.getParameter("pw");
            try {
                        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                        String url = "jdbc:mysql://localhost:3306/final_project23";
                        Connection con = DriverManager.getConnection(url, "root", "abc123");

                        String sql = "select * from user where un=? and pw=?";
                        PreparedStatement pst =  con.prepareStatement(sql);
                        pst.setString(1,un);
                        pst.setString(2,pw);
                        ResultSet rs = pst.executeQuery();
                        if(rs.next()){
                            session.setAttribute("un", un);
                            response.sendRedirect("view.jsp");
                        }else{
        %>
                            <script>
                                alert("invalid login")    
                            </script>
        <%
                        }
                        con.close();
                        
                    } catch (SQLException e) {
        %>
                        <script>
                            alert("<%= e %>" + "issue")    
                        </script>
        <%
                    }
        }
    %>

</body>
</html>