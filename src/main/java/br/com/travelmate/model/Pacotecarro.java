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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "pacotecarro")
@NamedQueries({
    @NamedQuery(name = "Pacotecarro.findAll", query = "SELECT p FROM Pacotecarro p")})
public class Pacotecarro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpacoteCarro")
    private Integer idpacoteCarro;
    @Size(max = 45)
    @Column(name = "localizador")
    private String localizador;
    @Size(max = 100)
    @Column(name = "locaretirada")
    private String locaretirada;
    @Column(name = "dataretirada")
    @Temporal(TemporalType.DATE)
    private Date dataretirada;
    @Size(max = 5)
    @Column(name = "horaritirada")
    private String horaritirada;
    @Size(max = 100)
    @Column(name = "localdevolucao")
    private String localdevolucao;
    @Column(name = "chd")
    private Integer chd;
    @Column(name = "datadevolucao")
    @Temporal(TemporalType.DATE)
    private Date datadevolucao;
    @Size(max = 5)
    @Column(name = "horadevolucao")
    private String horadevolucao;
    @Size(max = 15)
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "adt")
    private Integer adt;
    @Column(name = "inf")
    private Integer inf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valornet")
    private Float valornet;
    @Column(name = "valorgross")
    private Float valorgross;
    @Column(name = "comissao")
    private Float comissao;
    @Column(name = "valormoedanacional")
    private Float valormoedanacional;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descritivo")
    private String descritivo;
    @JoinColumn(name = "cambio_idcambio", referencedColumnName = "idcambio")
    @ManyToOne(optional = false)
    private Cambio cambio;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "pacotetrecho_idpacotetrecho", referencedColumnName = "idpacotetrecho")
    @ManyToOne(optional = false)
    private Pacotetrecho pacotetrecho;

    public Pacotecarro() {
    }

    public Pacotecarro(Integer idpacoteCarro) {
        this.idpacoteCarro = idpacoteCarro;
    }

    public Integer getIdpacoteCarro() {
        return idpacoteCarro;
    }

    public void setIdpacoteCarro(Integer idpacoteCarro) {
        this.idpacoteCarro = idpacoteCarro;
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public String getLocaretirada() {
        return locaretirada;
    }

    public void setLocaretirada(String locaretirada) {
        this.locaretirada = locaretirada;
    }

    public Date getDataretirada() {
        return dataretirada;
    }

    public void setDataretirada(Date dataretirada) {
        this.dataretirada = dataretirada;
    }

    public String getHoraritirada() {
        return horaritirada;
    }

    public void setHoraritirada(String horaritirada) {
        this.horaritirada = horaritirada;
    }

    public String getLocaldevolucao() {
        return localdevolucao;
    }

    public void setLocaldevolucao(String localdevolucao) {
        this.localdevolucao = localdevolucao;
    }

    public Integer getChd() {
        return chd;
    }

    public void setChd(Integer chd) {
        this.chd = chd;
    }

    public Date getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

    public String getHoradevolucao() {
        return horadevolucao;
    }

    public void setHoradevolucao(String horadevolucao) {
        this.horadevolucao = horadevolucao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getAdt() {
        return adt;
    }

    public void setAdt(Integer adt) {
        this.adt = adt;
    }

    public Integer getInf() {
        return inf;
    }

    public void setInf(Integer inf) {
        this.inf = inf;
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

    public Float getComissao() {
        return comissao;
    }

    public void setComissao(Float comissao) {
        this.comissao = comissao;
    }

    public Float getValormoedanacional() {
        return valormoedanacional;
    }

    public void setValormoedanacional(Float valormoedanacional) {
        this.valormoedanacional = valormoedanacional;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public Pacotetrecho getPacotetrecho() {
        return pacotetrecho;
    }

    public void setPacotetrecho(Pacotetrecho pacotetrecho) {
        this.pacotetrecho = pacotetrecho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpacoteCarro != null ? idpacoteCarro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacotecarro)) {
            return false;
        }
        Pacotecarro other = (Pacotecarro) object;
        if ((this.idpacoteCarro == null && other.idpacoteCarro != null) || (this.idpacoteCarro != null && !this.idpacoteCarro.equals(other.idpacoteCarro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Pacotecarro[ idpacoteCarro=" + idpacoteCarro + " ]";
    }
    
}
