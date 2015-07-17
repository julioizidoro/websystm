/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "parametrosprodutos")
@NamedQueries({
    @NamedQuery(name = "Parametrosprodutos.findAll", query = "SELECT p FROM Parametrosprodutos p")})
public class Parametrosprodutos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparametrosProdutos")
    private Integer idparametrosProdutos;
    @Column(name = "visto")
    private Integer visto;
    @Column(name = "cursos")
    private Integer cursos;
    @Column(name = "seguroPrivado")
    private Integer seguroPrivado;
    @Column(name = "vistoOrcamento")
    private Integer vistoOrcamento;
    @Column(name = "seguroOrcamento")
    private Integer seguroOrcamento;
    @Column(name = "highSchool")
    private Integer highSchool;
    @Column(name = "programasTeens")
    private Integer programasTeens;
    @Column(name = "pacotes")
    private Integer pacotes;
    @Column(name = "passagem")
    private Integer passagem;
    @Column(name = "passagemTaxa")
    private Integer passagemTaxa;
    @Column(name = "passagemTaxaTM")
    private Integer passagemTaxaTM;
    @Column(name = "ladies")
    private Integer ladies;
    @Column(name = "aupair")
    private Integer aupair;
    @Column(name = "work")
    private Integer work;
    @Column(name = "fornecedor")
    private Integer fornecedor;
    @Size(max = 100)
    @Column(name = "emailCambio01")
    private String emailCambio01;
    @Size(max = 100)
    @Column(name = "emailCambio02")
    private String emailCambio02;
    @Size(max = 100)
    @Column(name = "emailCambio03")
    private String emailCambio03;
    @Column(name = "passagemTarifa")
    private Integer passagemTarifa;
    @Column(name = "seguroGovernamental")
    private Integer seguroGovernamental;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorTaxaTM")
    private Float valorTaxaTM;
    @Column(name = "trainee")
    private Integer trainee;
    @Column(name = "voluntariado")
    private Integer voluntariado;
    @Size(max = 100)
    @Column(name = "emailCotacao")
    private String emailCotacao;
    @Column(name = "descontomatriz")
    private Integer descontomatriz;
    @Column(name = "idplanocontas")
    private Integer idplanocontas;
    @Column(name = "boletos")
    private Float boletos;
    @Column(name = "cartaodebito")
    private Float cartaodebito;
    @Column(name = "cartao01")
    private Float cartao01;
    @Column(name = "cartao02")
    private Float cartao02;
    @Column(name = "cartao07")
    private Float cartao07;
    @Column(name = "juros")
    private Float juros;

    public Parametrosprodutos() {
    }

    public Parametrosprodutos(Integer idparametrosProdutos) {
        this.idparametrosProdutos = idparametrosProdutos;
    }

    public Integer getIdparametrosProdutos() {
        return idparametrosProdutos;
    }

    public void setIdparametrosProdutos(Integer idparametrosProdutos) {
        this.idparametrosProdutos = idparametrosProdutos;
    }

    public Integer getVisto() {
        return visto;
    }

    public void setVisto(Integer visto) {
        this.visto = visto;
    }

    public Integer getCursos() {
        return cursos;
    }

    public void setCursos(Integer cursos) {
        this.cursos = cursos;
    }

    public Integer getSeguroPrivado() {
        return seguroPrivado;
    }

    public void setSeguroPrivado(Integer seguroPrivado) {
        this.seguroPrivado = seguroPrivado;
    }

    public Integer getVistoOrcamento() {
        return vistoOrcamento;
    }

    public void setVistoOrcamento(Integer vistoOrcamento) {
        this.vistoOrcamento = vistoOrcamento;
    }

    public Integer getSeguroOrcamento() {
        return seguroOrcamento;
    }

    public void setSeguroOrcamento(Integer seguroOrcamento) {
        this.seguroOrcamento = seguroOrcamento;
    }

    public Integer getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(Integer highSchool) {
        this.highSchool = highSchool;
    }

    public Integer getProgramasTeens() {
        return programasTeens;
    }

    public void setProgramasTeens(Integer programasTeens) {
        this.programasTeens = programasTeens;
    }

    public Integer getPacotes() {
        return pacotes;
    }

    public void setPacotes(Integer pacotes) {
        this.pacotes = pacotes;
    }

    public Integer getPassagem() {
        return passagem;
    }

    public void setPassagem(Integer passagem) {
        this.passagem = passagem;
    }

    public Integer getPassagemTaxa() {
        return passagemTaxa;
    }

    public void setPassagemTaxa(Integer passagemTaxa) {
        this.passagemTaxa = passagemTaxa;
    }

    public Integer getPassagemTaxaTM() {
        return passagemTaxaTM;
    }

    public void setPassagemTaxaTM(Integer passagemTaxaTM) {
        this.passagemTaxaTM = passagemTaxaTM;
    }

    public Integer getLadies() {
        return ladies;
    }

    public void setLadies(Integer ladies) {
        this.ladies = ladies;
    }

    public Integer getAupair() {
        return aupair;
    }

    public void setAupair(Integer aupair) {
        this.aupair = aupair;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Integer getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Integer fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getEmailCambio01() {
        return emailCambio01;
    }

    public void setEmailCambio01(String emailCambio01) {
        this.emailCambio01 = emailCambio01;
    }

    public String getEmailCambio02() {
        return emailCambio02;
    }

    public void setEmailCambio02(String emailCambio02) {
        this.emailCambio02 = emailCambio02;
    }

    public String getEmailCambio03() {
        return emailCambio03;
    }

    public void setEmailCambio03(String emailCambio03) {
        this.emailCambio03 = emailCambio03;
    }

    public Integer getPassagemTarifa() {
        return passagemTarifa;
    }

    public void setPassagemTarifa(Integer passagemTarifa) {
        this.passagemTarifa = passagemTarifa;
    }

    public Integer getSeguroGovernamental() {
        return seguroGovernamental;
    }

    public void setSeguroGovernamental(Integer seguroGovernamental) {
        this.seguroGovernamental = seguroGovernamental;
    }

    public Float getValorTaxaTM() {
        return valorTaxaTM;
    }

    public void setValorTaxaTM(Float valorTaxaTM) {
        this.valorTaxaTM = valorTaxaTM;
    }

    public Integer getTrainee() {
        return trainee;
    }

    public void setTrainee(Integer trainee) {
        this.trainee = trainee;
    }

    public Integer getVoluntariado() {
        return voluntariado;
    }

    public void setVoluntariado(Integer voluntariado) {
        this.voluntariado = voluntariado;
    }

    public String getEmailCotacao() {
        return emailCotacao;
    }

    public void setEmailCotacao(String emailCotacao) {
        this.emailCotacao = emailCotacao;
    }

    public Integer getDescontomatriz() {
        return descontomatriz;
    }

    public void setDescontomatriz(Integer descontomatriz) {
        this.descontomatriz = descontomatriz;
    }

    public Integer getIdplanocontas() {
        return idplanocontas;
    }

    public void setIdplanocontas(Integer idplanocontas) {
        this.idplanocontas = idplanocontas;
    }

    public Float getBoletos() {
        return boletos;
    }

    public void setBoletos(Float boletos) {
        this.boletos = boletos;
    }

    public Float getCartaodebito() {
        return cartaodebito;
    }

    public void setCartaodebito(Float cartaodebito) {
        this.cartaodebito = cartaodebito;
    }

    public Float getCartao01() {
        return cartao01;
    }

    public void setCartao01(Float cartao01) {
        this.cartao01 = cartao01;
    }

    public Float getCartao02() {
        return cartao02;
    }

    public void setCartao02(Float cartao02) {
        this.cartao02 = cartao02;
    }

    public Float getCartao07() {
        return cartao07;
    }

    public void setCartao07(Float cartao07) {
        this.cartao07 = cartao07;
    }

    public Float getJuros() {
        return juros;
    }

    public void setJuros(Float juros) {
        this.juros = juros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparametrosProdutos != null ? idparametrosProdutos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrosprodutos)) {
            return false;
        }
        Parametrosprodutos other = (Parametrosprodutos) object;
        if ((this.idparametrosProdutos == null && other.idparametrosProdutos != null) || (this.idparametrosProdutos != null && !this.idparametrosProdutos.equals(other.idparametrosProdutos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Parametrosprodutos[ idparametrosProdutos=" + idparametrosProdutos + " ]";
    }
    
}
