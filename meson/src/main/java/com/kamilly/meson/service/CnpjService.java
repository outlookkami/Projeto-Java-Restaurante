package com.kamilly.meson.service;

import com.kamilly.meson.dto.request.RestauranteReqDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CnpjService {
    private final RestTemplate restTemplate =  new RestTemplate();

    public RestauranteReqDTO getCnpj(String cnpj) {

        String url = "https://brasilapi.com.br/api/cnpj/v1/{cnpj}";
        return restTemplate.getForObject(url, RestauranteReqDTO.class, cnpj);
    }
}
