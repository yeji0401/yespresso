package com.sh.yespresso.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.yespresso.common.YespressoFileRenamePolicy;
import com.sh.yespresso.review.model.dto.Review;
import com.sh.yespresso.review.model.dto.ReviewAttachment;
import com.sh.yespresso.review.model.service.ReviewService;

/**
 * Servlet implementation class MyReviewEnrollServlet
 */
@WebServlet("/myPage/myReviewEnroll")
public class MyReviewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/myPage/myReviewEnroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 0. MultipartRequest객체 생성 - 요청메세지에서 파일을 읽어(Input), 서버컴퓨터에 저장(Output)까지 처리
			String saveDirectory = getServletContext().getRealPath("/upload/review"); // 웹루트디렉토리(src/main/webapp)부터 탐색
			System.out.println(saveDirectory);

			int maxPostSize = 10 * 1024 * 1024; // byte단위 : 1kb-1024, 1mb-1024*1kb

			String encoding = "utf-8";

			// FileRenamePolicy policy = new DefaultFileRenamePolicy(); // 중복파일이 있는 경우,
			// abc1.txt, abc2.txt, ...
			FileRenamePolicy policy = new YespressoFileRenamePolicy(); // 년월일_시분초밀리초_난수.txt

			// MultipartRequest(HttpServletRequest request, String saveDirectory, int
			// maxPostSize, String encoding, FileRenamePolicy policy)
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);

			// 1. 사용자입력값 처리 - request가 아닌 MultipartRequest에서 값 꺼내기
			String reviewTitle = multiReq.getParameter("reviewTitle");
			String reviewMemberId = multiReq.getParameter("memberId");
			String reviewContent = multiReq.getParameter("reviewContent");

			Review review = new Review();
			review.setReviewTitle(reviewTitle);
			review.setReviewMemberId(reviewMemberId);
			review.setReviewContent(reviewContent);

			// 업로드한 파일처리
			if (multiReq.getFile("upFile1") != null) {
				ReviewAttachment reviewAttach = new ReviewAttachment();
				reviewAttach.setReviewFileName(multiReq.getOriginalFileName("upFile1"));
				reviewAttach.setReReviewFileName(multiReq.getFilesystemName("upFile1"));
				review.addAttachment(reviewAttach);
			}

			if (multiReq.getFile("upFile2") != null) {
				ReviewAttachment reviewAttach = new ReviewAttachment();
				reviewAttach.setReviewFileName(multiReq.getOriginalFileName("upFile2"));
				reviewAttach.setReReviewFileName(multiReq.getFilesystemName("upFile2"));
				review.addAttachment(reviewAttach);
			}
			System.out.println(review);

			// 2. 업무로직 insert into Review
			// (REVIEW_NO,REVIEW_MEMBER_ID,REVIEW_ORDER_NO,REVIEW_PRODUCT_NO,REVIEW_TITLE,REVIEW_CONTENT,REVIEW_RATING)
			// values
			// (SEQ_REVIEW_NO.nextval, ?, ?, ?, ?, ?, ?)
			int result = reviewService.insertReview(review);

			// 3. 리다이렉트
			response.sendRedirect(request.getContextPath() + "/myPage/reviewView?no=" + review.getReviewNo());

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "리뷰 등록중 오류가 발생했습니다.");
			response.sendRedirect(request.getContextPath() + "/myPage/myReviewsList");
		}

	}

}
