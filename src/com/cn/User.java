package com.cn;
/**
 *  ≥Ã¡‹  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
public class User {

	public User() {

	}

	public User(String username, String password, String gender, String telephone) {
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.telephone = telephone;
	}



	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private String username;
	private String password;
	private String gender;
	private String telephone;

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", gender='" + gender + '\''
				+ ", telephone='" + telephone + '\'' + '}';
	}
}
