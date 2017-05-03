package neu.edu.project.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private Long id;

	@NotEmpty
	@Column(name="userName",unique=true, length=45)
	private String userName;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String role;
	
	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;
	
	@Transient
	private MultipartFile photo;
	
	@Lob
	private byte[] photoBytes;
	
	private String photoContentType; 
	
	private String photoFilename;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user", targetEntity=Order.class)  //, fetch = FetchType.EAGER
	private Set<Order> orders;

	@OneToOne(mappedBy="user")   //  cascade=CascadeType.ALL,
                                                    //	@PrimaryKeyJoinColumn(name="cart_ID")
	private Cart cart;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
		setPhotoContentType(photo.getContentType());
		setPhotoFilename(photo.getOriginalFilename());
		try {
			setPhotoBytes(photo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getPhotoFilename() {
		return photoFilename;
	}

	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Long assignId() {
		this.id = idSequence.incrementAndGet();
		return id;
	}
	
	private static final AtomicLong idSequence = new AtomicLong();

}
