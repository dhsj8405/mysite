
<%@page import="com.douzone.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("list");
%>

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
			
				<form action="<%=request.getContextPath() %>/user" method="post">
				<input type = "hidden" name = "a" value = "guestbookadd">
				<table border=1 width=500>
					<tr>
						<td>이름</td><td><input type="text" name="name"></td>
						<td>비밀번호</td><td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
					</tr>
					<tr>
						<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
					</tr>
				</table>
				</form>
				<br>
			   	<%
					int index = list.size();
					for(GuestbookVo vo : list){
				%>
				<table width=510 border=1>
					<tr>
						<td><%=index--%></td>
						<td><%=vo.getName() %></td>
						<td><%=vo.getRegDate() %></td>
						<td><a href="<%=request.getContextPath()%>/user?a=guestbookdeleteform&no=<%=vo.getNo()%>">삭제</a></td>
					</tr>
					<tr>
						<td colspan=4><%=vo.getMessage()%></td>
					</tr> 
				</table>
				<br>
				<%
					}
				%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>