package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardTestDAO;
import com.itwillbs.board.db.BoardTestDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			                     HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardWriteAction_excute() 실행! ");
		
		BoardTestDTO dto = new BoardTestDTO();
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setIp(request.getRemoteAddr());
		
		System.out.println(" M: "+dto);
		
		BoardTestDAO dao = new BoardTestDAO();
		dao.insertBoard(dto);
		System.out.println(" M : 글쓰기 완료! ");
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
