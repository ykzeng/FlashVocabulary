<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userHome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <body class="uni_background">
    <div class="center_left">
        <div class="lib_status_card hover_hand" id="collection_card" onclick="location.href='index.html';">
            <h3 style="color:#707070;">COLLECTIONS</h3>
            <h3 style="font-weight:bold;font-size:36px;margin-top:20px;">TOTAL<strong style="color:black;"> ${userCollectCount}</strong></h3>
            <h3 style="font-size:36px;text-align:right;color:#2dbe60">RECITE'EM NOW</h3>
        </div>
        <div class="today_status_card hover_hand" id="lib_card" onclick="location.href='libChange.html';">
            <h3 style="color:#707070;">WORD LIB</h3>
            <h3 style="font-size:36px;"><span>CURRENT</span></h3>
            <h3 style="font-weight:normal;font-size:36px;"><span style="color:#2dbe60">${libName}</span></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span style="color:red;">CLICK TO CHANGE</span></h3>
        </div>
    </div>
    <div class="center_right" style="background:#ecf2f3;">
        <div class="today_status_card hover_hand" id="checkin_card">
            <h3 style="color:#707070;">CHECK IN DAYS</h3>
            <h3 style="font-size:36px;margin-top:20px;">TOTAL<strong style="color:black;"> ${checkinDays}</strong></h3>
            <h3 style="font-size:36px;">CONTINUATION<strong style="color:#2dbe60"> ${continuation}</strong></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span style="color:red;">SEE MY CHECKINS</span></h3>
        </div>
        <div class="lib_status_card hover_hand" id="setting_card">
            <h3 style="color:#707070;">SETTINGS</h3>
            <h3 style="font-weight:normal;font-size:36px;"><span>SET YOUR</span></h3>
            <h3 style="font-weight:normal;font-size:36px;"><span style="color:#2dbe60">PASSWORD</span></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span style="color:red;">TARGET</span></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span>ETC</span></h3>
        </div>
    </div>

    <script type="text/javascript">
        function test(){
            var temp = document.getElementById("try_strong").innerText;
            alert(temp);
        }
    </script>
</body>
  </body>
</html>
