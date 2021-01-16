package br.com.curso.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso.domain.Cliente;

@Repository
public class Clientes {

	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		entityManager.persist(cliente);
		
		return cliente;
	}
	
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		entityManager.merge(cliente);
		
		return cliente;
	}
	
	@Transactional
	public void deletar(Cliente cliente) {
		if (!entityManager.contains(cliente)) {
			cliente = entityManager.merge(cliente);
		}
		entityManager.remove(cliente);
	}
	
	@Transactional
	public void deletar(Integer id) {
		Cliente cliente = entityManager.find(Cliente.class, id);
		deletar(cliente);
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome like :nome";
		
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> obterTodos() {
		return entityManager.createQuery("FROM Cliente", Cliente.class).getResultList();
	}

}
