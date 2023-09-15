<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>  
	<html lang="en" >  
<head>  
  <meta charset="UTF-8">  
  <title> Login  
</title>  
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">  
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik+Moonrocks&display=swap" rel="stylesheet">
</head>  
<style>  
body {  
          background-repeat: no-repeat, repeat;  
          background-position: center;  
          background-size: cover;  
          padding-top: 50px; 
          background-image: url('/static/images/background4.gif');
        }  
        .login-form {  
          /* background:#0000007a;   */
          background: rgba(0, 0, 0, 0);
          margin-top: 40px;  
          margin-bottom: 100px;  
          padding: 100px;  
          border-radius: 50px;  
          color: white;  
          box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.75);  
        }  
        .login-heading {  
          text-align: center;  
          margin-top: 20px;  
        }  
        /* .btn-primary {
            display: block;
            align-items: center;
          margin-left:34%;   
          text-align: center;
          align-content: center;
          width:120px;
          background-color:whitesmoke ;
          color:black;
          /* border-radius: 4%;   
        } */
        /* .btn-primary:hover{
            background-color: #29ccd7;
            color:white;
        }   */
        .form-signup{
            text-align: center;
            padding:20 0 0;
            color:white;
        }
        .signup-button{
            color:orange;
            font-weight: bold;
            font-size: large;
        }
        .signup-button:hover{
            text-decoration: none;
            color:orange;
            font-weight: bolder;
            font-size: larger;
        }
        label{
            font-size: large;
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
        }
        .forbutton{
            box-shadow: 5px 5px 5px black;
            border-radius: 10px;
            width:100px;
            /* background:linear-gradient(to right,rgb(250,48,90),rgb(128,36,206)) */
            /* background:linear-gradient(to right,rgb(255,152,93),rgb(255,61,87)); */
            background-color:orange;
            font-weight: bold;
            font-size: large;
            color:white;
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            }
        .forbutton:hover{
            background-color: orange;
            /* width:110px;
            height: 40px; */
            font-size: larger;
        }
        .heading{
            color:orange;
            font-family:Arial, Helvetica, sans-serif;
            font-weight: bolder;
        }
        label{
            color:orange;
        }
</style>  
<body>
    <div class="container">  
      <div class="row">  
        <div class="col-md-8 offset-md-2">  
          <div class="login-form"> 
            <h1 class="text-center heading"> Login </h1> 
            <form id="form" action="login" method="post">  
              <div class="form-group">
              <label>Mobile Number</label>
              <input type="text" class="form-control text" id="mobilenumber" name="mobilenumber" placeholder="MobileNumber">
              </div>
              <div class="form-group">  
                <label > Password </label>  
                <br>
                <input type="password" class="form-control text" id="password" name="password" placeholder="Password">  
              </div> 
              <div id="error" style="color:red;">
              </div> 
              <%if(session.getAttribute("error")!=null){ %><div style="color:red;"><%=session.getAttribute("error") %></div><%} %>
              <br>
              <button type="submit" class="btn btn-primary forbutton" id="submit" > Login </button>  
              <br>
              <p class="form-signup">Not a User? <a href="signup" class="signup-button">Signup</a> </p>
            </form>  
          </div>  
        </div>  
      </div>  
    </div> 
    <script defer type="text/javascript" src="/static/jsfiles/script.js"></script> 
  </body>  
</html>