<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login | HungryMate</title>

<style>
    body {
        font-family: "Segoe UI", sans-serif;
        background: linear-gradient(120deg, #1e1f1c, #6b8e23);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0;
    }

    .login-box {
        width: 360px;
        background: #ffffff;
        padding: 30px;
        border-radius: 14px;
        box-shadow: 0 15px 40px rgba(0,0,0,0.35);
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
        color: #2b2b2b;
    }

    label {
        display: block;
        margin-top: 12px;
        font-weight: 500;
    }

    input {
        width: 100%;
        padding: 10px;
        margin-top: 6px;
        border-radius: 8px;
        border: 1px solid #ccc;
        font-size: 14px;
    }

    button {
        width: 100%;
        margin-top: 22px;
        padding: 12px;
        background: #6b8e23;
        color: #fff;
        border: none;
        border-radius: 8px;
        font-size: 15px;
        cursor: pointer;
    }

    button:hover {
        background: #55701c;
    }

    .error {
        color: red;
        text-align: center;
        margin-bottom: 10px;
    }

    .footer {
        text-align: center;
        margin-top: 15px;
    }

    .footer a {
        text-decoration: none;
        color: #6b8e23;
        font-weight: 500;
    }
</style>
</head>

<body>

<div class="login-box">
    <h2>HungryMate Login</h2>

    <%-- Error message from LoginServlet --%>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="error"><%= error %></div>
    <%
        }
    %>

    <form action="login" method="post">
        <label>Email</label>
        <input type="email" name="email" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit">Login</button>
    </form>

    <div class="footer">
        New user?
        <a href="register.jsp">Create an account</a>
    </div>
</div>

</body>
</html>
