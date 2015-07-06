<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c}">  
<center><b>글쓰기</b>
<br>
<form method="post" name="writeform" action="writePro.do" onsubmit="return writeSave()" enctype = "multipart/form-data">
<!-- hidden 타입으로 request 로 받은 값들을 넘겨준다 -->
<input type = "hidden" name = "id" value = "${id}">
<input type = "text" name = "num" value = "${num}">
<input type = "hidden" name = "ref" value = "${ref}">
<input type = "hidden" name = "re_step" value = "${re_step}">
<input type = "hidden" name = "re_level" value = "${re_level}">
<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="${value_c}" align="center">
   <tr>
    <td align="right" colspan="2" bgcolor="${value_c}">
     <a href="htmlboard.jsp"> 글목록</a> 
   </td>
   </tr>
   <tr>
    <td  width="70"  bgcolor="${value_c}" align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center" >제 목
    </td>
    <td width="330"><c:if test = "${num == 0}">
   		<input type = "text" size = "40" maxlength = "50" name = "subject"></c:if>
   		<c:if test = "${num != 0}">
   		<input type = "text" size = "40" maxlength = "50" name = "subject" value = "[답변]">
   </td>
   </c:if>	
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center">Email</td>
    <td  width="330">
       <input type="text" size="40" maxlength="30" name="email" ></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center" >내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40"></textarea> </td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center" >비밀번호</td>
    <td  width="330" >
     <input type="password" size="8" maxlength="12" name="passwd"> 
  </td>
  </tr>
  <!-- 파일 업로드 , cos.jar 필요!! -->
  <tr>
  <td width = "70" bgcolor = "${value_c}" align = "center">첨부 파일</td>
  <td width = "330">
  	<input type = "file" name = "uploadFile"><br>
  </tr>
  <!--------------------------------------------------->
<tr>      
 <td colspan=2 bgcolor="${value_c}" align="center"> 
  <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='htmlboard.jsp'">
</td></tr></table>    
     
</form>      
</body>
</html> 