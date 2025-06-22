package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import app.entity.Cliente;
import app.entity.Estoque;
import app.entity.Funcionario;
import app.entity.Venda;
import app.repository.VendaRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaService vendaService;

    private Venda criarVendaExemplo() {//criando exemplo para ser usado nos testes
        Venda venda = new Venda();
        venda.setIdVenda(1L);
        venda.setQuantidadeComprada(2);
        venda.setValorTotal(100);
        venda.setTipoPagamento("PIX");
        
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNomeCliente("Cliente Exemplo");
        venda.setCliente(cliente);
        
        Estoque estoque = new Estoque();
        estoque.setIdProduto(1L);
        estoque.setNomeProduto("Produto X");
        venda.setEstoques(Arrays.asList(estoque));
        
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(1L);
        funcionario.setNomeFuncionario("Funcionário Y");
        venda.setFuncionario(funcionario);
        
        return venda;
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Salvar venda")
    void testSalvarVenda() {
        Venda venda = criarVendaExemplo();

        when(vendaRepository.save(venda)).thenReturn(venda);

        String resposta = vendaService.save(venda);

        assertEquals("Venda feita com sucesso!", resposta);
        verify(vendaRepository, times(1)).save(venda);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Atualizar venda")
    void testAtualizarVenda() {
        Venda venda = criarVendaExemplo();

        when(vendaRepository.save(venda)).thenReturn(venda);

        String resposta = vendaService.update(venda, 1L);

        assertEquals("Venda alterada com sucesso!", resposta);
        verify(vendaRepository, times(1)).save(venda);
        assertEquals(1L, venda.getIdVenda());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deletar venda")
    void testDeletarVenda() {
        doNothing().when(vendaRepository).deleteById(1L);

        String resposta = vendaService.delete(1L);

        assertEquals("Venda deletada com sucesso!", resposta);
        verify(vendaRepository, times(1)).deleteById(1L);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Teste de findAll venda")
    void testListarTodasVendas() {
        Venda v1 = criarVendaExemplo();
        Venda v2 = criarVendaExemplo();
        v2.setIdVenda(2L);

        List<Venda> listaMock = Arrays.asList(v1, v2);

        when(vendaRepository.findAll()).thenReturn(listaMock);

        List<Venda> resultado = vendaService.findAll();

        assertEquals(2, resultado.size());
        verify(vendaRepository, times(1)).findAll();
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Teste de FindById des venda")
    void testBuscarVendaPorId() {
        Venda venda = criarVendaExemplo();

        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));

        Venda resultado = vendaService.findById(1L);

        assertEquals(2, resultado.getQuantidadeComprada());
        assertEquals("PIX", resultado.getTipoPagamento());
        assertEquals(100, resultado.getValorTotal());
        verify(vendaRepository, times(1)).findById(1L);
    }
}
