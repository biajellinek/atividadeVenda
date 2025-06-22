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

import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {
	
	@Mock
	FuncionarioRepository funcionarioRepository;

	@InjectMocks
	FuncionarioService funcionarioService;

	@Test//TESTE DE INTEGRACAO
	@DisplayName("Salvar funcionário com sucesso")
	void salvarFuncionario() {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNomeFuncionario("mrcio");
		funcionario.setTelefoneFuncionario("123456789");
		
		when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
		
		String resposta = funcionarioService.save(funcionario);
		
		assertEquals("Funcionario salvo com sucesso!", resposta);
		verify(funcionarioRepository, times(1)).save(funcionario);
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Atualizar funcionário existente com sucesso")
	void atualizarFuncionario() {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNomeFuncionario("mrcio");
		funcionario.setTelefoneFuncionario("123456789");
		
		when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
		
		String resposta = funcionarioService.update(funcionario, 1L);
		
		assertEquals("Funcionario alterado com sucesso!", resposta);
		verify(funcionarioRepository).save(funcionario);
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deletar funcionário pelo ID com sucesso")
	void deletarFuncionario () {
		
		String resposta = funcionarioService.delete(1L);
		
		assertEquals("Funcionario deletado com sucesso!", resposta);
		verify(funcionarioRepository).deleteById(1L);
	}
	
	@Test//TESTE DE INTEGRACAO
    @DisplayName("Listar todos os funcionários")
    void listarFuncionarios() {
        Funcionario funcionario1 = new Funcionario();
        Funcionario funcionario2 = new Funcionario();

        List<Funcionario> listaMock = Arrays.asList(funcionario1, funcionario2);

        when(funcionarioRepository.findAll()).thenReturn(listaMock);

        List<Funcionario> resultado = funcionarioService.findAll();

        assertEquals(2, resultado.size());
        verify(funcionarioRepository).findAll();
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar funcionário pelo ID")
    void findById() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario("bia");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        Funcionario resultado = funcionarioService.findById(1L);

        assertEquals("bia", resultado.getNomeFuncionario());
        verify(funcionarioRepository).findById(1L);
    }
}
