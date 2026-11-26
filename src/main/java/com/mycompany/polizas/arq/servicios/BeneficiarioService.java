package com.mycompany.polizas.arq.servicios;

import com.mycompany.polizas.arq.modelo.BeneficiarioPoliza;
import com.mycompany.polizas.arq.repositorio.BeneficiarioPolizaRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BeneficiarioService {

    private static final String API_URL = "http://nachintoch.mx:8080";

    @Autowired
    private BeneficiarioPolizaRepository beneficiarioRepository;

    public List<BeneficiarioPoliza> obtenerTodosDesdeApi() {
        RestClient clienteRest = RestClient.create();
        return clienteRest.get()
                .uri(API_URL + "/beneficiarios")
                .retrieve()
                .body(new ParameterizedTypeReference<List<BeneficiarioPoliza>>() {
                });
    }

    public List<BeneficiarioPoliza> obtenerTodosDesdeDb() {
        return beneficiarioRepository.findAll();
    }

    public BeneficiarioPoliza guardarBeneficiario(
            BeneficiarioPoliza beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    public List<BeneficiarioPoliza> buscarPorClavePoliza(UUID clavePoliza) {
        return beneficiarioRepository.findAll().stream()
                .filter(b -> b.getId() != null
                        && clavePoliza.equals(b.getId().getClave()))
                .toList();
    }
}