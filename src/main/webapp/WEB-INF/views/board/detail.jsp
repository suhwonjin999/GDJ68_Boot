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
    	<!-- sidebar -->
    	<c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
    	     <!-- Content Wrapper -->
	     <div id="content-wrapper" class="d-flex flex-column">
	     	<div id="content">
	     	
				<c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
	     		
	     		<div class="container-fluid">
	     			<!-- PAGE 실제 내용 -->
	     			제목 : ${list.boardTitle}
	     			<br>
	     			작성자 : ${list.boardWrite}
	     			<br>
	     			내용 : ${list.boardContents}
	     			<br>
	     			작성일 : ${list.boardDate}
	     			<br>
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