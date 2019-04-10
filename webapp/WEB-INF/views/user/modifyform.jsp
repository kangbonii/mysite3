<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<!-- /header -->


		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<!-- /navigation -->


		<div id="content">
			<div id="c_box">
				<div id="user">
					<h2>로그인</h2>

					<form class="form-box" method="post" action="${pageContext.request.contextPath}/user/modify">
						<input type="hidden" name="no" value="${uservo.no}">
						<div class="form-group">
							<label class="block-label" for="name">이름</label> <input id="name" type="text" name="name" value="${requestScope.uservo.name}">
						</div>

						<div class="form-group">
							<label class="block-label" for="email">이메일</label>
							<p>
								<strong>${requestScope.uservo.email}</strong>
							</p>
						</div>
						<div class="form-group">
							<label class="block-label" for="password">패스워드</label> <input id="password" type="password" name="password" value="${requestScope.uservo.password}">
						</div>
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female"> <label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						</fieldset>

						<input type="submit" value="수정완료">

					</form>

				</div>
				<!-- /user -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->




		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		<!-- /footer -->

	</div>
	<!-- /container -->
</body>
</html>

