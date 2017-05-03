package neu.edu.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO) 	
    private Long id;
	
	private double total;
	private boolean status;
	private ArrayList<Long> productId;

	@ManyToMany(cascade = {CascadeType.ALL},targetEntity=Products.class, fetch = FetchType.EAGER)
	@JoinTable (name="order_products",joinColumns={@JoinColumn(name="order_ID")}, inverseJoinColumns={@JoinColumn(name="products_ID")} )
	private Set<Products> products;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_ID")
	private User user;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getTotal() {
		total = 0;
		if(products != null){
			for(Products p: products ){
				this.total = this.total + p.getPrice();
			}
		}
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public  Set<Products> getProductsUtil(){
		if(this.products == null){
			products = new HashSet<Products>();
		}
		return this.products;
	}
	
	public List<Products> getProducts() {
		List<Products> allProducts = new ArrayList<Products>(getProductsUtil());
		return allProducts;
	}
	
	public void setProducts(Set<Products> products) {
		this.products = products;
	}
	
	public void addProducts(Products p){
		Set<Products> d = getProductsUtil();
		d.add(p);
	}
	
	public int numOfProducts(){
		return getProductsUtil().size();
	}
	
	public ArrayList<Long> getProductId() {
		if(productId == null){
			this.productId = new ArrayList<Long>();
		}
		return productId;
	}
	
	public void setProductId(ArrayList<Long> productId) {
		this.productId = productId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
