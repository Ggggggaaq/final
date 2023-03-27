<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
</head>
<body>
<!-- datapicker를 사용할 input 요소 -->
	<label for="datepicker"></label>
	<input type="text" id="datepicker">
											
</body>

<script type="text/javascript">
$(function() {
	  $("#datepicker").datepicker();
	});
</script>
</html>