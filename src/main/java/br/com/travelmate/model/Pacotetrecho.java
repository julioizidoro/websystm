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
import javax.persistence.Lob;
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
@Table(name = "pacotetrecho")
@NamedQueries({
    @NamedQuery(name = "Pacotetrecho.findAll", query = "SELECT p FROM Pacotetrecho p")})
public class Pacotetrecho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpacotetrecho")
    private Integer idpacotetrecho;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descritivo")
    private String descritivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacotepasseio> pacotepasseioList;
    @JoinColumn(name = "pacotes_idpacotes", referencedColumnName = "idpacotes")
    @ManyToOne(optional = false)
    private Pacotes pacotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacotecarro> pacotecarroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacotetransfer> pacotetransferList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacotetrem> pacotetremList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacotehotel> pacotehotelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacotecruzeiro> pacotecruzeiroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacoteingresso> pacoteingressoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotetrecho")
    private List<Pacotepassagem> pacotepassagemList;

    public Pacotetrecho() {
    }

    public Pacotetrecho(Integer idpacotetrecho) {
        this.idpacotetrecho = idpacotetrecho;
    }

    public Integer getIdpacotetrecho() {
        return idpacotetrecho;
    }

    public void setIdpacotetrecho(Integer idpacotetrecho) {
        this.idpacotetrecho = idpacotetrecho;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    public List<Pacotepasseio> getPacotepasseioList() {
        return pacotepasseioList;
    }

    public void setPacotepasseioList(List<Pacotepasseio> pacotepasseioList) {
        this.pacotepasseioList = pacotepasseioList;
    }

    public Pacotes getPacotes() {
        return pacotes;
    }

    public void setPacotes(Pacotes pacotes) {
        this.pacotes = pacotes;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpacotetrecho != null ? idpacotetrecho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pacotetrecho)) {
            return false;
        }
        Pacotetrecho other = (Pacotetrecho) object;
        if ((this.idpacotetrecho == null && other.idpacotetrecho != null) || (this.idpacotetrecho != null && !this.idpacotetrecho.equals(other.idpacotetrecho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Pacotetrecho[ idpacotetrecho=" + idpacotetrecho + " ]";
    }
    
}
