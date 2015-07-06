<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "board.BoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>
<% request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id = "article" scope = "page" class = "board.boardInfo">
	<jsp:setProperty name = "article" property = "*"/>
</jsp:useBean>
<%
	String pageNum = request.getParameter("pageNum");
	BoardDBBean dbPro = BoardDBBean.getInstance();
	/* int check = dbPro.deleteArticle(article, "javaboard"); */
	int num = Integer.parseInt(request.getParameter("num"));
	String passwd = request.getParameter("passwd");
	int check = dbPro.deleteArticle(num, passwd);
	if(check == 1) { %>
		<meta http-equiv = "Refresh" content = "0;url=htmlboard.jsp?PageNum=${pageNum}">
		<script>alert("게시판 삭제 완료");</script>
<% } else { %>
	<script>alert("비밀번호가 맞지 않습니다");</script> 
<%
}
 %>