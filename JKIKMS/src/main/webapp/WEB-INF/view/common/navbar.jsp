<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">JKIKMS</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto mr-5">
          <c:forEach var="menu" items="${menuList}">
        		<c:choose>
        			<c:when test="${menu.menuUrl eq currentMenu}">
	        			<li class="nav-item active">
			            	<a class="nav-link" href="/${menu.menuUrl}"><c:out value="${menu.menuName}"></c:out>
			              		<span class="sr-only">(current)</span>
			            	</a>
			          	</li>
        			</c:when>
        			<c:otherwise>
        				<li class="nav-item">
		            		<a class="nav-link" href="/${menu.menuUrl}"><c:out value="${menu.menuName}"></c:out></a>
		          		</li>
        			 </c:otherwise>
        		</c:choose>
        		
        	</c:forEach>
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