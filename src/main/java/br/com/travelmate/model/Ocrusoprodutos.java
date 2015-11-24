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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "ocrusoprodutos")
@NamedQueries({
    @NamedQuery(name = "Ocrusoprodutos.findAll", query = "SELECT o FROM Ocrusoprodutos o")})
public class Ocrusoprodutos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idocrusoprodutos")
    private Integer idocrusoprodutos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "numerosemanas")
    private Double numerosemanas;
    @Column(name = "valorpromocional")
    private Float valorpromocional;
    @Column(name = "valororiginal")
    private Float valororiginal;
    @JoinColumn(name = "valorcoprodutos_idvalorcoprodutos", referencedColumnName = "idvalorcoprodutos")
    @ManyToOne(optional = false)
    private Valorcoprodutos valorcoprodutos;

    public Ocrusoprodutos() {
    }

    public Ocrusoprodutos(Integer idocrusoprodutos) {
        this.idocrusoprodutos = idocrusoprodutos;
    }

    public Integer getIdocrusoprodutos() {
        return idocrusoprodutos;
    }

    public void setIdocrusoprodutos(Integer idocrusoprodutos) {
        this.idocrusoprodutos = idocrusoprodutos;
    }

    public Double getNumerosemanas() {
        return numerosemanas;
    }

    public void setNumerosemanas(Double numerosemanas) {
        this.numerosemanas = numerosemanas;
    }

    public Float getValorpromocional() {
        return valorpromocional;
    }

    public void setValorpromocional(Float valorpromocional) {
        this.valorpromocional = valorpromocional;
    }

    public Float getValororiginal() {
        return valororiginal;
    }

    public void setValororiginal(Float valororiginal) {
        this.valororiginal = valororiginal;
    }

    public Valorcoprodutos getValorcoprodutos() {
        return valorcoprodutos;
    }

    public void setValorcoprodutos(Valorcoprodutos valorcoprodutos) {
        this.valorcoprodutos = valorcoprodutos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idocrusoprodutos != null ? idocrusoprodutos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocrusoprodutos)) {
            return false;
        }
        Ocrusoprodutos other = (Ocrusoprodutos) object;
        if ((this.idocrusoprodutos == null && other.idocrusoprodutos != null) || (this.idocrusoprodutos != null && !this.idocrusoprodutos.equals(other.idocrusoprodutos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Ocrusoprodutos[ idocrusoprodutos=" + idocrusoprodutos + " ]";
    }
    
}
