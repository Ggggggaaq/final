<%@page import="xyz.itwill.dto.Space"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
<style type="text/css">
body {
			margin: 0;
			padding: 0;
			height: 2000px; /* 콘텐츠 높이가 2000px 이라 가정 */
			font-family: 'Nanum Gothic', sans-serif;
			font-size: 1.5rem;
		}
		#btn-wrapper {
			position: fixed; /* 화면에서 고정 */
			top: 50%; /* 화면의 중앙에 위치 */
			transform: translateY(-50%); /* 버튼 높이의 절반 만큼 올라감 */
			left: 10px; /* 왼쪽에서 10px 위치 */
			background-color: #704DE4; /* 배경색 설정 */
			padding: 10px; /* 버튼 간격을 주기 위한 padding 설정 */
			border-radius: 10px; /* 모서리를 둥글게 설정 */
			box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3); /* 그림자 효과 */
			z-index: 9999; /* 다른 요소들보다 위에 위치 */
		}
		.btn {
			display: block;
			padding: 10px;
			margin-bottom: 10px;
			text-align: center;
			background-color: #fff;
			border: none;
			border-radius: 5px;
			cursor: pointer;
		}
  		
  		button {
  		background-color: #704DE4;
  		color: #F8C91E;
  		font-size: 20px;
  		font-family: 'Nanum Gothic', sans-serif;
		}
		
		.box1 {
  		border: 2px solid #704DE4;
 		margin: 10px;
 		border-radius: 5%;
 		padding: 20px;
		}
		.gg {
  border: 0.2em solid #F8C91E; 
  background-color: #F8C91E; 
  color: white; 
  width: 15%; /* 길이 조절 */
  text-align: left; /* 왼쪽 정렬 */
}

		.gg + div {
 		 margin-top: 40px; /* 공백 크기 조정 */
		}
	hr.no-border {
  border: none;
  
  
}
	

</style>
</head>

<body>
	<div id="btn-wrapper">
		<button class="btn" onclick="scrollToSection('section1')">공간소개</button>
		<button class="btn" onclick="scrollToSection('section2')">시설안내</button>
		<button class="btn" onclick="scrollToSection('section3')">유의사항</button>
		<button class="btn" onclick="scrollToSection('section4')">환불정책</button>
		<button class="btn" onclick="scrollToSection('section5')">Q&A</button>
		<button class="btn" onclick="scrollToSection('section6')">이용후기</button>
	</div>
	
		
		
<form action="/" method="post" class="flex-container" id="form1" name="f" >
	<div style="background-color: #F6F6F6;">
		<!-- Hero End -->
		<div>
			<section class="section">
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-md-7 col-12">
							<div class="section-title">
							
								<h1 class="mt-4 text-center" style="text-align: left;">${spaces.SName }</h1>
								
								<!-- 예약공간  -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">${spaces.STag }</h2>
										<div class="d-flex justify-content-between align-items-center">
											<!-- Space 상세 페이지에서 호스트가 등록한 이미지 출력 -->
											<img class="img-fluid w-100" src="${pageContext.request.contextPath}/images/img1/${spaces.SImg}" alt="s_name = ${spaces.SName }의 Space Image">
											<!-- 
											<div id="slider">
	 							 				<ul>
	  							  	 				<li><img src="${sliderImage1}" alt="Slider Image 1"></li>
	   								 			    <li><img src="${sliderImage2}" alt="Slider Image 2"></li>
	  								 			    <li><img src="${sliderImage3}" alt="Slider Image 3"></li>
	  							    			</ul>
											</div>
											-->
											<ul id="slideshow">
	  											<c:forEach var="img" items="${ImgList}">
	   											<li><img src="${img}" /></li>
	  											</c:forEach>
											</ul>
										</div>
									</div>
								</div>
								
		
										
								
								<section id="section1"></section>
								<!-- 호스트정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">공간소개</h2>
										<p class="gg"></p>
										<div style="text-align: left;">${spaces.SInfo.replaceAll("\\.", "<br/>") }</div>
									</div>
								</div>
								
								<section id="section2"></section>
								<!-- 호스트 Space 정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">시설안내</h2>
										<p class="gg"></p>
										<div style="text-align: left;">${spaces.SGuide.replaceAll("\\.", "<br/>") }</div>
									</div>
								</div>
								
								<section id="section3"></section>
								<!-- 호스트 Space 정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">유의사항</h2>
										<p class="gg"></p>
										<div style="text-align: left;">
											<p>예약시 주의사항</p>
											${spaces.SNotice.replaceAll("\\.", "<br/>") }
										</div>
									</div>
								</div>
							
								<section id="section4"></section>
								<!-- 호스트 Space 정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">환불정책</h2>
										<p class="gg"></p>
										<div style="text-align: left;">
											<p>환불 규정 안내</p>
											${spaces.SRefund.replaceAll("\\.", "<br/>") }
										</div>
									</div>
								</div>
								
								<!-- 호스트 Space 정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">${spaces.SName }</h2>
										<h2 class="title mt-4" style="text-align: left;">${spaces.SMap }</h2>
										<h2 class="title mt-4" style="text-align: left;">${spaces.SDetailMap }</h2>
										<p class="gg"></p>
										<!-- 각자 발급받은 Client ID 값 넣기 [spacemoon의 client id : suw8zvq1lh] -->
										<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=suw8zvq1lh"></script>
										<div id="map" style="width:100%;height:450px;"></div>
	    								<!-- 
	    								<script id="code">
	   										   var mapDiv = document.getElementById('map');
	    									   var map = new naver.maps.Map(mapDiv);
										</script>
										 -->
									</div>
								</div>
								
								
								<section id="section5"></section>
								<!-- 호스트 Space 정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">Q&A</h2>
										<p class="gg">
																				
										<div id="viewtable" class="w-100" style="margin: 0 auto;"></div>
										<div id="pageNumDiv2"></div>
										
																			
										
										
										</p>
									</div>
								</div>
								
								<section id="section6"></section>
								<!-- 호스트 Space 정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4" style="text-align: left;">이용후기</h2>
										<p class="gg">    	
								    	
										<div id="noticetable" class="w-100" style="margin: 0 auto;"></div>
										<div id="pageNumDiv"></div>
		
										</p>
									</div>
								</div>
								
								<!-- 호스트 Space 정보 -->
								<div class="card mb-5">
									<div class="card-body">
										<h2 class="title mt-4">호스트의 다른 공간</h2>
										<hr>
	   									<c:forEach items="${spaceHostList}" var="spaceHost">
	 									 	<tr>		
	 									 		<td>${spaceHost.SName}</td>
	   											<td>${spaceHost.SPrice}</td> 	
											</tr>   											
										</c:forEach>
									</div>
								</div>
	
							
	
							</div>
						</div>
						
						<!--end col-->
	
						<div class="col-lg-4 col-md-5 col-12 mt-4 mt-sm-0 pt-2 pt-sm-0">	
						<div>
												
							<div class="box1" style="background-color: white;">
								<div class="sidebar sticky-sidebar">
								
									<div class="widget mb-4 pb-2">
										
												<span>예약날짜</span>
												<label for="pRedate"></label>
												<input type="text" id="pRedate" name="pRedate" value="">
												</div>
												<!-- <span class="float-right">${spaces.SPdate }</span> -->
												<!-- datapicker를 사용할 input 요소 -->
											
												<div>
													 <div>
														<label for="quantity" style="display: inline-block;">총 예약인원</label>
	 													 	<div class="personcontainer d-flex align-items-center">
	  													 		<div id="minus" class="button" style="margin-left: 110px;"><i class="bi bi-file-minus"></i></div>
	   													 			<input type="number" id="quantity" name="pRepeople" value="1" min="1" max="${spaces.SPerson}" required>
	   																<div id="plus" class="button"><i class="bi bi-plus-square" style="margin-left: 10px;"></i></div>
	  														</div>
													</div>	
	              								</div>
											
												<div>
													<button type="submit" onclick="location.href='${pageContext.request.contextPath}/member_heart?sNo='+${space.sNo }">찜</button>
													<br>
													<button onclick="goToOrderPage();">예약신청하기</button>
												</div>
										
									</div>
								</div>
							</div>
						</div>
						
						</div>
					</div>
			</section>
		</div>
	</div>


</form>			
						
<div id="container">
  <!-- 내용을 출력할 컨테이너 -->
</div>
<script>

//스크롤 이동
function scrollToSection(id) {
	const section = document.getElementById(id); // 클릭한 버튼에 해당하는 섹션 요소를 가져옴
	const sectionTop = section.offsetTop; // 섹션 요소의 위쪽에서의 거리(offset)를 가져옴
	window.scrollTo({ top: sectionTop, behavior: 'smooth' }); // 스크롤을 해당 섹션으로 이동
}
	
        
      //네이버 지도
    	
    	var HOME_PATH = window.HOME_PATH || '.';
    	var spacemap = new naver.maps.LatLng(37.65259599999992, 126.90814729999975),
        map = new naver.maps.Map('map', {
            center: spacemap.destinationPoint(0, 500),
            zoom: 18
        }),
        marker = new naver.maps.Marker({
            map: map,
            position: spacemap
        });

    	var contentString = [
    		'<div class="iw_inner">',
            '   <h3>　${spaces.SName}</h3>',
            '   <p>　${spaces.SMap }　</p><br />',
            '    　${spaces.SDetailMap} <br />',
    	    '</div>'
    					
        ].join('');

    	var infowindow = new naver.maps.InfoWindow({
       		 content: contentString
    	});

    	naver.maps.Event.addListener(marker, "click", function(e) {
      	  if (infowindow.getMap()) {
       	  	   infowindow.close();
      	  } else {
        	    infowindow.open(map, marker);
      	  }
    	});

    	infowindow.open(map, marker);
    	  var quantity = $("#quantity");
    	
	    	//예약하기
	    	function goToOrderPage() {
	    		//sNo값 히든태그로 저장 후 폼태그에 전달.
	    		 var urlParams = new URLSearchParams(window.location.search);
	    		 var sNo = urlParams.get('sNo');
	    		 
	    		 var input = document.createElement('input');
	    		  input.type = 'hidden';
	    		  input.name = 'sNo';
	    		  input.value = sNo;
	    		  document.getElementById('form1').appendChild(input);
	    		//pRepeople값 히든태그로 저장 후 폼태그에 전달.  
	    		
			
	    		f.action = "<c:url value="/order"/>";
	    		f.submit();
				//var url = "${pageContext.request.contextPath}/order?sNo="+sNo;
				//window.location.href = url;
			}
	    	
    	$(document).ready(function() {
    		
    		
    		 $("#pRedate").datepicker({
    		        showOtherMonths: true,
    		        selectOtherMonths: true,
    		        changeMonth: true,
    		        changeYear: true,
    		        showButtonPanel: true,
    		        dateFormat: 'yy-mm-dd'
    		    });
 
    		 
    		  content1.find('#minus').on('click', function () {
    			  var $quantity = $(this).parent().find('#quantity');
    			  if ($quantity.val() > 1) {
    			    $quantity.val(parseInt($quantity.val()) - 1);
    			    $('#quantity').val($quantity.val());
    			  }
    			});
    			content1.find('#plus').on('click', function () {
    			  var $quantity = $(this).parent().find('#quantity');
    			  if ($quantity.val() < parseInt($quantity.attr('max'))) {
    			    $quantity.val(parseInt($quantity.val()) + 1);
    			    $('#quantity').val($quantity.val());
    			  }
    			});
    		  $('#container').empty().append(content1);
    		  
    		  // container 내용을 오른쪽 끝에서 위아래로 출력
    		  $('#container').css({position: 'absolute', right: '100px', top: '300px'});
				
    		
    		
    		
    		});
    	
    	
    // URL에서 sno 값을 추출하여 변수에 할당
   
	var page=1;
	noticeListDisplay(page);
	
	function noticeListDisplay(pageNum) {
		 var urlParams = new URLSearchParams(window.location.search);
		    var rSno = urlParams.get('sNo');

	    page = pageNum;
		    $.ajax({
		        type: "get",
		        url: "${pageContext.request.contextPath}/space_reviewList/" + rSno + "?pageNum=" + pageNum,
		        dataType: "json",
		        success: function(result) {
	        	  if (result.reviewList.length == 0) {
	        	    var html = "<table>";
	        	    html += "<tr>";
	        	    html += "<th colspan='6'>작성된 게시글이 없습니다.</th>";
	        	    html += "</tr>";
	        	    html += "</table>";
	        	    $("#noticetable").html(html);
	        	    return;
	        	  }
	        	  var html = "<div class='row'>";
	        	  html += "<div class='col-lg-8 col-md-6 col-12 mx-auto'>";
	        	  html += "<div class='card mb-5'>";
	        	  html += "<div class='card-body'>";
	        	  html += "<table class='table'>";
	        	  html += "<thead>";
	        	  html += "<tr>";
	        	  html += "<th scope='col'>리뷰 번호</th>";
	        	  html += "<th scope='col'>제목</th>";
	        	  html += "<th scope='col'>내용</th>";
	        	  html += "<th scope='col'>답변 상태</th>";
	       
	        	  html += "</tr>";
	        	  html += "</thead>";
	        	  html += "<tbody>";
	        	  $(result.reviewList).each(function() {
	        	    html += "<tr>";
	        	    html += "<td scope='row'>" + this.reviewList[0].rno + "</td>";
	        	    html += "<td scope='row'>" + this.reviewList[0].rtitle + "</td>";
	        	    html += "<td scope='row'>" + this.reviewList[0].rcontent + "</td>";
	        	    html += "<td scope='row'>" + this.reviewList[0].rstatus + "</td>";
	        	    html += "</tr>";
	        	  });
	        	  html += "</tbody>";
	        	  html += "</table>";
	        	  html += "</div>";
	        	  html += "</div>";
	        	  html += "</div>";
	        	  html += "</div>";
	        	  pageNumDisplay(result.pager)
	        	  $("#noticetable").html(html);
	        	}

	        				
	        				
	        			,
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
		   	
    	


noticeListDisplay2(page);

function noticeListDisplay2(pageNum) {
	 var urlParams = new URLSearchParams(window.location.search);
	    var qSno = urlParams.get('sNo');

    page = pageNum;
    $.ajax({
        type: "get",
        url: "${pageContext.request.contextPath}/space_questionList/" + qSno + "?pageNum=" + pageNum,
	     
        dataType: "json",
        success: function(result) {
        	  if (result.questionList.length == 0) {
        	    var html = "<table>";
        	    html += "<tr>";
        	    html += "<th colspan='6'>작성된 게시글이 없습니다.</th>";
        	    html += "</tr>";
        	    html += "</table>";
        	    $("#viewtable").html(html);
        	    return;
        	  }
        	  var html = "<div class='row'>";
        	  html += "<div class='col-lg-8 col-md-6 col-12 mx-auto'>";
        	  html += "<div class='card mb-5'>";
        	  html += "<div class='card-body'>";
        	  html += "<table class='table'>";
        	  html += "<thead>";
        	  html += "<tr>";
        	  html += "<th scope='col'>Q&A 번호</th>";
        	  html += "<th scope='col'>제목</th>";
        	  html += "<th scope='col'>내용</th>";
        	  html += "<th scope='col'>답변 상태</th>";

        	  html += "</tr>";
        	  html += "</thead>";
        	  html += "<tbody>";
        	  $(result.questionList).each(function() {
        	    html += "<tr>";
        	    html += "<td scope='row'>" + this.questionList[0].qno + "</td>";
        	    html += "<td scope='row'>" + this.questionList[0].qtitle + "</td>";
        	    html += "<td scope='row'>" + this.questionList[0].qcontent + "</td>";
        	    html += "<td scope='row'>" + this.questionList[0].qstatus + "</td>";
			    html += "</tr>";
        	  });
        	  html += "</tbody>";
        	  html += "</table>";
        	  html += "</div>";
        	  html += "</div>";
        	  html += "</div>";
        	  html += "</div>";
        	  pageNumDisplay2(result.pager)
        	  $("#viewtable").html(html);
        	}

        				
        				
        			,
        error: function(xhr, status, error) {
            console.log(error);
        }
    });
}



function pageNumDisplay2(pager) {
	var html="";
	
	if(pager.startPage > pager.blockSize) {
		html+="<a href='javascript:noticeListDisplay2("+pager.prevPage+")'>[이전]</a>";
	}
	for(i=pager.startPage;i<=pager.endPage;i++) {
		if(pager.pageNum!=i) {
			html+="<a href='javascript:noticeListDisplay2("+i+")'>[ "+i+" ]</a>";
		} else {
			html+="[ "+i+" ] ";
		}
	}
	if(pager.endPage != pager.totalPage) {
		html+="<a href='javascript:noticeListDisplay2("+pager.nextPage+")'>[다음]</a>";
	}
	$("#pageNumDiv2").html(html);
}


	
	
</script>
</body>
</html>