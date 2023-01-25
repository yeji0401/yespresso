package com.sh.yespresso.product.model.dto;

import java.sql.Date;

public class Product {
	private String productNo; // 제품번호
	private ProductCategory productCategory; // 카테고리번호 (ENUM)
	private String productName; // 제품명
	private int productPrice; // 가격
	private int productStock; // 재고
	private int productSaleCnt; // 판매량
	private Date productDate; // 제품등록일
	private String thumbnailFilename; // 썸네일파일
	
	private Type type; // 제품타입 (ENUM)
	private Aroma aroma; // 아로마 (ENUM)
	private int acidity; // 산미감
	private int roasting; // 강도
	private CupSize cupSize; // 컵사이즈 (ENUM)
	
	public Product() {
		super();
	}

	public Product(String productNo, ProductCategory productCategory, String productName, int productPrice,
			int productStock, int productSaleCnt, Date productDate, String thumbnailFilename, Type type, Aroma aroma,
			int acidity, int roasting, CupSize cupSize) {
		super();
		this.productNo = productNo;
		this.productCategory = productCategory;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productSaleCnt = productSaleCnt;
		this.productDate = productDate;
		this.thumbnailFilename = thumbnailFilename;
		this.type = type;
		this.aroma = aroma;
		this.acidity = acidity;
		this.roasting = roasting;
		this.cupSize = cupSize;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getProductSaleCnt() {
		return productSaleCnt;
	}

	public void setProductSaleCnt(int productSaleCnt) {
		this.productSaleCnt = productSaleCnt;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getThumbnailFilename() {
		return thumbnailFilename;
	}

	public void setThumbnailFilename(String thumbnailFilename) {
		this.thumbnailFilename = thumbnailFilename;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Aroma getAroma() {
		return aroma;
	}

	public void setAroma(Aroma aroma) {
		this.aroma = aroma;
	}

	public int getAcidity() {
		return acidity;
	}

	public void setAcidity(int acidity) {
		this.acidity = acidity;
	}

	public int getRoasting() {
		return roasting;
	}

	public void setRoasting(int roasting) {
		this.roasting = roasting;
	}

	public CupSize getCupSize() {
		return cupSize;
	}

	public void setCupSize(CupSize cupSize) {
		this.cupSize = cupSize;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productCategory=" + productCategory + ", productName="
				+ productName + ", productPrice=" + productPrice + ", productStock=" + productStock
				+ ", productSaleCnt=" + productSaleCnt + ", productDate=" + productDate + ", thumbnailFilename="
				+ thumbnailFilename + ", type=" + type + ", aroma=" + aroma + ", acidity=" + acidity + ", roasting="
				+ roasting + ", cupSize=" + cupSize + "]";
	}
	
	
}
