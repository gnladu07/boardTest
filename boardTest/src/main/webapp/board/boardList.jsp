<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./BoardList.bo">
	<fieldset>
		<legend>검색&리스트</legend>
		<input type="text" title="검색어를 입력하세요."
		       name="search" placeholder="검색어를 입력해주세요.">
		<input type="submit" value="검색">
	</fieldset>
</form>
<fieldset>
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dto" items="${btList }" >
		<tr>
			<th>${dto.bno }</th>
			<td>
				<c:if test="${dto.re_lev > 0 }">
					<img src="./img/level.gif" width="${dto.re_lev * 10 }">
					<img src="./img/re.gif">
				</c:if>
				<a href="./BoardContent.bo?bno=${dto.bno }&pageNum=${pageNum }">
					${dto.subject }
				</a>
			</td>
			<th>${dto.name }</th>
			<th>${dto.readcount }</th>
			<th>${dto.date }</th>
		</tr>
		</c:forEach>
	</tbody>
</table>
</fieldset>
<br>
<input type="button" value="글쓰기" 
       onclick=" location.href='./BoardWrite.bo'; ">
       
<input type="button" value="파일 글쓰기" 
       onclick=" location.href='./BoardFlieWrite.bo'; ">
<!-- 페이징 버튼 -->
<div>
	<c:if test="${startPage > pageBlock}" >
		<!-- 이전 -->
		<a href="./BoardList.bo?pageNum=${startPage - pageBlock }">
			<img src="./img/btn_firstpage.png" alt="첫 페이지로 이동">
		</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
		<a href="./BoardList.bo?pageNum=${i }">
			<span class="pagenum 
			<c:if test="${pageNum == i }">
				currentpage
			</c:if>
			">${i }
			</span>
		</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount }">
		<!-- 다음 -->
		<a href="./BoardList.bo?pageNum=${startPage + pageBlock }"> 
			<img src="./img/btn_lastpage.png" alt="마지막 페이지로 이동">
		</a>
	</c:if>
</div>

</body>
</html>