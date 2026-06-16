package com.giovannaabreu.petshop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giovannaabreu.petshop.entities.Produtos;
import com.giovannaabreu.petshop.services.ProdutosService;



@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {
    
    @Autowired
    private final ProdutosService service;
    
    public ProdutosController(ProdutosService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<Produtos> getAll() {
        return service.findAll();
    }
    
    @GetMapping ("/{id}")
    public Optional<Produtos> getById(@PathVariable Integer id) {
        return service.findById(id);
    }
    
    @PostMapping
    public Produtos create(@RequestBody Produtos produtos) {
        return service.save(produtos);
    }

    @PutMapping("/{id}")
    public Produtos update(@PathVariable Integer id, @RequestBody Produtos produtos) {
        produtos.setIdProdutos(id);
        return service.save(produtos);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
