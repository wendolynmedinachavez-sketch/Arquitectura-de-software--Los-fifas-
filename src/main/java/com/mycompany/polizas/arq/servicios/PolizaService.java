package com.mycompany.polizas.arq.servicios;

import com.mycompany.polizas.arq.modelo.Poliza;
import com.mycompany.polizas.arq.repositorio.PolizaRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PolizaService {

    private static final String URL_API_EXTERNA = "http://nachintoch.mx:8080";

    @Autowired
    private PolizaRepository polizaRepository;

    private final RestClient restClient;

    public PolizaService() {
        this.restClient = RestClient.builder()
                .baseUrl(URL_API_EXTERNA)
                .build();
    }

    // GET /polizas desde nachintoch.mx
    public List<Poliza> obtenerTodasDesdeApi() {
        try {
            Poliza[] respuesta = restClient.get()
                    .uri("/polizas")
                    .retrieve()
                    .body(Poliza[].class);
            return respuesta != null ? Arrays.asList(respuesta) : Collections.emptyList();
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al listar pólizas: " + ex.getMessage(), ex);
        }
    }

    // GET /poliza/{clave} desde nachintoch.mx
    public Poliza buscarPorClaveEnApi(UUID clave) {
        try {
            return restClient.get()
                    .uri("/poliza/{clave}", clave.toString())
                    .retrieve()
                    .body(Poliza.class);
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible o póliza no encontrada: " + ex.getMessage(), ex);
        }
    }

    // Alta consistente: POST /poliza/{clave}/{tipo}/{monto}/{descripcion}/{curp_cliente} + BD local
    public Poliza registrarPoliza(Poliza poliza) {
        if (poliza.getClave() == null) {
            poliza.setClave(UUID.randomUUID());
        }
        try {
            restClient.post()
                    .uri("/poliza/{clave}/{tipo}/{monto}/{descripcion}/{curp}",
                            poliza.getClave().toString(),
                            poliza.getTipo(),
                            poliza.getMonto().toPlainString(),
                            poliza.getDescripcion().trim(),
                            poliza.getCliente().getCurp().trim())
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al registrar póliza: " + ex.getMessage(), ex);
        }
        return polizaRepository.save(poliza);
    }

    // Modificación consistente  BD local
    public Poliza modificarPoliza(Poliza poliza) {
        try {
            restClient.put()
                    .uri("/poliza/{clave}/{tipo}/{monto}/{descripcion}/{curp}",
                            poliza.getClave().toString(),
                            poliza.getTipo(),
                            poliza.getMonto().toPlainString(),
                            poliza.getDescripcion().trim(),
                            poliza.getCliente().getCurp().trim())
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al modificar póliza: " + ex.getMessage(), ex);
        }
        return polizaRepository.save(poliza);
    }

    // Baja consistente: DELETE /poliza/{clave} + BD local
    public void eliminarPoliza(UUID clave) {
        try {
            restClient.delete()
                    .uri("/poliza/{clave}", clave.toString())
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al eliminar póliza: " + ex.getMessage(), ex);
        }
        polizaRepository.deleteById(clave);
    }

    // Consultas en PostgreSQL local
    public List<Poliza> obtenerTodasDesdeDb() {
        return polizaRepository.findAll();
    }

    public Optional<Poliza> buscarPorClaveDb(UUID clave) {
        return polizaRepository.findById(clave);
    }
}