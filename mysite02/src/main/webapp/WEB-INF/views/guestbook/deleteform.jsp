<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="user">
				<form method="post" action="${pageContext.request.contextPath }/guest?a=delete">
					<input type="hidden" name="no" value="${param.no }">
					<input type="password" name="password" >
					<input type="submit" value="확인">
				</form>
				
				<br>
				<td><a href="${pageContext.request.contextPath }/guest?a=guestbook">메인으로 돌아가기</a></td>
			</div>
		</div>
		<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url ="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>