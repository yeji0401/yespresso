package com.sh.yespresso.question.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.question.model.dto.Question;
import com.sh.yespresso.question.model.service.QuestionService;


@WebServlet("/myPage/myQuestionView")
public class MyQuestionViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService = new QuestionService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		System.out.println("questionNo = " + questionNo);

		// 2. 업무로직
		Question question = questionService.selectOneQuestion(questionNo);
		System.out.println("question = " + question);

		// 개행문자 변환처리
		question.setQuestionContent(YespressoUtils.convertLineFeedToBr(YespressoUtils.escapeHtml(question.getQuestionContent())));

		// 관리자 답변 조회
		List<Question> answersList = questionService.selectAnswerList(questionNo);
		System.out.println("answersList = " + answersList);

		// 3. view단 위임
		request.setAttribute("question", question);
		request.setAttribute("answersList", answersList);
		request.getRequestDispatcher("/WEB-INF/views/myPage/myQuestionView.jsp").forward(request, response);
	}

}
