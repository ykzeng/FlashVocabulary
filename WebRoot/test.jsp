<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="SearchResultServlet" method="post">
		<input type="text" name="word"/>
		<input type="submit" value="Search">
	</form>
	<form action="UserCollectServlet" method="post">
		<input type="hidden" name="uid" value="18">
		<input type="submit" value="查看我的单词本" name="getCollect"/>
	</form>
</body>
</html>