<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<section class="content">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-9 write-wrap">
				<div class="titlebox">
					<p>상세보기</p>
				</div>

				<form action="#">
					<div>
						<label>DATE</label>
						<p>${article.regdate}</p>
					</div>
					<div class="form-group">
						<label for="bId">번호</label> <input type="text" id="bId"
							value="${article.bno}" class="form-control" readonly>
					</div>
					<div class="form-group">
						<label for="writer">작성자</label> <input type="text" id="writer"
							value="${article.writer}" class="form-control" readonly>
					</div>
					<div class="form-group">
						<label for="title">제목</label> <input type="text" id="title"
							value="${article.title}" class="form-control" readonly>
					</div>
					<div class="form-group">
						<label for="content">내용</label>
						<textarea class="form-control" rows="10" readonly></textarea>
					</div>

					<button class="btn btn-primary">수정</button>
					<button class="btn btn-dark">목록</button>
				</form>

			</div>
		</div>
	</div>
</section>


<!-- 댓글구현화면 -->
<section class="reply">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-9 write-wrap">
				<div class="reply-wrap">
					<div class="reply-image">
						<img src="../resources/img/profile.png" alt="prof">
					</div>
					<div class="reply-content">
						<textarea class="form-control" rows="3" id="reply"></textarea>
						<div class="reply-group clearfix">
							<div class="reply-input">
								<input type="text" class="form-control" id="replyId"
									placeholder="이름"> <input type="password"
									class="form-control" id="replyPw" placeholder="비밀번호">
							</div>
							<button class="btn btn-info" id="replyRegist">등록하기</button>
						</div>
					</div>
				</div>
				<!-- 댓글 달릴 공간 -->
				<div class="reply-wrap">
					<div class="reply-image">
						<img src="../resources/img/profile.png" alt="prof">
					</div>
					<div class="reply-content">
						<div class="reply-group">
							<strong class="left">honggildong</strong> <small class="left">2021/12/12</small>

							<a href="#" class="right"> <span
								class="glyphicon glyphicon-pencil"></span>수정
							</a> <a href="#" class="right"><span
								class="glyphicon glyphicon-remove"></span>삭제</a>
						</div>
						<p>여기는 댓글 영역</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>

<script>
	$(document).ready(function() {

		$('#replyRegist').click(function() {

			/*
			댓글을 등록하려면 게시글 번호도 보내 주셔야 합니다.
			댓글 내용, 작성자, 댓글 비밀번호, 게시글 번호를 
			json 표기 방법으로 하나로 모아서 전달해 주시면 됩니다.
			비동기 통신으로 댓글 삽입을 처리해 주시고,
			console.log를 통해 '댓글 등록 완료!'를 확인하시고
			실제 DB에 댓글이 추가되는지도 확인해 주세요.
			전송방식 : POST, url : /reply/replyRegist
			 */
			const bno = '${article.bno}';
			const reply = $('#reply').val();
			const replyId = $('#replyId').val();
			const replyPw = $('#replyPw').val();

			if (reply === '' || replyId === '' || replyPw === '') {
				alert('이름, 비밀번호, 내용을 입력하세요');
				return;
			}

			const info = {
				"bno" : bno,
				"reply" : reply,
				"replyId" : replyId,
				"replyPw" : replyPw
			}
			console.log(info);
			$.ajax({
				type : 'POST',
				url : '<c:url value="/reply/replyRegist" />',
				contentType : 'application/json',
				dataType : "text",//서버로부터 어떤 형식으로 받을지 (생략가능함)
				data : JSON.stringify(info),
				success : function(data) { //서버가 전송해준 데이터가 여기로 들어올것
					console.log('댓글 등록 완료! : ' + data);
					alert('성공!');

					$('#reply').val('');
					$('#replyId').val('');
					$('#replyPw').val('');
					getList();// 등록 성공 후 댓글 목록 함수를 호출해서 비동기식으로 목록 표현
				},
				error : function(status, error) {
					alert('실패!');
					console.log('댓글 등록 실패!');
					console.log(status, error);
				}
			});

		});

	}); //end jQuery
</script>