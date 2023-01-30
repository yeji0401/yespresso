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
import com.sh.yespresso.question.model.exception.QuestionException;

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
	public List<Question> selectQuestionList(Connection conn, Map<String, Object> param, String questionMemberId) {
		String sql = prop.getProperty("selectQuestionList");
		List<Question> questionList = new ArrayList<>();

		int page = (int) param.get("page");
		int limit = (int) param.get("limit"); // 5

		int start = (page - 1) * limit + 1; // 1, 6, 11, 16, ...
		int end = page * limit; // 5, 10, 15, 20, ...

		// selectQuestionList = select * from (select row_number() over(order by no
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

		return question;
	}

	/**
	 * awon end
	 */

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

	/**
	 * jooh start
	 */
	/**
	 * jooh end
	 */

}
