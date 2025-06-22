package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired // vai alocar uma unica vez na memoria, pra evitar utilizar memoria
	private FuncionarioRepository funcionarioRepository;
	
	public String save (Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
		return "Funcionario salvo com sucesso!";
	}
	
	public String update (Funcionario funcionario, long idFuncionario) {
		funcionario.setIdFuncionario(idFuncionario);
		this.funcionarioRepository.save(funcionario);
		return "Funcionario alterado com sucesso!";
	}
	
	public String delete (long idFuncionario) {
		this.funcionarioRepository.deleteById(idFuncionario);
		return "Funcionario deletado com sucesso!";
	}
	
	public List<Funcionario> findAll(){
		List<Funcionario> lista = this.funcionarioRepository.findAll();
		return lista;
	}
	
	 public Funcionario findById(long idFuncionario) {
		 Funcionario funcionario = this.funcionarioRepository.findById(idFuncionario).get();
		 return funcionario;
	 }
	
	 //ARRUMAR OS RELACIONAMENTOS
	 
	public List<Funcionario> findByCpfFuncionario(String cpfFuncionario) {
        return this.funcionarioRepository.findByCpfFuncionario(cpfFuncionario);
    }

    public List<Funcionario> findByNomeFuncionario(String nomeFuncionario) {
        return this.funcionarioRepository.findByNomeFuncionario(nomeFuncionario);
    }
}
