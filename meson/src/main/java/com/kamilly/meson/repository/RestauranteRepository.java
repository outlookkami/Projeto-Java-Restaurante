package com.kamilly.meson.repository;

import com.kamilly.meson.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

    List<Restaurante> findByStatus(StatusRestaurante statusRestaurante);
}
