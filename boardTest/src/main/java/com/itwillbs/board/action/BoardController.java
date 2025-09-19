package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	// http://localhost:8088/boardTest/BoardWrite.bo
	// http://localhost:8088/boardTest/test.bo
	
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : BoardController_doPocess()실행! ");
		System.out.println(" C : GET/POST 방식 상관없이 한번에 처리 ");
		
		System.out.println("/********************1. 가상주소 계산 - 시작*********************/");
			// 가상 주소 불러오기
			String requestURI = request.getRequestURI();
			System.out.println(" C : request : " + requestURI); // URI가 URL 주소에서 프로젝트명/페이지 주소 만 있는거
			// 프로젝트 (컨텍스트) 명 불러오기
			String CTXPath = request.getContextPath(); // 프로젝트명을 불러와서
			System.out.println(" C : CTXPath : " + CTXPath);
			String command = requestURI.substring(CTXPath.length()); // 프로젝트명을 subString함수를 이용하여 잘라낸다
			System.out.println(" C : command : " + command);
		System.out.println("/********************1. 가상주소 계산 - 끝*********************/");
		
		System.out.println("/********************2. 가상주소 매핑 - 시작*********************/");
			Action action = null;
			ActionForward forward = null;
		
			if(command.equals("/test.bo")) {
				System.out.println(" C : 필요한 동작을 처리 ");
			}
			else if(command.equals("/BoardWrite.bo")) {
				System.out.println(" C : /BoardWrite.bo 매핑! ");
				System.out.println(" C 패턴 1 - 데이터처리(DB사용)X, 페이지이동O ");
				
				forward = new ActionForward();
				forward.setPath("./board/writeForm.jsp");
				forward.setRedirect(false);
				
			}
			
			else if(command.equals("/BoardWriteAction.bo")) {
				System.out.println(" C : /BoardWriteAction.bo 매핑! ");
				System.out.println(" C : 패턴 2 - 데이터처리(DB사용)O, 페이지이동O ");
				
				action = new BoardWriteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(command.equals("/BoardList.bo")) {
				System.out.println(" C : /BoardList.bo 매핑! ");
				System.out.println(" C : 패턴 3 - 데이터처리(DB사용)O, 페이지출력O ");
				
				action = new BoardListAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else if(command.equals("/BoardContent.bo")) {
				System.out.println(" C : /BoardContent.bo 매핑! ");
				System.out.println(" C : 패턴 3 - 데이터처리(DB사용)O, 페이지출력O ");
				
				action = new BoardContentAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(command.equals("/BoardUpdate.bo")) {
				System.out.println(" C : /BoardUpdate.bo 매핑! ");
				System.out.println(" C : 패턴 2 - 데이터처리(DB사용)O, 페이지이동O ");
				
				action = new BoardUpdateAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(command.equals("/BoardUpdatePro.bo")) {
				System.out.println(" C : /BoardUpdatePro.bo 매핑! ");
				System.out.println(" C : 패턴 2 - 데이터처리(DB사용)O, 페이지이동O ");
				
				action = new BoardUpdateProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(command.equals("/BoardDelete.bo")) {
				System.out.println(" C : /BoardDelete.bo 매핑! ");
				System.out.println(" C : 패턴 1 - 데이터처리(DB사용)X, 페이지이동O ");
				
				forward = new ActionForward();
				forward.setPath("./board/deleteForm.jsp");
				forward.setRedirect(false);
			}
			
			else if(command.equals("/BoardDeleteAction.bo")) {
				System.out.println(" C : /BoardDeleteAction.bo 매핑! ");
				System.out.println(" C : 패턴 2 - 데이터처리(DB사용)O, 페이지이동O ");
				
				action = new BoardDeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(command.equals("/BoardReWrite.bo")) {
				System.out.println(" C : /BoardReWrite.bo 매핑! ");
				System.out.println(" C : 패턴 1 - 데이터처리(DB사용), 페이지이동O ");
				
				forward = new ActionForward();
				forward.setPath("./board/reWriteForm.jsp");
				forward.setRedirect(false);
			}
			
			else if(command.equals("/BoardReWriteAction.bo")) {
				System.out.println(" C : /BoardReWriteAction.bo 매핑! ");
				System.out.println(" C : 패턴 2 - 데이터추가(DB사용)O, 페이지이동O ");
				
				action = new BoardReWriteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		System.out.println("/********************2. 가상주소 매핑 - 끝*********************/");
	
		System.out.println("***************3. 가상주소 이동 - 시작 ***************");
			if(forward != null) {
				// => 페이지 이동정보(티켓)이 있을때
				if(forward.isRedirect()) { // true
					System.out.println(" C : path - "+forward.getPath()+",redirect : "+forward.isRedirect());
					response.sendRedirect(forward.getPath());
				} else { // false
					System.out.println(" C : path - "+forward.getPath()+",redirect : "+forward.isRedirect());
					
					RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
					dis.forward(request, response);
				}
			}
		System.out.println("***************3. 가상주소 이동 - 끝 ***************\n");
	}

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : BoardController_doGet()실행! ");
		doProcess(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : BoardController_doPost()실행! ");
		doProcess(request, response);
		
		
	}
	
	

}
