<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./BoardReWriteAction.bo?pageNum=${param.pageNum }" method="post">
	<input type="hidden" name="bno" value="${param.bno }">
	<input type="hidden" name="re_ref" value="${param.re_ref }">
	<input type="hidden" name="re_lev" value="${param.re_lev }">
	<input type="hidden" name="re_seq" value="${param.re_seq }"> 
	
	<fieldset>
		<legend>답글 입력 창</legend>
		<p>${dto.date }</p>
		<ul>
			<li>
				<label>작성자</label>
				<div>
					<input type="text" name="name" 
					       placeholder="작성자를 입력해주세요">
				</div>
			</li>
			<li>
				<label>비밀번호</label>
				<div>
					<input type="password" name="pass" 
					       placeholder="비밀번호를 입력해주세요">
				</div>
			</li>
			<li>
				<label>제 목</label>
				<div>
					<input type="text" name="subject" value="[답글]">
				</div>
			</li>
			<li>
				<label>내 용</label>
				<div>
					<textarea name="content">
					</textarea>
				</div>
			</li>
		</ul>
		<p>
			<input type="submit" value="답글 쓰기">
		</p>
	</fieldset>
</form>
</body>
</html>