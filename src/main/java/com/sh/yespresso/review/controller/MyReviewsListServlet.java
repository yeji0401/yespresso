package com.sh.yespresso.review.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.review.model.dto.Review;
import com.sh.yespresso.review.model.service.ReviewService;


@WebServlet("/myPage/myReviewsList")
public class MyReviewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReviewService reveiewService = new ReviewService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String reviewMemberId = request.getParameter("memberId");
		final int limit = 5;
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {}
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		
		// 2. 업무로직
		
		List<Review> myReviewsList = reveiewService.selectMyReviewsList(param, reviewMemberId);
		System.out.println(myReviewsList);
		// b. 페이지바
		int totalCount = reveiewService.selectTotalCount(); // select count(*) from review
		System.out.println(totalCount);
		
		String url = request.getRequestURI(); // /yespresso/myPage/myReviewsList
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);
		
		// 3. view단 위임.
		request.setAttribute("myReviewsList", myReviewsList);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/myPage/myReviewsList.jsp").forward(request, response);
		
	}

}
