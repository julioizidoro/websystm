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
@Table(name = "pacotecruzeiro")
@NamedQueries({
    @NamedQuery(name = "Pacotecruzeiro.findAll", query = "SELECT p FROM Pacotecruzeiro p")})
public class Pacotecruzeiro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpacotecruzeiro")
    private Integer idpacotecruzeiro;
    @Column(name = "datasaida")
    @Temporal(TemporalType.DATE)
    private Date datasaida;
    @Column(name = "datechegada")
    @Temporal(TemporalType.DATE)
    private Date datechegada;
    @Lob
    @Size(max = 16777215)
    @Column(name = "intinerario")
    private String intinerario;
    @Size(max = 10)
    @Column(name = "tipocabine")
    private String tipocabine;
    @Size(max = 15)
    @Column(name = "categoria")
    private String categoria;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descritivo")
    private String descritivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorgross")
    private Float valorgross;
    @Column(name = "valornet")
    private Float valornet;
    @Column(name = "comissao")
    private Float comissao;
    @Column(name = "valormoedanacional")
    private Float valormoedanacional;
    @Column(name = "comissaoloja")
    private Float comissaoloja;
    @JoinColumn(name = "cambio_idcambio", referencedColumnName = "idcambio")
    @ManyToOne(optional = false)
    private Cambio cambio;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "pacotetrecho_idpacotetrecho", referencedColumnName = "idpacotetrecho")
    @ManyToOne(optional = false)
    private Pacotetrecho pacotetrecho;

    public Pacotecruzeiro() {
    }

    public Pacotecruzeiro(Integer idpacotecruzeiro) {
        this.idpacotecruzeiro = idpacotecruzeiro;
    }

    public Integer getIdpacotecruzeiro() {
        return idpacotecruzeiro;
    }

    public void setIdpacotecruzeiro(Integer idpacotecruzeiro) {
        this.idpacotecruzeiro = idpacotecruzeiro;
    }

    public Date getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(Date datasaida) {
        this.datasaida = datasaida;
    }

    public Date getDatechegada() {
        return datechegada;
    }

    public void setDatechegada(Date datechegada) {
        this.datechegada = datechegada;
    }

    public String getIntinerario() {
        return intinerario;
    }

    public void setIntinerario(String intinerario) {
        this.intinerario = intinerario;
    }

    public String getTipocabine() {
        return tipocabine;
    }

    public void setTipocabine(String tipocabine) {
        this.tipocabine = tipocabine;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    public Float getValorgross() {
        return valorgross;
    }

    public void setValorgross(Float valorgross) {
        this.valorgross = valorgross;
    }

    public Float getValornet() {
        return valornet;
    }

    public void setValornet(Float valornet) {
        this.valornet = valornet;
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

    public Float getComissaoloja() {
        return comissaoloja;
    }

    public void setComissaoloja(Float comissaoloja) {
        this.comissaoloja = comissaoloja;
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
        hash += (idpacotecruzeiro != null ? idpacotecruzeiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacotecruzeiro)) {
            return false;
        }
        Pacotecruzeiro other = (Pacotecruzeiro) object;
        if ((this.idpacotecruzeiro == null && other.idpacotecruzeiro != null) || (this.idpacotecruzeiro != null && !this.idpacotecruzeiro.equals(other.idpacotecruzeiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Pacotecruzeiro[ idpacotecruzeiro=" + idpacotecruzeiro + " ]";
    }
    
}
