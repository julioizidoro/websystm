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

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "cambio")
@NamedQueries({
    @NamedQuery(name = "Cambio.findAll", query = "SELECT c FROM Cambio c")})
public class Cambio implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Orcamento> orcamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotes> pacotesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotepasseio> pacotepasseioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotecarro> pacotecarroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotetransfer> pacotetransferList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotetrem> pacotetremList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotehotel> pacotehotelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotecruzeiro> pacotecruzeiroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacoteingresso> pacoteingressoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cambio")
    private List<Pacotepassagem> pacotepassagemList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcambio")
    private Integer idcambio;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @JoinColumn(name = "moedas_idmoedas", referencedColumnName = "idmoedas")
    @ManyToOne(optional = false)
    private Moedas moedas;

    public Cambio() {
    }

    public Cambio(Integer idcambio) {
        this.idcambio = idcambio;
    }

    public Integer getIdcambio() {
        return idcambio;
    }

    public void setIdcambio(Integer idcambio) {
        this.idcambio = idcambio;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Moedas getMoedas() {
        return moedas;
    }

    public void setMoedas(Moedas moedas) {
        this.moedas = moedas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcambio != null ? idcambio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cambio)) {
            return false;
        }
        Cambio other = (Cambio) object;
        if ((this.idcambio == null && other.idcambio != null) || (this.idcambio != null && !this.idcambio.equals(other.idcambio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Cambio[ idcambio=" + idcambio + " ]";
    }

    public List<Pacotes> getPacotesList() {
        return pacotesList;
    }

    public void setPacotesList(List<Pacotes> pacotesList) {
        this.pacotesList = pacotesList;
    }

    public List<Pacotepasseio> getPacotepasseioList() {
        return pacotepasseioList;
    }

    public void setPacotepasseioList(List<Pacotepasseio> pacotepasseioList) {
        this.pacotepasseioList = pacotepasseioList;
    }

    public List<Pacotecarro> getPacotecarroList() {
        return pacotecarroList;
    }

    public void setPacotecarroList(List<Pacotecarro> pacotecarroList) {
        this.pacotecarroList = pacotecarroList;
    }

    public List<Pacotetransfer> getPacotetransferList() {
        return pacotetransferList;
    }

    public void setPacotetransferList(List<Pacotetransfer> pacotetransferList) {
        this.pacotetransferList = pacotetransferList;
    }

    public List<Pacotetrem> getPacotetremList() {
        return pacotetremList;
    }

    public void setPacotetremList(List<Pacotetrem> pacotetremList) {
        this.pacotetremList = pacotetremList;
    }

    public List<Pacotehotel> getPacotehotelList() {
        return pacotehotelList;
    }

    public void setPacotehotelList(List<Pacotehotel> pacotehotelList) {
        this.pacotehotelList = pacotehotelList;
    }

    public List<Pacotecruzeiro> getPacotecruzeiroList() {
        return pacotecruzeiroList;
    }

    public void setPacotecruzeiroList(List<Pacotecruzeiro> pacotecruzeiroList) {
        this.pacotecruzeiroList = pacotecruzeiroList;
    }

    public List<Pacoteingresso> getPacoteingressoList() {
        return pacoteingressoList;
    }

    public void setPacoteingressoList(List<Pacoteingresso> pacoteingressoList) {
        this.pacoteingressoList = pacoteingressoList;
    }

    public List<Pacotepassagem> getPacotepassagemList() {
        return pacotepassagemList;
    }

    public void setPacotepassagemList(List<Pacotepassagem> pacotepassagemList) {
        this.pacotepassagemList = pacotepassagemList;
    }

    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }
    
}
