package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
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
import app.repository.ClienteRepository;
import app.service.ClienteService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do ClienteController")
public class ClienteControllerTest {
	
	@Mock
	ClienteRepository clienteRepository;
	
	@InjectMocks
	ClienteController clienteController;
	
	@Mock
	ClienteService clienteService;
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve buscar cliente pelo ID")
	void findById() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNomeCliente("benjamin");
		cliente.setTelefoneCliente("123456789");
		
		when(clienteService.findById(1L)).thenReturn(cliente);
		
		ResponseEntity<Cliente> resposta = clienteController.findById(1L);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals("benjamin", resposta.getBody().getNomeCliente());
		assertEquals("123456789", resposta.getBody().getTelefoneCliente());
	}

	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve salvar cliente")
	void saveCliente() {
		Cliente cliente = new Cliente();
		cliente.setNomeCliente("benjamin");
		cliente.setTelefoneCliente("123456789");
		
		String mensagemEsperada = "Cliente salvo com sucesso!";
		
		when(clienteService.save(cliente)).thenReturn(mensagemEsperada);
		
		ResponseEntity<String> resposta = clienteController.save(cliente);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(mensagemEsperada, resposta.getBody());
	}
	
	  @Test//TESTE DE INTEGRACAO
	    @DisplayName("Deve retornar erro 400 ao lançar exceção")
	    void saveCliente_ComErro() {
	        Cliente cliente = new Cliente();
	        cliente.setNomeCliente("benjamin");
	        cliente.setTelefoneCliente("123456789");

	        when(clienteService.save(cliente)).thenThrow(new RuntimeException("Falha ao salvar"));

	        ResponseEntity<String> resposta = clienteController.save(cliente);

	        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	        assertEquals("Deu algo errado ao salvar!", resposta.getBody());
	    }
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve atualizar cliente")
	void updateCliente() {
		Cliente cliente = new Cliente();
		cliente.setNomeCliente("benjamin");
		cliente.setTelefoneCliente("123456789");
		
		String mensagemEsperada = "Cliente alterado com sucesso!";

		when(clienteService.update(cliente, 1L)).thenReturn(mensagemEsperada);
		
		ResponseEntity<String> resposta = clienteController.update(cliente, 1L);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(mensagemEsperada, resposta.getBody());
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve deletar cliente")
	void deleteCliente() {
		String mensagemEsperada = "Cliente deletado com sucesso!";

		when(clienteService.delete(1L)).thenReturn(mensagemEsperada);
		ResponseEntity<String> resposta = clienteController.delete(1L);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(mensagemEsperada, resposta.getBody());
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve buscar cliente pelo CPF")
	void testFindByCpfCliente() {
	    String cpf = "123.456.789-00";
	    Cliente cliente = new Cliente();
	    cliente.setCpfCliente(cpf);
	    cliente.setNomeCliente("João");

	    when(clienteService.findByCpfCliente(cpf)).thenReturn(Arrays.asList(cliente));

	    ResponseEntity<List<Cliente>> resposta = clienteController.findByCpfCliente(cpf);

	    assertEquals(HttpStatus.OK, resposta.getStatusCode());
	    assertEquals(1, resposta.getBody().size());
	    assertEquals("João", resposta.getBody().get(0).getNomeCliente());
	}

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve buscar cliente pelo nome")
    void testFindByNomeCliente() {
        String nome = "maria";
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(nome);
        cliente.setCpfCliente("987.654.321-00");

        when(clienteService.findByNomeCliente(nome)).thenReturn(Arrays.asList(cliente));

        ResponseEntity<List<Cliente>> resposta = clienteController.findByNomeCliente(nome);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
        assertEquals("maria", resposta.getBody().get(0).getNomeCliente());
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar lista de clientes com sucesso e status 200")
    void findAllClientes_ComSucesso() {
        Cliente cliente1 = new Cliente();
        cliente1.setNomeCliente("Maria");

        Cliente cliente2 = new Cliente();
        cliente2.setNomeCliente("João");

        List<Cliente> lista = Arrays.asList(cliente1, cliente2);
        when(clienteService.findAll()).thenReturn(lista);

        ResponseEntity<List<Cliente>> resposta = clienteController.findAll();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals(2, resposta.getBody().size());
        verify(clienteService, times(1)).findAll();
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar erro 400 ao lançar exceção no findAll")
    void findAllClientes_ComErro() {
        when(clienteService.findAll()).thenThrow(new RuntimeException("Erro ao buscar clientes"));

        ResponseEntity<List<Cliente>> resposta = clienteController.findAll();

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(clienteService, times(1)).findAll();
    }
}
