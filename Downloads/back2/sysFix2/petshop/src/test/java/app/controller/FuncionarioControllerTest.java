package app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

import app.entity.Funcionario;
import app.service.FuncionarioService;

@ExtendWith(MockitoExtension.class)
public class FuncionarioControllerTest {

    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioService;

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar funcionário pelo ID com status 200")
    void cenarioFindById() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario("carol");
        funcionario.setFuncaoFuncionario("atendente");

        when(funcionarioService.findById(1L)).thenReturn(funcionario);

        ResponseEntity<Funcionario> resposta = funcionarioController.findById(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("carol", resposta.getBody().getNomeFuncionario());
        assertEquals("atendente", resposta.getBody().getFuncaoFuncionario());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve salvar funcionário com status 201")
    void cenarioSaveFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario("carol");
        funcionario.setFuncaoFuncionario("atendente");

        String mensagemEsperada = "Funcionario salvo com sucesso! ";

        when(funcionarioService.save(funcionario)).thenReturn(mensagemEsperada);

        ResponseEntity<String> resposta = funcionarioController.save(funcionario);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(mensagemEsperada, resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve atualizar funcionário com status 201")
    void updateFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario("carol");
        funcionario.setFuncaoFuncionario("atendente");

        String mensagemEsperada = "Funcionario alterado com sucesso! ";

        when(funcionarioService.update(funcionario, 1L)).thenReturn(mensagemEsperada);
        ResponseEntity<String> resposta = funcionarioController.update(funcionario, 1L);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(mensagemEsperada, resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve deletar funcionário com status 200")
    void deleteFuncionario() {
        String mensagemEsperada = "Funcionario deletado com sucesso! ";

        when(funcionarioService.delete(1L)).thenReturn(mensagemEsperada);
        ResponseEntity<String> resposta = funcionarioController.delete(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(mensagemEsperada, resposta.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar todos os funcionários com status 200")
    void testGetAllFuncionarios_Success() {
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setCpfFuncionario("12345678900");
        funcionario1.setNomeFuncionario("Maria");

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setCpfFuncionario("09876543211");
        funcionario2.setNomeFuncionario("João");

        List<Funcionario> lista = Arrays.asList(funcionario1, funcionario2);

        when(funcionarioService.findAll()).thenReturn(lista);

        ResponseEntity<List<Funcionario>> response = funcionarioController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lista, response.getBody());
        verify(funcionarioService, times(1)).findAll();
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar lista vazia quando não houver funcionários")
    void testGetAllFuncionarios_NoResults() {
        when(funcionarioService.findAll()).thenReturn(Arrays.asList());

        ResponseEntity<List<Funcionario>> response = funcionarioController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(funcionarioService, times(1)).findAll();
    }



   


    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar BAD_REQUEST ao buscar funcionário por ID e ocorrer exceção")
    void testFindById_ExceptionThrown() {
        when(funcionarioService.findById(1L)).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<Funcionario> response = funcionarioController.findById(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

   

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar BAD_REQUEST ao buscar todos os funcionários e ocorrer exceção")
    void testFindAll_ExceptionThrown() {
        when(funcionarioService.findAll()).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<List<Funcionario>> response = funcionarioController.findAll();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve lançar exceção ao buscar por CPF fora do bloco try")
    void testGetFuncionarioByCpf_ExceptionThrown() {
        String cpf = "12345678900";

        when(funcionarioService.findByCpfFuncionario(cpf)).thenThrow(new RuntimeException("Erro"));

        assertThrows(RuntimeException.class, () -> {
            funcionarioController.getFuncionarioByCpf(cpf);
        });
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve retornar funcionário por CPF com status 200")
    void testGetFuncionarioByCpf_Success() {
        String cpf = "12345678900";
        Funcionario funcionario = new Funcionario();
        funcionario.setCpfFuncionario(cpf);
        List<Funcionario> lista = List.of(funcionario);

        when(funcionarioService.findByCpfFuncionario(cpf)).thenReturn(lista);

        ResponseEntity<List<Funcionario>> response = funcionarioController.getFuncionarioByCpf(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(cpf, response.getBody().get(0).getCpfFuncionario());

        verify(funcionarioService, times(2)).findByCpfFuncionario(cpf);
    }

    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve lançar exceção ao buscar por CPF fora do try")
    void testGetFuncionarioByCpf_ExceptionOutsideTry() {
        String cpf = "12345678900";

        when(funcionarioService.findByCpfFuncionario(cpf)).thenThrow(new RuntimeException("Erro ao buscar CPF"));

        assertThrows(RuntimeException.class, () -> {
            funcionarioController.getFuncionarioByCpf(cpf);
        });
//
        verify(funcionarioService, times(1)).findByCpfFuncionario(cpf); // Falha na primeira chamada
    }

//testes
}
