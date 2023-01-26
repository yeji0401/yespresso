package com.sh.yespresso.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp")
			.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 1. 인코딩 처리
//		request.setCharacterEncoding("utf-8");
//		
//		// 2. 사용자 입력값 가져오기
//		String memberId = request.getParameter("memberId");
//		String password = YespressoUtils.getEncryptedPassword(request.getParameter("password"), memberId);
//		String saveId = request.getParameter("saveId");
//		
//		System.out.println("memberId = " + memberId);
//		System.out.println("password = " + password);
//		System.out.println("saveId = " + saveId);
//		
//		 // 3. 업무로직
//		Member member = memberService.selectOneMember(memberId);
//		System.out.println("member = " + member);
//		
		
	}

}
