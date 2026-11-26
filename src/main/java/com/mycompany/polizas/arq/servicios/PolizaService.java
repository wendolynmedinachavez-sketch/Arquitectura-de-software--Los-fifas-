package com.mycompany.polizas.arq.servicios;

import com.mycompany.polizas.arq.modelo.Poliza;
import com.mycompany.polizas.arq.repositorio.PolizaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PolizaService {

    private static final String API_URL = "http://nachintoch.mx:8080";

    @Autowired
    private PolizaRepository polizaRepository;

    public List<Poliza> obtenerTodasDesdeApi() {
        RestClient clienteRest = RestClient.create();
        return clienteRest.get()
                .uri(API_URL + "/polizas")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Poliza>>() {
                });
    }

    public List<Poliza> obtenerTodasDesdeDb() {
        return polizaRepository.findAll();
    }

    public Optional<Poliza> buscarPorClave(UUID clave) {
        return polizaRepository.findById(clave);
    }

    public Poliza guardarPoliza(Poliza poliza) {
        return polizaRepository.save(poliza);
    }

    public void eliminarPoliza(UUID clave) {
        polizaRepository.deleteById(clave);
    }
}