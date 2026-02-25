package com.kamilly.meson.repository;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

    List<Restaurante> findByStatus(StatusRestaurante statusRestaurante);
}
