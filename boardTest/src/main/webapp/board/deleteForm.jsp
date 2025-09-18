<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./BoardDeleteAction.bo" method="post">
	<input type="hidden" name="bno" value="${param.bno }" >
	
	<fieldset>
		<legend>삭제 전 비밀번호를 입력해주세요</legend>
		<p>필수 입력</p>
		<ul>
			<li>
				<label>비밀번호</label>
				<div>
					<input type="password" name="pass" placeholder="비밀번호를 입력해주세요">
				</div>
			</li>
		</ul>
		<p>
			<input type="submit" value="글 삭제">
		</p>
	</fieldset>

</form>
</body>
</html>