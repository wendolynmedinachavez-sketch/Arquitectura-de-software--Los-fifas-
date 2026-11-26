package com.mycompany.polizas.arq.servicios;

import com.mycompany.polizas.arq.modelo.Cliente;
import com.mycompany.polizas.arq.repositorio.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ClienteService {

    private static final String API_URL = "http://nachintoch.mx:8080";

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosDesdeApi() {
        RestClient clienteRest = RestClient.create();
        return clienteRest.get()
                .uri(API_URL + "/clientes")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Cliente>>() {
                });
    }

    public List<Cliente> obtenerTodosDesdeDb() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorCurp(String curp) {
        return clienteRepository.findById(curp);
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}