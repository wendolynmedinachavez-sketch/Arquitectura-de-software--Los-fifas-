package com.mycompany.polizas.arq.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "beneficiario_poliza")
public class BeneficiarioPoliza {

    @EmbeddedId
    private BeneficiarioPolizaId id;

    @Column(name = "porcentaje", nullable = false)
    private Integer porcentaje;

    public BeneficiarioPoliza() {
    }

    public BeneficiarioPoliza(BeneficiarioPolizaId id, Integer porcentaje) {
        this.id = id;
        this.porcentaje = porcentaje;
    }

    public BeneficiarioPolizaId getId() {
        return id;
    }

    public void setId(BeneficiarioPolizaId id) {
        this.id = id;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BeneficiarioPoliza)) {
            return false;
        }
        BeneficiarioPoliza otro = (BeneficiarioPoliza) obj;
        return Objects.equals(id, otro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}