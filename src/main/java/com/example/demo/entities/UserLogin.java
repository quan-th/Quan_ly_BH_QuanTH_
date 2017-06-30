/**
 * 
 */
package com.example.demo.entities;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author HP
 *
 */
public class UserLogin {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
