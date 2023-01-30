package com.sh.yespresso.review.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.yespresso.review.model.dao.ReviewDao;
import com.sh.yespresso.review.model.dto.Review;
import com.sh.yespresso.review.model.dto.ReviewAttachment;

public class ReviewService {
	private ReviewDao reviewDao = new ReviewDao();

	/**
	 * hj start
	 */
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
