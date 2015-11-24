/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "valorcoprodutos")
@NamedQueries({
    @NamedQuery(name = "Valorcoprodutos.findAll", query = "SELECT v FROM Valorcoprodutos v")})
public class Valorcoprodutos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "valorcoprodutos")
    private List<Ocrusoprodutos> ocrusoprodutosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvalorcoprodutos")
    private Integer idvalorcoprodutos;
    @Size(max = 2)
    @Column(name = "tipodata")
    private String tipodata;
    @Column(name = "datainicial")
    @Temporal(TemporalType.DATE)
    private Date datainicial;
    @Column(name = "datafinal")
    @Temporal(TemporalType.DATE)
    private Date datafinal;
    @Column(name = "numerosemanainicial")
    private Integer numerosemanainicial;
    @Column(name = "numerosemanafinal")
    private Integer numerosemanafinal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valororiginal")
    private Float valororiginal;
    @Column(name = "valorpromocional")
    private Float valorpromocional;
    @Column(name = "promocional")
    private Boolean promocional;
     @Column(name = "cobranca")
    private String cobranca;
    @JoinColumn(name = "coprodutos_idcoprodutos", referencedColumnName = "idcoprodutos")
    @ManyToOne(optional = false)
    private Coprodutos coprodutos;
    

    public Valorcoprodutos() {
    }

    public Valorcoprodutos(Integer idvalorcoprodutos) {
        this.idvalorcoprodutos = idvalorcoprodutos;
    }

    public Integer getIdvalorcoprodutos() {
        return idvalorcoprodutos;
    }

    public void setIdvalorcoprodutos(Integer idvalorcoprodutos) {
        this.idvalorcoprodutos = idvalorcoprodutos;
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

    public Integer getNumerosemanainicial() {
        return numerosemanainicial;
    }

    public void setNumerosemanainicial(Integer numerosemanainicial) {
        this.numerosemanainicial = numerosemanainicial;
    }

    public Integer getNumerosemanafinal() {
        return numerosemanafinal;
    }

    public void setNumerosemanafinal(Integer numerosemanafinal) {
        this.numerosemanafinal = numerosemanafinal;
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

    public Boolean getPromocional() {
        return promocional;
    }

    public void setPromocional(Boolean promocional) {
        this.promocional = promocional;
    }

    public String getCobranca() {
        return cobranca;
    }

    public void setCobranca(String cobranca) {
        this.cobranca = cobranca;
    }

    public Coprodutos getCoprodutos() {
        return coprodutos;
    }

    public void setCoprodutos(Coprodutos coprodutos) {
        this.coprodutos = coprodutos;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvalorcoprodutos != null ? idvalorcoprodutos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valorcoprodutos)) {
            return false;
        }
        Valorcoprodutos other = (Valorcoprodutos) object;
        if ((this.idvalorcoprodutos == null && other.idvalorcoprodutos != null) || (this.idvalorcoprodutos != null && !this.idvalorcoprodutos.equals(other.idvalorcoprodutos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Valorcoprodutos[ idvalorcoprodutos=" + idvalorcoprodutos + " ]";
    }

    public List<Ocrusoprodutos> getOcrusoprodutosList() {
        return ocrusoprodutosList;
    }

    public void setOcrusoprodutosList(List<Ocrusoprodutos> ocrusoprodutosList) {
        this.ocrusoprodutosList = ocrusoprodutosList;
    }
    
}
