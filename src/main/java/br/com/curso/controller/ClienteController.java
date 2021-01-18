package br.com.curso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@ResponseBody
	@PostMapping("/api/clientes")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientes.save(cliente);
		
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@ResponseBody
	@DeleteMapping("/api/clientes/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		
		if (cliente.isPresent()) {
			clientes.delete(cliente.get());
			
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@ResponseBody
	@PutMapping("/api/clientes/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return clientes.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clientes.save(cliente);
			
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/api/clientes")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		
		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clientes.findAll(example);
		
		return ResponseEntity.ok(lista);
	}
}
