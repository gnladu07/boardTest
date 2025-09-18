<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="" method="post">
	<fieldset>
		<legend>본문</legend>
		<p>${dto.date }</p>
		<ul>
			<li>
				<label>글번호</label>
				<div>
					<input type="text" name="name" 
					       value="${dto.bno }" readonly>
				</div>
			</li>
			<li>
				<label>작성자</label>
				<div>
					<input type="text" name="name" 
					       value="${dto.name }" readonly>
				</div>
			</li>
			<li>
				<label>제 목</label>
				<div>
					<input type="text" name="subject" 
					       value="${dto.subject }" readonly>
				</div>
			</li>
			<li>
				<label>내 용</label>
				<div>
					<textarea name="content" readonly>
						${dto.content }
					</textarea>
				</div>
			</li>
			<li>
				<label>조회수</label>
				<div>
					<input type="text" name="name" 
					       value="${dto.readcount }" readonly>
				</div>
			</li>
			<li>
				<label>파 일</label>
				<div>
					<input type="text" name="name" 
					       value="${dto.file }" readonly>
				</div>
			</li>
		</ul>
		<p>
			<input type="button" value="수정" 
			       onclick=" location.href='./BoardUpdate.bo?bno=${dto.bno}&pageNum=${pageNum }'; ">
		</p>
		<p>
			<input type="button" value="삭제" 
			       onclick=" location.href='./BoardDelete.bo?bno=${dto.bno}&pageNum=${pageNum }'; ">
		</p>
		<p>
			<input type="button" value="목록" 
			       onclick=" location.href='./BoardList.bo?pageNum=${pageNum}'; ">
		</p>
	</fieldset>
</form>
</body>
</html>