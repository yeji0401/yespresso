package com.sh.yespresso.review.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.yespresso.review.model.dto.ReviewAttachment;
import com.sh.yespresso.review.model.service.ReviewService;


@WebServlet("/myPage/myReviewDelete")
public class MyReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReviewService reveiewService = new ReviewService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		System.out.println("reviewNo = " + reviewNo);
		
		// 2. 업무로직 - review삭제 - attachment삭제
		// 저장된 첨부파일 삭제
		List<ReviewAttachment> reviewAttachments = reveiewService.selectAttachmentByReviewNo(reviewNo);
		String saveDirectory = getServletContext().getRealPath("/upload/review");
		for(ReviewAttachment reviewAttach : reviewAttachments) {
			File delFile = new File(saveDirectory, reviewAttach.getReReviewFileName());
			boolean bool = delFile.delete();
			System.out.println(bool ? "파일 삭제 성공!" : "파일 삭제 실패!");
		}
		
		int result = reveiewService.deleteMyReview(reviewNo); 
		
		// 3. redirect : /myPage/myReviewsList
		request.getSession().setAttribute("msg", "리뷰를 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/myPage/myReviewsList");
		
	}

}






