package br.com.curso.controller;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.domain.Usuario;
import br.com.curso.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioServiceImpl usuarioService;
	
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada =  passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		return usuarioService.salavar(usuario);
	}
	
}
