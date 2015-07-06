<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page import="board.boardInfo"%>
<%@ page import="mybatis.MybatisBoardDBBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>

<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<%-- <%
	boardInfo article = new boardInfo();
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
	try {
		BoardDBBean dbpro = BoardDBBean.getInstance();
		article = dbpro.getArticle(num, "htmlboard");
		
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
%> --%>

<body>
<center><b>글내용 보기</b>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="${bodyback_c}" align="center">
   <tr height="30">
      <td align="center" width="125" bgcolor="${value_c}">글번호</td>
      <td align="center" width="125" align="center">
         ${article.num}</td>
      <td align="center" width="125" bgcolor="${value_c}">조회수</td>
      <td align="center" width="125" align="center">
         ${article.readcount}</td>
   </tr>
   <tr height="30">
      <td align="center" width="125" bgcolor="${value_c}">작성자</td>
      <td align="center" width="125" align="center">
         ${article.writer}</td>
      <td align="center" width="125" bgcolor="${value_c}">작성일</td>
      <td align="center" width="125" align="center">
         ${article.reg_date}</td>
   </tr>
   <tr height="30">
      <td align="center" width="125" bgcolor="${value_c}">글제목</td>
      <td align="center" width="375" align="center" colspan="3">
         ${article.subject}</td>
     </tr>
   <tr height="30">     
      <td align="center" width="125" bgcolor="${value_c}">글내용</td>
      <td align="left" width="375" colspan="3"><pre>
         ${article.content}</pre></td>
   </tr>
    <tr height="30">     
      <td align="center" width="125" bgcolor="${value_c}">이미지</td>
      <td align="left" width="375" colspan="3"><pre>
         <img src = "/Aven/fileSave/${article.filename}"/></pre></td>
   </tr>
   <tr height="30">
      <td colspan="4" bgcolor="${value_c}" align="right">
         <input type="button" value="글수정" onclick="document.location.href='updateForm.do?num=${article.num}&pageNum=${pageNum}'">
         &nbsp;&nbsp;&nbsp;&nbsp;
         <input type="button" value="글삭제" onclick="document.location.href='deleteForm.do?num=${article.num}&pageNum=${pageNum}'">
         &nbsp;&nbsp;&nbsp;&nbsp;
         <!-- ref 와 re_step, re_level 이 세팅 되어있음 -->
         <input type="button" value="답글쓰기" onclick="document.location.href='writeForm.do?num=${num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}&pageNum=${pageNum}'">
         &nbsp;&nbsp;&nbsp;&nbsp;
         <input type="button" value="글목록" onclick="document.location.href='htmlboard.do?pageNum=${pageNum}'">
      </td>
   </tr>
</table>
<%-- <%
   }catch(Exception e) {}
%> --%>
</form>
</center>
</body>
</html>
