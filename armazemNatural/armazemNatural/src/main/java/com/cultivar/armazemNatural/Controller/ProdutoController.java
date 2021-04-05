package com.cultivar.armazemNatural.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultivar.armazemNatural.model.Produto;
import com.cultivar.armazemNatural.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	@GetMapping
	public ResponseEntity<List<Produto>>getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> getByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Produto>> getByMarca(@PathVariable String marca){
		return ResponseEntity.ok(repository.findAllByMarcaContainingIgnoreCase(marca));
	}
	
	@GetMapping("/fornecedor/{fornecedor}")
	public ResponseEntity<List<Produto>> getByFornecedor(@PathVariable String fornecedor){
		return ResponseEntity.ok(repository.findAllByFornecedorContainingIgnoreCase(fornecedor));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void  delete(@PathVariable long id){
		repository.deleteById(id);
	}
}