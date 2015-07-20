/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "cobranca")
@NamedQueries({
    @NamedQuery(name = "Cobranca.findAll", query = "SELECT c FROM Cobranca c")})
public class Cobranca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcobranca")
    private Integer idcobranca;
    @Size(max = 15)
    @Column(name = "fone")
    private String fone;
    @JoinColumn(name = "vendas_idvendas", referencedColumnName = "idvendas")
    @ManyToOne(optional = false)
    private Vendas vendas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cobranca")
    private List<Historicocobranca> historicocobrancaList;

    public Cobranca() {
    }

    public Cobranca(Integer idcobranca) {
        this.idcobranca = idcobranca;
    }

    public Integer getIdcobranca() {
        return idcobranca;
    }

    public void setIdcobranca(Integer idcobranca) {
        this.idcobranca = idcobranca;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }
    
    public List<Historicocobranca> getHistoricocobrancaList() {
        return historicocobrancaList;
    }

    public void setHistoricocobrancaList(List<Historicocobranca> historicocobrancaList) {
        this.historicocobrancaList = historicocobrancaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcobranca != null ? idcobranca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cobranca)) {
            return false;
        }
        Cobranca other = (Cobranca) object;
        if ((this.idcobranca == null && other.idcobranca != null) || (this.idcobranca != null && !this.idcobranca.equals(other.idcobranca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Cobranca[ idcobranca=" + idcobranca + " ]";
    }
    
}
