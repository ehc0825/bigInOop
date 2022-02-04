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
<c:if test="${result eq 1 }">
<script type="text/javascript">
	alert("아이템이 업데이트 되었습니다");
	location.href="peace.do";
</script>
</c:if>
<c:if test="${result ne 1 }">
<script type="text/javascript">
	alert("아이템 업데이트 실패");
	location.href="peace.do";
</script>
</c:if>
</body>
</html>