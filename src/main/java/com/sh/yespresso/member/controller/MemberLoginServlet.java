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
		// 1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자 입력값 가져오기
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		
		
		System.out.println("memberId = " + memberId);
		System.out.println("password = " + password);
		
		 // 3. 업무로직
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member = " + member);
		
		//HttpSession객체 - 접속한 클라이언트(브라우저)별로 제공되는 객체
		HttpSession session = request.getSession();
		
		
		
		if(member != null && password.equals(member.getPassword())) {
			// 로그인 성공
			session.setAttribute("loginMember", member);
			
		}
		else {
			// 로그인 실패
			session.setAttribute("msg", "아이디가 존재하지 않거나 비밀번호가 틀립니다.");
		}
		
		// 4. view단처리 - redirect(url변경 - 새로고침 안되도록)
		response.sendRedirect(request.getContextPath() + "/"); // 클라이언트가 다시 요청할 주소
		
		
	}
		
		
	

}
