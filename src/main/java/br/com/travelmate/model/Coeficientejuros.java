/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "coeficientejuros")
@NamedQueries({
    @NamedQuery(name = "Coeficientejuros.findAll", query = "SELECT c FROM Coeficientejuros c")})
public class Coeficientejuros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcoeficienteJuros")
    private Integer idcoeficienteJuros;
    @Column(name = "numeroParcelas")
    private Integer numeroParcelas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "juros")
    private Double juros;
    @Column(name = "coeficiente")
    private Double coeficiente;

    public Coeficientejuros() {
    }

    public Coeficientejuros(Integer idcoeficienteJuros) {
        this.idcoeficienteJuros = idcoeficienteJuros;
    }

    public Integer getIdcoeficienteJuros() {
        return idcoeficienteJuros;
    }

    public void setIdcoeficienteJuros(Integer idcoeficienteJuros) {
        this.idcoeficienteJuros = idcoeficienteJuros;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcoeficienteJuros != null ? idcoeficienteJuros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coeficientejuros)) {
            return false;
        }
        Coeficientejuros other = (Coeficientejuros) object;
        if ((this.idcoeficienteJuros == null && other.idcoeficienteJuros != null) || (this.idcoeficienteJuros != null && !this.idcoeficienteJuros.equals(other.idcoeficienteJuros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Coeficientejuros[ idcoeficienteJuros=" + idcoeficienteJuros + " ]";
    }
    
}
