package com.sh.yespresso.review.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	// DQL 제품에 해당하는 리뷰 목록
	public List<Review> selectReviewByPdNo(Connection conn, Map<String, Object> param) {
		// select * from (select row_number() over(order by REVIEW_NO desc) rnum, r.* from REVIEW r where REVIEW_PRODUCT_NO = ?) where rnum between ? and ?;
		String sql = prop.getProperty("selectReviewByPdNo"); 
		List<Review> reviewList = new ArrayList<>();
		
		int page = (int) param.get("page");
		int limit = (int) param.get("limit");
		String pdNo = (String) param.get("pdNo"); // 담아뒀던 제품번호 가져옴
		System.out.println(pdNo);
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pdNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			try(ResultSet rset = pstmt.executeQuery()){
				
				while(rset.next()) {
					Review review = new Review();
					review.setReviewNo(rset.getInt("REVIEW_NO"));
					review.setReviewMemberId(rset.getString("REVIEW_MEMBER_ID"));
					review.setReviewOrderNo(rset.getString("REVIEW_ORDER_NO"));
					review.setReviewProductNo(rset.getString("REVIEW_PRODUCT_NO"));
					review.setReviewTitle(rset.getString("REVIEW_TITLE"));
					review.setReviewContent(rset.getString("REVIEW_CONTENT"));
					review.setReviewRating(rset.getInt("REVIEW_NO"));
					review.setReviewDate(rset.getDate("REVIEW_DATE"));
					reviewList.add(review);
				}
			}
			
		} catch (SQLException e) {
			throw new ReviewException("제품별 리뷰목록 조회 오류!", e);
		}
		
		return reviewList;
	}
	
	// DQL 제품에 해당하는 리뷰 개수
	public int selectRvCountByPdNo(Connection conn, String pdNo) {
		String sql = prop.getProperty("selectRvCountByPdNo");
		int totalCount = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pdNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				if (rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
		} catch (Exception e) {
			throw new ReviewException("제품별 리뷰 수 조회 오류!", e);
		}
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

	public List<Review> selectMyReviewsList(Connection conn, Map<String, Object> param, String reviewMemberId) {
		String sql = prop.getProperty("selectMyReviewsList");
		List<Review> myReviewsList = new ArrayList<>();

		int page = (int) param.get("page");
		int limit = (int) param.get("limit"); // 5

		int start = (page - 1) * limit + 1; // 1, 6, 11, 16, ...
		int end = page * limit; // 5, 10, 15, 20, ...

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, reviewMemberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			try (ResultSet rset = pstmt.executeQuery()) {

				while (rset.next()) {
					Review review = handleReviewResultSet(rset);
					review.setAttachCnt(rset.getInt("attach_cnt"));
					myReviewsList.add(review);
				}
			}

		} catch (SQLException e) {
			throw new ReviewException("나의 리뷰 조회 오류!", e);
		}

		return myReviewsList;
	}

	private Review handleReviewResultSet(ResultSet rset) throws SQLException {
		Review review = new Review();
		review.setReviewNo(rset.getInt("review_no"));
		review.setReviewMemberId(rset.getString("review_member_id"));
		review.setReviewOrderNo(rset.getString("review_order_no"));
		review.setReviewProductNo(rset.getString("review_product_no"));
		review.setReviewTitle(rset.getString("review_title"));
		review.setReviewContent(rset.getString("review_content"));
		review.setReviewRating(rset.getInt("review_rating"));
		review.setReviewDate(rset.getDate("review_date"));
		review.setProductName(rset.getString("product_name"));
		return review;
	}

	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount");
		int totalCount = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rset = pstmt.executeQuery();) {
			if (rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new ReviewException("전체 리뷰 수 조회 오류!", e);
		}
		return totalCount;
	}

	public Review selectOneReview(Connection conn, int reviewNo) {
		String sql = prop.getProperty("selectOneReview");
		Review review = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reviewNo);

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					review = handleReviewResultSet(rset);
				}
			}

		} catch (Exception e) {
			throw new ReviewException("리뷰 한건 조회 오류!", e);
		}

		return review;
	}

	public List<ReviewAttachment> selectAttachmentByReviewNo(Connection conn, int reviewNo) {
		String sql = prop.getProperty("selectAttachmentByReviewNo"); // select * from review_attachment where
																		// fk_review_no = ?
//		this.reviewAttachments.add(reviewAttach);
		List<ReviewAttachment> reviewAttachments = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reviewNo);

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					ReviewAttachment reviewAttach = handleAttachmentResultSet(rset);
					reviewAttachments.add(reviewAttach);
				}
			}

		} catch (Exception e) {
			throw new ReviewException("첨부파일 한건 조회 오류!", e);
		}

		return reviewAttachments;
	}

	private ReviewAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		ReviewAttachment reviewAttach = new ReviewAttachment();
		reviewAttach.setReviewFileNo(rset.getInt("review_file_no"));
		reviewAttach.setReviewNo(rset.getInt("fk_review_no"));
		reviewAttach.setReviewFileName(rset.getString("REVIEW_FILENAME"));
		reviewAttach.setReReviewFileName(rset.getString("REVIEW_FILENAME"));
		reviewAttach.setReviewFileDate(rset.getDate("REVIEW_FILENAME"));

		return reviewAttach;
	}

	public int deleteMyReview(Connection conn, int reviewNo) {
		String sql = prop.getProperty("deleteMyReview"); // deleteMyReview = delete from review where review_no = ?
		int result = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ReviewException("리뷰 삭제 오류!", e);
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
