package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.domain.ItemPedido;

@Repository
public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {

}
