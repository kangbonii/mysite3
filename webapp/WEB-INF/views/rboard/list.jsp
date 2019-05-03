<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include><!-- /navigation -->
		
		<div id="content">
			<div id="c_box">
				<div id="rboard">
					<h2>계층형게시판</h2>
					
					<form action="${pageContext.request.contextPath}/rboard/list" method="get">
						<input type="text" id="kwd" name="kwd" value="">
						<input type="submit" value="찾기">
					</form>
					
				
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>
						<c:forEach items="${pMap.list}" var="vo" >	
						<tr>
							<td>${vo.no}</td>
							<td><a href="${pageContext.request.contextPath}/rboard/read?no=${vo.no}&crtPage=${param.crtPage}&kwd=${param.kwd}">${vo.title} </a></td>
							<td>${vo.name}</td>
							<td>${vo.hit}</td>
							<td>${vo.reg_date}</td>
							
							<c:if test="${vo.user_no == authUser.no}">
							<td>
								<a href="${pageContext.request.contextPath}/rboard/delete?no=${vo.no}" class="del" >삭제</a>
							</td>
							</c:if>
						</tr>
						</c:forEach>
					
					</table>
				
					<div class="pager">
					
						<ul>
							<c:if test="${pMap.prev eq true}"> 
							<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${pMap.startPageBtnNo-1}">◀</a></li>
							</c:if>
							
							<c:forEach begin = "${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page" >
								<c:choose>
									<c:when test="${param.crtPage eq page}">
										<li class="selected"><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${page}">${page}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${page}">${page }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							
							<c:if test="${pMap.next eq true}"> 
							<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${pMap.endPageBtnNo+1}">▶</a></li>
							</c:if>
						</ul>
					</div>			
					
					<c:if test="${not empty sessionScope.authUser}">	
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/rboard/writeform" id="new-book">글쓰기</a>
					</div>
					</c:if>
					
				
					
				</div><!-- /rboard -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
			
			
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include><!-- /footer -->
	</div><!-- /container -->
</body>
</html>

