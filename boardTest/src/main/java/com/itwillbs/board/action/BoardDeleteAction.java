package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardTestDAO;
import com.itwillbs.board.db.BoardTestDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSFunctionClass;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			                     HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardDeleteAction_execute() 실행! ");
		
		BoardTestDTO dto = new BoardTestDTO();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		
		dto.setPass(request.getParameter("pass"));
		
		System.out.println(" M : 삭제될 데이터 "+dto);
		
		BoardTestDAO dao = new BoardTestDAO();
		
		int result = dao.deleteBoard(dto);
		System.out.println(" M : result : "+result);
		
		if(result == -1) {
			JSFunctionClass.alertAndBack(response, "게시글 정보가 없습니다.");
			return null;
		}
		
		if(result == 0) {
			JSFunctionClass.alertAndBack(response, "글 비밀번호 오류! 재확인 부탁드립니다!");
			return null;
		}
		
		JSFunctionClass.alertAndMove(response, "글 삭제 완료!", "./BoardList.bo");
		return null;
	}

}
