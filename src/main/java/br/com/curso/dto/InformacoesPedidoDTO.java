package br.com.curso.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacoesPedidoDTO {

	private Integer codigo;
	
	private String cpf;
	
	private String nomeCliente;
	
	private BigDecimal total;
	
	private String dataPedido;
	
	private List<InformacaoItemPedidoDTO> itens;
}
