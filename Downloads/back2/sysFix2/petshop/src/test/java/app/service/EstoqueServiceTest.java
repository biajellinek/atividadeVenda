package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import app.entity.Estoque;
import app.repository.EstoqueRepository;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {

    @Mock
    EstoqueRepository estoqueRepository;

    @InjectMocks
    EstoqueService estoqueService;

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Salvar produto no estoque com sucesso")
    void testSalvarEstoque() {
        Estoque estoque = new Estoque();
        when(estoqueRepository.save(estoque)).thenReturn(estoque);

        String resposta = estoqueService.save(estoque);

        assertEquals("Produto salvo com sucesso!", resposta);
        verify(estoqueRepository, times(1)).save(estoque);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Atualizar produto existente no estoque com sucesso")
    void testAtualizarEstoque() {
        Estoque estoque = new Estoque();
        when(estoqueRepository.save(estoque)).thenReturn(estoque);

        String resposta = estoqueService.update(estoque, 1L);

        assertEquals("Produto alterado com sucesso!", resposta);
        assertEquals(1L, estoque.getIdProduto());
        verify(estoqueRepository, times(1)).save(estoque);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deletar produto do estoque pelo ID com sucesso")
    void testDeletarEstoque() {
        String resposta = estoqueService.delete(1L);

        assertEquals("Produto deletado com sucesso!", resposta);
        verify(estoqueRepository, times(1)).deleteById(1L);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Listar todos os produtos do estoque")
    void testListarTodos() {
        Estoque e1 = new Estoque();
        Estoque e2 = new Estoque();
        List<Estoque> lista = Arrays.asList(e1, e2);

        when(estoqueRepository.findAll()).thenReturn(lista);

        List<Estoque> resultado = estoqueService.findAll();

        assertEquals(2, resultado.size());
        verify(estoqueRepository, times(1)).findAll();
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar produto do estoque por ID")
    void testBuscarPorId() {
        Estoque estoque = new Estoque();
        estoque.setIdProduto(1L);

        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));

        Estoque resultado = estoqueService.findById(1L);

        assertEquals(1L, resultado.getIdProduto());
        verify(estoqueRepository, times(1)).findById(1L);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar produtos do estoque pelo nome do produto")
    void testBuscarPorNomeProduto() {
        Estoque e1 = new Estoque();
        Estoque e2 = new Estoque();
        List<Estoque> lista = Arrays.asList(e1, e2);

        when(estoqueRepository.findByNomeProduto("Sabão")).thenReturn(lista);

        List<Estoque> resultado = estoqueService.findByNomeProduto("Sabão");

        assertEquals(2, resultado.size());
        verify(estoqueRepository, times(1)).findByNomeProduto("Sabão");
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar produtos do estoque pelo tipo de produto")
    void testBuscarPorTipoProduto() {
        Estoque e1 = new Estoque();
        List<Estoque> lista = Arrays.asList(e1);

        when(estoqueRepository.findByTipoProduto("Limpeza")).thenReturn(lista);

        List<Estoque> resultado = estoqueService.findByTipoProduto("Limpeza");

        assertEquals(1, resultado.size());
        verify(estoqueRepository, times(1)).findByTipoProduto("Limpeza");
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar produtos com estoque abaixo do limite")
    void testBuscarProdutosComEstoqueBaixo() {
        Estoque e1 = new Estoque();
        Estoque e2 = new Estoque();
        List<Estoque> lista = Arrays.asList(e1, e2);

        when(estoqueRepository.buscarProdutosComEstoqueBaixo(10L)).thenReturn(lista);

        List<Estoque> resultado = estoqueService.buscarProdutosComEstoqueBaixo(10L);

        assertEquals(2, resultado.size());
        verify(estoqueRepository, times(1)).buscarProdutosComEstoqueBaixo(10L);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Somar total de produtos em estoque")
    void testSomarTotalDeProdutosEmEstoque() {
        EstoqueService estoqueService = new EstoqueService();
        List<Integer> lista = Arrays.asList(1, 2, 7);

        int resultado = estoqueService.somarTotalDeProdutosEmEstoque(lista);

        assertEquals(10, resultado);
    }
    
    @Test//se é de unidade nao poderia chamar o service
    @DisplayName("Somar total de itens vendidos")
    void testSomarTotalDeItensVendidos() {
        VendaService vendaService = new VendaService();
        List<Integer> lista = Arrays.asList(2, 3, 5);

        int resultado = vendaService.somarTotalDeItensVendidos(lista);

        assertEquals(10, resultado);
    }
    
    @Test//TESTE DE UNIDADE - passou no junit
    @DisplayName("Somar total de itens vendidos")
    void cenario01() {
        List<Integer> lista = new ArrayList<>();
        lista.add(3);  // Produto 1
        lista.add(5);  // Produto 2
        lista.add(2);  // Produto 3
               
        int resultadoEsperado = 10;
        int resultadoObtido = estoqueService.somarTotalDeProdutosEmEstoque(lista);
        
        assertEquals(resultadoEsperado, resultadoObtido);
    }
    
    @Test//TESTE DE UNIDADE - passou no junit
    @DisplayName("Somar com valores inválidos")
    void cenario02() {
    	List<Integer> lista = new ArrayList<>();
    	lista.add(3);
    	lista.add(null);
    	lista.add(2);
    	
    	assertThrows(Exception.class, () ->{
    		this.estoqueService.somarTotalDeProdutosEmEstoque(lista);
    	});
    }
    
 
}
