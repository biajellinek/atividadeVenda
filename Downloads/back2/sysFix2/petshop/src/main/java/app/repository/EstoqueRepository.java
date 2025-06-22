package app.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	//ARRUMAR DPS
    public List<Estoque> findByNomeProduto(String nomeProduto);

    public List<Estoque> findByTipoProduto(String tipoProduto);

    @Query("SELECT e FROM Estoque e WHERE e.quantidadeProduto < :quantidadeMinima")
    public List<Estoque> buscarProdutosComEstoqueBaixo(@Param("quantidadeMinima") Long quantidadeMinima);

}