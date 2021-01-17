package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.domain.Pedido;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

}
