package br.com.curso.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.curso.domain.Produto;
import br.com.curso.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private Produtos produtos;

	public ProdutoController(Produtos produtos) {
		this.produtos = produtos;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Produto save(@RequestBody @Valid Produto produto) {
		return produtos.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
		produtos
			.findById(id)
			.map(p -> {
				produto.setId(p.getId());
				produtos.save(produto);
				
				return produto;
			}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtos
		.findById(id)
		.map(p -> {
			produtos.delete(p);
			
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
		
	}
	
	@GetMapping("{id}")
	public Produto getById(@PathVariable Integer id) {
		return produtos
			.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
	}
	
	@GetMapping
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		
		Example example = Example.of(filtro, matcher);
		
		return produtos.findAll(example);
	}
	
}
