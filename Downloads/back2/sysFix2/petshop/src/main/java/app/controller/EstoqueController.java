package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Estoque;
import app.service.EstoqueService;

@RestController
@RequestMapping ("api/estoque")
@CrossOrigin(origins = "*")
public class EstoqueController {

	@Autowired
	private EstoqueService estoqueService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Estoque estoque){
		try {
			String mensagem = this.estoqueService.save(estoque);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Deu algo errado ao salvar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{idEstoque}")
	public ResponseEntity<String> update (@RequestBody Estoque estoque, @PathVariable long idEstoque){
		try {
			String mensagem = this.estoqueService.update(estoque, idEstoque);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu algo errado ao alterar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping ("/delete/{idEstoque}")
	public ResponseEntity<String> delete (@PathVariable long idEstoque){
		try {
			String mensagem = this.estoqueService.delete(idEstoque);
			return new ResponseEntity<String> (mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu algo errado ao deletar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping ("/findAll")
	public ResponseEntity<List<Estoque>> findAll(){
		try {
			List <Estoque> lista = this.estoqueService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping ("/findById/{idEstoque}")
	public ResponseEntity<Estoque> findById(@PathVariable long idEstoque){
		try {
			Estoque estoque = this.estoqueService.findById(idEstoque);
			return new ResponseEntity<> (estoque, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/estoqueBaixo/{quantidadeMinima}")
	public ResponseEntity<List<Estoque>> buscarProdutosComEstoqueBaixo(@PathVariable Long quantidadeMinima) {
	    try {
	        List<Estoque> produtos = estoqueService.buscarProdutosComEstoqueBaixo(quantidadeMinima);
	        return new ResponseEntity<>(produtos, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}

	
	@GetMapping ("/findByNomeProduto")
	public ResponseEntity<List<Estoque>> findByNomeProduto(@RequestParam String nomeProduto){
		try {
			List <Estoque> lista = this.estoqueService.findByNomeProduto(nomeProduto);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping ("/findByTipoProduto")
	public ResponseEntity<List<Estoque>> findByTipoProduto(@RequestParam String tipoProduto){
		try {
			List <Estoque> lista = this.estoqueService.findByTipoProduto(tipoProduto);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
}
