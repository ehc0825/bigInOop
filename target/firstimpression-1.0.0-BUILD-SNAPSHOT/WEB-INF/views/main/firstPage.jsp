<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</table>
</form>
<table>
<tr>
<td>랭킹테이블</td>
</tr>
</table>
</body>
</html>