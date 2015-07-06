<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="board.boardInfo"%>
<%@ page import="mybatis.MybatisBoardDBBean"%>
<%-- <%
	int num = 0, ref = 1, re_step = 0, re_level =0;
	num = Integer.parseInt(request.getParameter("num"));
 	String pageNum = request.getParameter("pageNum");
   BoardDBBean dbpro = null;
   boardInfo article = null;
   
   try{
      dbpro = BoardDBBean.getInstance();
      article = dbpro.updateGetArticle(num);
   }catch(Exception e){}
%> --%>
<form name="updateForm" action="updatePro.do" onsubmit="return writeSave()">
	<input type="hidden" name="num" value="${num}"> <input
		type="hidden" name="pageNum" value="${pageNum}"> <input
		type="hidden" name="ref" value="${ref}"> <input type="hidden"
		name="re_step" value="${re_step}"> <input type="hidden"
		name="re_level" value="${re_level}">
	<table width="400" border="1" cellspacing="0" cellpadding="0"
		bgcolor="${bodyback_c}" align="center">
		<tr>
			<td align="right" colspan="2" bgcolor="${value_c}"><a
				style="text-decoration: none" href="qnaBoard.jsp">
					글목록&nbsp;&nbsp;</a></td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">제 목</td>
			<td width="330"><input type="text" size="40" maxlength="50"
				name="subject" value="${article.subject}"></td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">Email</td>
			<td width="330"><input type="text" size="40" maxlength="30"
				name="email" value="${article.email}"></td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">내 용</td>
			<td width="330"><textarea name="content" rows="13" cols="40">${article.content}</textarea>
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">비밀번호</td>
			<td width="330"><input type="password" size="8" maxlength="12"
				name="passwd" value="${article.passwd}"></td>
		</tr>
		<tr>
			<td colspan=2 bgcolor="${value_c}" align="center"><input
				type="submit" value="글쓰기"> <input type="reset" value="다시작성">
				<input type="button" value="목록보기"
				OnClick="window.location='qnaboard.do'"></td>
		</tr>
	</table>
</form>