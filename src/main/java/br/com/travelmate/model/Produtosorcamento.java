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
@Table(name = "produtosorcamento")
@NamedQueries({
    @NamedQuery(name = "Produtosorcamento.findAll", query = "SELECT p FROM Produtosorcamento p")})
public class Produtosorcamento implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtosorcamento")
    private List<Valoresseguro> valoresseguroList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprodutosOrcamento")
    private Integer idprodutosOrcamento;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;

    public Produtosorcamento() {
    }

    public Produtosorcamento(Integer idprodutosOrcamento) {
        this.idprodutosOrcamento = idprodutosOrcamento;
    }

    public Integer getIdprodutosOrcamento() {
        return idprodutosOrcamento;
    }

    public void setIdprodutosOrcamento(Integer idprodutosOrcamento) {
        this.idprodutosOrcamento = idprodutosOrcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprodutosOrcamento != null ? idprodutosOrcamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtosorcamento)) {
            return false;
        }
        Produtosorcamento other = (Produtosorcamento) object;
        if ((this.idprodutosOrcamento == null && other.idprodutosOrcamento != null) || (this.idprodutosOrcamento != null && !this.idprodutosOrcamento.equals(other.idprodutosOrcamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Produtosorcamento[ idprodutosOrcamento=" + idprodutosOrcamento + " ]";
    }

    public List<Valoresseguro> getValoresseguroList() {
        return valoresseguroList;
    }

    public void setValoresseguroList(List<Valoresseguro> valoresseguroList) {
        this.valoresseguroList = valoresseguroList;
    }
}
