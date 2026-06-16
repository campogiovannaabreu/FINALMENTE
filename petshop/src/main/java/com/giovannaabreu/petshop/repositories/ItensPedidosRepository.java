package com.giovannaabreu.petshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.giovannaabreu.petshop.entities.ItensPedidos;

public interface ItensPedidosRepository extends JpaRepository<ItensPedidos, Integer> {

    Optional<ItensPedidos> findByIdPedidoFkAndIdProdutoFk(Long idPedidoFk, Integer idProdutoFk);
   
    @Query("SELECT i FROM ItensPedidos i WHERE i.idPedidoFk = :idPedido AND i.idProdutoFk = :idProduto")
    Optional<ItensPedidos> buscarItemExistente(@Param("idPedido") Long idPedido, @Param("idProduto") Integer idProduto);
   
    @Query("SELECT i FROM ItensPedidos i WHERE i.idPedidoFk = :idPedido")
    List<ItensPedidos> findByIdPedidoFk(@Param("idPedido") Long idPedido);

}