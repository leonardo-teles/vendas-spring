package br.com.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.curso.domain.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

	//@Query("SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
	@Query(value = "SELECT * FROM clientes c WHERE c.nome LIKE '%:nome%'", nativeQuery = true) //query nativa
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	@Query("DELETE FROM Cliente c WHERE c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);
	
	boolean existsByNome(String nome);
	
	@Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.id = :id")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
