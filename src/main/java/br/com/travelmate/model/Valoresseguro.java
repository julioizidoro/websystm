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
import javax.validation.constraints.Size;

/**
 *
 * @author Kamila Rodrigues
 */
@Entity
@Table(name = "valoresseguro")
@NamedQueries({
    @NamedQuery(name = "Valoresseguro.findAll", query = "SELECT v FROM Valoresseguro v")})
public class Valoresseguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvaloresseguro")
    private Integer idvaloresseguro;
    @Size(max = 50)
    @Column(name = "plano")
    private String plano;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valornet")
    private Float valornet;
    @Column(name = "valorgross")
    private Float valorgross;
    @Column(name = "comissaoexpress")
    private Float comissaoexpress;
    @Column(name = "comissaopremium")
    private Float comissaopremium;
    @Size(max = 30)
    @Column(name = "situacao")
    private String situacao;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "produtosOrcamento_idprodutosOrcamento", referencedColumnName = "idprodutosOrcamento")
    @ManyToOne(optional = false)
    private Produtosorcamento produtosorcamento;

    public Valoresseguro() {
    }

    public Valoresseguro(Integer idvaloresseguro) {
        this.idvaloresseguro = idvaloresseguro;
    }

    public Integer getIdvaloresseguro() {
        return idvaloresseguro;
    }

    public void setIdvaloresseguro(Integer idvaloresseguro) {
        this.idvaloresseguro = idvaloresseguro;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public Float getValornet() {
        return valornet;
    }

    public void setValornet(Float valornet) {
        this.valornet = valornet;
    }

    public Float getValorgross() {
        return valorgross;
    }

    public void setValorgross(Float valorgross) {
        this.valorgross = valorgross;
    }

    public Float getComissaoexpress() {
        return comissaoexpress;
    }

    public void setComissaoexpress(Float comissaoexpress) {
        this.comissaoexpress = comissaoexpress;
    }

    public Float getComissaopremium() {
        return comissaopremium;
    }

    public void setComissaopremium(Float comissaopremium) {
        this.comissaopremium = comissaopremium;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvaloresseguro != null ? idvaloresseguro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoresseguro)) {
            return false;
        }
        Valoresseguro other = (Valoresseguro) object;
        if ((this.idvaloresseguro == null && other.idvaloresseguro != null) || (this.idvaloresseguro != null && !this.idvaloresseguro.equals(other.idvaloresseguro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Valoresseguro[ idvaloresseguro=" + idvaloresseguro + " ]";
    }
    
}
