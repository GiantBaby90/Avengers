<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<center><b>글목록(전체 글 : ${count})</b>
	<p style="font-size: 25px; font-family:'Comic Sans MS'">Q&A Board<p>
	<table width="700" align = "right">
		<tr>
			<td>
			<%-- <% if(session.getAttribute("id") != null) {%> --%>
			<a href="writeForm.do">글쓰기</a>
			<%-- <% } %> --%>
			</td>
			<td>
		</tr>
	</table>
	<c:if test = "${count == 0}">
	<table width="700" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" bgcolor="${value_c}">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table></c:if>
	<c:if test = "${count > 0}">
	<!--  게시판 리스트 VIEW  -->
	<table border="1" width="700" cellpadding="0" cellspacing="0"
		align="center">
		<tr height="30" bgcolor="${value_c}">
			<td align="center" width="50">번호</td>
			<td align="center" width="250">제 목</td>
			<td align="center" width="100">작성자</td>
			<td align="center" width="150">작성일</td>
			<td align="center" width="50">조 회</td>
			<td align="center" width="100">IP</td>
			<td align="center" width = "100">fileName</td>
			<td align="center" width = "50">fileSize</td>
		</tr>
		<c:forEach var = "article" items = "${articleList}">
		<!-- 게시판 테이블 내용 -->
		<tr>
            <td align = "center"><c:out value = "${number}"/><c:set var = "number" value = "${number - 1}"/></td>
            <td align = "center"><c:if test = "${article.re_level > 0}">
           	<img src = "picture/level.gif" width = "${5 * article.re_level}" height = "16">
            	<img src = "picture/re.gif">
            </c:if>
            <c:if test = "${article.re_level == 0}">
            	<img src = "picture/level.gif" width = "${5 * article.re_level}" height = "16">
            </c:if>
            <a href = "content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a>
            <c:if test = "${article.readcount >= 20}">
            	<img src = "picture/hot.gif" border = "0" height = "16">
            </c:if></td>
            <td align = "center">${article.writer}</a></td>
            <td align = "center">${article.reg_date}</td>
            <td align = "center">${article.readcount}</td>
            <td align = "center">${article.ip}</td>
            <!-- 업로드한 파일 정보 추가 -->
            <td align = "center"><a href = "/Aven/board/${article.filename}">${article.filename}</a></td>
            <td align = "center">${article.filesize}</td>
         </tr>
         </c:forEach>
	</table>
</c:if>
<c:if test="${count > 0}">
   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
   <c:set var="pageBlock" value="${10}"/>
   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
   <c:set var="startPage" value="${result * 10 + 1}" />
   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
   <c:if test="${endPage > pageCount}">
        <c:set var="endPage" value="${pageCount}"/>
   </c:if> 
  <c:if test="${startPage > 10}">
        <a href="qnaboard.do?pageNum=${startPage - 10 }">[이전]</a>
   </c:if>
  <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <a href="qnaboard.do?pageNum=${i}">[${i}]</a>
   </c:forEach>
  <c:if test="${endPage < pageCount}">
        <a href="qnaboard.do?pageNum=${startPage + 10}">[다음]</a>
   </c:if></c:if></center></body></html>