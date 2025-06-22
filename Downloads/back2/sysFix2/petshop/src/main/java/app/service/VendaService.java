package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Venda;
import app.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	public String save (Venda venda) {
		this.vendaRepository.save(venda);
		return "Venda feita com sucesso!";
	}
	public String update (Venda venda, long idVenda) {
		venda.setIdVenda(idVenda);
		this.vendaRepository.save(venda);
		return "Venda alterada com sucesso!";
	}
	public String delete (long idVenda) {
		this.vendaRepository.deleteById(idVenda);
		return "Venda deletada com sucesso!";
	}
	public List <Venda> findAll (){
		List <Venda> lista = this.vendaRepository.findAll();
		return lista;
	}
	public Venda findById(long idVenda) {
		Venda venda = this.vendaRepository.findById(idVenda).get();
		return venda;
	}
	
	//teste feito pra teste de unidade
	public int somarTotalDeItensVendidos(List<Integer> quantidadesVendidas) {
	    int soma = 0;
	    for (Integer quantidade : quantidadesVendidas) {
	        soma += quantidade;
	    }
	    return soma;
	}
}
