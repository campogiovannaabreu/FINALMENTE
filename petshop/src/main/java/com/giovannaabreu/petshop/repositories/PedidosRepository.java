package com.giovannaabreu.petshop.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.giovannaabreu.petshop.entities.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

    Optional<Pedidos> findByIdUsuarioFkAndStatus(Long idUsuarioFk, String status);

}