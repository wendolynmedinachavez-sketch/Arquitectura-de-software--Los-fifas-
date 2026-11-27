package com.mycompany.polizas.arq.controlador;

import com.mycompany.polizas.arq.modelo.Cliente;
import com.mycompany.polizas.arq.servicios.ClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/db")
    public List<Cliente> getAllFromDatabase() {
        return clienteService.obtenerTodosDesdeDb();
    }

    @GetMapping("/cliente/{curp}")
    public Cliente getClienteFromApi(@PathVariable String curp) {
        return clienteService.buscarPorCurpEnApi(curp);
    }

    // Wendolyn - Programa aquí el Alta de Cliente
    public Cliente registrarCliente(Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }

    // Jahir - Programa aquí la Modificación de Cliente
    public Cliente modificarCliente(Cliente cliente) {
        return clienteService.modificarCliente(cliente);
    }

    // Consulta auxiliar por CURP (BD local)
    public Optional<Cliente> buscarClientePorCurpDb(String curp) {
        return clienteService.buscarPorCurpDb(curp);
    }
}