<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">    
<html>
<head>
<style type="text/css">

.rating .star {
  color: #ccc;
  cursor: pointer;
  font-size: 30px;
}
.rating .star.active {
  color: gold;
}




</style>
<title>Space Moon</title>
</head>
<body>

<div class="content" style="width: 930px; margin: 0 auto; position: relative; text-align: center;" >
<br>
<h3>Review 수정</h3>
<br>
<br>
<div>
	<form class="row gx-3 gy-2 align-items-center" name="f" method="post">
		 
				
		<table class="table table-bordered">
			
			<tr>
				<th>별점 ${rSno}</th>	
				<td><div class="rating" >
					  <span class="star" title="1">&#9733;</span>
					  <span class="star" title="2">&#9733;</span>
					  <span class="star" title="3">&#9733;</span>
					  <span class="star" title="4">&#9733;</span>
					  <span class="star" title="5">&#9733;</span>
				</div>
				 <input type="hidden" id="rStar" name="rStar" value="${review.RStar}">
				 <input type="hidden" name="rNo" value="${review.RNo }">
				</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="rTitle" id="rTitle" style="width: 700px;" value="${review.RTitle}">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="5" cols="91" name="rContent" id="rContent" >${review.RContent}</textarea>
				</td>
			</tr>
			
						
			
			
		</table>	
	</form>
	<div style="text-align: center;">
	<br>
		<p><button onclick="reviewAdd();" type="submit" id="writeBtn" class="btn btn-primary btn-sm">게시글등록</button>
		<button type="button" id="resetBtn" class="btn btn-primary btn-sm"  onclick="reset();">다시작성</button></p>
	</div>
	</div>
	</div>
</body>
<script language="JavaScript">
function reviewAdd() {
	if ( rTitle.value == "" ) {
		alert("제목을 입력해주세요.");
		rTitle.focus();
		return;
	} 
	if ( rContent.value == "" ) {
		alert("내용을 입력해주세요.");
		rContent.focus();
		return;
	}
	
	f.action = "<c:url value="/member_review_modify"/>";
	f.submit();
}
	function reset() {
		  document.getElementById("rContent").value = "";
		  document.getElementById("rTitle").value = "";
		}
		
			const stars = document.querySelectorAll('.star');
			const ratingInput = document.querySelector('#rating-input');
			const rStar = document.querySelector('#rStar');
			stars.forEach(function(star, index) {
			  star.addEventListener('click', function() {
			    // 모든 별 모양(span 태그)에서 active 클래스 제거
			    stars.forEach(function(s) {
			      s.classList.remove('active');
			    });
		
			    // 클릭한 별과 그 앞의 별 모양에 active 클래스 추가
			    for (let i = 0; i <= index; i++) {
			      stars[i].classList.add('active');
			    }
			    
			    // hidden input 태그의 값 설정
			    rStar.value = star.getAttribute('title');
			  });	
			});
		
			  // 전체 체크박스 가져오기
		    var checkboxes = document.getElementsByName('rPno');

		    // 체크박스 체크 상태 변경 시 호출되는 함수
		    function toggleCheckbox(checkbox) {
		        // 다른 체크박스들 체크 해제
		        checkboxes.forEach(function(item) {
		            if (item !== checkbox) item.checked = false;
		        });
		    }

		    // 체크박스 클릭 이벤트 리스너 등록
		    checkboxes.forEach(function(item) {
		        item.addEventListener('click', function() {
		            toggleCheckbox(this);
		        });
		    });
	
</script>
</html>