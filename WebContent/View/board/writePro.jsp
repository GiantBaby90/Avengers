<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "board.BoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- 멤버 처럼 사용하려면 선언 해야됨 -->
<jsp:useBean id="article" class = "board.boardInfo">
	<jsp:setProperty name = "article" property = "*" />	
</jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String pageNum = request.getParameter("pageNum");
	String id = (String)session.getAttribute("id");
	/* article.setReg_date(new Timestamp(System.currentTimeMillis())); */
	article.setReg_date(new Timestamp(System.currentTimeMillis()));
	article.setIp(request.getRemoteAddr());
	BoardDBBean dbPro = BoardDBBean.getInstance();
	dbPro.insertArticle(article, id);

//	response.sendRedirect("list.jsp?pageNum="+pageNum);
%>
<script>location.href = "htmlboard.jsp"</script>
</body>
</html>