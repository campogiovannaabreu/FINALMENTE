package com.giovannaabreu.petshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giovannaabreu.petshop.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
    Categoria findByNome(String nome);
}
