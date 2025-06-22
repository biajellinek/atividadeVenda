package app.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import app.entity.Animal;
import app.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;

	public String save(Animal animal) {
		this.animalRepository.save(animal);
		return "Animal salvo com sucesso!";
	}

	public String update(Animal animal, long idAnimal) {
		animal.setIdAnimal(idAnimal);
		this.animalRepository.save(animal);
		return "Animal alterado com sucesso!";
	}

	public String delete(long idAnimal) {
		this.animalRepository.deleteById(idAnimal);
		return "Animal deletado com sucesso!";
	}

//	public List<Animal> findAll() {
//		List <Animal> lista = this.animalRepository.findAll();
//		return lista;
//	}
	
	public Page<Animal> findAll(int numPaginaAtual){
		int totalPorPagina = 2;
		PageRequest pageRequest = PageRequest.of(numPaginaAtual-1, totalPorPagina);
		return this.animalRepository.findAll(pageRequest);
	}

	public Animal findById(long idAnimal) {
		Animal animal = this.animalRepository.findById(idAnimal).get();
			return animal;
	}
	
	
	//RELACIONAMENTO DANDO ERRO, ARRUMAR E NOS ENTITYS TAMBEM
	
	public List <Animal> findByTipoAnimal (String tipoAnimal) {
		return this.animalRepository.findByTipoAnimal(tipoAnimal);
	}
	
	public List <Animal> findByIdCliente (long idCliente) {
	    return this.animalRepository.findByClienteId(idCliente); 
	}
}