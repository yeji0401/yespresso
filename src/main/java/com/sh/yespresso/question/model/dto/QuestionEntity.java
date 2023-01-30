package com.sh.yespresso.question.model.dto;

import java.sql.Date;

public class QuestionEntity {

		private int questionNo;
		private int questionLevel;
		private int questionRefNo;
		private String questionMemberId;
		private String questionProductNo;
		private String questionTitle;
		private String questionContent;
		private Date questionDate;
		public QuestionEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
		public QuestionEntity(int questionNo, int questionLevel, int questionRefNo, String questionMemberId,
				String questionProductNo, String questionTitle, String questionContent, Date questionDate) {
			super();
			this.questionNo = questionNo;
			this.questionLevel = questionLevel;
			this.questionRefNo = questionRefNo;
			this.questionMemberId = questionMemberId;
			this.questionProductNo = questionProductNo;
			this.questionTitle = questionTitle;
			this.questionContent = questionContent;
			this.questionDate = questionDate;
		}
		public int getQuestionNo() {
			return questionNo;
		}
		public void setQuestionNo(int questionNo) {
			this.questionNo = questionNo;
		}
		public int getQuestionLevel() {
			return questionLevel;
		}
		public void setQuestionLevel(int questionLevel) {
			this.questionLevel = questionLevel;
		}
		public int getQuestionRefNo() {
			return questionRefNo;
		}
		public void setQuestionRefNo(int questionRefNo) {
			this.questionRefNo = questionRefNo;
		}
		public String getQuestionMemberId() {
			return questionMemberId;
		}
		public void setQuestionMemberId(String questionMemberId) {
			this.questionMemberId = questionMemberId;
		}
		public String getQuestionProductNo() {
			return questionProductNo;
		}
		public void setQuestionProductNo(String questionProductNo) {
			this.questionProductNo = questionProductNo;
		}
		public String getQuestionTitle() {
			return questionTitle;
		}
		public void setQuestionTitle(String questionTitle) {
			this.questionTitle = questionTitle;
		}
		public String getQuestionContent() {
			return questionContent;
		}
		public void setQuestionContent(String questionContent) {
			this.questionContent = questionContent;
		}
		public Date getQuestionDate() {
			return questionDate;
		}
		public void setQuestionDate(Date questionDate) {
			this.questionDate = questionDate;
		}
		@Override
		public String toString() {
			return "QuestionEntity [questionNo=" + questionNo + ", questionLevel=" + questionLevel + ", questionRefNo="
					+ questionRefNo + ", questionMemberId=" + questionMemberId + ", questionProductNo="
					+ questionProductNo + ", questionTitle=" + questionTitle + ", questionContent=" + questionContent
					+ ", questionDate=" + questionDate + "]";
		}
		
		
}
