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

import com.google.gson.Gson;
import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.member.model.dto.Member;
import com.sh.yespresso.question.model.dto.Question;
import com.sh.yespresso.question.model.service.QuestionService;

/**
 * Servlet implementation class CoffeeQuestionServlet
 */
@WebServlet("/coffee/question")
public class CoffeeQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService = new QuestionService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징
		final int limit = 5; 
		int page = 1;
		// 페이지 기본값처리 1 
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch(NumberFormatException e) { // 없다면? 그냥 처음 지정한 page=1 로 사용
			
		}
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);

		
		// 1. 사용자입력값 - pdNo 받아오기
		String pdNo = request.getParameter("pdNo");
		param.put("pdNo", pdNo); //param에 제품번호도 넣어줌
		System.out.println("문의에서가져옴 =" + pdNo);

		// 2. 업무로직 - 받아온 pdNo로 Question테이블에서 문의 가져오기 
		// select * from QUESTION where QUESTION_PRODUCT_NO = ?(pdNo)
		List<Question> questionList = questionService.selectQuestionByPdNo(param);
		System.out.println(questionList);
		
		// 페이지바 보내기
		int totalCount = questionService.selectTotalCountByPdNo(pdNo);
		String url = request.getRequestURI();
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
	
		// 3. view단 - 가져온 문의 담아서 보내기
		request.setAttribute("questionList", questionList);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/product/coffeeQuestionList.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
