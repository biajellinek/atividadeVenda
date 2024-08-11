package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entities.VendaEntity;

@Repository
public class VendaRepository {

	public interface VendaRepository extends JpaRepository<VendaEntity,Long>{
		
	}
	
	@Query("SELECT v FROM Venda WHERE v.cliente.nome")
	public List<VendaEntity> findByNomeCliente(@Param("nome") String nomeCliente) {
		return null;
	}
	
	@Query("SELECT v FROM Venda WHERE v.funcionario.nome")
	public List<VendaEntity> findByNomeFuncionario(@Param("nomeFuncionario")String nomeFuncionario) {
		return null;
	}
	
	
	
}
