package com.luizaLabs.Cadastro.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizaLabs.Cadastro.model.usuario;
import com.luizaLabs.Cadastro.model.usuarioLogin;
import com.luizaLabs.Cadastro.reporsitory.usuarioRepository;
import com.luizaLabs.Cadastro.service.usuarioService;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class usuarioController {
	
	@Autowired
	private usuarioService userService;

	@Autowired
	private usuarioRepository userRepository;

	@GetMapping("/all")
	public ResponseEntity<List<usuario>> getAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@PostMapping("/logar")
	public ResponseEntity<usuarioLogin> login(@RequestBody Optional<usuarioLogin> user) {
		return userService.autenticarUsuario(user).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<usuario> GetById(@PathVariable long id) {
		return userRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<usuario> postUsuario(@Valid @RequestBody usuario user) {
		return userService.cadastrarUsuario(user)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}
