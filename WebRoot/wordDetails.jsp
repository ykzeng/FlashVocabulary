<%@page import="com.flashvocabulary.dto.WordSentenceView"%>
<%@page import="com.flashvocabulary.service.SearchService.tranList"%>
<%@page import="com.flashvocabulary.service.SearchService.searchResult"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="css/gh-buttons.css">
<link rel="stylesheet" type="text/css" href="css/basics.css"></link>
</head>

<%
	searchResult result = (searchResult)request.getAttribute("result");
	List<String> wordLibNameList = result.getWordlib_name();
	List<tranList> libTransList = result.getEveryTranList();
	List<WordSentenceView> wsvList = (List<WordSentenceView>) request.getAttribute("wsvList");
 %>

<body class="uni_background">
<jsp:include  page="include/header.jsp"/>
	<form action="UserCollectAction" method="post" id="collectSubmit">
		<div class="flashcard">
		<div class="word_label">
			<h1><%=result.getWord() %></h1>
			<h3><%=result.getPhonetics() %></h3>
		</div>
		<div class="trans_container">
		<% for(int i = 0; i < wordLibNameList.size(); i ++) {
		String [] tranList = libTransList.get(i).getEveryTran();%>
			<div class="trans_card" <%if(i%2 == 0){ %>style="margin-right:1.5%"<%} %>>
				<a class="fav_btn button icon favorite"  onclick="document.getElementById('collectSubmit').submit();">Collect</a>
				<h3><%=wordLibNameList.get(i)%></h3>
				<ol>
				<%for(int j = 0; j < tranList.length; j++){%>
					<li><%=tranList[j] %></li>
				<%} %>
				</ol>
			</div>
			<%if(i%2 == 1){ %><br><%} %>
		<% }%>
		</div>
		

		<div class="sentence_card">
			<h2>Usages</h2>
			<ol class="sentence_list">
			<%for(int i=0;i<wsvList.size();i++){ %>
				<li><span><%=wsvList.get(i).getSentence() %></span><br>
					<p><%=wsvList.get(i).getTranslation() %></p></li>
			<%} %>
			</ol>
		</div>
	</div>
	</form>
	
</body>
</html>