package com.sh.yespresso.question.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sh.yespresso.review.model.dto.ReviewAttachment;

public class Question extends QuestionEntity {

	private int attachCnt;
	private List<QuestionAttachment> questionAttachments = new ArrayList<>();

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(int questionNo, int questionLevel, int questionRefNo, String questionMemberId,
			String questionProductNo, String questionTitle, String questionContent, Date questionDate, int attachCnt) {
		super(questionNo, questionLevel, questionRefNo, questionMemberId, questionProductNo, questionTitle,
				questionContent, questionDate);
		this.attachCnt = attachCnt;
	}

	public Question(int questionNo, int questionLevel, int questionRefNo, String questionMemberId,
			String questionProductNo, String questionTitle, String questionContent, Date questionDate,
			List<QuestionAttachment> questionAttachments) {
		super(questionNo, questionLevel, questionRefNo, questionMemberId, questionProductNo, questionTitle,
				questionContent, questionDate);
		this.questionAttachments = questionAttachments;
	}

	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}

	public List<QuestionAttachment> getQuestionAttachments() {
		return questionAttachments;
	}

	public void setQuestionAttachments(List<QuestionAttachment> questionAttachments) {
		this.questionAttachments = questionAttachments;
	}

	@Override
	public String toString() {
		return "Question [attachCnt=" + attachCnt + ", questionAttachments=" + questionAttachments + ", toString()="
				+ super.toString() + "]";
	}

	/**
	 * question객체 생성시 초기화된 attachment에 Attachment객체를 추가하는 메소드
	 * 
	 * @param attach
	 */
	public void addAttachment(QuestionAttachment questionAttach) {
		this.questionAttachments.add(questionAttach);
	}

}
