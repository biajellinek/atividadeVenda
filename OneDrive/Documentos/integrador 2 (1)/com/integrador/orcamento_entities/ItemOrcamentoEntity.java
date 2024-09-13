package com.integrador.orcamento_entities;

@Entity
public class ItemOrcamentoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String descricao;
    private int quantidade;
    private float precoUnitario;
    private float subtotal;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;
}
