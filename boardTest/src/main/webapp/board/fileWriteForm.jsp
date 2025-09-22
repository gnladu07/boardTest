<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/board/fileBoardWrite.jsp</h1>
	
	<form action="./BoardFileWriteAction.bo" 
		  method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>파일 글쓰기</legend>
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
					<input type="text" name="subject"
			               placeholder="제목을 입력해주세요">
				</div>
			</li>
			<li>
				<label>내 용</label>
				<div>
					<textarea name="content" id="content_lbl" class="w100p" >
			        </textarea>
				</div>
			</li>
			<li>
				<label>파 일</label>
				<div>
					<input type="file" name="file">
				</div>
			</li>
		</ul>     
		<hr>
		<input type="submit" value="파일 글쓰기">
	</fieldset>
	</form>
</body>
</html>