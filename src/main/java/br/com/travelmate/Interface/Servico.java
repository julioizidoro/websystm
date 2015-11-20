/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.Interface;

import br.com.travelmate.model.Tarifarioservico;
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
@Table(name = "servico")
@NamedQueries({
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s")})
public class Servico implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servico")
    private List<Tarifarioservico> tarifarioservicoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico")
    private Integer idservico;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "tiposervico_idtiposervico", referencedColumnName = "idtiposervico")
    @ManyToOne(optional = false)
    private Tiposervico tiposervico;

    public Servico() {
    }

    public Servico(Integer idservico) {
        this.idservico = idservico;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tiposervico getTiposervico() {
        return tiposervico;
    }

    public void setTiposervico(Tiposervico tiposervico) {
        this.tiposervico = tiposervico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservico != null ? idservico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servico)) {
            return false;
        }
        Servico other = (Servico) object;
        if ((this.idservico == null && other.idservico != null) || (this.idservico != null && !this.idservico.equals(other.idservico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.Interface.Servico[ idservico=" + idservico + " ]";
    }

    public List<Tarifarioservico> getTarifarioservicoList() {
        return tarifarioservicoList;
    }

    public void setTarifarioservicoList(List<Tarifarioservico> tarifarioservicoList) {
        this.tarifarioservicoList = tarifarioservicoList;
    }
    
}
