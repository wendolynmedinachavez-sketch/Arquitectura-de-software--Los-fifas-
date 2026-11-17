package com.mycompany.polizas.arq;

import com.formdev.flatlaf.FlatDarkLaf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mycompany.polizas.arq.vista.MenuPrincipal;
import javax.swing.SwingUtilities;

@SpringBootApplication
public class PolizasArq {

    public static void main(String[] args) {
//        SpringApplication.run(PolizasArq.class, args);

     try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
        
        
        
        
//        // Esta líneaarranca Spring Boot (conecta la Base de Datos y lee las Entidades)
//        SpringApplication.run(PolizasArq.class, args);
//        
//        System.out.println("=================================================");
//        System.out.println("¡SISTEMA INTERMEDIARIO DE PÓLIZAS INICIADO!");
//        System.out.println("Conectado a PostgreSQL y listo para usar Swing.");
//        System.out.println("=================================================");
    }
}