<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	     		
	     		<!-- 페이지 실제 상세 내용 -->
	     		<div class="container-fluid">
	     			<!-- 글작성 버튼 클릭 시 보여지는 페이지 -->
	     			<!-- Contents 에 제목, 작성자, 내용 입력, row: 가로 하나를 의미함. -->
	     			<div class="row col-sm-12">
	     			  <form action="add" method="post" enctype="multipart/form-data">
	     			  	<sec:csrfInput/>
	     			  	<div class="mb-3">
	     			  	<label for="boardTitle" class="form-label">Title</label>
	     			  	<input type="text" class="form-control" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요">
	     			  	</div>
	     			  	
	     			   <div class="mb-3">
	     			  	<label for="boardWriter" class="form-label">Writer</label>
	     			  	<input type="text" class="form-control" id="boardWriter" name="boardWriter" placeholder="작성자를 입력하세요">
	     			  	</div>
	     			  	
	     			  	<div class="mb-3">
	     			  	<label for="boardContents" class="form-label">Contents</label>
	     			  	<textarea class="form-control" id="boardContents" name="boardContents" placeholder="내용을 입력하세요"></textarea>
	     			  	</div>
	     			  	
	     			  	<div class="mb-3">
						<!-- 파일업로드 -->
	     			  	<input type="file" class="form-control" name="files">
	     			  	</div>
	     			  	
	     			  	<div class="mb-3">
						<!-- 파일업로드 -->
	     			  	<input type="file" class="form-control" name="files">
	     			  	</div>
	     			  	
	     			  	<button class="btn btn-danger">Add</button>
	     			  </form>
	     			</div>
	
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