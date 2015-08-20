/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Kamila Rodrigues
 */
@Entity
@Table(name = "coobrigatorio")
@NamedQueries({
    @NamedQuery(name = "Coobrigatorio.findAll", query = "SELECT c FROM Coobrigatorio c")})
public class Coobrigatorio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcoobrigatorio")
    private Integer idcoobrigatorio;
    @Size(max = 1)
    @Column(name = "tipodata")
    private String tipodata;
    @Column(name = "datainicial")
    @Temporal(TemporalType.DATE)
    private Date datainicial;
    @Column(name = "datafinal")
    @Temporal(TemporalType.DATE)
    private Date datafinal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valororiginal")
    private Float valororiginal;
    @Column(name = "valorpromocional")
    private Float valorpromocional;
    @JoinColumn(name = "produtosOrcamento_idprodutosOrcamento", referencedColumnName = "idprodutosOrcamento")
    @ManyToOne(optional = false)
    private Produtosorcamento produtosorcamento;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;

    public Coobrigatorio() {
    }

    public Coobrigatorio(Integer idcoobrigatorio) {
        this.idcoobrigatorio = idcoobrigatorio;
    }

    public Integer getIdcoobrigatorio() {
        return idcoobrigatorio;
    }

    public void setIdcoobrigatorio(Integer idcoobrigatorio) {
        this.idcoobrigatorio = idcoobrigatorio;
    }

    public String getTipodata() {
        return tipodata;
    }

    public void setTipodata(String tipodata) {
        this.tipodata = tipodata;
    }

    public Date getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(Date datainicial) {
        this.datainicial = datainicial;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public Float getValororiginal() {
        return valororiginal;
    }

    public void setValororiginal(Float valororiginal) {
        this.valororiginal = valororiginal;
    }

    public Float getValorpromocional() {
        return valorpromocional;
    }

    public void setValorpromocional(Float valorpromocional) {
        this.valorpromocional = valorpromocional;
    }

    public Produtosorcamento getProdutosorcamento() {
        return produtosorcamento;
    }

    public void setProdutosorcamento(Produtosorcamento produtosorcamento) {
        this.produtosorcamento = produtosorcamento;
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
        hash += (idcoobrigatorio != null ? idcoobrigatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coobrigatorio)) {
            return false;
        }
        Coobrigatorio other = (Coobrigatorio) object;
        if ((this.idcoobrigatorio == null && other.idcoobrigatorio != null) || (this.idcoobrigatorio != null && !this.idcoobrigatorio.equals(other.idcoobrigatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Coobrigatorio[ idcoobrigatorio=" + idcoobrigatorio + " ]";
    }
    
}
