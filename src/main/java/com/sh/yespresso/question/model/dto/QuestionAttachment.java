package com.sh.yespresso.question.model.dto;

import java.sql.Date;

public class QuestionAttachment {
	private int questionFileNo;
	private int questionNo;
	private String questionFilename;
	private String reQuestionFilename;
	private Date questionFileDate;

	public QuestionAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionAttachment(int questionFileNo, int questionNo, String questionFilename, String reQuestionFilename,
			Date questionFileDate) {
		super();
		this.questionFileNo = questionFileNo;
		this.questionNo = questionNo;
		this.questionFilename = questionFilename;
		this.reQuestionFilename = reQuestionFilename;
		this.questionFileDate = questionFileDate;
	}

	public int getQuestionFileNo() {
		return questionFileNo;
	}

	public void setQuestionFileNo(int questionFileNo) {
		this.questionFileNo = questionFileNo;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestionFilename() {
		return questionFilename;
	}

	public void setQuestionFilename(String questionFilename) {
		this.questionFilename = questionFilename;
	}

	public String getReQuestionFilename() {
		return reQuestionFilename;
	}

	public void setReQuestionFilename(String reQuestionFilename) {
		this.reQuestionFilename = reQuestionFilename;
	}

	public Date getQuestionFileDate() {
		return questionFileDate;
	}

	public void setQuestionFileDate(Date questionFileDate) {
		this.questionFileDate = questionFileDate;
	}

	@Override
	public String toString() {
		return "QuestionAttachment [questionFileNo=" + questionFileNo + ", questionNo=" + questionNo
				+ ", questionFilename=" + questionFilename + ", reQuestionFilename=" + reQuestionFilename
				+ ", questionFileDate=" + questionFileDate + "]";
	}

}
