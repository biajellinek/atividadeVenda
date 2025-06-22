package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Animal;
import app.service.AnimalService;

@ExtendWith(MockitoExtension.class)
public class AnimalControllerTest {

    @InjectMocks
    private AnimalController animalController;

    @Mock
    private AnimalService animalService;

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Cenário - Buscar Animal por ID")
    void cenarioFindById() {
        Animal animal = new Animal();
        animal.setNomeAnimal("thor");
        animal.setTipoAnimal("cachorro");

        when(animalService.findById(1L)).thenReturn(animal);

        ResponseEntity<Animal> resposta = animalController.findById(1L);

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals("thor", resposta.getBody().getNomeAnimal());
        assertEquals("cachorro", resposta.getBody().getTipoAnimal());
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deletar Animal por ID")
    void deleteAnimal() {
        String mensagemEsperada = "Animal deletado com sucesso!";
    	
        when(animalService.delete(1L)).thenReturn(mensagemEsperada);
        ResponseEntity<String> resposta = animalController.delete(1L);
    	
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(mensagemEsperada, resposta.getBody());
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Salvar Animal - Sucesso")
    void testSave_Success() {
        Animal animal = new Animal();
        animal.setTipoAnimal("Cachorro");

        String mensagemEsperada = "Animal salvo com sucesso!";

        when(animalService.save(any(Animal.class))).thenReturn(mensagemEsperada);

        ResponseEntity<String> response = animalController.save(animal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemEsperada, response.getBody());

        verify(animalService, times(1)).save(animal);
    }

//    @Test//TESTE DE INTEGRACAO
//    @DisplayName("Buscar Todos os Animais - Sucesso")
//    void testFindAll_Success() {
//        Animal animal1 = new Animal();
//        animal1.setTipoAnimal("Gato");
//        Animal animal2 = new Animal();
//        animal2.setTipoAnimal("Pássaro");
//
//        List<Animal> lista = Arrays.asList(animal1, animal2);
//
//        when(animalService.findAll()).thenReturn(lista);
//
//        ResponseEntity<List<Animal>> response = animalController.findAll();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        List<Animal> body = response.getBody();
//        assertNotNull(body);
//        assertEquals(2, body.size());
//        assertEquals("Gato", body.get(0).getTipoAnimal());
//        assertEquals("Pássaro", body.get(1).getTipoAnimal());
//
//        verify(animalService, times(1)).findAll();
//    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar Animais por Tipo - Sucesso")
    void testFindByTipoAnimal_Success() {
        Animal animal = new Animal();
        animal.setTipoAnimal("Peixe");

        List<Animal> lista = Arrays.asList(animal);

        when(animalService.findByTipoAnimal("Peixe")).thenReturn(lista);

        ResponseEntity<List<Animal>> response = animalController.findByTipoAnimal("Peixe");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Animal> body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.size());
        assertEquals("Peixe", body.get(0).getTipoAnimal());

        verify(animalService, times(1)).findByTipoAnimal("Peixe");
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar Animais por Tipo - Exceção")
    void testFindByTipoAnimal_Exception() {
        when(animalService.findByTipoAnimal(anyString())).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<List<Animal>> response = animalController.findByTipoAnimal("Inexistente");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(animalService, times(1)).findByTipoAnimal("Inexistente");
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar Animais por ID Cliente - Sucesso")
    void testFindByIdCliente_Success() {
        Animal animal = new Animal();
        animal.setTipoAnimal("Coelho");

        List<Animal> lista = Arrays.asList(animal);

        when(animalService.findByIdCliente(1L)).thenReturn(lista);

        ResponseEntity<List<Animal>> response = animalController.findByIdCliente(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Animal> body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.size());
        assertEquals("Coelho", body.get(0).getTipoAnimal());

        verify(animalService, times(1)).findByIdCliente(1L);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar Animais por ID Cliente - Exceção")
    void testFindByIdCliente_Exception() {
        when(animalService.findByIdCliente(anyLong())).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<List<Animal>> response = animalController.findByIdCliente(999L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(animalService, times(1)).findByIdCliente(999L);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Atualizar Animal - Sucesso")
    void testUpdate_Success() {
        Animal animal = new Animal();
        animal.setTipoAnimal("Hamster");

        String mensagemEsperada = "Animal atualizado com sucesso!";

        when(animalService.update(any(Animal.class), anyLong())).thenReturn(mensagemEsperada);

        ResponseEntity<String> response = animalController.update(animal, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemEsperada, response.getBody());

        verify(animalService, times(1)).update(animal, 1L);
    }
}
