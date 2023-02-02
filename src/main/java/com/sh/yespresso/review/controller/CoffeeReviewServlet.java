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

/**
 * Servlet implementation class CoffeeReviewServlet
 */
@WebServlet("/coffee/review")
public class CoffeeReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();

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
		System.out.println("param=" + param);
		
		// 2. 업무로직 - 받아온 pdNo로 리뷰테이블에서 리뷰 가져오기
		// select * from REVIEW where REVIEW_PRODUCT_NO = ?(pdNo)
		List<Review> reviewList = reviewService.selectReviewByPdNo(param);
		 System.out.println("리뷰목록=" + reviewList);
		
		// 페이지바 보내주기
		int totalCount = reviewService.selectRvCountByPdNo(pdNo);
		String url = request.getRequestURI();
		String pagebar = YespressoUtils.getPagebar(page, limit, totalCount, url);
		
		// 3. view단 - 가져온 리뷰 담아서 되돌려줌 
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/product/coffeeReviewList.jsp") // 담은 reviewList를 coffeeReviewList.jsp에 전달
			.forward(request, response);
	}

}
