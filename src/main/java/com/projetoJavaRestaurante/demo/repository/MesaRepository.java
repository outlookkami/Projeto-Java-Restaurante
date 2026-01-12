package com.projetoJavaRestaurante.demo.repository;

import com.projetoJavaRestaurante.demo.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

}