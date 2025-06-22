package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnimal;

	@NotBlank(message = "O nome do animal é obrigatório")
	private String nomeAnimal;
	
	@NotNull(message = "A idade do animal é obrigatória")
	private int idadeAnimal;
	
	@NotBlank(message = "O porte do animal é obrigatório")
	private String porteAnimal;

	@NotBlank(message = "O tipo do animal é obrigatório")
	private String tipoAnimal;

	@NotBlank(message = "O sexo do animal é obrigatório")
	private String sexoAnimal;
	
	//@ManyToMany(mappedBy = "animais")
	//@JsonIgnoreProperties("animais")
	//private List<Cliente> clientes;
	
	@NotNull(message = "O cliente no animal é obrigatório")
	@ManyToOne
    @JoinColumn(name = "cliente_id") // Caso use um relacionamento com a tabela cliente
    private Cliente cliente;

	@OneToMany(mappedBy = "animal")
	@JsonIgnoreProperties("animal")
	private List<Funcionario> funcionarios;
}