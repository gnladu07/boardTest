package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.BoardTestDAO;
import com.itwillbs.board.db.BoardTestDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			                     HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute() 실행 ");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		HttpSession session = request.getSession();
		boolean updateStatus = (boolean)session.getAttribute("updateStatus");
		
		BoardTestDAO dao = new BoardTestDAO();
		
		if(updateStatus) {
			dao.increseReadcount(bno);
			session.setAttribute("updateStatus", false);
		}
		
		BoardTestDTO dto = dao.getBoard(bno);
		System.out.println(" M : "+dto);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
