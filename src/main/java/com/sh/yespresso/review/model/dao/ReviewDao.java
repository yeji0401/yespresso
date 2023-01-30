package com.sh.yespresso.review.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.sh.yespresso.review.model.dto.Review;
import com.sh.yespresso.review.model.dto.ReviewAttachment;
import com.sh.yespresso.review.model.exception.ReviewException;

public class ReviewDao {
	private Properties prop = new Properties();

	public ReviewDao() {
		String path = ReviewDao.class.getResource("/sql/review/review-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ReviewDao 쿼리 로드 완료! - " + prop);
	}

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
	public int insertReview(Connection conn, Review review) {
		String sql = prop.getProperty("insertReview");
		int result = 0;
		// insertReview = insert into Review(review_no, review_member_id,
		// review_order_no, review_product_no, review_title, review_content,
		// review_rating) values (seq_review_no.nextval, ?, ?, ?, ?, ?, ?)
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// review_member_id, review_order_no, review_product_no, review_title,
			// review_content, review_rating
			pstmt.setString(1, review.getReviewMemberId());
			pstmt.setString(2, review.getReviewOrderNo());
			pstmt.setString(3, review.getReviewProductNo());
			pstmt.setString(4, review.getReviewTitle());
			pstmt.setString(5, review.getReviewContent());
			pstmt.setInt(6, review.getReviewRating());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ReviewException("리뷰 등록 오류!", e);
		}
		return result;
	}

	public int selectLastReviewNo(Connection conn) {
		String sql = prop.getProperty("selectLastReviewNo");
		int reviewNo = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rset = pstmt.executeQuery();) {
			if (rset.next())
				reviewNo = rset.getInt(1);

		} catch (SQLException e) {
			throw new ReviewException("리뷰번호 조회 오류!", e);
		}

		return reviewNo;
	}

	public int insertReviewAttachment(Connection conn, ReviewAttachment reviewAttach) {
		String sql = prop.getProperty("insertReviewAttach");
		// insert into
		// attachment(review_file_no,fk_review_no,review_filename,re_review_filename)
		// values (seq_review_attachment_no.nextval, ?, ?, ?)
		int result = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reviewAttach.getReviewNo());
			pstmt.setString(2, reviewAttach.getReviewFileName());
			pstmt.setString(3, reviewAttach.getReReviewFileName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new ReviewException("리뷰 첨부파일 등록 오류!", e);
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
