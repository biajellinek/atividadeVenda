package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Estoque;
import app.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	public String save (Estoque estoque) {
		this.estoqueRepository.save(estoque);
		return "Produto salvo com sucesso!";
	}
	public String update (Estoque estoque, long idEstoque) {
		estoque.setIdProduto(idEstoque);
		this.estoqueRepository.save(estoque);
		return "Produto alterado com sucesso!";
	}
	public String delete (long idEstoque) {
		this.estoqueRepository.deleteById(idEstoque);
		return "Produto deletado com sucesso!";
	}
	public List<Estoque> findAll(){
		List<Estoque> lista = this.estoqueRepository.findAll();
		return lista;
	}
	public Estoque findById (long idEstoque) {
		Estoque estoque = this.estoqueRepository.findById(idEstoque).get();
		return estoque;
	}
	
	//ARRUMAR OS RELACIONAMENTOS
	
	public List <Estoque> findByNomeProduto (String nomeProduto) {
		return this.estoqueRepository.findByNomeProduto(nomeProduto);
	}
	
	public List <Estoque> findByTipoProduto(String tipoProduto){
		return this.estoqueRepository.findByTipoProduto(tipoProduto);
	}
	
	public List<Estoque> buscarProdutosComEstoqueBaixo(Long quantidadeMinima) {
        return estoqueRepository.buscarProdutosComEstoqueBaixo(quantidadeMinima);
    }
	
	public int somarTotalDeProdutosEmEstoque(List<Integer> quantidadesEmEstoque) {
	    int soma = 0;
	    for (Integer quantidade : quantidadesEmEstoque) {
	        soma += quantidade;
	    }
	    return soma;
	}

}