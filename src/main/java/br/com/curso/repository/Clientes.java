package br.com.curso.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.curso.domain.Cliente;

@Repository
public class Clientes {

	private static final String INSERT = "INSERT INTO clientes(nome) VALUES(?)";
	private static final String SELECT_ALL = "SELECT * FROM clientes";
	private static final String UPDATE = "UPDATE clientes SET nome = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM clientes WHERE id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Cliente salvar(Cliente cliente) {
		jdbcTemplate.update(INSERT, new Object[] {cliente.getNome()} );
		
		return cliente;
	}
	
	public Cliente atualizar(Cliente cliente) {
		jdbcTemplate.update(UPDATE, new Object[] {cliente.getNome(), cliente.getId()});
		
		return cliente;
	}
	
	public void deletar(Cliente cliente) {
		deletar(cliente.getId());
	}
	
	public void deletar(Integer id) {
		jdbcTemplate.update(DELETE, new Object[] {id});
	}
	
	public List<Cliente> buscarPorNome(String nome) {
		return jdbcTemplate.query(SELECT_ALL.concat(" WHERE nome LIKE ?"),
				new Object[] {"%" + nome + "%"},
				obterMapper());
	}
	
	public List<Cliente> obterTodos() {
		return jdbcTemplate.query(SELECT_ALL, obterMapper());
	}

	private RowMapper<Cliente> obterMapper() {
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				String nome = rs.getString("nome");
				
				return new Cliente(id, nome);
			}
		};
	}
}
