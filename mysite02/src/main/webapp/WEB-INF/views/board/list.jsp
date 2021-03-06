<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?a=list" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align:left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:set var='count' value='${fn:length(list) }' />
					
					<c:forEach items='${list }' var='vo' varStatus='status'>

						<tr>
							<td>${vo.no}</td>
							<td style="text-align:left; padding-left:${20*vo.dept}px">
							<c:if test = '${vo.dept > 0 }'>
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' height="10" width="10"/>
							</c:if>
							<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a></td>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>

							<c:if test = '${vo.userNo == userNo}'>
								<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a></td>						
							</c:if>
						</tr>
					</c:forEach>
				</table>
				<!-- pager 추가 -->
				<c:set var = 'leftEdgeNo' value ='${(((curPageNo-1)/5)-(((curPageNo-1)/5)%1) )*5 +1 }'/>
				<div class="pager">
					<ul>
						<c:set var='count' value='${fn:length(list) }' />
						<c:choose>
							<c:when test = "${empty keyword }" >
								<c:set var='pageindexUrl' value = '/board?a=list&pageindex=' />
							</c:when>
							<c:otherwise>
								<c:set var='pageindexUrl' value = '/board?a=list&kwd=${keyword }&pageindex=' />
							</c:otherwise>
						</c:choose>
						<c:choose>				
							<c:when test='${curPageNo == 1}'>
								<li>◀</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }${pageindexUrl}${curPageNo-1 }">◀</a></li>
							</c:otherwise>
						</c:choose>	
						<c:forEach begin='${leftEdgeNo}' end='${leftEdgeNo+4 }' var='pageno'>
							<c:choose>
								<c:when test= '${pageno == curPageNo }'>
										<li class = "selected">${pageno }</li>
								</c:when> 
								<c:when test = '${pageno <= totalPageNo }'>
										<li><a href="${pageContext.request.contextPath }${pageindexUrl}${pageno }">${pageno }</a></li>
								</c:when>
								<c:otherwise>
									<li>${pageno }</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>  
						
						<c:choose>				
							<c:when test='${totalPageNo == curPageNo}'>
								<li>▶</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }${pageindexUrl}${curPageNo+1 }">▶</a></li>		
							</c:otherwise>
						</c:choose>
						
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=writeform&type=write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>