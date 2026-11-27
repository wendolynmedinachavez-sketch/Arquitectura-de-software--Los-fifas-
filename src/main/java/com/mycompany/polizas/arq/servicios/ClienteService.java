package com.mycompany.polizas.arq.servicios;

import com.mycompany.polizas.arq.modelo.Cliente;
import com.mycompany.polizas.arq.repositorio.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ClienteService {

    private static final String URL_API_EXTERNA = "http://nachintoch.mx:8080";

    @Autowired
    private ClienteRepository clienteRepository;

    private final RestClient restClient;

    public ClienteService() {
        this.restClient = RestClient.builder()
                .baseUrl(URL_API_EXTERNA)
                .build();
    }

    // GET /cliente/{curp} desde nachintoch.mx
    public Cliente buscarPorCurpEnApi(String curp) {
        try {
            return restClient.get()
                    .uri("/cliente/{curp}", curp.trim())
                    .retrieve()
                    .body(Cliente.class);
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al consultar cliente: " + ex.getMessage(), ex);
        }
    }

    // Alta consistente: POST /cliente/{curp}/{direccion}/{fecha}/{nombres}/{primer}/{segundo} + BD local
    public Cliente registrarCliente(Cliente cliente) {
        String segApellido = (cliente.getSegundoApellido() == null || cliente.getSegundoApellido().isBlank())
                ? "null"
                : cliente.getSegundoApellido().trim();
        try {
            restClient.post()
                    .uri("/cliente/{curp}/{direccion}/{fecha}/{nombres}/{primer}/{segundo}",
                            cliente.getCurp().trim(),
                            cliente.getDireccion().trim(),
                            cliente.getFechaNacimiento().toString(),
                            cliente.getNombres().trim(),
                            cliente.getPrimerApellido().trim(),
                            segApellido)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al registrar cliente: " + ex.getMessage(), ex);
        }
        return clienteRepository.save(cliente);
    }

    // Modificación consistente: PUT /cliente/{curp}/{direccion}/{fecha}/{nombres}/{primer}/{segundo} + BD local
    public Cliente modificarCliente(Cliente cliente) {
        String segApellido = (cliente.getSegundoApellido() == null || cliente.getSegundoApellido().isBlank())
                ? "null"
                : cliente.getSegundoApellido().trim();
        try {
            restClient.put()
                    .uri("/cliente/{curp}/{direccion}/{fecha}/{nombres}/{primer}/{segundo}",
                            cliente.getCurp().trim(),
                            cliente.getDireccion().trim(),
                            cliente.getFechaNacimiento().toString(),
                            cliente.getNombres().trim(),
                            cliente.getPrimerApellido().trim(),
                            segApellido)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al modificar cliente: " + ex.getMessage(), ex);
        }
        return clienteRepository.save(cliente);
    }

    // Consultas en PostgreSQL local
    public List<Cliente> obtenerTodosDesdeDb() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorCurpDb(String curp) {
        return clienteRepository.findById(curp);
    }
}