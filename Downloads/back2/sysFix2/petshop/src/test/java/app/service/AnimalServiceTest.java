package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import app.entity.Animal;
import app.repository.AnimalRepository;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {
    
    @Mock
    AnimalRepository animalRepository;
    
    @InjectMocks
    AnimalService animalService;
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Salvar animal com sucesso")
    void salvarAnimal(){
        
        Animal animal = new Animal();
        animal.setNomeAnimal("thor");
        animal.setTipoAnimal("cachorro");
        
        when(animalRepository.save(animal)).thenReturn(animal);
        
        String resposta = animalService.save(animal);
        
        assertEquals("Animal salvo com sucesso!", resposta);
        verify(animalRepository, times(1)).save(animal);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Atualizar animal existente com sucesso")
    void atualizarAnimal() {
        
        Animal animal = new Animal();
        animal.setNomeAnimal("thor");
        animal.setTipoAnimal("cachorro");
        
        when(animalRepository.save(animal)).thenReturn(animal);
        
        String resposta = animalService.update(animal, 1L);
        
        assertEquals("Animal alterado com sucesso!", resposta);
        verify(animalRepository).save(animal);
    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deletar animal pelo ID com sucesso")
    void deletarAnimal() {
        String resposta = animalService.delete(1L);
        
        assertEquals("Animal deletado com sucesso!", resposta);
        verify(animalRepository).deleteById(1L);
    }
    
//    @Test//TESTE DE INTEGRACAO
//    @DisplayName("Listar todos os animais com sucesso")
//    void listarAnimais() {
//        
//        Animal a1 = new Animal();
//        Animal a2 = new Animal();
//        
//        List<Animal> listaMock = Arrays.asList(a1, a2);
//        
//        when(animalRepository.findAll()).thenReturn(listaMock);
//        
//        List<Animal> resultado = animalService.findAll();
//        
//        assertNotNull(resultado, "A lista de animais não pode ser nula");
//        assertEquals(2, resultado.size(), "Deve retornar 2 animais");
//        verify(animalRepository, times(1)).findAll();
//    }
    
    @Test//TESTE DE INTEGRACAO
    @DisplayName("Buscar animal por ID com sucesso")
    void findById() {
        
        Animal animal = new Animal();
        animal.setNomeAnimal("thor");
        
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        
        Animal resultado = animalService.findById(1L);
        assertEquals("thor", resultado.getNomeAnimal());
        verify(animalRepository).findById(1L);
    }
}
