package com.itwillbs.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardTestDAO;
import com.itwillbs.board.db.BoardTestDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			                     HttpServletResponse response) throws Exception {
		System.out.println(" M : /BoardListAction.bo_execute() 실행!  ");
		
		BoardTestDAO dao = new BoardTestDAO();
		// 전체 글 개수
		int totalCount = dao.getBoardCount();
		System.out.println(" M : totalCount : "+totalCount);
		
		/**************************페이징 처리 1단계**************************/
		// 한 페이지에 출력할 글의 개수
		int pageSize = 5;
		
		// 현페이지 번호를 받아오는 작업
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		// 시작행 번호 계산하기 1 11 21 31 41 .... / pageSize, pageNum 사용
		int currentPage = Integer.parseInt(pageNum); // pageNum을 int로 변환해서 저장
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// 끝행 번호 계산하기 10 20 30 40 .... / pageSize, pageNum 사용
		int endRow = pageSize * currentPage;
		/**************************페이징 처리 1단계**************************/
		
		// List<BoardTestDTO> btList = dao.getBoardList(); // 일반 리스트 처리 
		List<BoardTestDTO> btList = null;
		if(totalCount > 0) {
			btList = dao.getBoardList(startRow, pageSize);
		}
	
		/**************************페이징 처리 2단계**************************/
		// 총 페이지 계산 => 총 글의 개수 / 페이지출력개수
		int pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
		
		// 한 화면에 출력될 페이지 블럭의 크기(페이지의 개수)
		int pageBlock = 2; // 1, 2, 3, 4 .... 10
		
		// 페이지 블럭의 시작번호 계산 1 ~ 10 => 1, 11 ~ 20 => 21 ~ 30
		int startPage = ((currentPage - 1)/pageBlock) * pageBlock + 1;
		
		// 페이지 블럭의 끝번호 계산
		int endPage = startPage + pageBlock - 1;
		
		// 글의 개수가 적을때 마지막 페이지를 다시 계산
		if(endPage > pageCount) endPage = pageCount;
		
		System.out.println(" M : 1, 2 단계에서 계산된 페이징 처리 값을 JSP페이지로 전달 ");
		/**************************페이징 처리 2단계**************************/
		
		request.setAttribute("btList", btList);
		
		// 페이징 관련 정보 request 영역에 저장
		request.setAttribute("pageNum", pageNum);       // 페이지 번호
		request.setAttribute("totalCount", totalCount); // 총 글의 개수
		request.setAttribute("pageCount", pageCount);   // 총 페이지 개수
		request.setAttribute("pageBlock", pageBlock);   // 페이지 블럭의 크기
		request.setAttribute("startPage", startPage);   // 페이지 블럭의 시작번호
		request.setAttribute("endPage", endPage);       // 페이지 블럭의 끝번호
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
