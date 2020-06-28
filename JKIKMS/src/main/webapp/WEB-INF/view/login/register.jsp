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
	<script src="/static/js/login/register.js"></script>
</head>

<body>


  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">JKIKMS</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto mr-5">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">위밋플레이스</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">SMTP</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">공용게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">개인스페이스</a>
          </li>
        </ul>
        <c:choose>
		    <c:when test="${empty userInfo}">
		    	<a href="/register">회원가입</a>
				<a class="ml-2" href="/login">로그인</a>
		    </c:when>
		    <c:otherwise>
		    	<a class="userName" href=""><c:out value="${userInfo.userName}" /> </a>
				<a class="ml-2" href="/logout">로그아웃</a>
		    </c:otherwise>
		</c:choose>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">
	<div class="row justify-content-center">
		<div class="col-md-6  m-5 border pt-5 pb-3 pl-4 pr-4">
			<div class="border-bottom text-center text-secondary pb-2"><h2>회원가입<h2></h2></div>
			<form class="form-horizontal pt-4" action="/registerCheck" method="POST" accept-charset="UTF-8">
	  			<div class="form-group">
	    			<label for="userId" class="control-label">아이디(*)</label>
		    		<div class="">
		      			<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디" required>
		      			
		    		</div>
	  			</div>
	  			<div class="row">
	  				<div class="form-group col-md-6">
				    	<label for="userPw" class="control-label">비밀번호(*)</label>
				    	<div class="">
				    		<input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호" required>
				    	</div>
				  	</div>
				  	<div class="form-group col-md-6">
				    	<label for="userPwChk" class="control-label">비밀번호 확인(*)</label>
				    	<div class="">
				    		<input type="password" class="form-control" id="userPwChk" name="userPwChk" placeholder="비밀번호 확인" required>
				    	</div>
				  	</div>
	  			</div>
	  			
	  			
			  	<div class="form-group">
			    	<label for="userName" class="control-label">이름(*)</label>
			    	<div class="">
			    		<input type="text" class="form-control" id="userName" name="userName" placeholder="이름" required>
			    	</div>
			  	</div>
			  	<div class="form-group mb-4">
			    	<label for="userEmail" class="control-label">이메일</label>
			    	<div class="">
			    		<input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="이메일">
			    	</div>
			  	</div>
	  			
	  			<div class="form-group mb-4">
			    	<label for="userLolNic" class="control-label">롤 닉네임</label>
			    	<div class="">
			    		<input type="text" class="form-control" id="userLolNic" name="userLolNic" placeholder="롤 닉네임">
			    	</div>
			  	</div>
			  	
			  	<div class="form-group text-right border-top">
			    	<div class="w-100 mt-4">
			     		<button type="submit" class="btn btn-default bg-primary text-white">가입하기</button>
			    	</div>
			  	</div>
			</form>
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
