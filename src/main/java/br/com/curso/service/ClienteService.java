package br.com.curso.service;

import org.springframework.stereotype.Service;

import br.com.curso.model.Cliente;
import br.com.curso.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		this.repository.persistir(cliente);
	}
	
	public void validarCliente(Cliente cliente) {
		//aplica validações
	}
}
