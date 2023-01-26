package com.sh.yespresso.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class MyAccontUpdateServlet
 */
@WebServlet("/myPage/myAccount/myAccountUpdate")
public class MyAccountUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// 1. 인코딩 처리
			request.setCharacterEncoding("utf-8");
			// 2. 사용자 입력값 -> member
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			String _birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			// 후처리
			Date birthday = !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
			Member member = new Member(memberId, null, null, memberName, birthday, null, email, phone, address, null);
			System.out.println(member);

			// 3. 업무로직 - db update
			int result = memberService.updateMember(member);
			System.out.println("result = " + result);

			if (result > 0) {
				session.setAttribute("msg", "회원정보를 성공적으로 수정했습니다.");

				// session 정보도 갱신
				session.setAttribute("loginMember", memberService.selectOneMember(memberId));
			}
		} catch (Exception e) {
			session.setAttribute("msg", "회원 정보 수정 중 오류가 발생했습니다.");
			e.printStackTrace();
		}

		// 4. 리다이렉트 - /myPage/myAccount/myAccountUpdate
		response.sendRedirect(request.getContextPath() + "/myPage/myAccount/myAccountUpdate");
	}

}
