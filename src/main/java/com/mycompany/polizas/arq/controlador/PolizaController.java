package com.mycompany.polizas.arq.controlador;

import com.mycompany.polizas.arq.modelo.Poliza;
import com.mycompany.polizas.arq.servicios.PolizaService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PolizaController {

    @Autowired
    private PolizaService polizaService;

    @GetMapping("/polizas")
    public List<Poliza> getAllFromApi() {
        return polizaService.obtenerTodasDesdeApi();
    }

    @GetMapping("/polizas/db")
    public List<Poliza> getAllFromDatabase() {
        return polizaService.obtenerTodasDesdeDb();
    }

    @GetMapping("/poliza/{clave}")
    public Poliza getPolizaFromApi(@PathVariable String clave) {
        return polizaService.buscarPorClaveEnApi(UUID.fromString(clave));
    }

    // Wendolyn - Programa aquí el Alta de Póliza
    public Poliza registrarPoliza(Poliza poliza) {
        return polizaService.registrarPoliza(poliza);
    }

    // Jahir - Programa aquí la Modificación de Póliza
    public Poliza modificarPoliza(Poliza poliza) {
        return polizaService.modificarPoliza(poliza);
    }

    // Ángel - Programa aquí la Consulta de Póliza (BD local)
    public Optional<Poliza> buscarPolizaPorClave(UUID clave) {
        return polizaService.buscarPorClaveDb(clave);
    }

    // Dany - Programa aquí la Baja de Póliza
    public void eliminarPoliza(UUID clave) {
        polizaService.eliminarPoliza(clave);
    }
}