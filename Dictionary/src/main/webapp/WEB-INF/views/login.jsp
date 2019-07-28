<!DOCTYPE html>
<html>
<head>
   <title>Login</title>
</head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
   body {
      background-color: #121523;
      text-align: center;
   }
   .container {
      background: white;
      margin-top: 50px;
      width: 300px;
      height: 420px;
      border-radius: 45px;
   }
   .content {
      padding-top: 50px;
      margin: auto;
      width: 80%;
   }
   .btn {
      width: 100%;
   }
   .form-group > input {
      background-color: #f4f4f4;
      height: 40px;
   }
   .error {
      color: red;
      display: none;
   }
   span {
      margin-top: 20px;
      margin-bottom: 20px;
   }
   h3 {
      margin-bottom: 30px;
      font-size: 14px;
      font-weight: bold;
   }
</style>
<body>
   <div class="container">
      <div class="content">
         <h3>TOPICA DICTIONARY SYSTEM</h3>
         <form:form action="login" method="post" modelAttribute="user">
            <div class="form-group">
               <form:input class="form-control" path="username" type="text" id="username" name="username" placeholder="username"/>
            </div>
            <div class="form-group">
              <span class="error" id="message-error"></span >
            </div>
            <div class="form-group">
               <form:input class="form-control" path="password" type="password" id="password" name="password" placeholder="password"/>
            </div>
            <div class="form-group">
               <button type="submit" class="btn btn-primary">Login</button>
            </div>
            <div class="form-group">
               <p>You haven't an account yet?</p>
               <button type="button" class="btn btn-success">Register</button>
            </div>
         </form:form>
      </div>
   </div>
</body>
</html>