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
@Table(name = "coprodutos")
@NamedQueries({
    @NamedQuery(name = "Coprodutos.findAll", query = "SELECT c FROM Coprodutos c")})
public class Coprodutos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcoprodutos")
    private Integer idcoprodutos;
    @Size(max = 2)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 10)
    @Column(name = "licoes")
    private String licoes;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "produtosOrcamento_idprodutosOrcamento", referencedColumnName = "idprodutosOrcamento")
    @ManyToOne(optional = false)
    private Produtosorcamento produtosorcamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coprodutos")
    private List<Valorcoprodutos> valorcoprodutosList;

    public Coprodutos() {
    }

    public Coprodutos(Integer idcoprodutos) {
        this.idcoprodutos = idcoprodutos;
    }

    public Integer getIdcoprodutos() {
        return idcoprodutos;
    }

    public void setIdcoprodutos(Integer idcoprodutos) {
        this.idcoprodutos = idcoprodutos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLicoes() {
        return licoes;
    }

    public void setLicoes(String licoes) {
        this.licoes = licoes;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public Produtosorcamento getProdutosorcamento() {
        return produtosorcamento;
    }

    public void setProdutosorcamento(Produtosorcamento produtosorcamento) {
        this.produtosorcamento = produtosorcamento;
    }

    public List<Valorcoprodutos> getValorcoprodutosList() {
        return valorcoprodutosList;
    }

    public void setValorcoprodutosList(List<Valorcoprodutos> valorcoprodutosList) {
        this.valorcoprodutosList = valorcoprodutosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcoprodutos != null ? idcoprodutos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coprodutos)) {
            return false;
        }
        Coprodutos other = (Coprodutos) object;
        if ((this.idcoprodutos == null && other.idcoprodutos != null) || (this.idcoprodutos != null && !this.idcoprodutos.equals(other.idcoprodutos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Coprodutos[ idcoprodutos=" + idcoprodutos + " ]";
    }
    
}
