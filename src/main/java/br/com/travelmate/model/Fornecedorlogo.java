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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Kamila Rodrigues
 */
@Entity
@Table(name = "fornecedorlogo")
@NamedQueries({
    @NamedQuery(name = "Fornecedorlogo.findAll", query = "SELECT f FROM Fornecedorlogo f")})
public class Fornecedorlogo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfornecedorlogo")
    private Integer idfornecedorlogo;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @JoinColumn(name = "fornecedor_idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;

    public Fornecedorlogo() {
    }

    public Fornecedorlogo(Integer idfornecedorlogo) {
        this.idfornecedorlogo = idfornecedorlogo;
    }

    public Integer getIdfornecedorlogo() {
        return idfornecedorlogo;
    }

    public void setIdfornecedorlogo(Integer idfornecedorlogo) {
        this.idfornecedorlogo = idfornecedorlogo;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedorlogo != null ? idfornecedorlogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedorlogo)) {
            return false;
        }
        Fornecedorlogo other = (Fornecedorlogo) object;
        if ((this.idfornecedorlogo == null && other.idfornecedorlogo != null) || (this.idfornecedorlogo != null && !this.idfornecedorlogo.equals(other.idfornecedorlogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Fornecedorlogo[ idfornecedorlogo=" + idfornecedorlogo + " ]";
    }
    
}
