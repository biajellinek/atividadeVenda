package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProduto;

	@NotBlank(message = "O nome do produto é obrigatório")
	private String nomeProduto;

	@NotBlank(message = "O tipo do produto é obrigatório")
	private String tipoProduto;

	@NotNull(message = "A quantidade do produto é obrigatória")
	private long quantidadeProduto;

	@NotNull(message = "O valor do produto é obrigatório")
	private int valorProduto;

	@NotNull(message = "O valor de venda do produto é obrigatório")
	private int valorVenda;

	@ManyToMany(mappedBy = "estoques")
	@JsonIgnoreProperties("estoques")
	private List<Venda> vendas;
}