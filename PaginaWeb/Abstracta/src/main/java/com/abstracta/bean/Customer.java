package com.abstracta.bean;

//Generated 02/12/2013 10:07:39 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.annotation.DateTimeFormat;

/**
* Cart generated by hbm2java
*/
public class Customer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Sex { 
		
		FEMALE("F"), 
		MALE("M");
		
		private String code;
		
		Sex(String code){	
			 this.code = code;
		}
		
	    public String getCode() {
	        return code;
	    }	
	};

	private int customerId;
	
	@Size(min=8,max=20)	
	private String password;
	
	@Size(min=8,max=20)	
	private String confirmPassword;
	
	@AssertTrue
	public boolean isPasswordMatch() {
		
		if(this.password!=null && !this.password.isEmpty() 
				&& this.confirmPassword!=null && !this.confirmPassword.isEmpty())
			return this.password.equals(this.confirmPassword);
		else
			return true;
	}
	
	@NotEmpty
	@Pattern(regexp = "^[0-9]*$")
	private String identityCard;
	
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z_�����������[ ]]*$")
	private String name;
	
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z_�����������[ ]]*$")
	private String lastname;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Email
	private String confirmEmail;
	
	@AssertTrue
	public boolean isEmailMatch() {
		
		if(this.email!=null && this.confirmEmail!=null)
			return this.email.equals(this.confirmEmail);
		else
			return true;
	}
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Past
	private Date birthdate;
	
	@NotEmpty
	private String sex;
	
	@NotEmpty
	private String phone;
	
	@AssertTrue
	private boolean agreement;
	private boolean newsletter;
	
	private String role;
	private char active;
	private String username;
	private Date created;
	private String confirmToken;
	private String lastIp;
	private Date lastLogin;
	private String passwordResetToken;
	private Date passwordResetExp;

	public Customer() {
		this.newsletter = true;
	}

	public Customer(String username, String password, String name, String lastname,
			String email, Date created) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.created = created;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public boolean isAgreement() {
		return agreement;
	}

	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getConfirmToken() {
		return confirmToken;
	}

	public void setConfirmToken(String confirmToken) {
		this.confirmToken = confirmToken;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public Date getPasswordResetExp() {
		return passwordResetExp;
	}

	public void setPasswordResetExp(Date passwordResetExp) {
		this.passwordResetExp = passwordResetExp;
	}

	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
}