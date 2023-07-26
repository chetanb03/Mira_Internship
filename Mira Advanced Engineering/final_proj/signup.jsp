<%@ page import = "java.sql.*"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="row">
        <div class="col">
            <img src="images/signup.png" class="imgSignup"  alt="image">
          </div>
          <div class=" signup container shadow-lg p-3 mb-5 y rounded">
            <div class="col text-form">
                <h1>SignUp</h1>
                <br>
                <form>
                    <input type="text" name="un" class="form-control text " placeholder="Enetr user name" pattern="[A-Za-z ]+">
                    <br><br>
                    <input type="password" name="pw" class="form-control text " placeholder="Enter password">
                    <br><br>
                    <input type="password" name="pw1" class="form-control text " placeholder="Confirm password">
                    <br><br>
                    <input type="submit" name="btn" class="btn btn-primary" value="Signup">
                    <br><br>
                    <p>Already have an Account? <a class="login_link" href="index.jsp">Login</a></p>
                </form>
            </div>
    </div>
    </div>

    <% 
        if(request.getParameter("btn") != null){
            String un = request.getParameter("un");
            String pw = request.getParameter("pw");
            String pw1 = request.getParameter("pw1");
            if(!pw.equals(pw1)){
    %>
                <script>alert("Password does not match")</script>
    <%
                return;
            }
            try {
                        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                        String url = "jdbc:mysql://localhost:3306/final_project23";
                        Connection con = DriverManager.getConnection(url, "root", "abc123");

                        String sql = "insert into user values(?,?)";
                        PreparedStatement pst =  con.prepareStatement(sql);
                        pst.setString(1,un);
                        pst.setString(2,pw);
                        pst.executeUpdate();
                        response.sendRedirect("index.jsp");
                        con.close();
                    }catch(SQLIntegrityConstraintViolationException e){
    %>
                        <script>
                            alert("User already exists")
                        </script>
    <%
                    } 
                    catch (SQLException e) {
    %>
                        <script>
                            alert("<%= e %>"+ "issue");    
                        </script>
    <%
                    }
        }
    %>

</body>
</html>