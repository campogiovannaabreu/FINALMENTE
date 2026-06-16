package com.giovannaabreu.petshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_pedidos")
public class ItensPedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido")
    private Integer idItemPedido;
    
    private Integer quantidade;
    
    private Integer precoUnitario;

    @Column(name = "id_pedido_fk")
    private Long idPedidoFk;

    @Column(name = "id_produto_fk")
    private Integer idProdutoFk;
   
    @Column(name = "nome_produto")
    private String nomeProduto;

    public Integer getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Integer idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Long getIdPedidoFk() {
        return idPedidoFk;
    }

    public void setIdPedidoFk(Long idPedidoFk) {
        this.idPedidoFk = idPedidoFk;
    }

    public Integer getIdProdutoFk() {
        return idProdutoFk;
    }

    public void setIdProdutoFk(Integer idProdutoFk) {
        this.idProdutoFk = idProdutoFk;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getPrecoUnitario() {
        return precoUnitario;
    }
    
    public void setPrecoUnitario(Integer precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    


  
}