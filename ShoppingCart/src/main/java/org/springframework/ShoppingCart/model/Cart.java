package org.springframework.ShoppingCart.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@OneToOne
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    
    public Cart() {
    }

	public Cart(User user) {
        this.user = user;
        this.products = new ArrayList<>();
    }

    public Long getId() {
		return this.id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
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

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", products=" + products + "]";
	}

}