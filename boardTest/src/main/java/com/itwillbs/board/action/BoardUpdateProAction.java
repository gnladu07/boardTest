package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardTestDAO;
import com.itwillbs.board.db.BoardTestDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSFunctionClass;

public class BoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			                     HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateProAction_execute() 실행! ");
		
		BoardTestDTO dto = new BoardTestDTO();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		System.out.println(" M : (수정할 데이터) "+dto);
		
		BoardTestDAO dao = new BoardTestDAO();
		int result = dao.updateBoard(dto);
		System.out.println(" M : result = "+result);
		
		if(result == 0) { // 비밀번호를 잘못쓴경우
			JSFunctionClass.alertAndBack(response, "글 비밀번호 오류!");
			
			return null;
			
		}
		
		if(result == -1) { // 비밀번호를 잘못쓴경우
			JSFunctionClass.alertAndBack(response, "글 정보 없음!");
			
			return null;
			
		}
		
		JSFunctionClass.alertAndMove(response, "글 수정 완료!", "./BoardList.bo");
		return null;
	}

}
