package com.mycompany.polizas.arq.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class BeneficiarioPolizaId implements Serializable {

    @Column(name = "clave_poliza", nullable = false)
    private UUID clave;

    @Column(name = "nombres", length = 128, nullable = false)
    private String nombres;

    @Column(name = "primer_apellido", length = 128, nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 128, nullable = false)
    private String segundoApellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    public BeneficiarioPolizaId() {
    }

    public BeneficiarioPolizaId(UUID clave, String nombres,
            String primerApellido, String segundoApellido,
            LocalDate fechaNacimiento) {
        this.clave = clave;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public UUID getClave() {
        return clave;
    }

    public void setClave(UUID clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BeneficiarioPolizaId)) {
            return false;
        }
        BeneficiarioPolizaId otro = (BeneficiarioPolizaId) obj;
        boolean segApeIgual = (segundoApellido == null
                && otro.segundoApellido == null)
                || (segundoApellido != null
                && segundoApellido.equalsIgnoreCase(otro.segundoApellido));

        return Objects.equals(clave, otro.clave) &&
               nombres != null && nombres.equalsIgnoreCase(otro.nombres) &&
               primerApellido != null &&
               primerApellido.equalsIgnoreCase(otro.primerApellido) &&
               segApeIgual &&
               Objects.equals(fechaNacimiento, otro.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clave, nombres, primerApellido,
                segundoApellido, fechaNacimiento);
    }
}