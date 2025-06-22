package app.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    public List<Animal> findByTipoAnimal(String tipoAnimal); // ARURMAR DPS

    //public List<Animal> findByIdCliente(long idCliente); -errado

	//List <Animal> findByIdCliente(long idCliente); //certo
	
	@Query("SELECT a FROM Animal a WHERE a.cliente.id = :clienteId")
	List<Animal> findByClienteId(@Param("clienteId")long clienteId);
}