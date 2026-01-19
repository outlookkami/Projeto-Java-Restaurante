package com.kamilly.meson.service;

import com.kamilly.meson.dto.request.RestauranteDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CnpjService {
    private final RestTemplate restTemplate =  new RestTemplate();

    public RestauranteDTO getCnpj(String cnpj) {

        String url = "https://brasilapi.com.br/api/cnpj/v1/{cnpj}";
        return restTemplate.getForObject(url, RestauranteDTO.class, cnpj);
    }
}
