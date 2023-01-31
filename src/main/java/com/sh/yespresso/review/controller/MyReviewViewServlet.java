package com.sh.yespresso.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.common.YespressoUtils;
import com.sh.yespresso.review.model.dto.Review;
import com.sh.yespresso.review.model.service.ReviewService;

@WebServlet("/myPage/myReviewView")
public class MyReviewViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReviewService reviewService = new ReviewService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		System.out.println("reviewNo = " + reviewNo);

		// 2. 업무로직 - 게시판/첨부파일테이블 조회
		Review review = reviewService.selectOneReview(reviewNo);
		System.out.println("review = " + review);

		// 개행문자 변환처리
		review.setReviewContent(
				YespressoUtils.convertLineFeedToBr(YespressoUtils.escapeHtml(review.getReviewContent())));

		// 3. view단 위임
		request.setAttribute("review", review);
		request.getRequestDispatcher("/WEB-INF/views/myPage/myReviewView.jsp").forward(request, response);
	}

}
