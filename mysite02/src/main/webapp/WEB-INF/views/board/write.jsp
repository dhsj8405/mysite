<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board">
					<input type = "hidden" name = "a" value="write">
					<input type = "hidden" name = "type" value="${param.type }">
					<c:if test = "${param.no != null}">
						<input type = "hidden" name = "no" value="${param.no }">
					</c:if>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<c:choose>
								<c:when test = '${boardvo.title == null }'>
									<td><input type="text" name="title" value=""></td>
								</c:when>
								<c:otherwise>
									<td><input type="text" name="title" value="${boardvo.title }"></td>
								</c:otherwise>
							</c:choose>
							
						</tr>
						<tr>
							<td class="label">내용</td>
						
							<c:choose>
								<c:when test = '${boardvo.content == null }'>
									<td><textarea id="content" name="content"></textarea></td>
								</c:when>
								<c:otherwise>
									<td><textarea id="content" name="content">${boardvo.content }</textarea></td>								
								</c:otherwise>
							</c:choose>				
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>