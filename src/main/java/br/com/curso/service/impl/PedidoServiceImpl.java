package br.com.curso.service.impl;

import org.springframework.stereotype.Service;

import br.com.curso.repository.Pedidos;
import br.com.curso.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private Pedidos pedidos;

	public PedidoServiceImpl(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
