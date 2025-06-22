package app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Estoque;
import app.service.EstoqueService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do EstoqueController")
public class EstoqueControllerTest {
	
	@InjectMocks
	private EstoqueController estoqueController;

	@Mock
	private EstoqueService estoqueService;

	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve buscar estoque pelo ID")
	void cenarioFindById() {
		Estoque estoque = new Estoque();
		estoque.setNomeProduto("coca");
		estoque.setValorVenda(12);
		
		when(estoqueService.findById(1L)).thenReturn(estoque);
		
		ResponseEntity<Estoque> resposta = estoqueController.findById(1L);
		
		assertEquals(200, resposta.getStatusCodeValue());
		assertEquals("coca", resposta.getBody().getNomeProduto());
		assertEquals(12,resposta.getBody().getValorVenda());
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve salvar estoque com sucesso")
	void salvarEstoqueComSucesso() {
	    Estoque estoque = new Estoque();
	    estoque.setNomeProduto("coca");
	    estoque.setValorVenda(12);

	    String mensagem = "Produto salvo com sucesso!";

	    when(estoqueService.save(estoque)).thenReturn(mensagem);

	    ResponseEntity<String> resposta = estoqueController.save(estoque);

	    assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	    assertEquals(mensagem, resposta.getBody());
	}

	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve retornar erro ao salvar estoque")
	void salvarEstoqueComErro() {
	    Estoque estoque = new Estoque();
	    estoque.setNomeProduto("coca");
	    estoque.setValorVenda(12);

	    when(estoqueService.save(estoque)).thenThrow(new RuntimeException("Erro ao salvar no banco"));

	    ResponseEntity<String> resposta = estoqueController.save(estoque);

	    assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	    assertEquals("Deu algo errado ao salvar!", resposta.getBody());
	}

	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve alterar estoque com sucesso")
	void alterarEstoque() {
		Estoque estoque = new Estoque();
		estoque.setNomeProduto("coca");
		estoque.setValorVenda(12);
		
		String mensagem = "Produto alterado com sucesso!";

		when(estoqueService.update(estoque, 1L)).thenReturn(mensagem);
		ResponseEntity<String> resposta = estoqueController.update(estoque, 1L);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(mensagem, resposta.getBody());
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve deletar estoque com sucesso")
	void deleteEstoque() {
		String mensagem = "Produto deletado com sucesso!";
		
		when(estoqueService.delete(1L)).thenReturn(mensagem);
		ResponseEntity<String> resposta = estoqueController.delete(1L);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(mensagem, resposta.getBody());
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve buscar produtos com estoque baixo")
	void buscarProdutosComEstoqueBaixo() {
	    List<Estoque> produtos = new ArrayList<>();
	    Estoque estoque1 = new Estoque();
	    estoque1.setNomeProduto("coca");
	    estoque1.setValorVenda(12);
	    Estoque estoque2 = new Estoque();
	    estoque2.setNomeProduto("pepsi");
	    estoque2.setValorVenda(10);
	    produtos.add(estoque1);
	    produtos.add(estoque2);

	    when(estoqueService.buscarProdutosComEstoqueBaixo(5L)).thenReturn(produtos);

	    ResponseEntity<List<Estoque>> resposta = estoqueController.buscarProdutosComEstoqueBaixo(5L);

	    assertEquals(HttpStatus.OK, resposta.getStatusCode());
	    assertNotNull(resposta.getBody());
	    assertEquals(2, resposta.getBody().size());
	    assertEquals("coca", resposta.getBody().get(0).getNomeProduto());
	    assertEquals("pepsi", resposta.getBody().get(1).getNomeProduto());
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve buscar produtos pelo nome")
	void buscarProdutosPorNome() {
	    List<Estoque> produtos = new ArrayList<>();
	    
	    Estoque estoque1 = new Estoque();
	    estoque1.setNomeProduto("coca");
	    estoque1.setValorVenda(12);

	    Estoque estoque2 = new Estoque();
	    estoque2.setNomeProduto("coca");
	    estoque2.setValorVenda(15);

	    produtos.add(estoque1);
	    produtos.add(estoque2);

	    when(estoqueService.findByNomeProduto("coca")).thenReturn(produtos);

	    ResponseEntity<List<Estoque>> resposta = estoqueController.findByNomeProduto("coca");

	    assertEquals(HttpStatus.OK, resposta.getStatusCode());
	    assertNotNull(resposta.getBody());
	    assertEquals(2, resposta.getBody().size());
	    assertEquals("coca", resposta.getBody().get(0).getNomeProduto());
	    assertEquals("coca", resposta.getBody().get(1).getNomeProduto());
	    assertEquals(12, resposta.getBody().get(0).getValorVenda());
	    assertEquals(15, resposta.getBody().get(1).getValorVenda());
	}
	
	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve buscar produtos pelo tipo")
	void buscarProdutosPorTipo() {
	    List<Estoque> produtos = new ArrayList<>();
	    
	    Estoque estoque1 = new Estoque();
	    estoque1.setNomeProduto("coca");
	    estoque1.setTipoProduto("bebida");
	    estoque1.setValorVenda(12);

	    Estoque estoque2 = new Estoque();
	    estoque2.setNomeProduto("pepsi");
	    estoque2.setTipoProduto("bebida");
	    estoque2.setValorVenda(10);

	    produtos.add(estoque1);
	    produtos.add(estoque2);

	    when(estoqueService.findByTipoProduto("bebida")).thenReturn(produtos);

	    ResponseEntity<List<Estoque>> resposta = estoqueController.findByTipoProduto("bebida");

	    assertEquals(HttpStatus.OK, resposta.getStatusCode());
	    assertNotNull(resposta.getBody());
	    assertEquals(2, resposta.getBody().size());
	    assertEquals("coca", resposta.getBody().get(0).getNomeProduto());
	    assertEquals("bebida", resposta.getBody().get(0).getTipoProduto());
	    assertEquals("pepsi", resposta.getBody().get(1).getNomeProduto());
	    assertEquals("bebida", resposta.getBody().get(1).getTipoProduto());
	}

	@Test//TESTE DE INTEGRACAO
	@DisplayName("Deve buscar todos os produtos")
	void buscarTodosOsProdutos() {
	    List<Estoque> produtos = new ArrayList<>();
	    
	    Estoque estoque1 = new Estoque();
	    estoque1.setNomeProduto("coca");
	    estoque1.setValorVenda(12);

	    Estoque estoque2 = new Estoque();
	    estoque2.setNomeProduto("pepsi");
	    estoque2.setValorVenda(10);

	    produtos.add(estoque1);
	    produtos.add(estoque2);

	    when(estoqueService.findAll()).thenReturn(produtos);

	    ResponseEntity<List<Estoque>> resposta = estoqueController.findAll();

	    assertEquals(HttpStatus.OK, resposta.getStatusCode());
	    assertNotNull(resposta.getBody());
	    assertEquals(2, resposta.getBody().size());
	    assertEquals("coca", resposta.getBody().get(0).getNomeProduto());
	    assertEquals(12, resposta.getBody().get(0).getValorVenda());
	    assertEquals("pepsi", resposta.getBody().get(1).getNomeProduto());
	    assertEquals(10, resposta.getBody().get(1).getValorVenda());
	}

}
