package br.com.curso;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.curso.domain.Cliente;
import br.com.curso.domain.Pedido;
import br.com.curso.repository.Clientes;
import br.com.curso.repository.Pedidos;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(
			
			@Autowired 
			Clientes clientes,
			
			@Autowired
			Pedidos pedidos			
	) {
		return args -> {
			
			System.out.println("Salvando clientes");
			
			Cliente c = new Cliente("Leonardo");
			clientes.save(c);
			
			Pedido p = new Pedido();
			p.setCliente(c);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidos.save(p);			
			
			/*
			 * Cliente cliente = clientes.findClienteFetchPedidos(c.getId());
			 * 
			 * System.out.println(cliente); System.out.println(cliente.getPedidos());
			 */
			pedidos.findByCliente(c).forEach(System.out::println);
			
			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
