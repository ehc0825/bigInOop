<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="insertMember.do">
<table>
<tr>
<td>닉네임<input type="text" required="required" name="nick" maxlength="5"></td>
</tr>
<tr>
<td>종족<select name="tribe" required="required">
<option value="human">인간</option>
<option value="orc">오크</option>
<option value="elf">엘프</option>
</select></td>
</tr>
<tr>
<td>모험시작<input type="submit"value="시작하기"></td>
</tr>
</table>
</form>
<table>
<tr>
<td>랭킹테이블</td>
</tr>
<c:set var="i" value="1"></c:set>
<c:forEach var="record" items="${list }">
<tr>
<td>${i }</td><td>플레이어 닉네임:${record.nick}</td><td>플레이어 레벨:${record.stage}</td>
<c:set var="i" value="${i+1 }"></c:set>
</tr>
</c:forEach>
</table>
</body>
</html>