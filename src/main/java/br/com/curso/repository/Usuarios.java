package br.com.curso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.domain.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByLogin(String login);
}
