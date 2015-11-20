/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.Interface;

import br.com.travelmate.model.Fornecedorcidade;
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

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "programafornecedor")
@NamedQueries({
    @NamedQuery(name = "Programafornecedor.findAll", query = "SELECT p FROM Programafornecedor p")})
public class Programafornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprogramafornecedor")
    private Integer idprogramafornecedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programafornecedor")
    private List<Tarifario> tarifarioList;
    @JoinColumn(name = "programa_idprograma", referencedColumnName = "idprograma")
    @ManyToOne(optional = false)
    private Programa programa;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;

    public Programafornecedor() {
    }

    public Programafornecedor(Integer idprogramafornecedor) {
        this.idprogramafornecedor = idprogramafornecedor;
    }

    public Integer getIdprogramafornecedor() {
        return idprogramafornecedor;
    }

    public void setIdprogramafornecedor(Integer idprogramafornecedor) {
        this.idprogramafornecedor = idprogramafornecedor;
    }

    public List<Tarifario> getTarifarioList() {
        return tarifarioList;
    }

    public void setTarifarioList(List<Tarifario> tarifarioList) {
        this.tarifarioList = tarifarioList;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprogramafornecedor != null ? idprogramafornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programafornecedor)) {
            return false;
        }
        Programafornecedor other = (Programafornecedor) object;
        if ((this.idprogramafornecedor == null && other.idprogramafornecedor != null) || (this.idprogramafornecedor != null && !this.idprogramafornecedor.equals(other.idprogramafornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.Interface.Programafornecedor[ idprogramafornecedor=" + idprogramafornecedor + " ]";
    }
    
}
