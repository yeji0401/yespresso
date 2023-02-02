package com.sh.yespresso.admin.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberDeleteServlet
 */
@WebServlet("/admin/adminMemberDelete")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			HttpSession session = request.getSession();
			String memberId = request.getParameter("memberId");
			System.out.println("memberId = " + memberId);

			// 2. 업무 로직
			int result = memberService.deleteMember(memberId);

			// 모든 속성 제거하기
			Enumeration<String> names = session.getAttributeNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}
			// saveId cookie 제거
			Cookie c = new Cookie("saveId", memberId);
			c.setPath(request.getContextPath()); // /yespresso
			c.setMaxAge(0); // 쿠키의 유효기간 0=> 즉시삭제
			response.addCookie(c);

			// 3. 리다이렉트 처리
			session.setAttribute("msg", "회원을 성공적으로 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/adminMemberList");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
