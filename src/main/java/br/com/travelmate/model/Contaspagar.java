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
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "contaspagar")
@NamedQueries({
    @NamedQuery(name = "Contaspagar.findAll", query = "SELECT c FROM Contaspagar c")})
public class Contaspagar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontaspagar")
    private Integer idcontaspagar;
    @Column(name = "datalancamento")
    @Temporal(TemporalType.DATE)
    private Date datalancamento;
    @Column(name = "dataliberacao")
    @Temporal(TemporalType.DATE)
    private Date dataliberacao;
    @Column(name = "datavencimento")
    @Temporal(TemporalType.DATE)
    private Date datavencimento;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "dataagendamento")
    @Temporal(TemporalType.DATE)
    private Date dataagendamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorconta")
    private Float valorconta;
    @Size(max = 7)
    @Column(name = "competencia")
    private String competencia;
    @Column(name = "movimento")
    private Integer movimento;
    @Column(name = "datacompensacao")
    @Temporal(TemporalType.DATE)
    private Date datacompensacao;
    @Size(max = 1)
    @Column(name = "contapaga")
    private String contapaga;
    @Size(max = 50)
    @Column(name = "formapgamento")
    private String formapgamento;
    @Size(max = 50)
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Size(max = 1)
    @Column(name = "autorizarpagamento")
    private String autorizarpagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaspagar")
    private List<Eventocontaspagar> eventocontaspagarList;
    @JoinColumn(name = "planoconta_idplanoconta", referencedColumnName = "idplanoconta")
    @ManyToOne(optional = false)
    private Planoconta planoconta;
    @JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "unidadeNegocio_idunidadeNegocio", referencedColumnName = "idunidadeNegocio")
    @ManyToOne(optional = false)
    private Unidadenegocio unidadenegocio;

    public Contaspagar() {
    }

    public Contaspagar(Integer idcontaspagar) {
        this.idcontaspagar = idcontaspagar;
    }

    public Integer getIdcontaspagar() {
        return idcontaspagar;
    }

    public void setIdcontaspagar(Integer idcontaspagar) {
        this.idcontaspagar = idcontaspagar;
    }

    public Date getDatalancamento() {
        return datalancamento;
    }

    public void setDatalancamento(Date datalancamento) {
        this.datalancamento = datalancamento;
    }

    public Date getDataliberacao() {
        return dataliberacao;
    }

    public void setDataliberacao(Date dataliberacao) {
        this.dataliberacao = dataliberacao;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataagendamento() {
        return dataagendamento;
    }

    public void setDataagendamento(Date dataagendamento) {
        this.dataagendamento = dataagendamento;
    }

    public Float getValorconta() {
        return valorconta;
    }

    public void setValorconta(Float valorconta) {
        this.valorconta = valorconta;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public Integer getMovimento() {
        return movimento;
    }

    public void setMovimento(Integer movimento) {
        this.movimento = movimento;
    }

    public Date getDatacompensacao() {
        return datacompensacao;
    }

    public void setDatacompensacao(Date datacompensacao) {
        this.datacompensacao = datacompensacao;
    }

    public String getContapaga() {
        return contapaga;
    }

    public void setContapaga(String contapaga) {
        this.contapaga = contapaga;
    }

    public String getFormapgamento() {
        return formapgamento;
    }

    public void setFormapgamento(String formapgamento) {
        this.formapgamento = formapgamento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getAutorizarpagamento() {
        return autorizarpagamento;
    }

    public void setAutorizarpagamento(String autorizarpagamento) {
        this.autorizarpagamento = autorizarpagamento;
    }

    public List<Eventocontaspagar> getEventocontaspagarList() {
        return eventocontaspagarList;
    }

    public void setEventocontaspagarList(List<Eventocontaspagar> eventocontaspagarList) {
        this.eventocontaspagarList = eventocontaspagarList;
    }

    public Planoconta getPlanoconta() {
        return planoconta;
    }

    public void setPlanoconta(Planoconta planoconta) {
        this.planoconta = planoconta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontaspagar != null ? idcontaspagar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contaspagar)) {
            return false;
        }
        Contaspagar other = (Contaspagar) object;
        if ((this.idcontaspagar == null && other.idcontaspagar != null) || (this.idcontaspagar != null && !this.idcontaspagar.equals(other.idcontaspagar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Contaspagar[ idcontaspagar=" + idcontaspagar + " ]";
    }
    
}
