<%@page import="com.flashvocabulary.dto.User"%>
<%@ page contentType="text/html; charset=utf-8" %>

<div style="width:80%;top:0%;left:10%;right:10%;position:absolute;">
    <div style="width:50%;float:left">
        <h1 style="margin-top:15px;margin-left:5px;font-size:40px;">Flash Vocabulary</h1>
    </div>
    <div style="width:50%;float:right;text-align:right;margin-top:8px;">
    <div style="cursor:pointer;" onclick="location.href='UserHomeAction'"><%=((User)(request.getSession().getAttribute("user"))).getUname()%></div>
   <div style="float:right;width:50%;margin-top:3px;">
   		<form action="SearchAction" method="post">
    	<div style="width:88%;float:left;">
	    	<input name="word" style="padding:4px 6px 4px 6px;height:20px;border:1px solid #ccc;border-right:0px;">
	    </div>
	    <div style="width:12%;float:left;">
	    <input type="submit" value="" name="search" style="border:1px solid #ccc;height:30px;width:36px;text-align:center;background:url('images/search_btn.png') no-repeat center center;background-size:24px 24px;cursor:pointer;"></input>
	    </div>
	    </form>
	</div>
    </div>
</div>

