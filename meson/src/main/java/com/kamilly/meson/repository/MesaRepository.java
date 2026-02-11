package com.kamilly.meson.repository;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    Optional<Mesa> findByIdAndRestaurante(Long id, Restaurante restaurante);

    List<Mesa> findAllByRestaurante(Restaurante restaurante);
}
