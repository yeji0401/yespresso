package com.sh.yespresso.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/adminMemberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 입력값 - searchType, searchKeyword
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param = " + param);
		
		// 2. 업무로직 - 회원검색 
		List<Member> members = memberService.searchMember(param);
		System.out.println("members = " + members);
		
		// 3. forward(view단처리)
		request.setAttribute("members", members);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberList.jsp")
			.forward(request, response);
	}

}
