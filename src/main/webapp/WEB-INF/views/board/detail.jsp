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
	     		
	     		<!-- PAGE 실제 내용 -->
	     		<div class="container-fluid">
	     		<!-- row : 한 줄이라는 의미이다. -->
	     			<div class="row">
						<div>${boardVO.boardTitle}</div>
	     				<div>${boardVO.boardWriter}</div>
	     				<div>${boardVO.boardContents}</div>
	     			</div>	
	     			<!-- 파일 출력 -->
	     			<div class="row">
	     				<c:forEach items="#{boardVO.list}" var="f">
	     					<!-- upload 경로 기재 시, 실제 배포할 때 나오지않는다. -->
	     					<!-- 현재위치 : NOTICE, 저장된 파일명: f.fileName -->
	     					<!-- ROOT 밑에 files 으로 시작하는 모든 URL은 컨트롤러로 가지말고 서버에 있는 폴더로 바로 가기 지정함. -->
	     					<%-- <img alt="" src="../files/notice/${f.fileName}"> --%>
	     					<!-- NoticeController에서 모든 메서드에 적용하는 경로를 지정함. board를 호출하면 notice으로 리턴되어 그 경로에 들어있는 파일이 출력됨. -->
	     					<img alt="" src="../files/${board}/${f.fileName}">
	     					
	     					<!-- 클릭 시 이미지 파일 다운로드 되게끔 함. -->
	     					<a href="./fileDown?fileNo=${f.fileNo}">${f.oriName}</a>
	     				</c:forEach>
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