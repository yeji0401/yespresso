package com.sh.yespresso.question.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.question.model.dto.Question;
import com.sh.yespresso.question.model.service.QuestionService;

/**
 * Servlet implementation class MyQuestionsListServlet
 */
@WebServlet("/myPage/myQuestionsList")
public class MyQuestionsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QuestionService questionService = new QuestionService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리 : id 가져오기
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		String questionMemberId = loginMember.getMemberId();
		
		// 1. 사용자입력값 처리 : 페이지바.
		final int limit = 5;
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}

		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);

		// 2. 업무로직
		// a. db에서 목록조회(페이징)
		List<Question> myQuestionsList = questionService.selectMyQuestionsList(param, questionMemberId);
		int totalCount = questionService.selectTotalCount(); // select count(*) from question
		System.out.println(totalCount);

		// b. 페이지바
		String url = request.getRequestURI(); // /yespresso/myPage/myQuestionsList
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);

		// 3. view단 위임.
		request.setAttribute("myQuestionsList", myQuestionsList);
		request.setAttribute("pagebar", pagebar);
		request.setAttribute("questionMemberId", questionMemberId);
		request.getRequestDispatcher("/WEB-INF/views/myPage/myQuestionsList.jsp").forward(request, response);
	}

}
