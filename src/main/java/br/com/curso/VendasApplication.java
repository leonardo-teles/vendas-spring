package br.com.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.curso.domain.Cliente;
import br.com.curso.repository.Clientes;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			
			System.out.println("Salvando clientes");
			clientes.save(new Cliente("Leonard"));
			clientes.save(new Cliente("Manuela"));
			
			List<Cliente> resultado = clientes.encontrarPorNome("Leonardo");
			resultado.forEach(System.out::println);
			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
