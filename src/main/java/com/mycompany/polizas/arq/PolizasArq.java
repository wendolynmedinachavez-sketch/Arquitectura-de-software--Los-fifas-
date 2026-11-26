package com.mycompany.polizas.arq;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mycompany.polizas.arq.vista.MenuPrincipal;
import java.awt.EventQueue;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PolizasArq {

    private static ConfigurableApplicationContext contextoSpring;

    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        contextoSpring = new SpringApplicationBuilder(PolizasArq.class)
                .headless(false)
                .run(args);

        System.out.println("=========================================");
        System.out.println("SISTEMA INTERMEDIARIO DE POLIZAS INICIADO");
        System.out.println("Conectado a PostgreSQL y Swing listo.");
        System.out.println("=========================================");

        EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }

    public static <T> T getBean(Class<T> claseBean) {
        return contextoSpring.getBean(claseBean);
    }
}