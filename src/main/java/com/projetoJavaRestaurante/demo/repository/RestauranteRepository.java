package com.projetoJavaRestaurante.demo.repository;

import com.projetoJavaRestaurante.demo.model.Restaurante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CrudRepository<Restaurante, Integer> {
    Restaurante findById(int id);
}
