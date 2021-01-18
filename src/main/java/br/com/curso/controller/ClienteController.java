package br.com.curso.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.curso.domain.Cliente;
import br.com.curso.repository.Clientes;

@Controller
public class ClienteController {

	private Clientes clientes;
	
	public ClienteController(Clientes clientes) {
		this.clientes = clientes;
	}

	@ResponseBody
	@GetMapping("/api/clientes/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
		
		
		
	}
}
