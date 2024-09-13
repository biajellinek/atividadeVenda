package com.integrador.orcamento_entities;
import java.time.LocalDateTime;
import javax.persistence.Entity;

@Entity
public class UsuarioEntity {
    
	  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;

}
