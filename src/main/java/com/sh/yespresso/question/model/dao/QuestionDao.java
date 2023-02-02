package com.sh.yespresso.question.model.dao;

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

import com.sh.yespresso.question.model.dto.Question;
import com.sh.yespresso.question.model.dto.QuestionAttachment;
import com.sh.yespresso.question.model.exception.QuestionException;
import com.sh.yespresso.review.model.dto.Review;
import com.sh.yespresso.review.model.exception.ReviewException;

public class QuestionDao {

	private Properties prop = new Properties();

	public QuestionDao() {
		String path = QuestionDao.class.getResource("/sql/question/question-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("QuestionDao 쿼리 로드 완료! - " + prop);
	}

	/**
	 * hj start
	 */
	// DQL 제품에 해당하는 문의리스트
	public List<Question> selectQuestionByPdNo(Connection conn, Map<String, Object> param) {
		// select * from (select row_number() over(order by QUESTION_NO desc) rnum, q.* from QUESTION q where QUESTION_PRODUCT_NO = ?) where rnum between ? and ?
		String sql = prop.getProperty("selectQuestionByPdNo"); 
		List<Question> questionList = new ArrayList<>();
		
		int page = (int) param.get("page");
		int limit = (int) param.get("limit");
		String pdNo = (String) param.get("pdNo"); // 담아뒀던 제품번호 가져옴
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pdNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			try(ResultSet rset = pstmt.executeQuery()){
				
				while(rset.next()) {
					Question question = handleQuestionResultSet(rset);
					questionList.add(question);					
				}
			}
			
		} catch (SQLException e) {
			throw new QuestionException("제품별 문의목록 조회 오류!", e);
		}
		return questionList;
	}
	
	// DQL 제품에 해당하는 문의 수
	public int selectTotalCountByPdNo(Connection conn, String pdNo) {
		String sql = prop.getProperty("selectTotalCountByPdNo");
		int totalCount = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pdNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				if (rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
		} catch (Exception e) {
			throw new QuestionException("제품별 리뷰 수 조회 오류!", e);
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
	public List<Question> selectMyQuestionsList(Connection conn, Map<String, Object> param, String questionMemberId) {
		String sql = prop.getProperty("selectMyQuestionsList");
		List<Question> questionList = new ArrayList<>();

		int page = (int) param.get("page");
		int limit = (int) param.get("limit"); // 5

		int start = (page - 1) * limit + 1; // 1, 6, 11, 16, ...
		int end = page * limit; // 5, 10, 15, 20, ...

		// selectMyQuestionsList = select * from (select row_number() over(order by no
		// desc) rnum, q.*, (select count(*) from question_attachment where
		// fk_question_no = q.question_no) attach_cnt from question q where
		// question_member_id = ?) where rnum between ? and ?
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, questionMemberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			try (ResultSet rset = pstmt.executeQuery()) {

				while (rset.next()) {
					Question question = handleQuestionResultSet(rset);
					question.setAttachCnt(rset.getInt("attach_cnt"));
					questionList.add(question);
				}
			}

		} catch (SQLException e) {
			throw new QuestionException("질문 목록 조회 오류!", e);
		}

		return questionList;
	}

	private Question handleQuestionResultSet(ResultSet rset) throws SQLException {
		Question question = new Question();
		question.setQuestionNo(rset.getInt("question_no"));
		question.setQuestionLevel(rset.getInt("question_level"));
		question.setQuestionRefNo(rset.getInt("question_ref_no"));
		question.setQuestionMemberId(rset.getString("question_member_id"));
		question.setQuestionProductNo(rset.getString("question_product_no"));
		question.setQuestionTitle(rset.getString("question_title"));
		question.setQuestionContent(rset.getString("question_content"));
		question.setQuestionDate(rset.getDate("question_date"));
		question.setProductName(rset.getString("product_name"));

		return question;
	}

	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount");
		int totalCount = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rset = pstmt.executeQuery();) {
			if (rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new QuestionException("전체 질문수 조회 오류!", e);
		}
		return totalCount;
	}

	public Question selectOneQuestion(Connection conn, int questionNo) {
		String sql = prop.getProperty("selectOneQuestion");
		// select * from question where question_no = ?
		Question question = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, questionNo);

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					question = handleQuestionResultSet(rset);
				}
			}

		} catch (Exception e) {
			throw new QuestionException("질문 한건 조회 오류!", e);
		}

		return question;
	}

	public List<QuestionAttachment> selectQuestionAttachmentByQuestionNo(Connection conn, int questionNo) {
		String sql = prop.getProperty("selectQuestionAttachmentByQuestionNo"); // select * from question_attachment
																				// where fk_question_no = ?
		// this.questionAttachments.add(questionAttach);
		List<QuestionAttachment> questionAttachments = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, questionNo);

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					QuestionAttachment questionAttach = handleAttachmentResultSet(rset);
					questionAttachments.add(questionAttach);
				}
			}

		} catch (Exception e) {
			throw new QuestionException("게시글 한건 조회 오류!", e);
		}

		return questionAttachments;
	}

	private QuestionAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		QuestionAttachment questionAttach = new QuestionAttachment();
		questionAttach.setQuestionFileNo(rset.getInt("question_file_no"));
		questionAttach.setQuestionNo(rset.getInt("fk_question_no"));
		questionAttach.setQuestionFilename(rset.getString("question_filename"));
		questionAttach.setReQuestionFilename(rset.getString("re_question_filename"));
		questionAttach.setQuestionFileDate(rset.getDate("question_file_date"));

		return questionAttach;

	}

	public List<Question> selectAnswerList(Connection conn, int questionNo) {
		String sql = prop.getProperty("selecAnswerList");
		List<Question> answersList = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, questionNo);

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					Question answer = new Question();
					answer.setQuestionNo(rset.getInt("question_no"));
					answer.setQuestionLevel(rset.getInt("question_level"));
					answer.setQuestionRefNo(rset.getInt("question_ref_no"));
					answer.setQuestionMemberId(rset.getString("question_member_id"));
					answer.setQuestionProductNo(rset.getString("question_product_no"));
					answer.setQuestionTitle(rset.getString("question_title"));
					answer.setQuestionContent(rset.getString("question_content"));
					answer.setQuestionDate(rset.getDate("question_date"));
					answersList.add(answer);
				}
			}
		} catch (SQLException e) {
			throw new QuestionException("관리자 답변 조회 오류!", e);
		}
		return answersList;
	}

	public QuestionAttachment selectOneAttachment(Connection conn, int questionNo) {
		String sql = prop.getProperty("selectOneAttachment");
		QuestionAttachment questionAttach = null;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, questionNo);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next()) {
					questionAttach = handleAttachmentResultSet(rset);
				}
			}

		} catch (SQLException e) {
			throw new QuestionException("첨부파일 한건 조회 오류", e);
		}

		return questionAttach;
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
