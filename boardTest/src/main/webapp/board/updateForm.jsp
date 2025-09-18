<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./BoardUpdatePro.bo" method="post">
	<!-- input 태그 추가 사용(DB에서 사용O, 외부 노출 X) -->
	<input type="hidden" name="bno" value="${dto.bno }">
	
	<fieldset>
		<legend>수정 내용 입력</legend>
		<p>${dto.date }</p>
		<ul>
			<li>
				<label>작성자</label>
				<div>
					<input type="text" name="name" 
					       value="${dto.name }" >
				</div>
			</li>
			<li>
				<label>비밀번호</label>
				<div>
					<input type="password" name="pass" 
					       placeholder="비밀번호를 입력해주세요" required>
				</div>
			</li>
			<li>
				<label>제 목</label>
				<div>
					<input type="text" name="subject" 
					       value="${dto.subject }" >
				</div>
			</li>
			<li>
				<label>내 용</label>
				<div>
					<textarea name="content" >
						${dto.content }
					</textarea>
				</div>
			</li>
		</ul>
		<p>
			<input type="submit" value="수정하기">
		</p>
	</fieldset>
</form>
</body>
</html>