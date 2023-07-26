<%@ page import = "java.sql.*"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Login</h1>
    <br>
    <% 
    String un = (String)session.getAttribute("un");
    if(un == null){
        response.sendRedirect("index.jsp");
    }else{
        out.println("welcome" + un);
    }
     %>

     <form >
        <input type="submit" name="add" value="add">
        <br>
        <input type="submit" name="view" value="View">
        <br>
        <input type="submit" name="update" value="Update">
        <br>
        <input type="submit" name="delete" value="Delete">
        <br>
        <input type="submit" name="btn" value="logout">
        <br><br>
     </form>

    <% 
        // if(request.getParameter("btn") != null){
        //     session.invalidate();
        //     response.sendRedirect("index.jsp");
        // }
        if(request.getParameter("add") != null){
            response.sendRedirect("add.jsp");
        }
        if(request.getParameter("view") != null){
            response.sendRedirect("view.jsp");
        }
        if(request.getParameter("update") != null){
            response.sendRedirect("update.jsp");
        }
        if(request.getParameter("delete") != null){
            response.sendRedirect("delete.jsp");
        }
    %>

</body>
</html>