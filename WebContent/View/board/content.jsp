<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page import="board.boardInfo"%>
<%@ page import="mybatis.MybatisBoardDBBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>

<title>�Խ���</title>
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
<center><b>�۳��� ����</b>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="${bodyback_c}" align="center">
   <tr height="30">
      <td align="center" width="125" bgcolor="${value_c}">�۹�ȣ</td>
      <td align="center" width="125" align="center">
         ${article.num}</td>
      <td align="center" width="125" bgcolor="${value_c}">��ȸ��</td>
      <td align="center" width="125" align="center">
         ${article.readcount}</td>
   </tr>
   <tr height="30">
      <td align="center" width="125" bgcolor="${value_c}">�ۼ���</td>
      <td align="center" width="125" align="center">
         ${article.writer}</td>
      <td align="center" width="125" bgcolor="${value_c}">�ۼ���</td>
      <td align="center" width="125" align="center">
         ${article.reg_date}</td>
   </tr>
   <tr height="30">
      <td align="center" width="125" bgcolor="${value_c}">������</td>
      <td align="center" width="375" align="center" colspan="3">
         ${article.subject}</td>
     </tr>
   <tr height="30">     
      <td align="center" width="125" bgcolor="${value_c}">�۳���</td>
      <td align="left" width="375" colspan="3"><pre>
         ${article.content}</pre></td>
   </tr>
    <tr height="30">     
      <td align="center" width="125" bgcolor="${value_c}">�̹���</td>
      <td align="left" width="375" colspan="3"><pre>
         <img src = "/Aven/fileSave/${article.filename}"/></pre></td>
   </tr>
   <tr height="30">
      <td colspan="4" bgcolor="${value_c}" align="right">
         <input type="button" value="�ۼ���" onclick="document.location.href='updateForm.do?num=${article.num}&pageNum=${pageNum}'">
         &nbsp;&nbsp;&nbsp;&nbsp;
         <input type="button" value="�ۻ���" onclick="document.location.href='deleteForm.do?num=${article.num}&pageNum=${pageNum}'">
         &nbsp;&nbsp;&nbsp;&nbsp;
         <!-- ref �� re_step, re_level �� ���� �Ǿ����� -->
         <input type="button" value="��۾���" onclick="document.location.href='writeForm.do?num=${num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}&pageNum=${pageNum}'">
         &nbsp;&nbsp;&nbsp;&nbsp;
         <input type="button" value="�۸��" onclick="document.location.href='htmlboard.do?pageNum=${pageNum}'">
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
