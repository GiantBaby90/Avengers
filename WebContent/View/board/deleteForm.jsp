<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="board.boardInfo"%>
<%@ page import="mybatis.MybatisBoardDBBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%
	int num = 0, ref = 1, re_step = 0, re_level =0;
	num = Integer.parseInt(request.getParameter("num"));
 	String pageNum = request.getParameter("pageNum");
   BoardDBBean dbpro = null;
   boardInfo article = null;
   
   /* try{
      dbpro = BoardDBBean.getInstance();
      article = dbpro.updateGetArticle(num, "javaboard");
   }catch(Exception e){} */
%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
</head>
<body>
<form method = "post" name = "delForm" action = "deletePro.do?pageNum=${pageNum}" onsubmit = "return deleteSave()">
<table border = "1" align = "center" cellspacing = "0" cellpadding = "0" width = "360">
	<tr height = "30">
		<td align = center bgcolor = "${value_c}">
			<b> ��й�ȣ�� �Է��� �ּ���. </b></td>
	</tr>
	<tr height = "30">
		<td align = center> ��й�ȣ : 
			<input type = "password" name = "passwd" size = "8" maxlength = "12">
			<input type = "hidden" name = "num" value = "${num}"></td>
	</tr>
	<tr height = "30">
		<td align = center bgcolor = "${value_c}">
			<input type = "submit" value = "�ۻ���">
			<input type = "button" value = "�۸��"
			onclick = "document.location.href = "htmlboard.jsp?pageNum=${pageNum}">
		</td>
	</tr>
</table>
</form>	

</body>
</html>