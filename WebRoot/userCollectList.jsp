<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
    <title>My JSP 'userCollectList.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="css/basics.css">
	<script type="text/javascript">
	function changelibview(divid)
	{
		for (var i = 0; i < 4; i++) {
		var libid = document.getElementById("lib-div"+i);
            if(divid==i)
            {
            	libid.style.display=="block";
            }
            else
            {
            	libid.style.display=="none";
            }
            
        }
		
	}
	</script>
</head>
<body class="uni_background">
    <div class="center_div">
    	 <div class="usercheckin-div">
    	 <h1 style="font-size:40px;color:#707070">USER COLLECTION</h1><hr>
    	 	<input type="submit" style="background:#e77e23;" id="sb1" value="CET-4" onclick="changelibview(0)">
    	 	<input type="submit" style="background:green;" id="sb2" value="CET-6" onclick="changelibview(1)">
    	 	<input type="submit" style="background:green;" id="sb3" value="GRE3000" onclick="changelibview(2)">
    	 	<input type="submit" style="background:green;" id="sb4" value="TOEFL" onclick="changelibview(3)"> 
         </div>
         <div id="lib-div0" style="display:block;">
	         <center><h2>${libName1==null?"无":libName1}</h2></center>
	         <center>${longSTR1}</center>
         </div>
         <div id="lib-div1" style="display:none;">
	         <center><h2>${libName2==null?"无":libName2}</h2></center>
	         <center>${longSTR2}</center>
         </div>
         <div id="lib-div2" style="display:none;">
	         <center><h2>${libName3==null?"无":libName3}</h2></center>
	         <center>${longSTR3}</center>
         </div>
         <div id="lib-div3" style="display:none;">
	         <center><h2>${libName4==null?"无":libName4}</h2></center>
	         <center>${longSTR4}</center>
         </div>
         </div>
</body>
</html>