package com.mycompany.polizas.arq.servicios;

import com.mycompany.polizas.arq.modelo.BeneficiarioPoliza;
import com.mycompany.polizas.arq.modelo.BeneficiarioPolizaId;
import com.mycompany.polizas.arq.repositorio.BeneficiarioPolizaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BeneficiarioService {

    private static final String URL_API_EXTERNA = "http://nachintoch.mx:8080";

    @Autowired
    private BeneficiarioPolizaRepository beneficiarioRepository;

    private final RestClient restClient;

    public BeneficiarioService() {
        this.restClient = RestClient.builder()
                .baseUrl(URL_API_EXTERNA)
                .build();
    }

    // Alta  envía POST al Sistemao y guarda en PostgreSQL local
    public BeneficiarioPoliza registrarBeneficiario(BeneficiarioPoliza beneficiario) {
        BeneficiarioPolizaId id = beneficiario.getId();
        if (id == null || id.getClave() == null) {
            throw new IllegalArgumentException("Los datos de identificación del beneficiario son obligatorios.");
        }

        int porcentajeNuevo = beneficiario.getPorcentaje() != null ? beneficiario.getPorcentaje() : 0;
        if (porcentajeNuevo <= 0 || porcentajeNuevo > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 1 y 100.");
        }

        int acumulado = obtenerPorClavePolizaDb(id.getClave()).stream()
                .filter(b -> !b.getId().equals(id))
                .mapToInt(BeneficiarioPoliza::getPorcentaje)
                .sum();

        if (acumulado + porcentajeNuevo > 100) {
            throw new IllegalStateException("La suma de porcentajes de la póliza excede el 100% (disponible: "
                    + (100 - acumulado) + "%).");
        }

        String segApellido = (id.getSegundoApellido() == null || id.getSegundoApellido().isBlank())
                ? "null"
                : id.getSegundoApellido().trim();

        try {
            restClient.post()
                    .uri("/beneficiario/{fecha}/{clave}/{porcentaje}/{nombres}/{primer}/{segundo}",
                            id.getFechaNacimiento().toString(),
                            id.getClave().toString(),
                            porcentajeNuevo,
                            id.getNombres().trim(),
                            id.getPrimerApellido().trim(),
                            segApellido)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible (nachintoch.mx): " + ex.getMessage(), ex);
        }

        return beneficiarioRepository.save(beneficiario);
    }

    // Consulta exacta en el Sistema nachintoch (GET /beneficiario/{fecha}/{clave}/{nombres}/{primer}/{segundo})
    public BeneficiarioPoliza consultarEnApi(LocalDate fechaNacimiento, UUID clavePoliza,
            String nombres, String primerApellido, String segundoApellido) {
        String segApellido = (segundoApellido == null || segundoApellido.isBlank())
                ? "null"
                : segundoApellido.trim();
        try {
            return restClient.get()
                    .uri("/beneficiario/{fecha}/{clave}/{nombres}/{primer}/{segundo}",
                            fechaNacimiento.toString(),
                            clavePoliza.toString(),
                            nombres.trim(),
                            primerApellido.trim(),
                            segApellido)
                    .retrieve()
                    .body(BeneficiarioPoliza.class);
        } catch (Exception ex) {
            throw new RuntimeException("Sistema Dueño no disponible al consultar beneficiario: " + ex.getMessage(), ex);
        }
    }

    // Consultas en PostgreSQL local
    public List<BeneficiarioPoliza> obtenerTodosDesdeDb() {
        return beneficiarioRepository.findAll();
    }

    public List<BeneficiarioPoliza> obtenerPorClavePolizaDb(UUID clavePoliza) {
        return beneficiarioRepository.findAll().stream()
                .filter(b -> b.getId() != null && clavePoliza.equals(b.getId().getClave()))
                .collect(Collectors.toList());
    }

    public Optional<BeneficiarioPoliza> buscarPorIdDb(BeneficiarioPolizaId id) {
        return beneficiarioRepository.findById(id);
    }
}