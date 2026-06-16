package com.giovannaabreu.petshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giovannaabreu.petshop.entities.ItensPedidos;
import com.giovannaabreu.petshop.services.ItensPedidosService;
import com.giovannaabreu.petshop.services.PedidosService;

@RestController
@RequestMapping("/api/carrinho")
public class ItensPedidosController {

    @Autowired
    private PedidosService pedidosService;
    private ItensPedidosService itensPedidosService;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(

            @RequestParam Long idUsuario,
            @RequestParam Integer idProdutos,
            @RequestParam Integer quantidade,
            @RequestParam Integer precoUnitario
    ) {

        pedidosService.adicionarAoCarrinho(
                idUsuario,
                idProdutos,
                quantidade,
                precoUnitario
        );

        return ResponseEntity.ok(
                "Produto adicionado"
        );
    }


    @GetMapping("/{idPedido}")
    public ResponseEntity<List<ItensPedidos>> listar(
            @PathVariable Long idPedido
    ) {

        return ResponseEntity.ok(
                pedidosService.buscarItensPorPedido(idPedido)
        );
    }


    @DeleteMapping("/{idItemPedido}/{idPedido}")
    public ResponseEntity<String> remover(

            @PathVariable Integer idItensPedidos,
            @PathVariable Long idPedido
    ) {

        pedidosService.removerItemDoCarrinho(
                idItensPedidos,
                idPedido
        );

        return ResponseEntity.ok(
                "Item removido"
        );
    }
    
    @PutMapping("/quantidade/{idItem}/{idPedido}")
    public ResponseEntity<String> alterarQuantidade(

            @PathVariable Long idPedido,
            @RequestParam Integer quantidade,
            @RequestParam Integer idProdutos,
            @RequestParam Integer precoUnitario
    ) {

        itensPedidosService.adicionarOuAtualizarItem(
                idPedido,
                quantidade,
                idProdutos,
                precoUnitario
        );

        return ResponseEntity.ok(
                "Quantidade atualizada"
        );
    }
    
}