<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/styles/index.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
    <title>Document</title>
</head>
<body> 
   
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>    
<%
ArrayList<String> userdetails=(ArrayList<String>)session.getAttribute("userdetails");
String name=userdetails.get(0);
String mobilenumber="/static/profileimages/"+userdetails.get(1)+".jpg";
%>       
    <div class="divider">
    <div class="sidebar">
    <div class="myprofile">
        <img src=<%=mobilenumber %> alt="">
    </div>
    <div class="globalchat">
        <button onclick="location.href='chatroom';"><img src="/static/images/globalchat.png" alt=""></button>
    </div>
    <div class="privatechat">
        <button onclick="location.href='privatechat';"><img src="/static/images/chat.png" alt=""></button>
    </div>
    <br><br>
    <div ><a href="/logout" style="color:white;margin-bottom:5px;width: 35%;justify-content: center;margin-left:14px;">Logout</a></div>
</div>
<div class="messagecontent">
    <div class="scrollablemessagecontent" id="widthassign">
        <div class="emptyspace" id="marker"></div>
        <Script>
        </Script>
    </div>
    <div class="messagesender">
        <input type="text" placeholder="Enter your message" id="sendmessage">
        <button onclick="sendmessage()"><img src="/static/images/send.png" alt=""></button>
    </div>
</div>
</div> 
<script>
var id=0;
setInterval(Readmessage, 1000);
Readmessage()
function Readmessage(){
	$(document).ready(function(){
		console.log("calling");
		$.ajax({
			type:'POST',
			url:'GlobalController',data:{currentId:id},
			success:function(result){
				if(result!=""){
				var parseresult=$.parseJSON(result);
				id=id+parseresult.length;
				parseresult;
				console.log(id);
				console.log(result);
				$.each(parseresult,function(i,value){
					console.log("<%=name%>");
					if(value.sender=="<%=name%>"){
						/* var message=atob(value.message) */
						$("#marker").append('<div class="rightmessage"><h3>'+value.sender+'</h3><div class="message"><p>'+value.message+'</p></div></div>');
					}else{
						/* var message2=atob(value.message) */
						$("#marker").append('<div class="leftmessage"><h3>'+value.sender+'</h3><div class="message"><p>'+value.message+'</p></div></div>');
					}
				});
				var elem = document.getElementById('widthassign');
				  elem.scrollTop = elem.scrollHeight;

				}
			}
		});
	})
}
$(document).keyup(function(event) {
    if (event.which === 13) {
    	sendmessage();
    }
});
    function sendmessage(){
        $(document).ready(function(){
        	var inputelement=document.getElementById("sendmessage");
        	var message=inputelement.value;
        	if(message!=''){
        	$.ajax({
        		type:'GET',data:{message:message},
        		url:'GlobalController',
        		success:function(result){
        			console.log("sended "+message);
        			Readmessage();
        		}
        	});
        	inputelement.value='';
        	}
        });
    }
</script>
</body>
</html>