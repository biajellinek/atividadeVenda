package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Cliente;

//import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List <Cliente> findByCpfCliente(String cpfCliente);

    public List<Cliente> findByNomeCliente(String nomeCliente);
}