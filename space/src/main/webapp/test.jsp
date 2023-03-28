<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 
	<meta charset="utf-8">
	 <link href="css/style.css" rel="stylesheet" type="text/css" />
	 
	<title>예약 관리 리스트</title>
	
	
<style type="text/css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	

	.check {
		margin-right:20px;
		margin-top:30px;
		display: inline-block;
	}

	
	.check-group {
		display: flex;
		justify-content: flex-end;
		 margin-bottom: 30px;
	}
	
	
	.select-group {
	    display: flex;
	    justify-content: center;
	   
	}
	
	.input-group {
		margin-top: 30px;
		width:1200px;
		text-align: center;
		margin-left: 60px;
	}
	

	.select {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 20px;
		padding: 5px 10px;
		border-radius: 5px;
		height: 50px;
		width: 600px;
	}

	.select.text-white {
		background-color: #704de4;
		color: white;
	}
</style>
</head>
<body>
			<table>
	        	  <tr>
	       <th colspan='6'>작성된 게시글이 없습니다.</th>
	        	</tr>
	        	   </table>
	        	 
	        	<div class='row'>
	        	  <div class='col-lg-8 col-md-6 col-12 mx-auto'>
	        	<div class='card mb-5'>
	        <div class='card-body'>
	        <table class='table'>
	   		 <thead>
	        	<tr>
	        	<th scope='col'>공간 번호</th>
	        	<th scope='col'>Q&A 번호</th>
	        	  <th scope='col'>제목</th>
	        	  <th scope='col'>내용</th>
	        	<th scope='col'>답변 상태</th>
	        <th scope='col'>회원 ID</th>
	      <th scope='col'>링크</th>
	        	</tr>
	        	</thead>
	        	<tbody>
	        	<tr>
	        	 <td scope='row'>1</td>
	        	 <td scope='row'>2</td>
	        	 <td scope='row'>43</td>
	        	 <td scope='row'>4</td>
	        	 <td scope='row'>5</td>
	        	 <td scope='row'>6</td>

	        	 <td scope='row'><button id="addQnaBtn">답글작성</button></td>
	        	  </tr>
	        	  
	        </tbody>
	        	 </table>
	        	</div>
	        	</div>
	        	</div>
	        	</div>
	<form id="qnaForm" style="display: none;">
  <div class='qTitle' >
    <label for='title'>제목</label>
    <input type='text' class='form-control' id='title' placeholder='제목을 입력하세요'>
  </div>
  <div class='qContent'>
    <label for='content'>내용</label>
    <textarea class='form-control' id='content' rows='5' placeholder='내용을 입력하세요'></textarea>
  </div>
  <button type='submit' class='btn btn-primary'>답글 작성</button>
</form>
								
</body>

<script type="text/javascript">
const addQnaBtn = document.getElementById('addQnaBtn');
const qnaForm = document.getElementById('qnaForm');
addQnaBtn.addEventListener('click', () => {

	qnaForm.style.display = 'block';

});
</script>
</html>