package com.integrador.orcamento_service;

import com.integrador.orcamento_entities.UsuarioEntity;
import com.integrador.orcamento_repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    // Retorna todos os orçamentos
    public List<OrcamentoEntity> getAllOrcamentos() {
        return orcamentoRepository.findAll();
    }

    // Retorna um orçamento por ID
    public OrcamentoEntity getOrcamentoById(int id) {
        return orcamentoRepository.findById(id).orElse(null);
    }

    // Salva um novo orçamento ou atualiza um existente
    public OrcamentoEntity saveOrcamento(OrcamentoEntity orcamento) {
        return orcamentoRepository.save(orcamento);
    }

    // Deleta um orçamento por ID
    public void deleteOrcamento(int id) {
        orcamentoRepository.deleteById(id);
    }
}