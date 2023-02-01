package com.sh.yespresso.product.model.dto;

import java.sql.Date;

public class ProductDetail {
	private int detailsNo;
	private String productAttachmentNo;
	private String productFilename;
	private String reProductFilename;
	private Date productFileDate;
	
	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetail(int detailsNo, String productAttachmentNo, String productFilename, String reProductFilename,
			Date productFileDate) {
		super();
		this.detailsNo = detailsNo;
		this.productAttachmentNo = productAttachmentNo;
		this.productFilename = productFilename;
		this.reProductFilename = reProductFilename;
		this.productFileDate = productFileDate;
	}

	public int getDetailsNo() {
		return detailsNo;
	}

	public void setDetailsNo(int detailsNo) {
		this.detailsNo = detailsNo;
	}

	public String getProductAttachmentNo() {
		return productAttachmentNo;
	}

	public void setProductAttachmentNo(String productAttachmentNo) {
		this.productAttachmentNo = productAttachmentNo;
	}

	public String getProductFilename() {
		return productFilename;
	}

	public void setProductFilename(String productFilename) {
		this.productFilename = productFilename;
	}

	public String getReProductFilename() {
		return reProductFilename;
	}

	public void setReProductFilename(String reProductFilename) {
		this.reProductFilename = reProductFilename;
	}

	public Date getProductFileDate() {
		return productFileDate;
	}

	public void setProductFileDate(Date productFileDate) {
		this.productFileDate = productFileDate;
	}

	@Override
	public String toString() {
		return "ProductDetail [detailsNo=" + detailsNo + ", productAttachmentNo=" + productAttachmentNo
				+ ", productFilename=" + productFilename + ", reProductFilename=" + reProductFilename
				+ ", productFileDate=" + productFileDate + "]";
	}

}
