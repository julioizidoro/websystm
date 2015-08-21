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

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "fornecedorcidade")
@NamedQueries({
    @NamedQuery(name = "Fornecedorcidade.findAll", query = "SELECT f FROM Fornecedorcidade f")})
public class Fornecedorcidade implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Coprodutos> coprodutosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfornecedorcidade")
    private Integer idfornecedorcidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Fornecedorcidadeidioma> fornecedorcidadeidiomaList;
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    @ManyToOne(optional = false)
    private Cidade cidade;
    @JoinColumn(name = "fornecedor_idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos")
    @ManyToOne(optional = false)
    private Produtos produtos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Fornecedorferias> fornecedorferiasList;

    public Fornecedorcidade() {
    }

    public Fornecedorcidade(Integer idfornecedorcidade) {
        this.idfornecedorcidade = idfornecedorcidade;
    }

    public Integer getIdfornecedorcidade() {
        return idfornecedorcidade;
    }

    public void setIdfornecedorcidade(Integer idfornecedorcidade) {
        this.idfornecedorcidade = idfornecedorcidade;
    }

    public List<Fornecedorcidadeidioma> getFornecedorcidadeidiomaList() {
        return fornecedorcidadeidiomaList;
    }

    public void setFornecedorcidadeidiomaList(List<Fornecedorcidadeidioma> fornecedorcidadeidiomaList) {
        this.fornecedorcidadeidiomaList = fornecedorcidadeidiomaList;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public List<Fornecedorferias> getFornecedorferiasList() {
        return fornecedorferiasList;
    }

    public void setFornecedorferiasList(List<Fornecedorferias> fornecedorferiasList) {
        this.fornecedorferiasList = fornecedorferiasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedorcidade != null ? idfornecedorcidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedorcidade)) {
            return false;
        }
        Fornecedorcidade other = (Fornecedorcidade) object;
        if ((this.idfornecedorcidade == null && other.idfornecedorcidade != null) || (this.idfornecedorcidade != null && !this.idfornecedorcidade.equals(other.idfornecedorcidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Fornecedorcidade[ idfornecedorcidade=" + idfornecedorcidade + " ]";
    }

    public List<Coprodutos> getCoprodutosList() {
        return coprodutosList;
    }

    public void setCoprodutosList(List<Coprodutos> coprodutosList) {
        this.coprodutosList = coprodutosList;
    }
    
}
