package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.domain.Produto;

@Repository
public interface Produtos extends JpaRepository<Produto, Integer> {

}
