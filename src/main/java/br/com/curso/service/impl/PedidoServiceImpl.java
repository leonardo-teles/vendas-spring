package br.com.curso.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso.domain.Cliente;
import br.com.curso.domain.ItemPedido;
import br.com.curso.domain.Pedido;
import br.com.curso.domain.Produto;
import br.com.curso.dto.ItemPedidoDTO;
import br.com.curso.dto.PedidoDTO;
import br.com.curso.exception.RegraNegocioException;
import br.com.curso.repository.Clientes;
import br.com.curso.repository.ItensPedido;
import br.com.curso.repository.Pedidos;
import br.com.curso.repository.Produtos;
import br.com.curso.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	private final Pedidos pedidos;
	
	private final Clientes clientes;
	
	private final Produtos produtos;
	
	private final ItensPedido itens;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente =  clientes.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
		pedidos.save(pedido);
		itens.saveAll(itensPedido);
		pedido.setItensPedido(itensPedido);
		
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
		if (itens.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
		}
		
		return itens.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtos.findById(idProduto).orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));
			
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			
			return itemPedido;
		}).collect(Collectors.toList());
	}
	
}
