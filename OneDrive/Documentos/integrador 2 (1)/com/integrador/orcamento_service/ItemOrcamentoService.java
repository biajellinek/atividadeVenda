package com.integrador.orcamento_service;

import com.integrador.orcamento_entities.ItemOrcamentoEntity;
import com.integrador.orcamento_repository.ItemOrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOrcamentoService {

	@Autowired
	private ItemOrcamentoRepository itemOrcamentoRepository;

	public List<ItemOrcamento> getAllItens() {
		return itemOrcamentoRepository.findAll();
	}

	public ItemOrcamento getItemById(int id) {
		return itemOrcamentoRepository.findById(id).orElse(null);
	}

	public ItemOrcamento saveItem(ItemOrcamento itemOrcamento) {
		return itemOrcamentoRepository.save(itemOrcamento);
	}

	public void deleteItem(int id) {
		itemOrcamentoRepository.deleteById(id);
	}
}
