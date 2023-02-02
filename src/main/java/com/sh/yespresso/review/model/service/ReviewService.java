package com.sh.yespresso.review.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.yespresso.review.model.dao.ReviewDao;
import com.sh.yespresso.review.model.dto.Review;
import com.sh.yespresso.review.model.dto.ReviewAttachment;

public class ReviewService {
	private ReviewDao reviewDao = new ReviewDao();

	/**
	 * hj start
	 */

	// DQL 제품에 해당하는 리뷰리스트 
	public List<Review> selectReviewByPdNo(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Review> reviewList = reviewDao.selectReviewByPdNo(conn, param);
		close(conn);
		return reviewList;
	}
	
	// DQL 제품에 해당하는 리뷰 수
	public int selectTotalCountByPdNo(String pdNo) {
		Connection conn = getConnection();
		int totalCount = reviewDao.selectTotalCountByPdNo(conn, pdNo);
		close(conn);
		return totalCount;
	}
	/**
	 * hj end
	 */

	/**
	 * yeji start
	 */
	/**
	 * yeji end
	 */

	/**
	 * awon start
	 */
	public int insertReview(Review review) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 게시글등록
			result = reviewDao.insertReview(conn, review);

			// 방금 등록된 review.reviewNo컬럼값을 조회 - 시퀀스객체의 현재값
			int reviewNo = reviewDao.selectLastReviewNo(conn); // select seq_review_no.currval from dual
			System.out.println("reviewNo = " + reviewNo);

			review.setReviewNo(reviewNo);

			// 첨부파일 등록 // this.reviewAttachments.add(reviewAttach);
			List<ReviewAttachment> reviewAttachments = review.getReviewAttachment();
			if (!reviewAttachments.isEmpty()) {
				for (ReviewAttachment reviewAttach : reviewAttachments) {
					reviewAttach.setReviewNo(reviewNo); // fk컬럼값 세팅
					result = reviewDao.insertReviewAttachment(conn, reviewAttach);
				}
			}

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Review> selectMyReviewsList(Map<String, Object> param, String reviewMemberId) {
		Connection conn = getConnection();
		List<Review> myReviewsList = reviewDao.selectMyReviewsList(conn, param, reviewMemberId);
		close(conn);
		return myReviewsList;
	}

	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = reviewDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
	}

	public Review selectOneReview(int reviewNo) {
		Connection conn = getConnection();
		Review review = reviewDao.selectOneReview(conn, reviewNo);
		List<ReviewAttachment> reviewAttachments = reviewDao.selectAttachmentByReviewNo(conn, reviewNo);
		review.setReviewAttachment(reviewAttachments);
		close(conn);
		return review;
	}

	public List<ReviewAttachment> selectAttachmentByReviewNo(int reviewNo) {
		Connection conn = getConnection();
		List<ReviewAttachment> reviewAttachments = reviewDao.selectAttachmentByReviewNo(conn, reviewNo);
		close(conn);
		return reviewAttachments;
	}

	public int deleteMyReview(int reviewNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// dao요청
			result = reviewDao.deleteMyReview(conn, reviewNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	/**
	 * awon end
	 */

	/**
	 * jooh start
	 */
	/**
	 * jooh end
	 */

}
