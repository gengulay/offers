package com.gengulay.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gengulay.spring.validation.constraints.ValidEmail;

public class Offer {

	private int id;

	@NotNull
	@Size(min = 5, max = 100, message = "name must be between 5 and 100 characters")
	private String name;

	@NotNull
	@Size(min = 20, max = 255, message = "text must be between 20 and 255 characters")
	private String text;

	@NotNull
	// @Pattern(regexp = ".*\\@.*\\..*", message = "not valid email")
	@ValidEmail(min = 5, message = "this email add is a blunder")
	private String email;

	public Offer() {

	}

	public Offer(String name, String text, String email) {
		this.name = name;
		this.text = text;
		this.email = email;
	}

	public Offer(int id, String name, String text, String email) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", text=" + text + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Offer other = (Offer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
