package com.mycompany.polizas.arq.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "poliza")
public class Poliza {

    @Id
    @Column(name = "clave", length = 36, nullable = false)
    private UUID clave;

    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @Column(name = "monto", precision = 9, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "descripcion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curp_cliente", nullable = false)
    private Cliente cliente;

    public Poliza() {
    }

    public UUID getClave() {
        return clave;
    }

    public void setClave(UUID clave) {
        this.clave = clave;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Poliza)) {
            return false;
        }
        Poliza otra = (Poliza) obj;
        return Objects.equals(clave, otra.clave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clave);
    }
}