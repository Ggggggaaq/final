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
	<div class="container">
		<h1>Q&amp;A 관리</h1>
		
		<!-- 예약 정보 검색 UI -->
		<div class="row mb-4">
			
				<div class="select-group">
					<div class="select" style="background-color: #ebebeb;">
						<a href="${pageContext.request.contextPath}/member_review">이용 후기</a>
					</div>	
					<div class="select text-white">
						<a href="${pageContext.request.contextPath}/member_qna">Q&amp;A</a>
					</div>
				</div>
		
				
			
		<div class="check-group">
			<div class="check">
				<select class="form-control"  style="margin-top: 30px;">
					<option value="1">전체</option>
					<option value="2">미답변</option>
					<option value="3">답변</option>
				</select>
			</div>
		
		</div>
	</div>
	
		<div id="noticetable" class="w-100" style="margin: 0 auto;"></div>
		<div id="pageNumDiv"></div>
		
		
			
	<button onclick="location.href='${pageContext.request.contextPath}/member_question_write'" type="button" id="writeBtn" class="btn btn-primary btn-sm">게시글등록</button>
	
	</body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
	var page=1;
	noticeListDisplay(page);
	
	function noticeListDisplay(pageNum) {
	    page = pageNum;
	    $.ajax({
	        type: "get",
	        url: "${pageContext.request.contextPath}/member_questionList?pageNum=" + pageNum,
	        dataType: "json",
	        success: function(result) {
	            if (result.questionList.length == 0) {
	                var html = "<table>";
	                html += "<tr>";
	                html += "<th colspan='6'>작성된 게시글이 없습니다.</th>";
	                html += "</tr>";
	                html += "</table>";
	                $("#noticetable").html(html);
	                return;
	            }
	            var html = "<div class='row'>";
	            var count = 0;
	            $(result.questionList).each(function() {
	                if (count == 3) {
	                    html += "</div><div class='row'>";
	                    count = 0;
	                }
	                html += "<div class='col-md-4'>";
	                html += "  <div class='card mb-4'>";
	                html += "    <div class='card-header'>";
	                if (this.questionList[0].qstatus === 1) {
	                	  html += "      <p id='pRepeople' style='color:blue; font-weight: bold;'> 미답변</p>";
	                	} else if (this.questionList[0].qstatus) {
	                	  html += "      <p id='pRepeople' style='color:blue; font-weight: bold;'> 답변</p>";
	                	  
	                	} 

		            html += "      <h5 id='pSno'>공간번호: " + this.questionList[0].qsno + "</h5>";
	                html += "    </div>";

	                html += "    <div class='card-body'>";
	                

	                html += "      <h5 id='pRename'>Q&A 제목 : " + this.questionList[0].qtitle + "</h5>";
	                html += "      <h5 id='pRedate'>Q&A 내용: " + this.questionList[0].qcontent  + "</h5>";
	                html += "<button type='button' onclick='location.href=\"${pageContext.request.contextPath}/member_question_modify/" + this.questionList[0].qno + "\";' id='modifyBtn' class='btn btn-primary btn-sm' style='margin-right : 20px;'>글 변경</button>";
	                html +="<button type='button' onclick='remove("+this.questionList[0].qno+");' id='removeBtn' class='btn btn-primary btn-sm'>글 삭제</button>";
	                html += "    </div>";
	                html += "  </div>";

	                html += "</div>";
	               
	                count++;
	            });
	            html += "</div>";	
	            $("#noticetable").html(html);
	            pageNumDisplay(result.pager);
	        },
	        error: function(xhr, status, error) {
	            console.log(error);
	        }
	    });
	}

	
	
	function pageNumDisplay(pager) {
		var html="";
		
		if(pager.startPage > pager.blockSize) {
			html+="<a href='javascript:noticeListDisplay("+pager.prevPage+")'>[이전]</a>";
		}
		for(i=pager.startPage;i<=pager.endPage;i++) {
			if(pager.pageNum!=i) {
				html+="<a href='javascript:noticeListDisplay("+i+")'>[ "+i+" ]</a>";
			} else {
				html+="[ "+i+" ] ";
			}
		}
		if(pager.endPage != pager.totalPage) {
			html+="<a href='javascript:noticeListDisplay("+pager.nextPage+")'>[다음]</a>";
		}
		$("#pageNumDiv").html(html);
	}
		
	
	
	function remove(qNo) {
		if(confirm("게시글을 삭제 하시겠습니까?")) {
			$.ajax({
				type: "delete",
				url: "${pageContext.request.contextPath}/member_question_delete/"+qNo,
				dataType: "text",
				success: function(result) {
					if(result=="success") {
						NoticeListDisplay(page);
					}
				}, 
				error: function(xhr) {
					alert("에러코드(게시글 삭제) = "+xhr.status)
					alert("qno번호= "+this.questionList[0].qno)
				}
			});
		}
	}	
	
	</script>	
	
</html>
			