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

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "ocursoformapagamento")
@NamedQueries({
    @NamedQuery(name = "Ocursoformapagamento.findAll", query = "SELECT o FROM Ocursoformapagamento o")})
public class Ocursoformapagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idocursoformapagamento")
    private Integer idocursoformapagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aVista")
    private Float aVista;
    @Column(name = "percentualEntrada2")
    private Double percentualEntrada2;
    @Column(name = "valorEntrada2")
    private Float valorEntrada2;
    @Column(name = "percentualSaldo2")
    private Double percentualSaldo2;
    @Column(name = "valorSaldo2")
    private Float valorSaldo2;
    @Column(name = "percentualEntrada3")
    private Double percentualEntrada3;
    @Column(name = "valorEntrada3")
    private Float valorEntrada3;
    @Column(name = "percentualSaldo3")
    private Double percentualSaldo3;
    @Column(name = "valorSaldo3")
    private Float valorSaldo3;
    @Column(name = "numeroParcelasFinanciamento")
    private Integer numeroParcelasFinanciamento;
    @Column(name = "finaciamento")
    private Float finaciamento;
    @Column(name = "numeroParcelas02")
    private Integer numeroParcelas02;
    @Column(name = "valorParcela02")
    private Float valorParcela02;
    @Column(name = "numeroParcelas03")
    private Integer numeroParcelas03;
    @Column(name = "valorParcela03")
    private Float valorParcela03;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ocursoformapagamento")
    private List<Ocurso> ocursoList;

    public Ocursoformapagamento() {
        //setar 0
        setAVista(0.0f);
        setFinaciamento(0.0f);
        setValorEntrada2(0.0f);
        setPercentualEntrada2(0.0);
        setPercentualEntrada3(0.0);
        setPercentualSaldo2(0.0);
        setPercentualSaldo3(0.0);
        setValorEntrada2(0.0f);
        setValorEntrada3(0.0f);
        setValorParcela02(0.0f);
        setValorParcela03(0.0f);
        setValorSaldo2(0.0f);
        setValorSaldo3(0.0f);
    }

    public Ocursoformapagamento(Integer idocursoformapagamento) {
        this.idocursoformapagamento = idocursoformapagamento;
    }

    public Integer getIdocursoformapagamento() {
        return idocursoformapagamento;
    }

    public void setIdocursoformapagamento(Integer idocursoformapagamento) {
        this.idocursoformapagamento = idocursoformapagamento;
    }

    public Float getAVista() {
        return aVista;
    }

    public void setAVista(Float aVista) {
        this.aVista = aVista;
    }

    public Double getPercentualEntrada2() {
        return percentualEntrada2;
    }

    public void setPercentualEntrada2(Double percentualEntrada2) {
        this.percentualEntrada2 = percentualEntrada2;
    }

    public Float getValorEntrada2() {
        return valorEntrada2;
    }

    public void setValorEntrada2(Float valorEntrada2) {
        this.valorEntrada2 = valorEntrada2;
    }

    public Double getPercentualSaldo2() {
        return percentualSaldo2;
    }

    public void setPercentualSaldo2(Double percentualSaldo2) {
        this.percentualSaldo2 = percentualSaldo2;
    }

    public Float getValorSaldo2() {
        return valorSaldo2;
    }

    public void setValorSaldo2(Float valorSaldo2) {
        this.valorSaldo2 = valorSaldo2;
    }

    public Double getPercentualEntrada3() {
        return percentualEntrada3;
    }

    public void setPercentualEntrada3(Double percentualEntrada3) {
        this.percentualEntrada3 = percentualEntrada3;
    }

    public Float getValorEntrada3() {
        return valorEntrada3;
    }

    public void setValorEntrada3(Float valorEntrada3) {
        this.valorEntrada3 = valorEntrada3;
    }

    public Double getPercentualSaldo3() {
        return percentualSaldo3;
    }

    public void setPercentualSaldo3(Double percentualSaldo3) {
        this.percentualSaldo3 = percentualSaldo3;
    }

    public Float getValorSaldo3() {
        return valorSaldo3;
    }

    public void setValorSaldo3(Float valorSaldo3) {
        this.valorSaldo3 = valorSaldo3;
    }

    public Integer getNumeroParcelasFinanciamento() {
        return numeroParcelasFinanciamento;
    }

    public void setNumeroParcelasFinanciamento(Integer numeroParcelasFinanciamento) {
        this.numeroParcelasFinanciamento = numeroParcelasFinanciamento;
    }

    public Float getFinaciamento() {
        return finaciamento;
    }

    public void setFinaciamento(Float finaciamento) {
        this.finaciamento = finaciamento;
    }

    public Integer getNumeroParcelas02() {
        return numeroParcelas02;
    }

    public void setNumeroParcelas02(Integer numeroParcelas02) {
        this.numeroParcelas02 = numeroParcelas02;
    }

    public Float getValorParcela02() {
        return valorParcela02;
    }

    public void setValorParcela02(Float valorParcela02) {
        this.valorParcela02 = valorParcela02;
    }

    public Integer getNumeroParcelas03() {
        return numeroParcelas03;
    }

    public void setNumeroParcelas03(Integer numeroParcelas03) {
        this.numeroParcelas03 = numeroParcelas03;
    }

    public Float getValorParcela03() {
        return valorParcela03;
    }

    public void setValorParcela03(Float valorParcela03) {
        this.valorParcela03 = valorParcela03;
    }

    public List<Ocurso> getOcursoList() {
        return ocursoList;
    }

    public void setOcursoList(List<Ocurso> ocursoList) {
        this.ocursoList = ocursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idocursoformapagamento != null ? idocursoformapagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocursoformapagamento)) {
            return false;
        }
        Ocursoformapagamento other = (Ocursoformapagamento) object;
        if ((this.idocursoformapagamento == null && other.idocursoformapagamento != null) || (this.idocursoformapagamento != null && !this.idocursoformapagamento.equals(other.idocursoformapagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Ocursoformapagamento[ idocursoformapagamento=" + idocursoformapagamento + " ]";
    }
    
}
