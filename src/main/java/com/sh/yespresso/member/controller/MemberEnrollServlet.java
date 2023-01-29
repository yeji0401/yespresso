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
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		try {
			//사용자 입력값 가져오기
			String memberId = request.getParameter("memberId");
			String password = YespressoUtils.getEncryptedPassword(request.getParameter("password"), memberId);
			String memberName = request.getParameter("memberName");
			String _birthday = request.getParameter("birthday"); // "" "1988-08-08"
			String _gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			//후처리
			Date birthday = !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
			Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
			
			Member member = new Member(memberId,null,password,memberName,birthday,gender,email,phone,address,null);
			System.out.println(member);
			
			// 업무로직
			int result = memberService.insertMember(member);
			
			if(result > 0) {
				session.setAttribute("msg","회원가입을 축하드립니다.");
			}
		} catch(Exception e) {
			session.setAttribute("msg", "회원가입에 실패하였습니다.");
			e.printStackTrace();
		}
		
		//리다이렉트
		response.sendRedirect(request.getContextPath()+"/");
	}

}
