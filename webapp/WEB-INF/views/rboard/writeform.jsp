<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet"
	type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<!-- /navigation -->

		<div id="content">
			<div id="c_box">
				<div id="rboard">
					<h2>게시판-등록</h2>

					<form id="addform" class="board-form" method="post" action="${pageContext.request.contextPath}/rboard/write">
						<table class="tbl-ex">
							<tr>
								<th colspan="2">글쓰기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td><input type="text" name="title" value=""></td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td><textarea id="content" name="content" form="addform"></textarea>
								</td>
							</tr>
						</table>
						<div class="bottom">
							<a href="${pageContext.request.contextPath}/rboard/list">취소</a>
							<input type="submit" value="등록">
						</div>
					</form>

				</div>
				<!-- /rboard -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->



		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- /footer -->
	</div>
	<!-- /container -->
</body>
</html>

