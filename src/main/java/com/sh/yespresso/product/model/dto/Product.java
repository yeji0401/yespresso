package com.sh.yespresso.product.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Product extends ProductEntity {
    
    private List<ProductDetail> details = new ArrayList<>();

    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Product(String productNo, ProductCategory productCategory, String productName, int productPrice,
            int productStock, int productSaleCnt, Date productDate, String thumbnailFilename, Type type, Aroma aroma,
            int acidity, int roasting, CupSize cupSize) {
        super(productNo, productCategory, productName, productPrice, productStock, productSaleCnt, productDate,
                thumbnailFilename, type, aroma, acidity, roasting, cupSize);
        // TODO Auto-generated constructor stub
    }

    public Product(String productNo, ProductCategory productCategory, String productName, int productPrice,
            int productStock, int productSaleCnt, Date productDate, String thumbnailFilename, Type type, Aroma aroma,
            int acidity, int roasting, CupSize cupSize, List<ProductDetail> details) {
        super(productNo, productCategory, productName, productPrice, productStock, productSaleCnt, productDate,
                thumbnailFilename, type, aroma, acidity, roasting, cupSize);
        this.details = details;
    }

    public List<ProductDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetail> details) {
        this.details = details;
    }
    

    @Override
    public String toString() {
        return "Product [details=" + details + ", toString()=" + super.toString() +"]";
    }

    
    public void addDetails(ProductDetail detail) {
        this.details.add(detail);
    }

	public Product(Type type, Aroma aroma, int acidity, int roasting, CupSize cupSize) {
		super(type, aroma, acidity, roasting, cupSize);
		// TODO Auto-generated constructor stub
	}
    
}