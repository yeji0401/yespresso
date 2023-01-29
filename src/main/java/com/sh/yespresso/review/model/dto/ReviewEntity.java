package com.sh.yespresso.review.model.dto;

import java.sql.Date;

public class ReviewEntity {
	private int reviewNo;
	private String reviewMemberId;
	private String reviewOrderNo;
	private String productNo;
	private String reviewTitle;
	private String reviewContent;
	private int reviewRating;
	private Date reviewDate;

	public ReviewEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewEntity(int reviewNo, String reviewMemberId, String reviewOrderNo, String productNo, String reviewTitle,
			String reviewContent, int reviewRating, Date reviewDate) {
		super();
		this.reviewNo = reviewNo;
		this.reviewMemberId = reviewMemberId;
		this.reviewOrderNo = reviewOrderNo;
		this.productNo = productNo;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewMemberId() {
		return reviewMemberId;
	}

	public void setReviewMemberId(String reviewMemberId) {
		this.reviewMemberId = reviewMemberId;
	}

	public String getReviewOrderNo() {
		return reviewOrderNo;
	}

	public void setReviewOrderNo(String reviewOrderNo) {
		this.reviewOrderNo = reviewOrderNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "ReviewEntity [reviewNo=" + reviewNo + ", reviewMemberId=" + reviewMemberId + ", reviewOrderNo="
				+ reviewOrderNo + ", productNo=" + productNo + ", reviewTitle=" + reviewTitle + ", reviewContent="
				+ reviewContent + ", reviewRating=" + reviewRating + ", reviewDate=" + reviewDate + "]";
	}

}
