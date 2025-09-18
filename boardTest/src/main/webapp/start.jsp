<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>start.jsp</h1>
	MVC 프로젝트를 실행하기위해 생성한 페이지
	
	* MVC 프로젝트에서는 JSP 페이지를 절대로 직접 실행X
	* 실행되는 주소가 .jsp주소가 보인다면 잘못된 코드를 작성한것!
	
	* 실행가능한 유일한 JSP 페이지!
	
	<%
		System.out.println("\n\n V : start.jsp 페이지 실행 ");
		System.out.println(" V : 유일하게 직접 실행가능한 JSP 페이지 ");		
		System.out.println(" V : start.jsp -> BoardController 호출 \n\n");
		
		// response.sendRedirect("http://localhost:8088/boardTest/BoardWrite.bo");
		// response.sendRedirect("./BoardWrite.bo");
		response.sendRedirect("./BoardList.bo");
	%>
</body>
</html>