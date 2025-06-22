package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import app.entity.Cliente;
import app.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    
    @Mock
    ClienteRepository clienteRepository;
    
    @InjectMocks
    ClienteService clienteService;
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Salvar cliente com sucesso")
    void saveCliente() {
        
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("bia");
        cliente.setCpfCliente("15117141903");
        
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        
        String resposta = clienteService.save(cliente);
        
        assertEquals("Cliente salvo com sucesso!", resposta);
        verify(clienteRepository, times(1)).save(cliente);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Atualizar cliente existente com sucesso")
    void atualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("bia");
        cliente.setCpfCliente("15117141903");
        
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        
        String resposta = clienteService.update(cliente, 1L);
        
        assertEquals("Cliente alterado com sucesso!", resposta);
        verify(clienteRepository).save(cliente);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deletar cliente pelo ID com sucesso")
    void deletarCliente() {
        
        String resposta = clienteService.delete(1L);
        
        assertEquals("Cliente deletado com sucesso!", resposta);
        verify(clienteRepository).deleteById(1L);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Listar todos os clientes com sucesso")
    void listarClientes() {
        
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        
        List<Cliente> listaMock = Arrays.asList(cliente2, cliente1);
        
        when(clienteRepository.findAll()).thenReturn(listaMock);
        
        List<Cliente> resultado = clienteService.findAll();
        
        assertEquals(2, resultado.size());
        verify(clienteRepository).findAll();
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar cliente por ID com sucesso")
    void findById() {
        
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("bia");
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        
        Cliente resultado = clienteService.findById(1L);
        assertEquals("bia", resultado.getNomeCliente());
        verify(clienteRepository).findById(1L);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Somar lista com números positivos")
    void cenario01() {
        List<Integer> lista = Arrays.asList(1, 2, 3);
        int retorno = clienteService.somar(lista);
        assertEquals(6, retorno);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Somar lista com números negativos")
    void cenario02() {
        List<Integer> lista = Arrays.asList(-1, -2, -3);
        int retorno = clienteService.somar(lista);
        assertEquals(-6, retorno);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Somar lista vazia")
    void cenario03() {
        List<Integer> lista = Arrays.asList();
        int retorno = clienteService.somar(lista);
        assertEquals(0, retorno);
    }
}
