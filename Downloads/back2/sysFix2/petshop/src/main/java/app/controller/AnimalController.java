package app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import app.entity.Animal;
import app.repository.AnimalRepository;
import app.repository.ClienteRepository;
import app.repository.FuncionarioRepository;
import app.service.AnimalService;


@RestController
@RequestMapping("/api/animal")
@CrossOrigin(origins = "*")
public class AnimalController {
	
	@Autowired
	private AnimalService animalService;
	//animal, cliente e funcionario repository
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Animal animal){
			String mensagem = this.animalService.save(animal);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	}
	
	@PutMapping ("/update/{idAnimal}")
	public ResponseEntity<String> update (@RequestBody Animal animal, @PathVariable("idAnimal") long idAnimal){
			String mensagem = this.animalService.update(animal, idAnimal);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{idAnimal}")//jn//dfgdffdgdf
	public ResponseEntity<String> delete (@PathVariable ("idAnimal") long idAnimal){
			String mensagem = this.animalService.delete(idAnimal);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
	}
	
//	@GetMapping("/findAll")
//	public ResponseEntity<List<Animal>> findAll(){
//			List <Animal> lista = this.animalService.findAll();
//			return new ResponseEntity<>(lista, HttpStatus.OK);
//	}
	
	@GetMapping("/findAll/{numPaginaAtual}")
	public ResponseEntity<Page<Animal>> findAll(@PathVariable("numPaginaAtual")int numPaginaAtual){
		Page<Animal> lista = this.animalService.findAll(numPaginaAtual);
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	//sdfsdfbbfd
	@GetMapping ("/findById/{idAnimal}")
	public ResponseEntity<Animal> findById (@PathVariable ("idAnimal") long idAnimal){
			Animal animal = this.animalService.findById(idAnimal);
			return new ResponseEntity<>(animal, HttpStatus.OK);
	}
	
	@GetMapping ("/findByTipoAnimal")
	public ResponseEntity<List<Animal>> findByTipoAnimal(@RequestParam String tipoAnimal){
		try {
			List <Animal> lista = this.animalService.findByTipoAnimal(tipoAnimal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping ("/findByIdCliente")
	public ResponseEntity<List<Animal>> findByIdCliente(@RequestParam Long idCliente){
		try {
			List <Animal> lista = this.animalService.findByIdCliente(idCliente);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//ADICIONAR UM NOVO ARQUIVO SOMENTE PARA A DASHBOARD POSTERIORMENTE
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/dashboard")
    public Map<String, Long> getDashboardTotais() { //count mostra o numero de registros da entidade
        long totalAnimais = animalRepository.count();
        long totalClientes = clienteRepository.count();
        long totalFuncionarios = funcionarioRepository.count();

        Map<String, Long> response = new HashMap<>();
        response.put("totalAnimais", totalAnimais);
        response.put("totalClientes", totalClientes);
        response.put("totalFuncionarios", totalFuncionarios);

        return response;
    }

	
}
