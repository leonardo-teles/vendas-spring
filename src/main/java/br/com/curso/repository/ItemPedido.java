package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedido extends JpaRepository<ItemPedido, Integer> {

}
