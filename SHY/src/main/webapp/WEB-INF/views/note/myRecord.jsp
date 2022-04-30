<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/admin_board.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
        
    <title>나의 활동(쪽지)</title>
    
    <style>

        h2 {
            text-align: left;
            margin-left: 270px;
            margin-top: 50px;
        }

        /* 4개 종류 분류 */
        .record_list {
            margin-top: 20px;
            margin-left: 270px;
        }

        .record_list_1 {
            margin-right: 15px;
        }

        .record_list_2 {
            margin-right: 15px;
        }

        .record_list_3 {
            margin-right: 15px;
        }

        .record_list_1:hover{
            color: gray;
        }

        .record_list_2:hover{
            color: gray;
        }

        .record_list_3:hover{
            color: gray;
        }

        .record_list_4:hover{
            color: gray;
        }

        /* 테이블 */
        table {
            border-collapse: collapse;
        }

        .myRecord_wrap{
            margin: 0 auto;
            border-top: 1px solid #666;
            border-bottom: 1px solid #f2f2f2;
            width: 1000px;
            text-align: center;
        }
        
        .myRecord_head {
            margin-top: 40px;
            border-bottom: 1px solid #f2f2f2;
            
        }

        .myRecord_content {
            padding: 10px 0;
            border-bottom: 1px solid #f2f2f2;
        }

        .myRecord_head_nm {
            padding: 5px;
            font-size: 15px;
            padding-right: 85px;
        }

        .myRecord_content_nm {
            padding: 3px;
            font-size: 13px;
            padding-right: 85px;
        }
        
        .modal_wrap {
	display: none;
	width: 200px;
	height: 150px;
	background: #c8b6e4;
	position: fixed;
	right: 50px;
	top: 50px;
}

.mini-title {
	margin-top: 10px;
	font-size: 14px;
	text-align: center;
}

.modal-txtcontent {
	margin-left: 5px;
	resize: none;
}

.send-modalBtn {
	margin-left: 50px;
}    

    </style>

</head>

<body>
   
     <%@include file = "../include/header.jsp" %>

    <section>
        <h2>
            나의 활동(쪽지)
        </h2>

    </section>
    <c:if test="${login.userId eq 'bbb1234' }">
 		<div class="record_list">
	        <a href="<c:url value='/note/myRecord?note_to=${login.userId}' />" class="record_list_4">받은 쪽지</a>
    	</div>
    </c:if>
    <c:if test="${login.userId ne 'bbb1234' }">
	    <div class="record_list">
	        <a href="<c:url value='/board/myRecord?board_writer=${login.userId}' />" class="record_list_1">작성글</a>
	        <a href="<c:url value='/comment/myRecord?com_writer=${login.userId}' />" class="record_list_2">작성댓글</a>
	        <a href="<c:url value='/scrap/myRecord?uses_Id=${login.userId}' />" class="record_list_3">스크랩</a>
	        <a href="<c:url value='/note/myRecord?note_to=${login.userId}' />" class="record_list_4">받은 쪽지</a>
	    </div>
    </c:if>
        
    <section style="padding-top: 10px; padding-bottom: 100px;">
        <table class="myRecord_wrap">
            <thead class="myRecord_head">
                <tr> 
                    <th class="myRecord_head_nm">보낸이</th>
                    <th class="myRecord_head_nm">내용</th>
                    <th class="myRecord_head_nm">답장</th>
                    <!-- <th class="myRecord_head_nm">제목</th> -->
                    <!--  <th class="myRecord_head_nm">작성자</th>-->
                    <!-- <th class="myRecord_head_nm">조회</th> -->
                </tr>
            </thead>
			
            <tbody>
				<c:forEach var="list" items="${note}">
					<tr class="myRecord_content">
			      		<td class="myRecord_content_nm">${list.note_from}</td> 
						<td class="myRecord_content_nm">${list.note_content}</td>
						<td class="myRecord_content_nm">
							<a href="#" class="modal-writer1" data-board-id = "${list.note_from}">
								<input type="button" id="del_btn" value="답장">
							</a> 
						</td>
				  </tr>
				</c:forEach>
            </tbody>
        </table>
        
        <!-- 쪽지 모달 -->
        <div class="modal_wrap">


			<div class="modal-contnet">
				<%-- <form action="<c:url value='/note/insertNote' />"> --%>
				<div class="mini-title"><input name="note_to" id="note_to" ></div>
				<textarea class="modal-txtcontent" id="note_content" rows="4" cols="25" name="note_content"></textarea>
				<button type="button" class="send-modalBtn" id="note" value='보내기'>
					보내기
				</button>
				<button class="close-modalBtn">
					<a href="#">닫기</a>
				</button>
				
				<!-- </form> -->
			</div>


		</div>

    </section>

     <%@include file = "../include/footer.jsp" %>      
</body>

<script>
	
	//쪽지 답장
	$(function(){
		$(".modal-writer1").on('click', function() {
			var modal1 = $(this).data("boardId");
			console.log(modal1);
			$('#note_to').val(modal1);
			$(".modal_wrap").fadeIn();
		})// end 쪽지 open
		
		$("#note").on('click', function(){
			console.log('쪽지 보내기 클릭');
			//보낼 데이터
           const note_to = $('#note_to').val();
           const note_content = $("#note_content").val();
           
           console.log(note_to);
           console.log(note_content);
           
           //쪽지 내용이 없으면 반환
           if(note_content == ''){
              alert('내용을 입력하세요');
              return;
           }
           
           //쪽지 보내기 비동기
           $.ajax({
              type:'post',
              url:'<c:url value="/note/insertNote" />',
              data: JSON.stringify({
                 "note_to":note_to,
                 "note_content":note_content
              }),
              contentType:'application/json',
              success:function(data){
                 console.log('통신성공' + data);
                 alert('쪽지를 보냈습니다');
                 $("#note_content").val('');
                 $(".modal_wrap").fadeOut();
              },
              error:function(){
                 alert('통신실패');
              }
           })//쪽지 보내기 비동기 끝
		})//보내기 버튼 클릭시
		
		
		//쪽지 모달 끄기
		$(".close-modalBtn").on('click', function(){
			$(".modal_wrap").fadeOut();
		})
	})
	

</script>

</html>