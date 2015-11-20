/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.Interface;

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
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "tiposervico")
@NamedQueries({
    @NamedQuery(name = "Tiposervico.findAll", query = "SELECT t FROM Tiposervico t")})
public class Tiposervico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtiposervico")
    private Integer idtiposervico;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposervico")
    private List<Servico> servicoList;

    public Tiposervico() {
    }

    public Tiposervico(Integer idtiposervico) {
        this.idtiposervico = idtiposervico;
    }

    public Integer getIdtiposervico() {
        return idtiposervico;
    }

    public void setIdtiposervico(Integer idtiposervico) {
        this.idtiposervico = idtiposervico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiposervico != null ? idtiposervico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposervico)) {
            return false;
        }
        Tiposervico other = (Tiposervico) object;
        if ((this.idtiposervico == null && other.idtiposervico != null) || (this.idtiposervico != null && !this.idtiposervico.equals(other.idtiposervico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.Interface.Tiposervico[ idtiposervico=" + idtiposervico + " ]";
    }
    
}
