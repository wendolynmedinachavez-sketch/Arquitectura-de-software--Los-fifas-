package com.mycompany.polizas.arq.controlador;
import com.mycompany.polizas.arq.modelo.BeneficiarioPoliza;
import com.mycompany.polizas.arq.repositorio.BeneficiarioPolizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import java.util.List;

@RestController
public class BeneficiarioController {

    @Autowired
    private BeneficiarioPolizaRepository beneficiarioRepository;

    //  ver los datos de la API externa
    @GetMapping("/beneficiarios")
    public List<BeneficiarioPoliza> getAllFromApi() {
        RestClient clienteRest = RestClient.create();
        
        List<BeneficiarioPoliza> beneficiarios = clienteRest.get()
                .uri("http://nachintoch.mx:8080/beneficiarios")
                .retrieve()
                .body(new ParameterizedTypeReference<List<BeneficiarioPoliza>>() {});
                
        return beneficiarios;
    }

    //  base de datos local (PostgreSQL)
    @GetMapping("/beneficiarios/db")
    public List<BeneficiarioPoliza> getAllFromDatabase() {
        return beneficiarioRepository.findAll();
    }
}