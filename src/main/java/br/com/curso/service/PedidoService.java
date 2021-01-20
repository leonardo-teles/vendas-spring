package br.com.curso.service;

import br.com.curso.domain.Pedido;
import br.com.curso.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
}
