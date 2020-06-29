<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var beforeMenu = '<c:out value="${beforeMenu}"/>';
	window.location.href = '/login?beforeMenu='+beforeMenu;
</script>
</head>
<body>

</body>
</html>