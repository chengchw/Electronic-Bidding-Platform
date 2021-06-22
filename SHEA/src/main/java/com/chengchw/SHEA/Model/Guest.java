package com.chengchw.SHEA.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Guest")
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Email(message = "Email is mandatory")
	 
	private String email;
	
	@Size(min = 5, message = "Password must be greater than 5 characters")
	
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "guest_product",
			   joinColumns = @JoinColumn(name = "guest_id"),
			   inverseJoinColumns = @JoinColumn(name = "product_id"))
	
	private List<Product> bidProducts;
	
	public Guest() {}

	public Guest(@NotBlank String name, @Email @NotBlank String email, @Size(min = 8) @NotBlank String password,
			String passwordConfirmation, List<Product> bidProducts) {
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.bidProducts = bidProducts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public List<Product> getBidProducts() {
		return bidProducts;
	}

	public void setBidProducts(List<Product> bidProducts) {
		this.bidProducts = bidProducts;
	}
	
	
	
	
	
	

}
