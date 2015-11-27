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
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "idioma")
@NamedQueries({
    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i")})
public class Idioma implements Serializable {
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idioma")
    private List<Ocurso> ocursoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ididioma")
    private Integer ididioma;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idioma")
    private List<Fornecedorcidadeidioma> fornecedorcidadeidiomaList;

    public Idioma() {
    }

    public Idioma(Integer ididioma) {
        this.ididioma = ididioma;
    }

    public Integer getIdidioma() {
        return ididioma;
    }

    public void setIdidioma(Integer ididioma) {
        this.ididioma = ididioma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Fornecedorcidadeidioma> getFornecedorcidadeidiomaList() {
        return fornecedorcidadeidiomaList;
    }

    public void setFornecedorcidadeidiomaList(List<Fornecedorcidadeidioma> fornecedorcidadeidiomaList) {
        this.fornecedorcidadeidiomaList = fornecedorcidadeidiomaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ididioma != null ? ididioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.ididioma == null && other.ididioma != null) || (this.ididioma != null && !this.ididioma.equals(other.ididioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Idioma[ ididioma=" + ididioma + " ]";
    }

    public List<Ocurso> getOcursoList() {
        return ocursoList;
    }

    public void setOcursoList(List<Ocurso> ocursoList) {
        this.ocursoList = ocursoList;
    }

    
}
