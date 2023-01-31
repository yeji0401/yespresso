package com.sh.yespresso.review.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Review extends ReviewEntity {
	private int attachCnt;
	private List<ReviewAttachment> reviewAttachments = new ArrayList<>();

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewNo, String reviewMemberId, String reviewOrderNo, String productNo, String reviewTitle,
			String reviewContent, int reviewRating, Date reviewDate, int attachCnt) {
		super(reviewNo, reviewMemberId, reviewOrderNo, productNo, reviewTitle, reviewContent, reviewRating, reviewDate);
		this.attachCnt = attachCnt;
	}

	public Review(int reviewNo, String reviewMemberId, String reviewOrderNo, String productNo, String reviewTitle,
			String reviewContent, int reviewRating, Date reviewDate, List<ReviewAttachment> reviewAttachments) {
		super(reviewNo, reviewMemberId, reviewOrderNo, productNo, reviewTitle, reviewContent, reviewRating, reviewDate);
		this.reviewAttachments = reviewAttachments;
	}

	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}

	public List<ReviewAttachment> getReviewAttachment() {
		return reviewAttachments;
	}

	public void setReviewAttachment(List<ReviewAttachment> reviewAttachments) {
		this.reviewAttachments = reviewAttachments;
	}

	@Override
	public String toString() {
		return "Review [attachCnt=" + attachCnt + ", reviewAttachment=" + reviewAttachments  + ", toString()=" + super.toString() + "]";
	}

	/**
	 * review객체 생성시 초기화된 attachment에 Attachment객체를 추가하는 메소드
	 * 
	 * @param attach
	 */
	public void addAttachment(ReviewAttachment reviewAttach) {
		this.reviewAttachments.add(reviewAttach);
	}

}
