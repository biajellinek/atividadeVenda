package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public String save (Cliente cliente) {
		this.clienteRepository.save(cliente);
		return "Cliente salvo com sucesso!";
	}
	public String update (Cliente cliente, long idCliente) {
		cliente.setIdCliente(idCliente);
		this.clienteRepository.save(cliente);
		return "Cliente alterado com sucesso!";
	}
	public String delete (long idCliente) {
		this.clienteRepository.deleteById(idCliente);
		return "Cliente deletado com sucesso!";
	}
	public List<Cliente> findAll(){
		List<Cliente> lista = this.clienteRepository.findAll();
		return lista;
	}
	public Cliente findById (long idCliente) {
		Cliente cliente = this.clienteRepository.findById(idCliente).get();
		return cliente;
	}
	
	//ARRUMAR OS RELACIONAMENTOS
	
	public List <Cliente> findByCpfCliente (String cpfCliente) {
		return this.clienteRepository.findByCpfCliente(cpfCliente);
	}
	
	public List <Cliente> findByNomeCliente (String nomeCliente) {
		return this.clienteRepository.findByNomeCliente(nomeCliente);
	}
	
	public int somar(List<Integer> list) {
		int soma = 0;
				for(Integer numero : list) {
					soma+=numero;
					
				}
				return soma;
	}

	
}
