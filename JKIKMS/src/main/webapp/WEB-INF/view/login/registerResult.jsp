<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<meta charset="UTF-8">
	<title>JKIKMS</title>
	<script type="text/javascript">
		var result = '<c:out value="${jRes.result}"/>';
		var errMsg = '<c:out value="${jRes.errMsg}"/>';
		console.log(errMsg)
		
		if(result == '1'){
			alert("회원가입을 축하드립니다!\n로그인 페이지로 이동합니다.");
			window.location.href = '/login';
		} else {
			alert("회원가입을 실패했습니다.\n뒤로 돌아갑니다.");
			window.history.back();
		}
	</script>
	
</head>

<body>
	
</body>
</html>
