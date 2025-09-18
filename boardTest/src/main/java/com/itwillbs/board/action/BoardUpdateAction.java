package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardTestDAO;
import com.itwillbs.board.db.BoardTestDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			                     HttpServletResponse response) throws Exception {
		System.out.println(" M : /BoardUpdateAction_execute() 실행  ");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardTestDAO dao = new BoardTestDAO();
		
		BoardTestDTO dto = dao.getBoard(bno);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
