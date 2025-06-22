package app.controller;

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

import app.entity.Venda;
import app.service.VendaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/api/venda")
@CrossOrigin(origins = "*")
public class VendaController {

	@Autowired
	private VendaService vendaService;
	
	@PostMapping ("/save")
	public ResponseEntity<String> save (@Valid @RequestBody Venda venda){
		try {
			String mensagem = this.vendaService.save(venda);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Deu algo errado ao salvar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping ("/update/{idVenda}")
	public ResponseEntity<String> update (@RequestBody Venda venda, @PathVariable long idVenda){
		try {
			String mensagem = this.vendaService.update(venda, idVenda);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Deu algo errado ao alterar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping ("/delete/{idVenda}")
	public ResponseEntity<String> delete (@PathVariable long idVenda){
		try {
			String mensagem = this.vendaService.delete(idVenda);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Deu algo errado ao deletar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Venda>> findAll(){
		try {
			System.out.println("Chamando vendaService.findAll()");
			List <Venda> lista = this.vendaService.findAll();
			System.out.println("Resultado: " + lista);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findById/{idVenda}")
	public ResponseEntity<Venda> findById (@PathVariable long idVenda){
		try {
			Venda venda = this.vendaService.findById(idVenda);
			return new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
		}
	}
}