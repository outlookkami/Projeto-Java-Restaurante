package com.projetoJavaRestaurante.demo.service;

import com.projetoJavaRestaurante.demo.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

    @Autowired
    private MesaRepository repository;
}
