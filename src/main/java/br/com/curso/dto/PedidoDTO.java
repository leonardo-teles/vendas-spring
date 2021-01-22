package br.com.curso.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.curso.validation.NotEmptList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	@NotNull(message = "Informe o código do cliente.")
	private Integer cliente;
	
	@NotNull(message = "Campo total do pedido é obrigatório.")
	private BigDecimal total;

	@NotEmptList(message = "Pedido não pode ser realizado sem itens.")
	private List<ItemPedidoDTO> itens;

}
