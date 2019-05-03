<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
<title>Mysite</title>
</head>
<body>
	<div id="container">
		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<!-- navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="content">
			<div id="c_box">
				<div id="gallery">
					<h2>갤러리</h2>

					<c:if test="${not empty sessionScope.authUser}">
						<input id="btnImgPop" class="btn btn-primary" type="button" value="이미지등록">
					</c:if>
					<ul class="view">
						<c:forEach items="${galleryvo}" var="vo">
							<li id="imgdiv" data-no="${vo.no}">
								<div>
									<img src="${pageContext.request.contextPath }/upload/${vo.saveName}" style="width: 150px">

								</div>
							</li>
						</c:forEach>
					</ul>

				</div>
				<!-- /gallery -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->




	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="delPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				<form method="post" action="${pageContext.request.contextPath }/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="formgroup">
							<label>코멘트작성</label><br> <input type="text" name="comments" id="comments"><br>
						</div>
						<div class="formgroup">
							<label>이미지선택</label><br> <input type="file" name="file" value="" id="file"> <br>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary" id="btnImgAdd">등록</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="img-modal" src="${pageContext.request.contextPath }/upload/${galleryVo.saveName}">
					</div>

					<div class="formgroup">
						<label>코멘트</label><br>
						<p id="comm-modal">${galleryVo.comments}</p>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel" data-no="${galleryVo.no}">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
	/* 이미지등록 팝업(모달)창 띄우기*/
	$("#btnImgPop").on("click", function() {
		$("#delPop").modal();

	});

	/* 이미지보기 팝업(모달)창 띄우기*/
	$(".view").on(
			"click",
			"#imgdiv",
			function() {
				console.log("aaa");
				$("#viewPop").modal();
				var $this = $(this);
				var no = $this.data("no");
				console.log(no);

				$.ajax({
					url : "${pageContext.request.contextPath}/gallery/viewimg",
					type : "post",
					//contentType : "application/json", 
					data : {
						no : no
					},

					//여기부턴 받을때
					dataType : "json",
					success : function(galleryVo) {
						/*성공시 처리해야될 코드 작성*/
						console.log(galleryVo.saveName);
						$("#img-modal").attr(
								"src",
								"${pageContext.request.contextPath }/upload/"
										+ galleryVo.saveName);
						console.log(galleryVo.comments);
						$("#comm-modal").text(galleryVo.comments);
						$("#btnDel").data("no", galleryVo.no);

					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});

			});

	$("#btnDel")
			.on(
					"click",
					function() {
						console.log("삭제버튼");
						var $this = $(this);
						var no = $this.data("no");
						console.log(no);

						$
								.ajax({
									url : "${pageContext.request.contextPath}/gallery/delete",
									type : "post",
									//contentType : "application/json", 
									data : {
										no : no
									},

									//여기부턴 받을때
									dataType : "json",
									success : function(count) {
										/*성공시 처리해야될 코드 작성*/
										if (count > 0) {
											$("#div" + no).remove();
										}
										$('#viewPop').modal('hide');
										window.location.href = "${pageContext.request.contextPath}/gallery/form";
									},
									error : function(XHR, status, error) {
										console.error(status + " : " + error);
									}
								});
					});
</script>







</html>