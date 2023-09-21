<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSP에서 properties에 있는 메세지를 사용할 수 있도록 하는 태그 : properties에 있는 내용을 JSP으로 사용하기 위해 라이브러리 등록함.-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
    	<!-- sidebar -->
    	<c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
    	     <!-- Content Wrapper -->
	     <div id="content-wrapper" class="d-flex flex-column">
	     	<div id="content">
	     	
				<c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
	     		
	     		<div class="container-fluid">
	     			<!-- PAGE 실제 내용 -->
	     			<!-- 메시지 KEY 값을 가져와서 화면에 출력하라. -->
	     			<!-- Default 값이 한국어로 되어있기 때문에 한국어로 출력됨. -->
	     			<h1>Welcome : <spring:message code="hello"></spring:message> </h1>
	     			
	     			<!-- KEY 값이 존재하지 않는 경우, 코드 자체가 웹 브라우저에 출력됨. -->
	     			<!-- KEY 값이 존재하지 않는 경우, 코드 대신 text에 기재한 내용이 출력됨. -->
	     			<!-- application-properties 설정 -->
	     			<h1><spring:message code="hi" text="기본메세지"></spring:message></h1>
	     			
	     		</div>
	     	</div> 
	     	
	     	<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
	     </div>
    </div>
    
    
<!-- <h1>Index Page</h1> -->
	<!-- src/main/resources/static : 가상의 폴더이다. -->
	<!-- src/main/resources/static : ROOT 로 인식됨. 절대경로 -->
	<!-- <img alt="" src="/images/canon.jpg"> -->

<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>