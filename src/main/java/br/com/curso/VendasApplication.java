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
			clientes.salvar(new Cliente("Leonardo"));
			clientes.salvar(new Cliente("Manuela"));
			
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
