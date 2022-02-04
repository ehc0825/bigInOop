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
<table>
<tr>
<td>${msg}</td>
</tr>
<tr>
<td>
<img alt="" src="resources/MirrorBTPic/${player.tribe}/${player.tribe}N.png">
</td>
</tr>
<tr>
<td>
플레이어 레벨:${player.lv}플레이어 닉네임:${player.nick}플레이어 종족:${player.tribe}
</td>
<td>
착용무기:
<c:if test="${player.itemNo eq 1 }">
숏소드
</c:if>
<c:if test="${player.itemNo eq 2 }">
숏액스
</c:if>
<c:if test="${player.itemNo eq 3 }">
숏보우
</c:if>
<c:if test="${player.itemNo eq 4 }">
롱소드
</c:if>
<c:if test="${player.itemNo eq 5 }">
아이언해머
</c:if>
<c:if test="${player.itemNo eq 6 }">
아이언보우
</c:if>
</td>
</tr>
</table>
<a href="action.do?lv=${player.lv}&state=peace">다음으로</a>
<form action="itemupdate.do">
<table>
<tr>
<td>
<!--인간일 경우 무기 -->
<c:if test="${player.tribe eq 'human'}">
<select name="weapon">
<option value="ShortSword">ShortSword</option>
<option value="LongSword">LongSword</option>
</select>
</c:if>
<!--오크일 경우 무기 -->
<c:if test="${player.tribe eq 'orc'}">
<select name="weapon">
<option value="ShortAxe">ShortAxe</option>
<option value="IronHammer">IronHammer</option>
</select>
</c:if>
<!--엘프일 경우 무기 -->
<c:if test="${player.tribe eq 'elf'}">
<select name="weapon">
<option value="Shortbow">ShortBow</option>
<option value="IronBow">IronBow</option>
</select>
</c:if>
</td>
<td><input type="submit" value="착용" class="btn btn-primary btn-sm mt-1"></td>
</tr>
</table>
</form>
<form action="lose.do">
<input type="submit" value="모험종료" class="btn btn-primary btn-sm mt-1">
</form>
</body>
</html>