<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<% 
response.setContentType("text/html;charset = UTF-8"); 
request.setCharacterEncoding("UTF-8"); 
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0059)file:///R:/Workspace/Front-End/FlashVocabulary/recital.html -->
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:c="http://java.sun.com/jstl/core">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>FV-WordDetails</title>
<link rel="stylesheet" type="text/css" href="css/basics.css"></link>
</head>
<body class="uni_background">
	<div class="flashcard">
    	<div class="word_label">
			<h1>${word}</h1>
			<h3>${phonetics}</h3>
		</div>
		<div class="sentence_card" style="margin-top:4%;">
				<h3>Translations</h3>
				${longTranSTR}
			</div>
		<div class="trans_container">
			<div class="trans_card">
				<h3>Synonyms</h3>
				${synonymSTR==null?"暂无":synonymSTR}
			</div>
			<div class="trans_card" style="float:right">
				<h3>Antonyms</h3>
				${antonymSTR==null?"暂无":antonymSTR}
			</div>
		</div>

		<div class="sentence_card">
			<h2>Usages</h2>
			${sentenceSTR}
		</div>
    </div>
</body>
</html>