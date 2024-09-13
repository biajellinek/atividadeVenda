package com.integrador.orcamento_entities;

@Entity
public class OrcamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dataCriacao;
    private String descricao;
    private float valorTotal;
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL)
    private List<ItemOrcamento> itens;

}
