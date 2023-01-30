package com.sh.yespresso.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.member.model.dto.Gender;
import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/myPage/myAccontUpdate")
public class MyAccontUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * sql
	 * 
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// 1. 인코딩처리
			request.setCharacterEncoding("utf-8");

			// 사용자 입력값 가져오기
			String memberId = request.getParameter("memberId");
			String password = YespressoUtils.getEncryptedPassword(request.getParameter("password"), memberId);
			String memberName = request.getParameter("memberName");
			String _birthday = request.getParameter("birthday"); // "" "1988-08-08"
			String _gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			// 후처리
			Date birthday = !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
			Gender gender = _gender != null ? Gender.valueOf(_gender) : null;

			Member member = new Member(memberId, null, password, memberName, birthday, gender, email, phone, address,
					null);
			System.out.println(member);

			// 3. 업무로직 - db update
			int result = memberService.updateMember(member);
			System.out.println("result = " + result);

			if (result > 0) {
				session.setAttribute("msg", "회원 정보를 성공적으로 수정했습니다.");

				// 세션정보도 갱신
				session.setAttribute("loginMember", memberService.selectOneMember(memberId));

			}

		} catch (Exception e) {
			session.setAttribute("msg", "회원 정보 수정중 오류가 발생했습니다.");
			e.printStackTrace();
		}

		// 4. 리다이렉트 - /myPage/myAccountView
		response.sendRedirect(request.getContextPath() + "/myPage/myAccountView");

	}
}