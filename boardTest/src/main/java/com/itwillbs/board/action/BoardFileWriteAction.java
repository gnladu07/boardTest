package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardTestDAO;
import com.itwillbs.board.db.BoardTestDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFileWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			                     HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardFileWriteAction_execute() 실행 ");
		
		// 파일 업로드 처리
		String realPath = request.getRealPath("/upload");
		System.out.println(" M : realPath : "+realPath);
		
		int maxSize = 10 * 1024 * 1024; // 10MB
		
		MultipartRequest multi 
			= new MultipartRequest(
					request, 
					realPath, 
					maxSize, 
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
		System.out.println(" M : MultipartRequest 객체 생성완료! ");
		System.out.println(" M : 업로드 완료! ");
		
		BoardTestDTO dto = new BoardTestDTO();
		
		dto.setName(multi.getParameter("name"));
		dto.setPass(multi.getParameter("pass"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		
		dto.setFile(multi.getFilesystemName("file"));
		
		dto.setIp(request.getRemoteAddr());
		
		System.out.println(" M : "+dto);
		
		BoardTestDAO dao = new BoardTestDAO();
		dao.insertBoard(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
