package app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Cliente;
import app.entity.Estoque;
import app.entity.Funcionario;
import app.entity.Venda;
import app.service.VendaService;

@ExtendWith(MockitoExtension.class)
public class VendaControllerTest {

    @InjectMocks
    private VendaController vendaController;

    @Mock
    private VendaService vendaService;

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar venda por ID com sucesso")
    void cenarioFindById() {
        Venda venda = criarVenda();
        when(vendaService.findById(1L)).thenReturn(venda);

        ResponseEntity<Venda> resposta = vendaController.findById(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals(5, resposta.getBody().getQuantidadeComprada());
        assertEquals("Cartão", resposta.getBody().getTipoPagamento());
        assertEquals("Cliente Teste", resposta.getBody().getCliente().getNomeCliente());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar venda por ID com erro retorna BAD_REQUEST")
    void cenarioFindByIdComErro() {
        when(vendaService.findById(1L)).thenThrow(new RuntimeException("Erro ao buscar venda"));
        
        ResponseEntity<Venda> resposta = vendaController.findById(1L);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNull(resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Salvar venda com sucesso")
    void salvarVendasComSucesso() {
        Venda venda = criarVenda();
        String mensagem = "Venda feita com sucesso!";

        when(vendaService.save(venda)).thenReturn(mensagem);

        ResponseEntity<String> resposta = vendaController.save(venda);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(mensagem, resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Salvar venda com erro retorna BAD_REQUEST")
    void salvarVendasComErro() {
        Venda venda = criarVenda();

        when(vendaService.save(venda)).thenThrow(new RuntimeException("Erro ao salvar venda"));

        ResponseEntity<String> resposta = vendaController.save(venda);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("Deu algo errado ao salvar!", resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Atualizar venda com sucesso")
    void atualizarVendasComSucesso() {
        Venda venda = criarVenda();
        String mensagemEsperada = "Venda alterada com sucesso!";

        when(vendaService.update(venda, 1L)).thenReturn(mensagemEsperada);

        ResponseEntity<String> resposta = vendaController.update(venda, 1L);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(mensagemEsperada, resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deletar venda com sucesso")
    void deletarVendaComSucesso() {
        String mensagemEsperada = "Venda deletada com sucesso!";

        when(vendaService.delete(1L)).thenReturn(mensagemEsperada);

        ResponseEntity<String> resposta = vendaController.delete(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(mensagemEsperada, resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar todas as vendas com sucesso")
    void buscarTodasVendasComSucesso() {
        List<Venda> vendas = List.of(criarVenda());

        when(vendaService.findAll()).thenReturn(vendas);

        ResponseEntity<List<Venda>> resposta = vendaController.findAll();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals(1, resposta.getBody().size());
        assertEquals("Cartão", resposta.getBody().get(0).getTipoPagamento());
    }

//    @Test
//    @DisplayName("Buscar todas as vendas com erro retorna BAD_REQUEST")
//    void buscarTodasVendasComErro() {
//        when(vendaService.findAll()).thenThrow(new RuntimeException("Erro ao buscar vendas"));
//
//        ResponseEntity<List<Venda>> resposta = vendaController.findAll();
//
//        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
//        assertNull(resposta.getBody());
//    }

    // Método auxiliar para criar uma instância de Venda
    private Venda criarVenda() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNomeCliente("Cliente Teste");

        Estoque estoque = new Estoque();
        estoque.setIdProduto(1L);
        estoque.setNomeProduto("Produto 1");
        estoque.setValorVenda(10);

        Funcionario funcionario = new Funcionario();
        funcionario.setFuncaoFuncionario("admin");
        funcionario.setCpfFuncionario("3265465456");

        Venda venda = new Venda();
        venda.setIdVenda(1L);
        venda.setQuantidadeComprada(5);
        venda.setValorTotal(50);
        venda.setTipoPagamento("Cartão");
        venda.setCliente(cliente);
        venda.setEstoques(List.of(estoque));
        venda.setFuncionario(funcionario);

        return venda;
    }
}
