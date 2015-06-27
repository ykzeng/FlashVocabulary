<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userCheckin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/gh-buttons.css">
	<link rel="stylesheet" type="text/css" href="css/basics.css">
	<script type="text/javascript">
	</script>

  </head>
  
  <body class="uni_background">

    <jsp:include  page="include/header.jsp"/>
    <form action="CheckInAction" method="POST" id="checkinForm">
    <div class="center_div_small">
    	 <div class="usercheckin-div">
    	 <h1 style="font-size:40px;color:#707070"><span style="font-weight:normal">CHECKIN DAY</span>${checkinCount}</h1>
         </div>
         <div style="margin:0% 6%;">
    	 	<textarea class="text_area" placeholder="How are you feeling about now?" rows="6" name="mytext"></textarea>
			<input type="submit" class="button big check_btn" name="checkin" value="CheckIn" disabled="${canCheckin}"/> 
    	 </div>
    </div>
    <div class="center_div_small">
    	 <div class="usercheckin-div">
    	 <h1 style="font-size:40px;color:#707070"><span style="font-weight:normal">HISTORY</span></h1>
         </div>
         <div class="check_his">
         	${longSTR}
    	 </div>
    </div>     
         <div>
         
         	<!--<ul>
         		<li>
         			<p>第99天打卡日记:</p>
         			nihao!
         			<p>6月23日，2015</p>
         		</li>
         		<li>
         			<p>第98天打卡日记:</p>
         			nihao!
         			<p>6月23日，2015</p>
         		</li>
         		<li>
         			<p>第97天打卡日记:</p>
         			nihao!
         			<p>6月23日，2015</p>
         		</li>
         	</ul-->
         </div>
    </div>
    </form>
  </body>
</html>
