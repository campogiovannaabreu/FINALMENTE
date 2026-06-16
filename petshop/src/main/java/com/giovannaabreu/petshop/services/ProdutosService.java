 package com.giovannaabreu.petshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.giovannaabreu.petshop.entities.Produtos;
import com.giovannaabreu.petshop.repositories.ProdutosRepository;

@Service
 public class ProdutosService {
     
     private final ProdutosRepository repository;
     
     public ProdutosService(ProdutosRepository repository) {
         this.repository = repository;
     }
     
     public List<Produtos> findAll() {
         return repository.findAll();
     }
     
     public Optional<Produtos> findById(Integer id) {
         return repository.findById(id);
     }
     
     public Produtos save(Produtos produtos) {
         return repository.save(produtos);
     }
     
     public void deleteById(Integer id) {
         repository.deleteById(id);
     }


 }