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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "ocurso")
public class Ocurso implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idocurso")
    private Integer idocurso;
    @Size(max = 30)
    @Column(name = "nivelidioma")
    private String nivelidioma;
    @Column(name = "datainicio")
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Column(name = "datatermino")
    @Temporal(TemporalType.DATE)
    private Date datatermino;
    @Column(name = "numerosemanas")
    private Integer numerosemanas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalmoedaestrangeira")
    private Float totalmoedaestrangeira;
    @Column(name = "totalmoedanacional")
    private Float totalmoedanacional;
    @Column(name = "desconto")
    private Float desconto;
    @Column(name = "valorcambio")
    private Float valorcambio;
    @Column(name = "dataorcamento")
    @Temporal(TemporalType.DATE)
    private Date dataorcamento;
    @JoinColumn(name = "cambio_idcambio", referencedColumnName = "idcambio")
    @ManyToOne(optional = false)
    private Cambio cambio;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "fornecedorcidadeidioma_idfornecedorcidadeidioma", referencedColumnName = "idfornecedorcidadeidioma")
    @ManyToOne(optional = false)
    private Fornecedorcidadeidioma fornecedorcidadeidioma;
    @JoinColumn(name = "idioma_ididioma", referencedColumnName = "ididioma")
    @ManyToOne(optional = false)
    private Idioma idioma;
    @JoinColumn(name = "ocursoformapagamento_idocursoformapagamento", referencedColumnName = "idocursoformapagamento")
    @ManyToOne(optional = false)
    private Ocursoformapagamento ocursoformapagamento;
    @JoinColumn(name = "produtosOrcamento_idprodutosOrcamento", referencedColumnName = "idprodutosOrcamento")
    @ManyToOne(optional = false)
    private Produtosorcamento produtosorcamento;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ocurso")
    private List<Ocursodesconto> ocursodescontoList;

    public Ocurso() {
    }

    public Ocurso(Integer idocurso) {
        this.idocurso = idocurso;
    }

    public Integer getIdocurso() {
        return idocurso;
    }

    public void setIdocurso(Integer idocurso) {
        this.idocurso = idocurso;
    }

    public String getNivelidioma() {
        return nivelidioma;
    }

    public void setNivelidioma(String nivelidioma) {
        this.nivelidioma = nivelidioma;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatatermino() {
        return datatermino;
    }

    public void setDatatermino(Date datatermino) {
        this.datatermino = datatermino;
    }

    public Integer getNumerosemanas() {
        return numerosemanas;
    }

    public void setNumerosemanas(Integer numerosemanas) {
        this.numerosemanas = numerosemanas;
    }

    public Float getTotalmoedaestrangeira() {
        return totalmoedaestrangeira;
    }

    public void setTotalmoedaestrangeira(Float totalmoedaestrangeira) {
        this.totalmoedaestrangeira = totalmoedaestrangeira;
    }

    public Float getTotalmoedanacional() {
        return totalmoedanacional;
    }

    public void setTotalmoedanacional(Float totalmoedanacional) {
        this.totalmoedanacional = totalmoedanacional;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public Date getDataorcamento() {
        return dataorcamento;
    }

    public void setDataorcamento(Date dataorcamento) {
        this.dataorcamento = dataorcamento;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Float getValorcambio() {
        return valorcambio;
    }

    public void setValorcambio(Float valorcambio) {
        this.valorcambio = valorcambio;
    }

    public Fornecedorcidadeidioma getFornecedorcidadeidioma() {
        return fornecedorcidadeidioma;
    }

    public void setFornecedorcidadeidioma(Fornecedorcidadeidioma fornecedorcidadeidioma) {
        this.fornecedorcidadeidioma = fornecedorcidadeidioma;
    }

    

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Ocursoformapagamento getOcursoformapagamento() {
        return ocursoformapagamento;
    }

    public void setOcursoformapagamento(Ocursoformapagamento ocursoformapagamento) {
        this.ocursoformapagamento = ocursoformapagamento;
    }

    public Produtosorcamento getProdutosorcamento() {
        return produtosorcamento;
    }

    public void setProdutosorcamento(Produtosorcamento produtosorcamento) {
        this.produtosorcamento = produtosorcamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idocurso != null ? idocurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocurso)) {
            return false;
        }
        Ocurso other = (Ocurso) object;
        if ((this.idocurso == null && other.idocurso != null) || (this.idocurso != null && !this.idocurso.equals(other.idocurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Ocurso[ idocurso=" + idocurso + " ]";
    }

    public List<Ocursodesconto> getOcursodescontoList() {
        return ocursodescontoList;
    }

    public void setOcursodescontoList(List<Ocursodesconto> ocursodescontoList) {
        this.ocursodescontoList = ocursodescontoList;
    }
    
}
