package org.springframework.ShoppingCart.dto;

public class ProductDto{
	
	private Long id;
    private String name;
    private Double price;
    
	public ProductDto() {
		super();
	}

	public ProductDto(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	} 
}