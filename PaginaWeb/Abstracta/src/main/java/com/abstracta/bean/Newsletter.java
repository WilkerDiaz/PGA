package com.abstracta.bean;

import java.util.Date;


public class Newsletter {	
	
	private int newsletterId;
	private String email;
	
	private char active;
	
	private Date created;
	private Date unsubscribed_date;
		
	public Newsletter() {

	}

	public Newsletter(String email, boolean active, Date created) {
		
		this.email = email;
		this.active = (char)(active?'Y':'N');
		this.created = created;
	}
	
	public Newsletter(int newsletterId, String email, boolean active,
			Date created, Date unsubscribed_date) {
		
		this.newsletterId = newsletterId;
		this.email = email;
		this.active = (char)(active?'Y':'N');
		this.created = created;
		this.unsubscribed_date = unsubscribed_date;
	}

	public int getNewsletterId() {
		return newsletterId;
	}

	public void setNewsletterId(int newsletterId) {
		this.newsletterId = newsletterId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUnsubscribed_date() {
		return unsubscribed_date;
	}

	public void setUnsubscribed_date(Date unsubscribed_date) {
		this.unsubscribed_date = unsubscribed_date;
	}
}
