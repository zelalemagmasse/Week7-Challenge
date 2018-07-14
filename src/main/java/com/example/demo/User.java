package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;

	@Column(name = "password", nullable = false)
	@NotEmpty(message = "Please provide a password")
	private String password;

	@Column(name = "userName", nullable = false, unique = true)
	@NotEmpty(message = "Please provide a user Name")
	private String userName;

	public Set<Role> getRoleOfUsers() {
		return roleOfUsers;
	}

	private boolean suspended;

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", userName='"
				+ userName + '\'' + ", roleOfUsers=" + roleOfUsers + '}';
	}

	public void setRoleOfUsers(Set<Role> roleOfUsers) {
		this.roleOfUsers = roleOfUsers;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roleOfUsers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User() {

		this.roleOfUsers = new HashSet<>();
	}

	public void addRole(Role rr) {
		this.roleOfUsers.add(rr);
	}

	public boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}



}
