package com.chengchw.SHEA.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Provider")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "You should put Valid name")
	private String name;
	
	
	
	@NotBlank
	private String state;
	
	@Email(message = "You should put valid email")
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 5)
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
//	@NotBlank
//	private String phoneNumber;
	
	@OneToMany(mappedBy="owner", fetch = FetchType.LAZY)
	private List<Product> onShelfProducts;
	
	public Provider() {}

	public Provider(@NotBlank String name, @NotBlank String state,
			@Email @NotBlank String email, @NotBlank String password, String passwordConfirmation) {
		
		
		this.name = name;
		
		this.state = state;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		
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

	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public List<Product> getOnShelfProducts() {
		return onShelfProducts;
	}

	public void setOnShelfProducts(List<Product> onShelfProducts) {
		this.onShelfProducts = onShelfProducts;
	}
	
	
	
	
	
	

}
