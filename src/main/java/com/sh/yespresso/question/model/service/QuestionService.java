package com.sh.yespresso.question.model.service;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.yespresso.question.model.dao.QuestionDao;
import com.sh.yespresso.question.model.dto.Question;
import com.sh.yespresso.question.model.dto.QuestionAttachment;

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
	// 나의 질문 리스트보기
	public List<Question> selectMyQuestionsList(Map<String, Object> param, String questionMemberId) {
		Connection conn = getConnection();
		List<Question> questionList = questionDao.selectMyQuestionsList(conn, param, questionMemberId);
		close(conn);
		return questionList;
	}

	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = questionDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
	}
	//질문중 하나 선택.
	public Question selectOneQuestion(int questionNo) {
		Connection conn = getConnection();
		Question question = questionDao.selectOneQuestion(conn, questionNo);

		List<QuestionAttachment> questionAttachments = questionDao.selectQuestionAttachmentByQuestionNo(conn,
				questionNo);
		question.setQuestionAttachments(questionAttachments);
		close(conn);
		return question;
	}

	public List<Question> selectAnswerList(int questionNo) {
		Connection conn = getConnection();
		List<Question> answersList = questionDao.selectAnswerList(conn, questionNo);
		close(conn);
		return answersList;
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
