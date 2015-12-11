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
@Table(name = "faturafranquias")
@NamedQueries({
    @NamedQuery(name = "Faturafranquias.findAll", query = "SELECT f FROM Faturafranquias f")})
public class Faturafranquias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfaturafranquias")
    private Integer idfaturafranquias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pagomatriz")
    private Float pagomatriz;
    @Column(name = "liquidopagar")
    private Float liquidopagar;
    @JoinColumn(name = "vendascomissao_idvendascomissao", referencedColumnName = "idvendascomissao")
    @ManyToOne(optional = false)
    private Vendascomissao vendascomissao;

    public Faturafranquias() {
    }

    public Faturafranquias(Integer idfaturafranquias) {
        this.idfaturafranquias = idfaturafranquias;
    }

    public Integer getIdfaturafranquias() {
        return idfaturafranquias;
    }

    public void setIdfaturafranquias(Integer idfaturafranquias) {
        this.idfaturafranquias = idfaturafranquias;
    }

    public Float getPagomatriz() {
        return pagomatriz;
    }

    public void setPagomatriz(Float pagomatriz) {
        this.pagomatriz = pagomatriz;
    }

    public Float getLiquidopagar() {
        return liquidopagar;
    }

    public void setLiquidopagar(Float liquidopagar) {
        this.liquidopagar = liquidopagar;
    }

    public Vendascomissao getVendascomissao() {
        return vendascomissao;
    }

    public void setVendascomissao(Vendascomissao vendascomissao) {
        this.vendascomissao = vendascomissao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfaturafranquias != null ? idfaturafranquias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Faturafranquias)) {
            return false;
        }
        Faturafranquias other = (Faturafranquias) object;
        if ((this.idfaturafranquias == null && other.idfaturafranquias != null) || (this.idfaturafranquias != null && !this.idfaturafranquias.equals(other.idfaturafranquias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Faturafranquias[ idfaturafranquias=" + idfaturafranquias + " ]";
    }
    
}
