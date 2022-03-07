package com.luizaLabs.Cadastro.reporsitory;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizaLabs.Cadastro.model.usuario;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Long>{

	public Optional<usuario> findByUsuario(String usuario);
	
}