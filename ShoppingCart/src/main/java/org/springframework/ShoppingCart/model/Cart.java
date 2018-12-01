package org.springframework.ShoppingCart.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Cart {

    private Long id;
    private List<Product> products;
    
    public Cart(Long id, String content) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		if(this.products.contains(product)) {
			Product inCart = this.findProduct(product);
			inCart.addOne();
		}
		else {
			this.products.add(product);
		}
	}
	
//	public void addMany(Product product, int quantity) {
//		if(this.products.contains(product)) {
//			Product inCart = this.findProduct(product);
//			inCart.addMany(quantity);
//		}
//		else {
//			product.setQuantity(quantity);
//			this.products.add(product);
//		}
//	}
	
	public boolean removeProduct(Product product) {
		return (this.products.remove(product));
	}
	
	public Product findProduct(Product product) {
		Iterator<Product> it = this.products.iterator();
		while(it.hasNext()) {
			Product inCart = it.next();
			if(inCart.equals(product)) {
				return inCart;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (id != other.id)
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

}