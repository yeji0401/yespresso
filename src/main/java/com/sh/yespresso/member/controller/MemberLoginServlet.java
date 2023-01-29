package com.sh.yespresso.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp")
			.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리
			request.setCharacterEncoding("UTF-8");
			
			// 2. 사용자입력값 가져오기
			String memberId = request.getParameter("memberId");
			String password = YespressoUtils.getEncryptedPassword(request.getParameter("password"), memberId);
			String saveId = request.getParameter("saveId");
			
			System.out.println("memberId = " + memberId);
			System.out.println("password = " + password);
			System.out.println("saveId = " + saveId);
			
			// 3. 업무로직
			Member member = memberService.selectOneMember(memberId);		
			System.out.println("member = " + member);
			
			// HttpSession객체 - 접속한 클라이언트(브라우져)별로 제공되는 객체
			HttpSession session = request.getSession(); // getSession(true)
			
			System.out.println("id = " + session.getId());
			System.out.println("유효시간 = " + session.getMaxInactiveInterval()); // 초단위 60 * 30
			System.out.println("생성시각 = " + new Date(session.getCreationTime()));
			System.out.println("마지막 접속시각 = " + new Date(session.getLastAccessedTime())); // 이번 말고 최근접속시각 
			
			
			if(member != null && password.equals(member.getPassword())) {
				// 로그인 성공
				session.setAttribute("loginMember", member);
				
				Cookie cookie = new Cookie("saveId", memberId);
				cookie.setPath(request.getContextPath()); // cookie를 전송할 url설정, 지정한 url하위경로에 모두 전송  
				if(saveId != null) {
					// 아이디저장 체크한 경우
					// 유효시간 setMaxAge, setExpire
					// - 유효시간을 설정하지 않으면, session-cookie로 작동(브라우져 끄면 제거)
					// - 유효시간을 설정하면, persistent-cookie로 작동(지정한 기간동안 저장)
					cookie.setMaxAge(60 * 60 * 24 * 7); // 7일간 클라이언트에 저장
				}
				else {
					// 아이디저장 체크해제한 경우
					cookie.setMaxAge(0); // 즉시 삭제
				}
				response.addCookie(cookie); // 응답헤더 Set-Cookie로 전송
				
				
			}
			else {
				// 로그인 실패
				session.setAttribute("msg", "아이디가 존재하지 않거나 비밀번호가 틀립니다.");
			}
			
			// 4. view단처리 - redirect(url변경 - 새로고침 안되도록)
			response.sendRedirect(request.getContextPath() + "/"); // 클라이언트가 다시 요청할 주소
			
			
		}
	
		
	

}
