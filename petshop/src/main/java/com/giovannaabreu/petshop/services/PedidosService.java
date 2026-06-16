package com.giovannaabreu.petshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovannaabreu.petshop.entities.ItensPedidos;
import com.giovannaabreu.petshop.entities.Pedidos;
import com.giovannaabreu.petshop.repositories.ItensPedidosRepository;
import com.giovannaabreu.petshop.repositories.PedidosRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidosService {

    @Autowired private PedidosRepository pedidosRepository;
    @Autowired private ItensPedidosService itensPedidosService; 
    @Autowired
    private ItensPedidosRepository itensPedidosRepository; 

    @Transactional 
    public void adicionarAoCarrinho(Long idUsuario, Integer idProduto, Integer quantidade, Integer precoUnitario) {
        
        Pedidos pedido = pedidosRepository.findByIdUsuarioFkAndStatus(idUsuario, "ABERTO")
                .orElseGet(() -> {
                    Pedidos p = new Pedidos();
                    p.setIdUsuarioFk(idUsuario);
                    p.setStatus("ABERTO");
                    p.setValorTotal(0.0);
                    return pedidosRepository.save(p);
                });

        
        itensPedidosService.adicionarOuAtualizarItem(pedido.getIdPedido(), idProduto, quantidade, precoUnitario);
        atualizarFinanceiro(pedido.getIdPedido());
    }

    @Transactional
    public void removerItemDoCarrinho(Integer idItemPedido, Long idPedido) {
        itensPedidosService.remover(idItemPedido);
        atualizarFinanceiro(idPedido);
    }

    public void atualizarFinanceiro(Long idPedido) {
        List<ItensPedidos> itens = itensPedidosService.listarPorPedido(idPedido);
        double total = itens.stream()
                .mapToDouble(i -> i.getPrecoUnitario().doubleValue() * i.getQuantidade())
                .sum();

        Pedidos pedido = pedidosRepository.findById(idPedido).get();
        pedido.setValorTotal(total);
        pedidosRepository.save(pedido);
    }
    public Pedidos buscarCarrinhoAberto(Long idUsuario) {
        return pedidosRepository.findByIdUsuarioFkAndStatus(idUsuario, "ABERTO")
                .orElseThrow(() -> new RuntimeException("Carrinho vazio ou não encontrado para este utilizador"));
    }
    @Transactional
    public void finalizarPedido(Long idPedido) {
        Pedidos pedido = pedidosRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (!"ABERTO".equals(pedido.getStatus())) {
            throw new RuntimeException("Este pedido não pode ser finalizado pois seu status é: " + pedido.getStatus());
        }

        List<ItensPedidos> itens = itensPedidosService.listarPorPedido(idPedido);
        if (itens.isEmpty()) {
            throw new RuntimeException("Não é possível finalizar um pedido sem itens");
        }

        pedido.setStatus("FINALIZADO");
        pedidosRepository.save(pedido);
    }
   
    public List<ItensPedidos> buscarItensPorPedido(Long idPedido) {
        return itensPedidosRepository.findByIdPedidoFk(idPedido);
    }
   
   
}