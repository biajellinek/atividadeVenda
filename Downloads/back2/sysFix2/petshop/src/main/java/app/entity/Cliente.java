package app.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente;

	@NotBlank(message = "O nome do cliente é obrigatório")
	private String nomeCliente;

	@NotBlank(message = "O CPF do cliente é obrigatório")
	@CPF (message = "O CPF deve ser válido")
	private String cpfCliente;

	@NotBlank(message = "O telefone do cliente é obrigatório")
	private String telefoneCliente;

//	@ManyToMany 
//	@NotEmpty(message = "O cliente deve possuir pelo menos um animal")
//	@JoinTable(
//			name = "cliente_id_cliente",
//			joinColumns = @JoinColumn(name = "id_cliente"),
//			inverseJoinColumns = @JoinColumn(name = "id_animal")
//			)
//	@JsonIgnoreProperties("clientes")
//	private List<Animal> animais;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnoreProperties("cliente")
	private List<Venda> vendas;
	
	
}