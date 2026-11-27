package com.mycompany.polizas.arq.controlador;

import com.mycompany.polizas.arq.modelo.BeneficiarioPoliza;
import com.mycompany.polizas.arq.modelo.BeneficiarioPolizaId;
import com.mycompany.polizas.arq.servicios.BeneficiarioService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @GetMapping("/beneficiarios/db")
    public List<BeneficiarioPoliza> getAllFromDatabase() {
        return beneficiarioService.obtenerTodosDesdeDb();
    }

    @GetMapping("/beneficiario/{fecha}/{clave}/{nombres}/{primer}/{segundo}")
    public BeneficiarioPoliza consultarEnApi(
            @PathVariable String fecha,
            @PathVariable String clave,
            @PathVariable String nombres,
            @PathVariable String primer,
            @PathVariable String segundo) {
        return beneficiarioService.consultarEnApi(
                LocalDate.parse(fecha),
                UUID.fromString(clave),
                nombres,
                primer,
                segundo);
    }

    // Jahir - Alta de Beneficiario (consistente con Sistema Dueño y BD local)
    public BeneficiarioPoliza registrarBeneficiario(BeneficiarioPoliza beneficiario) {
        return beneficiarioService.registrarBeneficiario(beneficiario);
    }

    // Jahir - Consulta de Beneficiarios por clave de póliza (BD local)
    public List<BeneficiarioPoliza> buscarBeneficiariosPorPoliza(UUID clavePoliza) {
        return beneficiarioService.obtenerPorClavePolizaDb(clavePoliza);
    }

    // Jahir - Consulta puntual de Beneficiario por llave compuesta (BD local)
    public Optional<BeneficiarioPoliza> buscarBeneficiarioPorId(BeneficiarioPolizaId id) {
        return beneficiarioService.buscarPorIdDb(id);
    }
}