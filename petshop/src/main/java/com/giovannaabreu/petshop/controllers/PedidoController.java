package com.giovannaabreu.petshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giovannaabreu.petshop.entities.Pedidos;
import com.giovannaabreu.petshop.services.PedidosService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidosService pedidosService;

    @GetMapping("/carrinho/{usuarioId}")
    public ResponseEntity<Pedidos> obterCarrinho(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidosService.buscarCarrinhoAberto(usuarioId));
    }

    @PutMapping("/finalizar/{idPedido}")
    public ResponseEntity<String> finalizarPedido(@PathVariable Long idPedido) {
        try {
            pedidosService.finalizarPedido(idPedido);
            return ResponseEntity.ok("Pedido finalizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
