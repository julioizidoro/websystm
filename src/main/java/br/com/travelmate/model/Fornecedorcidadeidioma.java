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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "fornecedorcidadeidioma")
@NamedQueries({
    @NamedQuery(name = "Fornecedorcidadeidioma.findAll", query = "SELECT f FROM Fornecedorcidadeidioma f")})
public class Fornecedorcidadeidioma implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidadeidioma")
    private List<Ocurso> ocursoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfornecedorcidadeidioma")
    private Integer idfornecedorcidadeidioma;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "idioma_ididioma", referencedColumnName = "ididioma")
    @ManyToOne(optional = false)
    private Idioma idioma;

    public Fornecedorcidadeidioma() {
    }

    public Fornecedorcidadeidioma(Integer idfornecedorcidadeidioma) {
        this.idfornecedorcidadeidioma = idfornecedorcidadeidioma;
    }

    public Integer getIdfornecedorcidadeidioma() {
        return idfornecedorcidadeidioma;
    }

    public void setIdfornecedorcidadeidioma(Integer idfornecedorcidadeidioma) {
        this.idfornecedorcidadeidioma = idfornecedorcidadeidioma;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedorcidadeidioma != null ? idfornecedorcidadeidioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Fornecedorcidadeidioma)) {
            return false;
        }
        Fornecedorcidadeidioma other = (Fornecedorcidadeidioma) object;
        if ((this.idfornecedorcidadeidioma == null && other.idfornecedorcidadeidioma != null) || (this.idfornecedorcidadeidioma != null && !this.idfornecedorcidadeidioma.equals(other.idfornecedorcidadeidioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Fornecedorcidadeidioma[ idfornecedorcidadeidioma=" + idfornecedorcidadeidioma + " ]";
    }

    public List<Ocurso> getOcursoList() {
        return ocursoList;
    }

    public void setOcursoList(List<Ocurso> ocursoList) {
        this.ocursoList = ocursoList;
    }
    
}
