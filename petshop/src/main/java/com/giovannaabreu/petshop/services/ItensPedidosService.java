package com.giovannaabreu.petshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovannaabreu.petshop.entities.ItensPedidos;
import com.giovannaabreu.petshop.entities.Produtos;
import com.giovannaabreu.petshop.repositories.ItensPedidosRepository;
import com.giovannaabreu.petshop.repositories.ProdutosRepository;

@Service
public class ItensPedidosService {

    @Autowired private ItensPedidosRepository itensPedidosRepository;
    @Autowired private ProdutosRepository produtoRepository;

    public void adicionarOuAtualizarItem(Long idPedido, Integer idProduto, Integer quantidade, Integer PrecoUnitario) {
        Produtos produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Optional<ItensPedidos> itemExistente = itensPedidosRepository
                .findByIdPedidoFkAndIdProdutoFk(idPedido, idProduto);

        if (itemExistente.isPresent()) {
            ItensPedidos item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() + quantidade);
            itensPedidosRepository.save(item);
        } else {
            ItensPedidos novoItem = new ItensPedidos();
            novoItem.setIdPedidoFk(idPedido);
            novoItem.setIdProdutoFk(idProduto);
            novoItem.setNomeProduto(produto.getNome());
            novoItem.setQuantidade(quantidade);
            novoItem.setPrecoUnitario(PrecoUnitario);
            itensPedidosRepository.save(novoItem);
        }
    }

    public List<ItensPedidos> listarPorPedido(Long idPedido) {
        return itensPedidosRepository.findByIdPedidoFk(idPedido);
    }

    public void remover(Integer idItemPedido) {
        itensPedidosRepository.deleteById(idItemPedido);
    }
   
    public void limparItensDoPedido(Long idPedido) {
        List<ItensPedidos> itens = itensPedidosRepository.findByIdPedidoFk(idPedido);
        itensPedidosRepository.deleteAll(itens);
    }

}
