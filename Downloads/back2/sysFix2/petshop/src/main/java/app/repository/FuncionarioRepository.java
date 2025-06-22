package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entity.Funcionario;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	
	//ARRUMAR DPS
    public List<Funcionario> findByNomeFuncionario(String nomeFuncionario);

    public List <Funcionario> findByCpfFuncionario(String cpfFuncionario);
    

}