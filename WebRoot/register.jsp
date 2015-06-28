<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0059)file:///R:/Workspace/Front-End/FlashVocabulary/recital.html -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>User register</title>
<link rel="stylesheet" type="text/css" href="css/basics.css"/>
</head>

<body class="uni_background">
<div class="green_top_strip"></div>
<div class="top_middle_div">
	<h1>Flash Vocabulary</h1>
	<h2 style="color:#2dbe60;">REGISTER</h2>
</div>
	<form action="RegisterAction" method="post">
	<div class="login_div">
		<div style="height:100%;margin:10%;">
			<div style="width:100%">
				<h3>UserName</h3>
			</div>
			<div class="input_div">
				<input type="text" name="uname"/>
			</div>
			<div style="width:100%">
				<h3>Password</h3>
			</div>
			<div class="input_div">
				<input type="password" name="pwd"/>
			</div>
			<div class="input_div" style="margin-top:40px;">
				<input type="submit" value="Register" style="width:90.5%;background-color:#28a956;border-color:#1fae52;color:white;cursor:pointer;"/>
			</div>
		</div>
	</div>
	</form>
</body></html>