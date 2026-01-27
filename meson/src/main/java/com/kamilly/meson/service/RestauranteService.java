package com.kamilly.meson.service;

import com.kamilly.meson.dto.request.CadastroDTO;
import com.kamilly.meson.dto.request.RestauranteReqDTO;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CnpjService cnpjService;

    public RestauranteService(RestauranteRepository restauranteRepository, CnpjService cnpjService) {
        this.restauranteRepository = restauranteRepository;
        this.cnpjService = cnpjService;
    }

    public Restaurante salvarRestaurante(CadastroDTO cadastroDTO) {
        String cnpjLimpo = cadastroDTO.getCnpj().replaceAll("\\D", "");
        String telLimpo = cadastroDTO.getTelefone().replaceAll("\\D", "");

        RestauranteReqDTO api = cnpjService.getCnpj(cadastroDTO.getCnpj());

        Restaurante restaurante = new Restaurante();
        restaurante.setCnpj(cnpjLimpo);
        restaurante.setTelefone(telLimpo);
        restaurante.setRazaoSocial(api.getRazaoSocial());
        restaurante.setNomeFantasia(api.getNomeFantasia());
        restaurante.setCnae(api.getCnae());
        restaurante.setDescricaoCnae(api.getDescricaoCnae());

        return restauranteRepository.save(restaurante);
    }
}
