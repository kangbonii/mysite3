<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
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
				<div id="guestbook">
					<h2>방명록</h2>

					<%-- <form id="addform" action="${pageContext.request.contextPath}/guestbook/add" method="post"> --%>
						<table>
							<tr>
								<td>이름</td>
								<td><input id = "name" type="text" name="name"></td>
								<td>비밀번호</td>
								<td><input id = "password" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea cols="75" rows="8" form="addform" name="content"  id = "addcontent"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input id="submit" type="button" VALUE="확인 "></td>
							</tr>
						</table>
					<!-- </form> -->

					<div id="gblist"></div>
				</div>
				<!-- /guestbook -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		<!-- /footer -->
	</div>
	<!-- /container -->
	
	

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label> <input type="password" name="modalPassword" id="modalPassword"><br> <input type="text" name="modalPassword" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal" id="btn_del">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
</body>

	<script type="text/javascript">
		$(document).ready(function() { //레디될때 기능을 넣어줌.그리기직전
		
			$.ajax({
				url : "${pageContext.request.contextPath }/api/gb/list", //컨트롤주소
				type : "post",
				//contentType : "application/json", //보낼데이터의 형태
				//data : {name: name, password: password, content: content},  //뒤에 데이터 붙어가는 뒤에가var email
				//데이터안가져오고 시키기만하면되니			
	
				//여기부턴 받을때
				dataType : "json",
				success : function(guestbookList) {
					/*성공시 처리해야될 코드 작성*/
					console.log(guestbookList);
	
					for (var i = 0; i < guestbookList.length; i++) {
						render(guestbookList[i], "down");
					}
	
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			})
			
		});
	
		$("#submit").on("click", function(){
			var name = $("#name").val();
			console.log(name);
			var password = $("#password").val();
			console.log(password);
			var content = $("#addcontent").val();
			console.log(content);
			
			guestbookVo = {
					
			}
			
			$.ajax({
				url : "${pageContext.request.contextPath }/api/gb/add", //컨트롤주소
				type : "post",
				contentType : "application/json", //보낼데이터의 형태
				data : JSON.stringify({name: name, password: password, content: content}),  //뒤에 데이터 붙어가는 뒤에가var email
				//데이터안가져오고 시키기만하면되니			
	
				//여기부턴 받을때
				dataType : "json",
				success : function(guestbookVo) {
					/*성공시 처리해야될 코드 작성*/
					console.log(guestbookVo);
					render(guestbookVo, "up");
					
					$("#name").val("");
					$("#password").val("");
					$("#addcontent").val("");
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
	
		});
		
		
	
	//삭제버튼클릭시
	$("#gblist").on("click", ".btndel", function(){  //.btndel하면 새로생긴건 안먹어서 부모인gblist
		console.log("삭제클릭");
		var $this= $(this); //삭제버튼
		var delno = $this.data("delno");
		console.log(delno);
		//var modalPassword = $("#modalPassword").val();
		//console.log(modalPassword);
		$("#modalNo").val(delno);
		$("#del-pop").modal();
		//$("#modalNo").val(delno);
		
	});
	
	
	

		$("#btn_del").on("click", function(){
			
			var no = $("#modalNo").val();
		   	var password = $("#modalPassword").val();
		   	
			$.ajax({
				url : "${pageContext.request.contextPath }/api/gb/delete", //컨트롤주소
				type : "post",
				//contentType : "application/json", //보낼데이터의 형태
				data : {no: no, password: password},  
				//데이터안가져오고 시키기만하면되니			

				//여기부턴 받을때
				dataType : "json",
				success : function(count) {
					/*성공시 처리해야될 코드 작성*/
					if(count>0){
						$("#div"+no).remove();
					}else{
						alert("비밀번호 틀림");
					}
					$("#modalPassword").val("");
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		});

	
	//스크롤이 화면 제일 아래에 왔을때
	$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	console.log("끝")
	    	
	    }
	});

	//데이터 그리기 함수 정의부분
	function render(guestbookVo, updown) {
		var str = "";
		str += "<div id='div"+guestbookVo.no+"'>";
		str += "	<table>";
		str += "	<tr>";
		str += "		<td>" + guestbookVo.no + "</td>";
		str += "		<td>" + guestbookVo.name + "</td>";
		str += "		<td>" + guestbookVo.reg_date + "</td>";
		str += "		<td><input class='btndel' type='button' value='삭제' data-delno="+guestbookVo.no+"> </td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<td colspan=4>" + guestbookVo.content + "</td>";
		str += "	</tr>";
		str += "</table>";
		str += "	<br>";
		str += "</div>";

		if (updown == "up") {
			$("#gblist").prepend(str);
		} else if ((updown == "down")) {
			$("#gblist").append(str);
		} else {
			console.log("오류");
		}

	}
</script>



</html>

