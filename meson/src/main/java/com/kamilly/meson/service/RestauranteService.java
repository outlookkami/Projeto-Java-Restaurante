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

    // ANÁLISE RESTAURANTE

    @Transactional(readOnly = true)
    public RestauranteAnaliseResDTO buscaParaAnalise(Integer id) {
        Restaurante resturante = restauranteRepository.findById(id).orElseThrow();
        RestauranteAnaliseResDTO dto = new RestauranteAnaliseResDTO();
        dto.setCnpj(restaurante.getCnpj());
        dto.setNomeFantasia(restaurante.getNomeFantasia());
        dto.setRazaoSocial(restaurante.getRazaoSocial());
        dto.setCnae(restaurante.getCnae());
        dto.setDescricaoCnae(restaurante.getDescricaoCnae());

        return dto;
    }

    @Transactional
    public void aprovarRestaurante(Integer id){
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        Restaurante restaurante = restauranteRepository.findById(id);
        restaurante.setStatus(StatusRestaurante.APROVADO);
        restaurante.setAtivo(true);
        restauranteRepository.save(restaurante);
    }

    @Transactional
    public void recusarRestaurante(Integer id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        restaurante.setStatus(StatusRestaurante.RECUSADO);
        restaurante.setAtivo(false);
        restauranteRepository.save(restaurante);
    }

    // Listagem baseada no status

    public List<Restaurante> listarRestaurantesPendentes(Restaurante restaurante) {
        if (restaurante.getStatus() != StatusRestaurante.PENDENTE) {
            throw new IllegalStateException("Restaurante não pôde ser listado.");
        }
        return restauranteRepository.findByStatus(StatusRestaurante.PENDENTE);
    }

    public List<Restaurante> listarRestaurantesAprovados(Restaurante restaurante) {
        if (restaurante.getStatus() != StatusRestaurante.APROVADO) {
            throw new IllegalStateException("Restaurante não pôde ser listado.");
        }
        return restauranteRepository.findByStatus(StatusRestaurante.APROVADO);
    }

    public List<Restaurante> listarRestaurantesRecusados(StatusRestaurante statusRestaurante) {
        if (statusRestaurante != StatusRestaurante.RECUSADO) {
            throw new IllegalStateException("Restaurante não pôde ser listado.");
        }
        return restauranteRepository.findByStatus(StatusRestaurante.RECUSADO);
    }


}
