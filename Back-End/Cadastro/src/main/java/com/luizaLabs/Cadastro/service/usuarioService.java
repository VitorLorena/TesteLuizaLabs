package com.luizaLabs.Cadastro.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luizaLabs.Cadastro.model.usuario;
import com.luizaLabs.Cadastro.model.usuarioLogin;
import com.luizaLabs.Cadastro.reporsitory.usuarioRepository;


@Service
public class usuarioService {
	
	@Autowired
	private usuarioRepository userRepository;

	public Optional<usuario> cadastrarUsuario(usuario user) {

		if (userRepository.findByUsuario(user.getUsuario()).isPresent())
			return Optional.empty();
		user.setSenha(criptografarSenha(user.getSenha()));
		return Optional.of(userRepository.save(user));
	}
	
	public Optional<usuarioLogin> autenticarUsuario(Optional<usuarioLogin> usuarioLogin) {

		Optional<usuario> usuario = userRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {

			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				
				return usuarioLogin;
			}
		}
		
		return Optional.empty();
	}

	private String criptografarSenha(String password) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(password);
	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		
		return "Basic " + new String(tokenBase64);
	}

}
