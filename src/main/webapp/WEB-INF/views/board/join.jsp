<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/headCSS.jsp"></c:import>
</head>
<body id="page-top">
   <!-- Page Wrapper -->
    <div id="wrapper">
       <c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
       <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
           <!-- Main Content -->
            <div id="content">
               <c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
               
               <!-- Begin Page Content -->
                <div class="container-fluid">
                <!-- Spring에서 제공하는 FORM 태그 사용. (HTML에서 제공하는 태그와 구성이 다름.) -->
                   <!-- form태그를 대체한다. -->
                   <!-- action 경로를 명시하지 않을 시, 컨트롤러에 오는 주소가 대체함. -->
                   <form:form modelAttribute="memberVO" method="post" enctype="multipart/form-data">
	                   <div class="form-group">
	                   <!-- for는 id와 매핑됨. -->
	                   <form:label path="username">Username</form:label>
	                   <!-- input이 type을 대체한다. input 태그는 text를 의미함. -->
	                   <!-- path 가 name을 대신하는 역할을 한다. memberVO의 setter 명을 적는다. -->
	                   <form:input id="username" path="username" cssClass="form-control"/>
	                   
	                   <!-- 에러가 발생한 경우, 에러메세지를 출력함. -->
	                   <form:errors path="username" cssClass=""></form:errors>
	                   </div>
	                   
	                 <div class="form-group">
	                 	<form:label path="password">Password</form:label>
	                 	<form:password path="password" cssClass="form-control"></form:password>
	                 	<form:errors path="password"></form:errors>
	                 </div>
	                 
	                <div class="form-group">
	                	<form:label path="passwordCheck">PasswordCheck</form:label>
	                	<form:password path="passwordCheck" cssClass="form-control"></form:password>
	                	<form:errors path="passwordCheck"></form:errors>
	                </div>
	                
	                <div class="form-group">
	                	<form:label path="name">Username</form:label>
	                	<form:input id="memberInfoVO.name" path="memberInfoVo.name" cssClass="form-control"/>
	                	<form:errors path="name"></form:errors>
	                </div>
	                
	                 <div class="form-group">
	                	<form:label path="email">Email</form:label>
	                	<form:input id="email" path="memberInfoVO.email" cssClass="form-control"/>
	                	<form:errors path="email"></form:errors>
	                </div>
	                
	                <div class="form-group">
	                	<form:label path="birth">Birth</form:label>
	                	<form:input id="birth" path="memberInfoVO.birth" cssClass="form-control"/>
	                	<!-- error 메시지 출력 -->
	                	<form:errors path="birth"></form:errors>
	                </div>
	                
	                <div class="form-group">
	                   <label for="photo">Photo</label>
	                   <input type="file" class="form-control" id="photo" name="photo" aria-describedby="photo">
	                   <small id="photoHelp" class="form-text text-muted">We'll never share your email with anyone else.</samll>
                 	</div>
                 	
                 	<button type="submit" class="btn btn-primary">Submit</button>
                   </form:form>
                   
                   <!-- <form action="./join" method="post" enctype="multipart/form-data"> -->
<!--            <div class="form-group">
                   <label for="username">Username</label>
                   <input type="text" class="form-control" id="username" name="username" aria-describedby="username">
                   <small id="usernameHelp" class="form-text text-muted">We'll never share your email with anyone else.</samll>
                 </div>
                 <div class="form-group">
                   <label for="password">Password</label>
                   <input type="password" name="password" class="form-control" id="password">
                 </div>
                 <div class="form-group">
                   <label for="passwordCheck">Password</label>
                   <input type="password" name="passwordCheck" class="form-control" id="passwordCheck">
                 </div>
                 <div class="form-group">
                   <label for="name">Name</label>
                   <input type="text" class="form-control" id="name" name="name" aria-describedby="name">
                   <small id="nameHelp" class="form-text text-muted">We'll never share your email with anyone else.</samll>
                 </div>
                 <div class="form-group">
                   <label for="email">Email</label>
                   <input type="email" class="form-control" id="email" name="email" aria-describedby="email">
                   <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</samll>
                 </div>
                 <div class="form-group">
                   <label for="birth">Birth</label>
                   <input type="date" class="form-control" id="birth" name="birth" aria-describedby="birth">
                   <small id="birthHelp" class="form-text text-muted">We'll never share your email with anyone else.</samll>
                 </div>
                 <div class="form-group">
                   <label for="photo">Photo</label>
                   <input type="file" class="form-control" id="photo" name="photo" aria-describedby="photo">
                   <small id="photoHelp" class="form-text text-muted">We'll never share your email with anyone else.</samll>
                 </div>
                 <div class="mb-3 form-check">
                   <input type="checkbox" class="form-check-input" id="exampleCheck1">
                   <label class="form-check-label" for="exampleCheck1">Check me out</label>
                 </div>
                 <button type="submit" class="btn btn-primary">Submit</button>
               </form>
 -->
                </div>
            </div>
         <c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        </div>
    </div>
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>