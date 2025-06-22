package app.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFuncionario;

	@NotBlank(message = "O nome do funcionário é obrigatório")
	@Pattern(regexp = "\\S+\\s+\\S+.*$", message = "O nome deve conter pelo menos duas palavras")
	private String nomeFuncionario;

	@NotBlank(message = "A função do funcionário é obrigatória")
	private String funcaoFuncionario;

	@CPF(message = "O CPF deve ser válido")
	@NotBlank(message = "O CPF do funcionário é obrigatório")
	private String cpfFuncionario;

	@NotBlank(message = "O e-mail do funcionário é obrigatório")
	private String emailFuncionario;

	@NotBlank(message = "O telefone do funcionário é obrigatório")
	private String telefoneFuncionario;

	@ManyToOne
	//@NotNull(message = "O animal vinculado ao funcionário é obrigatório")
	@JsonIgnoreProperties("funcionarios")
	private Animal animal; 

	@OneToMany(mappedBy = "funcionario")
	@JsonIgnoreProperties("funcionario")
	private List<Venda> vendas;
}