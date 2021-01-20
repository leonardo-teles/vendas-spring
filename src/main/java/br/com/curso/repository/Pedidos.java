package br.com.curso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.curso.domain.Cliente;
import br.com.curso.domain.Pedido;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente cliente);
	
	@Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.itensPedido WHERE p.id = :id")
	Optional<Pedido> findByIdFetchItensPedido(@Param("id") Integer id);
}
