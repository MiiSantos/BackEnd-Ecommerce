package com.cultivar.armazemNatural.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cultivar.armazemNatural.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail (String usuario);
}
