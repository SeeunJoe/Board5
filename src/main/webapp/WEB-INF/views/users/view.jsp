<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<style>
   input:not(input[type=submit]) { width:100%; }
   input[type=submit] { width : 100px; }
   #goList  { width : 80px; }
   
   td { 
      padding:10px;
      width: 700px;
      text-align: center;
   }
   td:nth-of-type(1) {
      width : 200px;
   }
   
   td:not([colspan]):first-child {
      background: black;
      color : white;
      font-weight: bold;
   }
   input[readonly]{
      background : #EEE;
   }
      

</style>
</head>
<body>
  <main>
	<h2>사용자 상세정보</h2>
	<form action="/Users/Write" method="POST">
	<table>
	 <tr>
	   <td>아이디</td>
	   <td>${vo.userid}</td>
	 </tr>
	 <tr>
	   <td>비밀번호</td>
	   <td>${vo['passwd'] }</td>
	 </tr>
	 <tr>
	   <td>이름</td>
	   <td>${vo.username}</td>
	 </tr>
	 <tr>
	   <td>이메일</td>
	   <td>${vo.email}</td>
	 </tr>
	 <tr>
	   <td>포인트</td>
	   <td>
	     <!--  <c:if test="${vo.upoint ne 0 }">
	        ${vo['point'] }</c:if>    -->
	      <c:choose>
	       <c:when test="${vo.upoint ne 0 }">
	         ${vo['upoint'] }
	       </c:when>
	       <c:otherwise>
	         <span style="color:red">없음</span>
	       </c:otherwise>
	      </c:choose>	      
	   </td>
	 </tr>
	 <tr>
	   <td>회원등급</td>
	   <td>${vo.upt}</td>
	 </tr>
	 <tr>
	   <td>가입일</td>
	   <td>${vo.indate}</td>
	 </tr>
	 <tr>
	   <td colspan="2">
	      <a class="btn btn-secondary btn-sm" role="button" 
	         href="/Users/WriteForm" >회원가입</a>
	      <a class="btn btn-secondary btn-sm" role="button"
	         href="/Users/UpdateForm?userid=${ vo.userid }" >회원수정</a>
	      <a class="btn btn-outline-danger btn-sm" role="button" 
	         href="/Users/Delete?userid=${ vo.userid }" >회원삭제</a>
	      <a class="btn btn-secondary btn-sm" role="button" 
	         href="/Users/List" >회원목록</a>
	      <a class="btn btn-primary btn-sm" href="/" role="button">HOME</a>
	   </td>
	 </tr>
	
	</table>	
	</form>   
	
  </main>
  
  <script>
  	const  goListEl  = document.getElementById('gouserList');
  	goListEl.addEventListener('click', function(e) {
  		location.href = '/Users/List';
  	})
  
  </script>
  
</body>
</html>





