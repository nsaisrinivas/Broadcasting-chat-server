<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/styles/privatechat.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
    <title>Document</title>
</head>

<body>
   
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %> 
<%
ArrayList<String> userdetails=(ArrayList<String>)session.getAttribute("userdetails");
String name=userdetails.get(1);
String name1=userdetails.get(0);
String profilepic="/static/profileimages/"+name+".jpg";
%> 
        <div class="divider">
            <div class="sidebar">
                <div class="myprofile">
                    <img src=<%=profilepic %> alt="">
                </div>
    <div class="globalchat">
        <button onclick="location.href='chatroom';"><img src="/static/images/globalchat.png" alt=""></button>
    </div>
    <div class="privatechat">
        <button onclick="location.href='privatechat';"><img src="/static/images/chat.png" alt=""></button>
    </div>
    <br><br>
    <div ><a href="/logout" style="color:white;margin-bottom:5px;width: 35%;justify-content: center;margin-left:30%;">Logout</a></div>
            </div>
            <div class="fixedscrollable">
                <div class="participants">
                    <img src="/static/images/search.png" alt="">
                    <input type="text" placeholder="Search to add people" id="searchperson">
                </div>
                <div id="chathistory">
                
                </div>
            </div>
            <div class="messagecontent">
                <div class="top">
                <div class="senderprofile">
                    <img src=<%=profilepic %> alt="" id="recimg">
                    <div class="senderprofilename">
                        <h3 id="receivername"></h3>
                        <p id="activestate"></p>
                    </div>
                    <div class="menu">
                    <img src="/static/images/menu.png" alt="" class="menu">
                </div>
                </div>
            </div>
                <div class="scrollablemessagecontent" id="widthassign">
                    <div class="emptyspace" id="marker"></div>
                </div>
                <div class="messagesender">
                    <input type="text" placeholder="Enter your message" id="sendmessage">
                    <button><img src="/static/images/send.png" alt=""></button>
                </div>
            </div>
        </div>
        <script>
        var messagenumber=0;
        var chatid=0;
        var historyid=0;
        var name="";
        setInterval(function(){Readhistory(1);Readmessage(chatid);}, 1000);
        $(document).keyup(function(event) {
            if (event.which === 13) {
            	search();
            	sendmessage(chatid);
            }
        });
        $("#selector").on("mousedown", function() {
            $(this).css('background','red');
        })
        .on("mouseup", function(e) {
        	$(this).css('background','pink');
        });
        function search(){
        	$(document).ready(function(){
            	var element=document.getElementById("searchperson");
            	console.log(element.value);
            	if(element.value!=""){
                	$.ajax({
                		type:'GET',data:{reciverId:element.value},
                		url:'privatechatAdd',
                		success:function(result){
                			alert(result);
                			
                		}
                	});
                	element.value='';
            	}
            });
        }
        function Readhistory(updateid){
        	if(chatid!=0){
        		$("div").find("[data-value='" + chatid + "']").addClass("active");
        	}
        	if(updateid==1){
        	$(document).ready(function(){
        		console.log("calling");
        		$.ajax({
        			type:'POST',
        			url:'privatechatAdd',data:{historyId:historyid},
        			success:function(result){
        				if(result!=""){
        				var parseresult=$.parseJSON(result);
        				var temp=historyid;
        				historyid=historyid+parseresult.length;
        				$("#chathistory").empty(); 
        				$.each(parseresult,function(i,value){ 
        					if(value.lastMessage!=" "){
        						$("#chathistory").append('<div class="chathistorygroup" id="selector" data-value1='+value.name+' data-value='+value.chat_id+' data-value2='+value.receiverPhone+'> <button style="width: 100%;z-index: 1;background-color: transparent;border-style: none;"><div class="chathistoryprofile" style="z-index: 0;"><img src="/static/profileimages/'+value.receiverPhone+".jpg"+'" alt="" style="width=1px;" class="chathistoryprofileimage"><div class="chathistorysendersprofile"><h3 style="margin-top:15%;text-align: left;">'+value.name+'</h3><p style="text-align: left;">'+value.lastMessage+'</p></div></div></button></div>');
        					}/* else{
        						$("#chathistory").append('<div class="chathistorygroup id="selector" data-value1='+value.receiverPhone+' data-value='+value.chat_id+'><button style="width: 100%;z-index: 1;background-color: transparent;border-style: none;" ><div class="chathistoryprofile" style="z-index: 0;"><img src="/static/images/profile.png" alt="" style="width=1px;" class="chathistoryprofileimage"><div class="chathistorysendersprofile"><h3 style="text-align: left;">'+value.receiverPhone+'</h3><p style="text-align: left;">lashfd a ahfljhafil halfha an ffjapfj apfpaokf pfkapo kpokaf oakfkoa k kf[a k[fk [afk[ak oafafj wfjafjijfpip af</p></div></div></button></div>');
        					} */
        				});
        				console.log(result);
        				}
        			}
        		});
        	})
        }
        	else{
        		$(document).ready(function(){
            		console.log("calling");
            		$.ajax({
            			type:'POST',
            			url:'privatechatAdd',data:{historyId:0},
            			success:function(result){
            				if(result!=""){
            				var parseresult=$.parseJSON(result);
            				var temp=historyid;
            				historyid=historyid+parseresult.length;
            				$("#chathistory").empty(); 
            				$.each(parseresult,function(i,value){ 
            					if(value.lastMessage!=" "){
            						$("#chathistory").append('<div class="chathistorygroup" id="selector" data-value1='+value.name+' data-value='+value.chat_id+' data-value2='+value.receiverPhone+'> <button style="width: 100%;z-index: 1;background-color: transparent;border-style: none;"><div class="chathistoryprofile" style="z-index: 0;"><img src="/static/profileimages/'+value.receiverPhone+".jpg"+'" alt="" style="width=1px;" class="chathistoryprofileimage"><div class="chathistorysendersprofile"><h3 style="margin-top:15%;text-align: left;">'+value.name+'</h3><p style="text-align: left;">'+value.lastMessage+'</p></div></div></button></div>');
            					}/* else{
            						$("#chathistory").append('<div class="chathistorygroup id="selector" data-value1='+value.receiverPhone+' data-value='+value.chat_id+'><button style="width: 100%;z-index: 1;background-color: transparent;border-style: none;" ><div class="chathistoryprofile" style="z-index: 0;"><img src="/static/images/profile.png" alt="" style="width=1px;" class="chathistoryprofileimage"><div class="chathistorysendersprofile"><h3 style="text-align: left;">'+value.receiverPhone+'</h3><p style="text-align: left;">lashfd a ahfljhafil halfha an ffjapfj apfpaokf pfkapo kpokaf oakfkoa k kf[a k[fk [afk[ak oafafj wfjafjijfpip af</p></div></div></button></div>');
            					} */
            				});
            				console.log(result);
            				}
            			}
            		});
            	})
        	}
        	}
        $(document).ready(function(){
        	$(document).on('click','#selector',function(){
        		removeselector();
        		$(this).addClass("active");
        		chatid=$(this).attr("data-value");
        		name=$(this).attr("data-value1");
        		receiver=$(this).attr("data-value2");
        		document.getElementById("receivername").innerText=name;
        		document.getElementById("activestate").innerText="Active Now";
        		document.getElementById("recimg").src="/static/profileimages/"+receiver+".jpg";
        		loadchat(chatid);
        	});
        })
        function removeselector(){
        	if($(".chathistorygroup").hasClass("active")){
        		$(".chathistorygroup").removeClass("active");
        	}
        }
        function loadchat(chartid){
        	
        	console.log("new chatid"+chatid);
        	messagenumber=0
        	$("#marker").empty(); 
        	Readmessage(chatid);
        }
        function sendmessage(chatid){
            $(document).ready(function(){
            	var inputelement=document.getElementById("sendmessage");
            	var message=inputelement.value;
            	if(message!='' && chatid!=0){
            	$.ajax({
            		type:'GET',data:{message:message,chatid:chatid},
            		url:'ReadPrivatechat',
            		success:function(result){
            			console.log("sended "+message);
            			Readmessage(chatid);
            			Readhistory(0);
            		}
            	});
            	inputelement.value='';
            	}
            });
        }
        function Readmessage(chatid){
        	if(chatid!=0){
        	$(document).ready(function(){
        		console.log("calling");
        		$.ajax({
        			type:'POST',
        			url:'ReadPrivatechat',data:{chatid:chatid,currentId:messagenumber},
        			success:function(result){
        				if(result!=""){
        				Readhistory(0);
        				var parseresult=$.parseJSON(result);
        				messagenumber=messagenumber+parseresult.length;
        				console.log(messagenumber);
        				console.log(result);
        				$.each(parseresult,function(i,value){
        					console.log("<%=name%>");
        					if(value.senderPhone=="<%=name%>"){
        						$("#marker").append('<div class="rightmessage"><h3><%=name1%></h3><div class="message"><p>'+value.message+'</p></div></div>');
        					}else{
        						$("#marker").append('<div class="leftmessage"><h3>'+name+'</h3><div class="message"><p>'+value.message+'</p></div></div>');
        					}
        				});
        				var elem = document.getElementById('widthassign');
        				  elem.scrollTop = elem.scrollHeight;

        				}
        			}
        		});
        	})        	
        	}
        }
        </script>
</body>

</html>