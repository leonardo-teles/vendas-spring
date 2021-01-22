package br.com.curso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso.domain.Usuario;
import br.com.curso.repository.Usuarios;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salavar(Usuario usuario) {
		return usuarios.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarios.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
		
		String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}

}
