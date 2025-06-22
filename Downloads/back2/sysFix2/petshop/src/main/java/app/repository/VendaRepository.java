package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entity.Venda;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	//ARRUMAR DPS
	public List<Venda> findByClienteIdCliente(Long idCliente);

    public List<Venda> findByFuncionarioIdFuncionario(Long idFuncionario);
}