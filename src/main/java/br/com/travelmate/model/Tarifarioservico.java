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
@Table(name = "tarifarioservico")
@NamedQueries({
    @NamedQuery(name = "Tarifarioservico.findAll", query = "SELECT t FROM Tarifarioservico t")})
public class Tarifarioservico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtarifarioservico")
    private Integer idtarifarioservico;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Column(name = "numerosemanas")
    private Integer numerosemanas;

    public Tarifarioservico() {
    }

    public Tarifarioservico(Integer idtarifarioservico) {
        this.idtarifarioservico = idtarifarioservico;
    }

    public Integer getIdtarifarioservico() {
        return idtarifarioservico;
    }

    public void setIdtarifarioservico(Integer idtarifarioservico) {
        this.idtarifarioservico = idtarifarioservico;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getNumerosemanas() {
        return numerosemanas;
    }

    public void setNumerosemanas(Integer numerosemanas) {
        this.numerosemanas = numerosemanas;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarifarioservico != null ? idtarifarioservico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tarifarioservico)) {
            return false;
        }
        Tarifarioservico other = (Tarifarioservico) object;
        if ((this.idtarifarioservico == null && other.idtarifarioservico != null) || (this.idtarifarioservico != null && !this.idtarifarioservico.equals(other.idtarifarioservico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Tarifarioservico[ idtarifarioservico=" + idtarifarioservico + " ]";
    }
    
}
