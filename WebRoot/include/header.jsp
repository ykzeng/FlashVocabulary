<%@page import="com.flashvocabulary.dto.User"%>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0059)file:///R:/Workspace/Front-End/FlashVocabulary/recital.html -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<script type="text/javascript">
</script>
</head>
<div class="navbar">
<div style="width:80%;left:10%;right:10%;position:absolute;">
    <div style="width:50%;float:left">
        <h1 style="margin-top:15px;margin-left:5px;font-size:40px;">Flash Vocabulary</h1>
    </div>
    <div class="nav_right">
        <form id="searchForm" action="SearchAction" method="post">
        <input name="word" placeholder="Search A Word..."><a onclick="document.getElementById('searchForm').submit();return false;"><i class="icon-search icon-large"></i></a><a href="UserHomeAction"><i class="icon-user icon-large"></i></a><a style="border-right:1px solid #ccc;" href="LoginAction"><i class="icon-home icon-large"></i></a>
        </form>
    </div>
</div>
</div>
<!-- <%--=((User)(request.getSession().getAttribute("user"))).getUname()--%> -->