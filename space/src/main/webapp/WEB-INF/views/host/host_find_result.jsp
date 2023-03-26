<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="${pageContext.request.contextPath}/host/host_find_end" method="post">
		<h1>회원 정보</h1>
		 <p id="mId">id = ${hId}</p>
 	     <p id="mPw">password = ${hPw}</p>
 	     
		  <input type="hidden" name="hId" value="${hId}" />
		  <input type="hidden" name="hPw" value="${hPw}" />
		  <input type="hidden" name="hStatus" value="${hStatus}" />
  <button type="submit">확인</button>
		</form>
	
</body>
</html>