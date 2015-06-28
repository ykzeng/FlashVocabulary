<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>FV-UserHome</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/basics.css">
  </head>
  
  <body>
    <body class="uni_background">
    <jsp:include  page="include/header.jsp"/>
    <div style="width:100%;overflow:hidden">
    	<div class="center_left">
        <div class="lib_status_card hover_hand" id="collection_card" onclick="location.href='GetUserCollectAction';">
            <h3 style="color:#707070;">COLLECTIONS</h3>
            <h3 style="font-weight:bold;font-size:36px;margin-top:20px;">TOTAL<strong style="color:black;"> ${userCollectCount}</strong></h3>
            <h3 style="font-size:36px;text-align:right;color:#2dbe60">RECITE'EM NOW</h3>
        </div>
        <div class="today_status_card hover_hand" id="lib_card" onclick="location.href='ToChooseWordLibAction';">
            <h3 style="color:#707070;">WORD LIB</h3>
            <h3 style="font-size:36px;"><span>CURRENT</span></h3>
            <h3 style="font-weight:normal;font-size:36px;"><span style="color:#2dbe60">${libName}</span></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span style="color:red;">CLICK TO CHANGE</span></h3>
        </div>
    </div>
    <div class="center_right" style="background:#ecf2f3;">
        <div class="today_status_card hover_hand" id="checkin_card" onclick="location.href='ToCheckInAction';">
            <h3 style="color:#707070;">CHECK IN DAYS</h3>
            <h3 style="font-size:36px;margin-top:20px;">TOTAL<strong style="color:black;"> ${checkinDays}</strong></h3>
            <h3 style="font-size:36px;">CONTINUATION<strong style="color:#2dbe60"> ${continuation}</strong></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span style="color:red;">SEE MY CHECKINS</span></h3>
        </div>
        <div class="lib_status_card hover_hand" id="setting_card" onclick="location.href='userSetting.jsp';">
            <h3 style="color:#707070;">SETTINGS</h3>
            <h3 style="font-weight:normal;font-size:36px;"><span>SET YOUR</span></h3>
            <h3 style="font-weight:normal;font-size:36px;"><span style="color:#2dbe60">PASSWORD</span></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span style="color:red;">TARGET</span></h3>
            <h3 style="font-weight:normal;text-align:right;margin-right:16px;font-size:36px;"><span>ETC</span></h3>
        </div>
    </div>
    </div>
    
<jsp:include  page="include/footer.jsp"/>
    <script type="text/javascript">
        function test(){
            var temp = document.getElementById("try_strong").innerText;
            alert(temp);
        }
    </script>
</body>
  </body>
</html>
