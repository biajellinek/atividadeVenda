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
import org.springframework.web.bind.annotation.RestController;

import app.entity.Cliente;
import app.entity.Funcionario;
import app.service.FuncionarioService;


@RestController
@RequestMapping("api/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Funcionario funcionario){
		try {
			String mensagem = this.funcionarioService.save(funcionario);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Deu algo errado ao salvar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping ("/update/{idFuncionario}")
	public ResponseEntity<String> update (@RequestBody Funcionario funcionario, @PathVariable long idFuncionario){
		try {
			String mensagem = this.funcionarioService.update(funcionario, idFuncionario);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu algo errado ao alterar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping ("/delete/{idFuncionario}")
	public ResponseEntity<String> delete (@PathVariable long idFuncionario){
		try {
			String mensagem = this.funcionarioService.delete(idFuncionario);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Deu algo errado ao deletar!", HttpStatus.BAD_REQUEST);
		}
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Funcionario>> findAll(){
		try {
			List <Funcionario> lista = this.funcionarioService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping ("/findById/{idFuncionario}")
	public ResponseEntity<Funcionario> findById(@PathVariable long idFuncionario){
		try {
			Funcionario funcionario = this.funcionarioService.findById(idFuncionario);
			return new ResponseEntity<>(funcionario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByCpfFuncionario")
    public ResponseEntity<List<Funcionario>> getFuncionarioByCpf(@PathVariable String cpfFuncionario) {
        List<Funcionario> funcionarios = funcionarioService.findByCpfFuncionario(cpfFuncionario);
		try {
			List <Funcionario> lista = funcionarioService.findByCpfFuncionario(cpfFuncionario);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    }

    @GetMapping("/findByNomeFuncionario")
    public ResponseEntity<List<Funcionario>> getFuncionarioByNome(@RequestParam String nomeFuncionario) {
        List<Funcionario> funcionarios = funcionarioService.findByNomeFuncionario(nomeFuncionario);
		try {
			List <Funcionario> lista = this.funcionarioService.findByNomeFuncionario(nomeFuncionario);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}    }
	
}