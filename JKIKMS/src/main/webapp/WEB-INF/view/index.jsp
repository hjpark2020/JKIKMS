<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	System.out.println(request.getAttribute("rMap"));
%>
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
	<script src="/static/js/index.js"></script>
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
				<a href="/login">로그인</a>
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

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Context list</h1>
        <div class="list-group">
          <a href="#" class="list-group-item">Category 1</a>
          <a href="#" class="list-group-item">Category 2</a>
          <a href="#" class="list-group-item">Category 3</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel" style = "overflow:hidden; height:400px">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="${pageContext.request.contextPath}/static/tempfile/1.PNG" alt="First slide" >
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="${pageContext.request.contextPath}/static/tempfile/2.PNG" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="${pageContext.request.contextPath}/static/tempfile/3.PNG" alt="Third slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
<div class="row">
		  <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">롤전적</a>
                </h4>
                <h5>blank</h5>
                <p class="card-text">롤전적 디스크립션</p>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">위밋플레이스</a>
                </h4>
                <h5>blank</h5>
                <p class="card-text">위밋플레이스 디스크립션</p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">영화 순위</a>
                </h4>
                <h5>blank</h5>
                <p class="card-text">영화 디스크립션</p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">공용게시판</a>
                </h4>
                <h5>blank</h5>
                <p class="card-text">공용게시판 디스크립션</p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">노래</a>
                </h4>
                <h5>엿먹어</h5>
                <p class="card-text">노래 디스크립션</p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">SMTM</a>
                </h4>
                <h5>엿먹어</h5>
                <p class="card-text">SMTM 디스크립션</p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <!-- <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a> -->
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">사진첩</a>
                </h4>
                <h5>엿먹어</h5>
                <p class="card-text">사진첩 디스크립션</p>
              </div>
            </div>
          </div>

        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

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
