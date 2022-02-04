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
${msg}
</tr>
<tr>
<td>
<img alt="" src="resources/MirrorBTPic/${player.tribe}/${player.tribe}N.png">
플레이어 레벨:${player.lv}플레이어 닉네임:${player.nick}
</td>
</tr>
</table>
<a href="action.do?lv=${player.lv}"></a>
</body>
</html>