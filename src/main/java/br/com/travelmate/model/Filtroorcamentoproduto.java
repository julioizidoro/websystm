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
@Table(name = "filtroorcamentoproduto")
@NamedQueries({
    @NamedQuery(name = "Filtroorcamentoproduto.findAll", query = "SELECT f FROM Filtroorcamentoproduto f")})
public class Filtroorcamentoproduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfiltroOrcamentoProduto")
    private Integer idfiltroOrcamentoProduto;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos")
    @ManyToOne(optional = false)
    private Produtos produtos;
    @JoinColumn(name = "produtosOrcamento_idprodutosOrcamento", referencedColumnName = "idprodutosOrcamento")
    @ManyToOne(optional = false)
    private Produtosorcamento produtosorcamento;

    public Filtroorcamentoproduto() {
    }

    public Filtroorcamentoproduto(Integer idfiltroOrcamentoProduto) {
        this.idfiltroOrcamentoProduto = idfiltroOrcamentoProduto;
    }

    public Integer getIdfiltroOrcamentoProduto() {
        return idfiltroOrcamentoProduto;
    }

    public void setIdfiltroOrcamentoProduto(Integer idfiltroOrcamentoProduto) {
        this.idfiltroOrcamentoProduto = idfiltroOrcamentoProduto;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Produtosorcamento getProdutosorcamento() {
        return produtosorcamento;
    }

    public void setProdutosorcamento(Produtosorcamento produtosorcamento) {
        this.produtosorcamento = produtosorcamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfiltroOrcamentoProduto != null ? idfiltroOrcamentoProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filtroorcamentoproduto)) {
            return false;
        }
        Filtroorcamentoproduto other = (Filtroorcamentoproduto) object;
        if ((this.idfiltroOrcamentoProduto == null && other.idfiltroOrcamentoProduto != null) || (this.idfiltroOrcamentoProduto != null && !this.idfiltroOrcamentoProduto.equals(other.idfiltroOrcamentoProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Filtroorcamentoproduto[ idfiltroOrcamentoProduto=" + idfiltroOrcamentoProduto + " ]";
    }
    
}
