package com.luizaLabs.Cadastro.model;

public class usuarioLogin {
	
	private long id;
	
	private String usuario = "magalu";
	
	private String senha = "m@galu123";
	
	private String token = "Basic dml0b3JAZW1haWwuY29tOjEyMzQ=";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}