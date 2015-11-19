/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.Interface;

import br.com.travelmate.model.Moedas;
import br.com.travelmate.model.Tarifarioservico;
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
@Table(name = "tarifario")
@NamedQueries({
    @NamedQuery(name = "Tarifario.findAll", query = "SELECT t FROM Tarifario t")})
public class Tarifario implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarifario")
    private List<Tarifarioservico> tarifarioservicoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtarifario")
    private Integer idtarifario;
    @Column(name = "datainicio")
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Column(name = "datafinal")
    @Temporal(TemporalType.DATE)
    private Date datafinal;
    @JoinColumn(name = "programafornecedor_idprogramafornecedor", referencedColumnName = "idprogramafornecedor")
    @ManyToOne(optional = false)
    private Programafornecedor programafornecedor;
    @JoinColumn(name = "moedas_idmoedas", referencedColumnName = "idmoedas")
    @ManyToOne(optional = false)
    private Moedas moedas;

    public Tarifario() {
    }

    public Tarifario(Integer idtarifario) {
        this.idtarifario = idtarifario;
    }

    public Integer getIdtarifario() {
        return idtarifario;
    }

    public void setIdtarifario(Integer idtarifario) {
        this.idtarifario = idtarifario;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public Programafornecedor getProgramafornecedor() {
        return programafornecedor;
    }

    public void setProgramafornecedor(Programafornecedor programafornecedor) {
        this.programafornecedor = programafornecedor;
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
        hash += (idtarifario != null ? idtarifario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifario)) {
            return false;
        }
        Tarifario other = (Tarifario) object;
        if ((this.idtarifario == null && other.idtarifario != null) || (this.idtarifario != null && !this.idtarifario.equals(other.idtarifario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.Interface.Tarifario[ idtarifario=" + idtarifario + " ]";
    }

    public List<Tarifarioservico> getTarifarioservicoList() {
        return tarifarioservicoList;
    }

    public void setTarifarioservicoList(List<Tarifarioservico> tarifarioservicoList) {
        this.tarifarioservicoList = tarifarioservicoList;
    }
    
}
