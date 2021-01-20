package br.com.curso.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoItemPedidoDTO {

	private String descricaoProduto;
	
	private BigDecimal precoUnitario;
	
	private Integer quantidade;
}
