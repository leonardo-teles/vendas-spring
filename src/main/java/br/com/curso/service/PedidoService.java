package br.com.curso.service;

import java.util.Optional;

import br.com.curso.domain.Pedido;
import br.com.curso.dto.PedidoDTO;
import br.com.curso.enums.StatusPedido;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizarStatus(Integer id, StatusPedido status);
}
