<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
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
					<h2>회원가입</h2>

					<form class="form-box" method="post" action="${pageContext.request.contextPath}/user/join">
						<div class="form-group">
							<label class="block-label" for="name">이름</label> <input id="name" type="text" name="name" value="">
						</div>

						<div class="form-group">
							<label class="block-label" for="email">이메일</label> <input id="email" type="text" name="email" value=""> <input id="btnCheck" type="button" value="id 중복체크">
							<p id="eamilResult">	<p>
						</div>

						<div class="form-group">
							<label class="block-label" for="password">패스워드</label> <input name="password" type="password" value="">
						</div>

						<fieldset>
							<legend>성별</legend>
							<label for="rf">여</label> <input id="rf" type="radio" name="gender" value="female" checked="checked"> <label for="rm">남</label> <input id="rm" type="radio" name="gender" value="male">
						</fieldset>

						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y"> <label for="agree-prov">서비스 약관에 동의합니다.</label>
						</fieldset>

						<input type="submit" value="가입하기">

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

<script type="text/javascript">
	$("#btnCheck").on("click", function(){
		console.log("버튼클릭");
		var email = $("#email").val();
		console.log(email);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/emailcheck",  //컨트롤주소
			type : "post",  
			//contentType : "application/json", //보낼데이터의 형태
			data : {email: email},  //뒤에 데이터 붙어가는 뒤에가var email
						
			
			//여기부턴 받을때
			dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				console.log(result);
				if(result == true){
					$("#eamilResult").text("사용할 수 있는 메일입니다");
					$("#eamilResult").css("color","blue");
				}else{
					$("#eamilResult").text("사용중인 메일입니다");
					$("#eamilResult").css("color","red");
				}
			},
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
		})
	});
</script>


</html>