<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="user">
				<form method="post" action="<%=request.getContextPath()%>/user?a=delete">
					<input type="hidden" name="no" value="<%=request.getParameter("no") %>">
					<input type="password" name="password" >
					<input type="submit" value="확인">
				</form>
				
				<br>
				<td><a href="/guestbook02/gb">메인으로 돌아가기</a></td>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>