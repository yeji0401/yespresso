package com.sh.yespresso.question.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.yespresso.question.model.dao.QuestionDao;
import com.sh.yespresso.question.model.dto.Question;

public class QuestionService {
	private QuestionDao questionDao = new QuestionDao();

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
	public List<Question> selectQuestionList(Map<String, Object> param, String questionMemberId) {
		Connection conn = getConnection();
		List<Question> questionList = questionDao.selectQuestionList(conn, param, questionMemberId);
		close(conn);
		return questionList;
	}

	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = questionDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
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
