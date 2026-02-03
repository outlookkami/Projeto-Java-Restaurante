package com.kamilly.meson.repository;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    Optional<Mesa> findByIdAndRestaurante(Long id, Restaurante idRestaurante);

    List<Mesa> findAllByRestaurante(Restaurante idRestaurante);


}
