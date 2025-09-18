package com.itwillbs.util;

// ActionForward 객체:
//           컨트롤러에서 페이지 이동할때 필요한 정보를 저장하는 객체
//            => 페이지 이동할때 반드시 필요한 객체

//            비행기 / 버스 / 기차 티켓 처럼 사용

public class ActionForward {
	
	private String path;			// 버스
	private boolean isRedirect;		// 이동방법
				// true  - sendRedirect() 방식으로 이동
				// false - forward() 방식으로 이동
	
	public ActionForward() {
		System.out.println("-------------------");
		System.out.println("     티켓 정보     ");
		System.out.println("    초기화 시작    ");
		System.out.println("-------------------");
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	@Override
	public String toString() {
		return "ActionForward [path (목적지) =" + path + 
				", isRedirect (이동방식) =" + isRedirect + "]";
	}
	
	
}	
