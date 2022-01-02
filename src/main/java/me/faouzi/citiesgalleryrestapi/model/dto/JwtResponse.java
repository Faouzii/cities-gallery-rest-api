package me.faouzi.citiesgalleryrestapi.model.dto;

import java.util.List;

public class JwtResponse {

	String token;
	Long id;
	String username;
	List<String> roles;

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String token, CustomUserDetails userDetails, List<String> roles) {
		super();
		this.token = token;
		this.id = userDetails.getId();
		this.username = userDetails.getUsername();
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
