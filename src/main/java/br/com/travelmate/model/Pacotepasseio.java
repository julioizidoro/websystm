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
@Table(name = "pacotepasseio")
@NamedQueries({
    @NamedQuery(name = "Pacotepasseio.findAll", query = "SELECT p FROM Pacotepasseio p")})
public class Pacotepasseio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpacotepasseio")
    private Integer idpacotepasseio;
    @Size(max = 50)
    @Column(name = "pais")
    private String pais;
    @Size(max = 50)
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "chd")
    private Integer chd;
    @Column(name = "inf")
    private Integer inf;
    @Column(name = "adt")
    private Integer adt;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descritivo")
    private String descritivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valornet")
    private Float valornet;
    @Column(name = "valorgross")
    private Float valorgross;
    @Column(name = "comissao")
    private Float comissao;
    @Column(name = "valormoedanacional")
    private Float valormoedanacional;
    @JoinColumn(name = "cambio_idcambio", referencedColumnName = "idcambio")
    @ManyToOne(optional = false)
    private Cambio cambio;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "pacotetrecho_idpacotetrecho", referencedColumnName = "idpacotetrecho")
    @ManyToOne(optional = false)
    private Pacotetrecho pacotetrecho;

    public Pacotepasseio() {
    }

    public Pacotepasseio(Integer idpacotepasseio) {
        this.idpacotepasseio = idpacotepasseio;
    }

    public Integer getIdpacotepasseio() {
        return idpacotepasseio;
    }

    public void setIdpacotepasseio(Integer idpacotepasseio) {
        this.idpacotepasseio = idpacotepasseio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getChd() {
        return chd;
    }

    public void setChd(Integer chd) {
        this.chd = chd;
    }

    public Integer getInf() {
        return inf;
    }

    public void setInf(Integer inf) {
        this.inf = inf;
    }

    public Integer getAdt() {
        return adt;
    }

    public void setAdt(Integer adt) {
        this.adt = adt;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
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
        hash += (idpacotepasseio != null ? idpacotepasseio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacotepasseio)) {
            return false;
        }
        Pacotepasseio other = (Pacotepasseio) object;
        if ((this.idpacotepasseio == null && other.idpacotepasseio != null) || (this.idpacotepasseio != null && !this.idpacotepasseio.equals(other.idpacotepasseio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Pacotepasseio[ idpacotepasseio=" + idpacotepasseio + " ]";
    }
    
}
