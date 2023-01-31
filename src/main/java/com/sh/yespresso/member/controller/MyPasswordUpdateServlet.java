package com.sh.yespresso.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdatePasswordServlet
 */
@WebServlet("/myPage/myPasswordUpdate")
public class MyPasswordUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/myPage/myPasswordUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			Member loginMember = (Member) session.getAttribute("loginMember");
			String location = request.getContextPath(); //
			String msg = null;

			// 1. 사용자입력값 가져오기
			String memberId = loginMember.getMemberId();
			String oldPassword = YespressoUtils.getEncryptedPassword(request.getParameter("oldPassword"), memberId);
			String newPassword = YespressoUtils.getEncryptedPassword(request.getParameter("newPassword"), memberId);

			// 2. 기존비밀번호 일치여부 검사
			boolean pass = oldPassword.equals(loginMember.getPassword());

			// 3. 업무로직 : 기존비밀번호가 일치한 경우만 신규비밀번호로 업데이트한다.
			if (pass) {
				// 4. 세션의 정보는 db의 정보 일치하는가? Yes
				loginMember.setPassword(newPassword);

				int result = memberService.myPasswordUpdate(loginMember);
				msg = "비밀번호를 성공적으로 변경했습니다.";
				location += "/myPage/myAccountView";
			} else {
				msg = "비밀번호가 일치하지 않습니다.";
				location += "/myPage/myPasswordUpdate";
			}

			// 4. 사용자경고창 및 리다이렉트 처리
			session.setAttribute("msg", msg);
			response.sendRedirect(location);

		} catch (Exception e) {
			session.setAttribute("msg", "비밀번호 변경 처리 도중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

}
