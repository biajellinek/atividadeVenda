package com.example.colaboradores.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EventosEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private String descricao;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private LocalDate avaliacoesJurados;
	private LocalDate avaliacoesP;
	
	@ManyToMany
	@JoinTable(
			name = "eventos_jurados",
			joinColumns = @JoinColumn(name = "eventos"),
			inverseJoinColumns = @JoinColumn(name = "jurados")
			)
	private List<JuradosEntity> jurados;
	
	
}
