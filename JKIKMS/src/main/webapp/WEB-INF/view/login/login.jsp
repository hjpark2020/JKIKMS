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
	<!-- 부트스트랩 기본 css -->
  	<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  	<!-- 부트스트랩 홈 스타일 -->
  	<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet">
	<%@ include file="/WEB-INF/view/common/common.jsp" %>
	<!-- <script src="/static/js/index.js"></script -->
	
</head>

<body>
	<%@ include file="/WEB-INF/view/common/navbar.jsp" %>
  <!-- Page Content -->
  <div class="container">
	<div class="row justify-content-center">
		<div class="col-md-4 col-sm-6 m-5 border pt-5 pb-3 pl-4 pr-4">
			<div class="border-bottom text-center text-secondary pb-2"><h2>로그인<h2></h2></div>
			<form class="form-horizontal pt-4 border-bottom" action="/loginCheck" method="POST">
				<input type="hidden" id="beforeMenu" name="beforeMenu" value="${beforeMenu}">
				
	  			<div class="form-group">
	    			<label for="inputEmail3" class="control-label">아이디</label>
		    		<div class="">
		      			<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디">
		    		</div>
	  			</div>
			  	<div class="form-group">
			    	<label for="inputPassword3" class="control-label">비밀번호</label>
			    	<div class="">
			    		<input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호">
			    	</div>
			  	</div>
			  	
			  	<div class="form-group text-right ">
			    	<div class="w-100">
			     		<button type="submit" class="btn btn-default bg-primary text-white">로그인</button>
			    	</div>
			  	</div>
			</form>
			<c:if test="${result eq 'N'}">
				<p class="mt-4 text-danger">아이디 또는 비밀번호가 잘못되었습니다.</p>
				<p class="mt-1">아이디나 비밀번호를 잊었을 때는, <a href="#">여기</a>를 눌러주세요.</p>
			</c:if>
			<c:if test="${result ne 'N'}">
				<p class="mt-4">아이디나 비밀번호를 잊었을 때는, <a href="/logout">여기</a>를 눌러주세요.</p>
			</c:if>
			<p class="mt-1">회원 가입은 <a href="/register">여기</a>에서 할 수 있습니다.</p>
		</div>
		<!--  -->
	</div>

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="/static/component/vendor/jquery/jquery.min.js"></script>
  <script src="/static/component/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
