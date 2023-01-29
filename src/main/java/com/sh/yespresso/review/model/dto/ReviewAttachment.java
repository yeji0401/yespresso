package com.sh.yespresso.review.model.dto;

import java.sql.Date;

public class ReviewAttachment {
	private int reviewFileNo;
	private int reviewNo;
	private String reviewFileName;
	private String reReviewFileName;
	private Date reviewFileDate;

	public ReviewAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewAttachment(int reviewFileNo, int reviewNo, String reviewFileName, String reReviewFileName,
			Date reviewFileDate) {
		super();
		this.reviewFileNo = reviewFileNo;
		this.reviewNo = reviewNo;
		this.reviewFileName = reviewFileName;
		this.reReviewFileName = reReviewFileName;
		this.reviewFileDate = reviewFileDate;
	}

	public int getReviewFileNo() {
		return reviewFileNo;
	}

	public void setReviewFileNo(int reviewFileNo) {
		this.reviewFileNo = reviewFileNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewFileName() {
		return reviewFileName;
	}

	public void setReviewFileName(String reviewFileName) {
		this.reviewFileName = reviewFileName;
	}

	public String getReReviewFileName() {
		return reReviewFileName;
	}

	public void setReReviewFileName(String reReviewFileName) {
		this.reReviewFileName = reReviewFileName;
	}

	public Date getReviewFileDate() {
		return reviewFileDate;
	}

	public void setReviewFileDate(Date reviewFileDate) {
		this.reviewFileDate = reviewFileDate;
	}

	@Override
	public String toString() {
		return "ReviewAttachment [reviewFileNo=" + reviewFileNo + ", reviewNo=" + reviewNo + ", reviewFileName="
				+ reviewFileName + ", reReviewFileName=" + reReviewFileName + ", reviewFileDate=" + reviewFileDate
				+ "]";
	}
}
