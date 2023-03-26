<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		.container2 {
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100vh;
		}

	</style>

</head>
<body >
	
	<h1 style="text-align: center; font-size: 1.5em; font-weight:900; color: #706FFF;">호스트 아이디/비밀번호 찾기</h1>
	<div class="container2"  class="d-flex justify-content-center align-items-center" style= "height: 100vh">
			<form action="${pageContext.request.contextPath }/host/host_find" method="post" id="findForm" 
			style="	
			border: 1px solid #706FFF;
			width: 50%;
			padding: 20px;
			border-radius: 15px;
			box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
			margin-bottom: 300px;">
			  <div>
			    <label>이름</label>
			    <input type="text"   class="form-control mb-3 text-center" id="hName" name="hName" required>
			  </div>
			  <div>
			    <label >이메일</label>
			    <input type="email"  class="form-control mb-3 text-center" id="hEmail" name="hEmail" required>
			  </div>
			  
			  <button type="submit">입력</button>
			  <c:if test="${not empty errorMessage}">
			    <p style="color:red">${errorMessage}</p>
			</c:if>
			</form>
	</div>
</body>
</html>