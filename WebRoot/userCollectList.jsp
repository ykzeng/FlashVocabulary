<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
    <title>FV-Collections</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="css/gh-buttons.css"/>
	<link rel="stylesheet" type="text/css" href="css/basics.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
	function changelibview(divid)
	{
		for (var i = 0; i < 4; i++) {
		var libid = ("lib-div"+i);
        var a_id = ("#a"+i);
            if(divid==i)
            {
            	document.getElementById(libid).style.display="block";
                $(a_id).addClass("btn_active");
            }
            else
            {
            	document.getElementById(libid).style.display="none";
                if ($(a_id).hasClass("btn_active")) {
                    $(a_id).removeClass("btn_active");
                }
            }
            
        }
		
	}
	</script>
</head>
<body class="uni_background">
<jsp:include  page="include/header.jsp"/>
    <div class="center_div">
    	 <div class="usercheckin-div">
             <div style="width:50%;float:left">
                <h1 style="font-size:40px;color:#707070">USER COLLECTION</h1>
             </div>
        	 <div style="width:50%;overflow:hidden;padding-top:26px;">
        	 	<ul class="button-group" style="float:right">
                    <li><a id="a0" onclick="changelibview(0)" class="button pill">CET-4</a></li>
                    <li><a id="a1" onclick="changelibview(1)" class="button pill">CET-6</a></li>
                    <li><a id="a2" onclick="changelibview(2)" class="button pill">GRE3000</a></li>
                    <li><a id="a3" onclick="changelibview(3)" class="button pill">TOEFL</a></li>
                </ul>	
        	 </div>
         </div>
         <div class="usercheckin-div">
             <div id="lib-div0" class="collect_div">
                 <center><h1>${libName1==null?"无":libName1}</h1></center>
                 ${longSTR1}
             </div>
             <div id="lib-div1" class="collect_div" style="display:none;">
                 <center><h1>${libName2==null?"无":libName2}</h1></center>
                 ${longSTR2}
             </div>
             <div id="lib-div2" class="collect_div" style="display:none;">
                 <center><h1>${libName3==null?"无":libName3}</h1></center>
                 ${longSTR3}
             </div>
             <div id="lib-div3" class="collect_div" style="display:none;">
                 <center><h1>${libName4==null?"无":libName4}</h1></center>
                 ${longSTR4}
             </div>
         </div>
    </div>
    <jsp:include  page="include/footer.jsp"/>
</body>
</html>