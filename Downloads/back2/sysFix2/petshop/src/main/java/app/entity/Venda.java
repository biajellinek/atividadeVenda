package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenda;

	@NotNull(message = "A quantidade comprada é obrigatória")
	private int quantidadeComprada;

	@NotNull(message = "O valor total da venda é obrigatório")
	private int valorTotal;

	@NotBlank(message = "O tipo de pagamento é obrigatório")
	private String tipoPagamento;
	
	private String status;
	//private int VendaFuncionario;
	//private int VendaCliente;

	@ManyToOne
	@NotNull(message = "O cliente da venda é obrigatório")
	@JsonIgnoreProperties("vendas")
	@JoinColumn(name = "cliente_id_cliente")
	private Cliente cliente;

	@ManyToMany
	@JoinTable(name = "venda_estoque")
	@NotEmpty(message = "A venda deve conter pelo menos um produto em estoque")
	@JsonIgnoreProperties("vendas")
	private List<Estoque> estoques;

	@ManyToOne
	@NotNull (message = "A venda deve ter um funcionário associado")
	@JsonIgnoreProperties("vendas")
	@JoinColumn (name = "funcionario_id_funcionario")
	private Funcionario funcionario;
}