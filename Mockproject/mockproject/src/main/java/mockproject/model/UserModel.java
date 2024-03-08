package mockproject.model;

import java.sql.Timestamp;

public class UserModel {
	private int id; 
	private String username; 
	private String password; 
	private String password2;
	private String role; 
	private String email; 
	private String full_name; 
	private String phone; 
	private Timestamp created_at; 
	private Timestamp updated_at;
	
	public UserModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public String getFull_name() {
		return full_name;
	}

	public String getPhone() {
		return phone;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	
}