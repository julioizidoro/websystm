/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "pacotepassagem")
@NamedQueries({
    @NamedQuery(name = "Pacotepassagem.findAll", query = "SELECT p FROM Pacotepassagem p")})
public class Pacotepassagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpacotepassagem")
    private Integer idpacotepassagem;
    @Size(max = 50)
    @Column(name = "localizador")
    private String localizador;
    @Size(max = 100)
    @Column(name = "ciaAerea")
    private String ciaAerea;
    @Size(max = 50)
    @Column(name = "formaPagamento")
    private String formaPagamento;
    @Size(max = 50)
    @Column(name = "cidadeDestino")
    private String cidadeDestino;
    @Size(max = 50)
    @Column(name = "paisDestino")
    private String paisDestino;
    @Column(name = "dataEmbarque")
    @Temporal(TemporalType.DATE)
    private Date dataEmbarque;
    @Column(name = "dataVolta")
    @Temporal(TemporalType.DATE)
    private Date dataVolta;
    @Size(max = 100)
    @Column(name = "pax01")
    private String pax01;
    @Size(max = 100)
    @Column(name = "pax02")
    private String pax02;
    @Size(max = 100)
    @Column(name = "pax03")
    private String pax03;
    @Size(max = 100)
    @Column(name = "pax04")
    private String pax04;
    @Size(max = 100)
    @Column(name = "pax05")
    private String pax05;
    @Size(max = 100)
    @Column(name = "pax06")
    private String pax06;
    @Size(max = 100)
    @Column(name = "pax07")
    private String pax07;
    @Size(max = 100)
    @Column(name = "pax08")
    private String pax08;
    @Size(max = 100)
    @Column(name = "pax09")
    private String pax09;
    @Size(max = 100)
    @Column(name = "pax10")
    private String pax10;
    @Lob
    @Size(max = 16777215)
    @Column(name = "observacoes")
    private String observacoes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorCambio")
    private Float valorCambio;
    @Size(max = 45)
    @Column(name = "referencia")
    private String referencia;
    @Size(max = 100)
    @Column(name = "intinerario")
    private String intinerario;
    @Column(name = "tuadtme")
    private Float tuadtme;
    @Column(name = "tuadtml")
    private Float tuadtml;
    @Size(max = 50)
    @Column(name = "atendente")
    private String atendente;
    @Column(name = "tuchdme")
    private Float tuchdme;
    @Column(name = "tuchdml")
    private Float tuchdml;
    @Column(name = "tuinfme")
    private Float tuinfme;
    @Column(name = "tuinfml")
    private Float tuinfml;
    @Column(name = "teme")
    private Float teme;
    @Column(name = "dataEmissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "teml")
    private Float teml;
    @Column(name = "tenac")
    private Float tenac;
    @Column(name = "tome")
    private Float tome;
    @Column(name = "toml")
    private Float toml;
    @Column(name = "totalop")
    private Float totalop;
    @Column(name = "taxas")
    private Float taxas;
    @Column(name = "geral")
    private Float geral;
    @Size(max = 100)
    @Column(name = "titularcartao")
    private String titularcartao;
    @Size(max = 50)
    @Column(name = "bandeira")
    private String bandeira;
    @Size(max = 45)
    @Column(name = "numerocartao")
    private String numerocartao;
    @Size(max = 7)
    @Column(name = "valiade")
    private String valiade;
    @Size(max = 3)
    @Column(name = "codigoseguranca")
    private String codigoseguranca;
    @Size(max = 45)
    @Column(name = "autorizacao")
    private String autorizacao;
    @Column(name = "valorentrada")
    private Float valorentrada;
    @Column(name = "numeroparcelas")
    private Integer numeroparcelas;
    @Column(name = "valorparcela")
    private Float valorparcela;
    @Size(max = 3)
    @Column(name = "tipopax1")
    private String tipopax1;
    @Size(max = 3)
    @Column(name = "tipopax2")
    private String tipopax2;
    @Size(max = 3)
    @Column(name = "tipopax3")
    private String tipopax3;
    @Size(max = 3)
    @Column(name = "tipopax4")
    private String tipopax4;
    @Size(max = 3)
    @Column(name = "tipopax5")
    private String tipopax5;
    @Size(max = 3)
    @Column(name = "tipopax6")
    private String tipopax6;
    @Size(max = 3)
    @Column(name = "tipopax7")
    private String tipopax7;
    @Size(max = 3)
    @Column(name = "tipopax8")
    private String tipopax8;
    @Size(max = 3)
    @Column(name = "tipopax9")
    private String tipopax9;
    @Size(max = 3)
    @Column(name = "tipopax10")
    private String tipopax10;
    @Column(name = "dataNascimentopax01")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax01;
    @Column(name = "dataNascimentopax02")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax02;
    @Column(name = "dataNascimentopax03")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax03;
    @Column(name = "dataNascimentopax04")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax04;
    @Column(name = "dataNascimentopax05")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax05;
    @Column(name = "dataNascimentopax06")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax06;
    @Column(name = "dataNascimentopax07")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax07;
    @Column(name = "dataNascimentopax08")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax08;
    @Column(name = "dataNascimentopax09")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax09;
    @Column(name = "dataNascimentopax10")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentopax10;
    @Size(max = 20)
    @Column(name = "passaporte01")
    private String passaporte01;
    @Size(max = 20)
    @Column(name = "passaporte02")
    private String passaporte02;
    @Size(max = 20)
    @Column(name = "passaporte03")
    private String passaporte03;
    @Size(max = 20)
    @Column(name = "passaporte04")
    private String passaporte04;
    @Size(max = 20)
    @Column(name = "passaporte05")
    private String passaporte05;
    @Size(max = 20)
    @Column(name = "passaporte06")
    private String passaporte06;
    @Size(max = 20)
    @Column(name = "passaporte07")
    private String passaporte07;
    @Size(max = 20)
    @Column(name = "passaporte08")
    private String passaporte08;
    @Size(max = 20)
    @Column(name = "passaporte09")
    private String passaporte09;
    @Size(max = 20)
    @Column(name = "passaporte10")
    private String passaporte10;
    @Column(name = "totalTaxaEmbarqueEstrangeira")
    private Float totalTaxaEmbarqueEstrangeira;
    @Column(name = "totalTaxaEmbarqueNacionall")
    private Float totalTaxaEmbarqueNacionall;
    @Column(name = "totalOPTaxas")
    private Float totalOPTaxas;
    @Column(name = "valornet")
    private Float valornet;
    @Column(name = "valorgross")
    private Float valorgross;
    @Column(name = "valormoedanacional")
    private Float valormoedanacional;
    @Column(name = "comissao")
    private Float comissao;
    @Column(name = "comissaoloja")
    private Float comissaoloja;
    @JoinColumn(name = "cambio_idcambio", referencedColumnName = "idcambio")
    @ManyToOne(optional = false)
    private Cambio cambio;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "pacotetrecho_idpacotetrecho", referencedColumnName = "idpacotetrecho")
    @ManyToOne(optional = false)
    private Pacotetrecho pacotetrecho;

    public Pacotepassagem() {
    }

    public Pacotepassagem(Integer idpacotepassagem) {
        this.idpacotepassagem = idpacotepassagem;
    }

    public Integer getIdpacotepassagem() {
        return idpacotepassagem;
    }

    public void setIdpacotepassagem(Integer idpacotepassagem) {
        this.idpacotepassagem = idpacotepassagem;
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public String getCiaAerea() {
        return ciaAerea;
    }

    public void setCiaAerea(String ciaAerea) {
        this.ciaAerea = ciaAerea;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public Date getDataEmbarque() {
        return dataEmbarque;
    }

    public void setDataEmbarque(Date dataEmbarque) {
        this.dataEmbarque = dataEmbarque;
    }

    public Date getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(Date dataVolta) {
        this.dataVolta = dataVolta;
    }

    public String getPax01() {
        return pax01;
    }

    public void setPax01(String pax01) {
        this.pax01 = pax01;
    }

    public String getPax02() {
        return pax02;
    }

    public void setPax02(String pax02) {
        this.pax02 = pax02;
    }

    public String getPax03() {
        return pax03;
    }

    public void setPax03(String pax03) {
        this.pax03 = pax03;
    }

    public String getPax04() {
        return pax04;
    }

    public void setPax04(String pax04) {
        this.pax04 = pax04;
    }

    public String getPax05() {
        return pax05;
    }

    public void setPax05(String pax05) {
        this.pax05 = pax05;
    }

    public String getPax06() {
        return pax06;
    }

    public void setPax06(String pax06) {
        this.pax06 = pax06;
    }

    public String getPax07() {
        return pax07;
    }

    public void setPax07(String pax07) {
        this.pax07 = pax07;
    }

    public String getPax08() {
        return pax08;
    }

    public void setPax08(String pax08) {
        this.pax08 = pax08;
    }

    public String getPax09() {
        return pax09;
    }

    public void setPax09(String pax09) {
        this.pax09 = pax09;
    }

    public String getPax10() {
        return pax10;
    }

    public void setPax10(String pax10) {
        this.pax10 = pax10;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Float getValorCambio() {
        return valorCambio;
    }

    public void setValorCambio(Float valorCambio) {
        this.valorCambio = valorCambio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getIntinerario() {
        return intinerario;
    }

    public void setIntinerario(String intinerario) {
        this.intinerario = intinerario;
    }

    public Float getTuadtme() {
        return tuadtme;
    }

    public void setTuadtme(Float tuadtme) {
        this.tuadtme = tuadtme;
    }

    public Float getTuadtml() {
        return tuadtml;
    }

    public void setTuadtml(Float tuadtml) {
        this.tuadtml = tuadtml;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public Float getTuchdme() {
        return tuchdme;
    }

    public void setTuchdme(Float tuchdme) {
        this.tuchdme = tuchdme;
    }

    public Float getTuchdml() {
        return tuchdml;
    }

    public void setTuchdml(Float tuchdml) {
        this.tuchdml = tuchdml;
    }

    public Float getTuinfme() {
        return tuinfme;
    }

    public void setTuinfme(Float tuinfme) {
        this.tuinfme = tuinfme;
    }

    public Float getTuinfml() {
        return tuinfml;
    }

    public void setTuinfml(Float tuinfml) {
        this.tuinfml = tuinfml;
    }

    public Float getTeme() {
        return teme;
    }

    public void setTeme(Float teme) {
        this.teme = teme;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Float getTeml() {
        return teml;
    }

    public void setTeml(Float teml) {
        this.teml = teml;
    }

    public Float getTenac() {
        return tenac;
    }

    public void setTenac(Float tenac) {
        this.tenac = tenac;
    }

    public Float getTome() {
        return tome;
    }

    public void setTome(Float tome) {
        this.tome = tome;
    }

    public Float getToml() {
        return toml;
    }

    public void setToml(Float toml) {
        this.toml = toml;
    }

    public Float getTotalop() {
        return totalop;
    }

    public void setTotalop(Float totalop) {
        this.totalop = totalop;
    }

    public Float getTaxas() {
        return taxas;
    }

    public void setTaxas(Float taxas) {
        this.taxas = taxas;
    }

    public Float getGeral() {
        return geral;
    }

    public void setGeral(Float geral) {
        this.geral = geral;
    }

    public String getTitularcartao() {
        return titularcartao;
    }

    public void setTitularcartao(String titularcartao) {
        this.titularcartao = titularcartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumerocartao() {
        return numerocartao;
    }

    public void setNumerocartao(String numerocartao) {
        this.numerocartao = numerocartao;
    }

    public String getValiade() {
        return valiade;
    }

    public void setValiade(String valiade) {
        this.valiade = valiade;
    }

    public String getCodigoseguranca() {
        return codigoseguranca;
    }

    public void setCodigoseguranca(String codigoseguranca) {
        this.codigoseguranca = codigoseguranca;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    public Float getValorentrada() {
        return valorentrada;
    }

    public void setValorentrada(Float valorentrada) {
        this.valorentrada = valorentrada;
    }

    public Integer getNumeroparcelas() {
        return numeroparcelas;
    }

    public void setNumeroparcelas(Integer numeroparcelas) {
        this.numeroparcelas = numeroparcelas;
    }

    public Float getValorparcela() {
        return valorparcela;
    }

    public void setValorparcela(Float valorparcela) {
        this.valorparcela = valorparcela;
    }

    public String getTipopax1() {
        return tipopax1;
    }

    public void setTipopax1(String tipopax1) {
        this.tipopax1 = tipopax1;
    }

    public String getTipopax2() {
        return tipopax2;
    }

    public void setTipopax2(String tipopax2) {
        this.tipopax2 = tipopax2;
    }

    public String getTipopax3() {
        return tipopax3;
    }

    public void setTipopax3(String tipopax3) {
        this.tipopax3 = tipopax3;
    }

    public String getTipopax4() {
        return tipopax4;
    }

    public void setTipopax4(String tipopax4) {
        this.tipopax4 = tipopax4;
    }

    public String getTipopax5() {
        return tipopax5;
    }

    public void setTipopax5(String tipopax5) {
        this.tipopax5 = tipopax5;
    }

    public String getTipopax6() {
        return tipopax6;
    }

    public void setTipopax6(String tipopax6) {
        this.tipopax6 = tipopax6;
    }

    public String getTipopax7() {
        return tipopax7;
    }

    public void setTipopax7(String tipopax7) {
        this.tipopax7 = tipopax7;
    }

    public String getTipopax8() {
        return tipopax8;
    }

    public void setTipopax8(String tipopax8) {
        this.tipopax8 = tipopax8;
    }

    public String getTipopax9() {
        return tipopax9;
    }

    public void setTipopax9(String tipopax9) {
        this.tipopax9 = tipopax9;
    }

    public String getTipopax10() {
        return tipopax10;
    }

    public void setTipopax10(String tipopax10) {
        this.tipopax10 = tipopax10;
    }

    public Date getDataNascimentopax01() {
        return dataNascimentopax01;
    }

    public void setDataNascimentopax01(Date dataNascimentopax01) {
        this.dataNascimentopax01 = dataNascimentopax01;
    }

    public Date getDataNascimentopax02() {
        return dataNascimentopax02;
    }

    public void setDataNascimentopax02(Date dataNascimentopax02) {
        this.dataNascimentopax02 = dataNascimentopax02;
    }

    public Date getDataNascimentopax03() {
        return dataNascimentopax03;
    }

    public void setDataNascimentopax03(Date dataNascimentopax03) {
        this.dataNascimentopax03 = dataNascimentopax03;
    }

    public Date getDataNascimentopax04() {
        return dataNascimentopax04;
    }

    public void setDataNascimentopax04(Date dataNascimentopax04) {
        this.dataNascimentopax04 = dataNascimentopax04;
    }

    public Date getDataNascimentopax05() {
        return dataNascimentopax05;
    }

    public void setDataNascimentopax05(Date dataNascimentopax05) {
        this.dataNascimentopax05 = dataNascimentopax05;
    }

    public Date getDataNascimentopax06() {
        return dataNascimentopax06;
    }

    public void setDataNascimentopax06(Date dataNascimentopax06) {
        this.dataNascimentopax06 = dataNascimentopax06;
    }

    public Date getDataNascimentopax07() {
        return dataNascimentopax07;
    }

    public void setDataNascimentopax07(Date dataNascimentopax07) {
        this.dataNascimentopax07 = dataNascimentopax07;
    }

    public Date getDataNascimentopax08() {
        return dataNascimentopax08;
    }

    public void setDataNascimentopax08(Date dataNascimentopax08) {
        this.dataNascimentopax08 = dataNascimentopax08;
    }

    public Date getDataNascimentopax09() {
        return dataNascimentopax09;
    }

    public void setDataNascimentopax09(Date dataNascimentopax09) {
        this.dataNascimentopax09 = dataNascimentopax09;
    }

    public Date getDataNascimentopax10() {
        return dataNascimentopax10;
    }

    public void setDataNascimentopax10(Date dataNascimentopax10) {
        this.dataNascimentopax10 = dataNascimentopax10;
    }

    public String getPassaporte01() {
        return passaporte01;
    }

    public void setPassaporte01(String passaporte01) {
        this.passaporte01 = passaporte01;
    }

    public String getPassaporte02() {
        return passaporte02;
    }

    public void setPassaporte02(String passaporte02) {
        this.passaporte02 = passaporte02;
    }

    public String getPassaporte03() {
        return passaporte03;
    }

    public void setPassaporte03(String passaporte03) {
        this.passaporte03 = passaporte03;
    }

    public String getPassaporte04() {
        return passaporte04;
    }

    public void setPassaporte04(String passaporte04) {
        this.passaporte04 = passaporte04;
    }

    public String getPassaporte05() {
        return passaporte05;
    }

    public void setPassaporte05(String passaporte05) {
        this.passaporte05 = passaporte05;
    }

    public String getPassaporte06() {
        return passaporte06;
    }

    public void setPassaporte06(String passaporte06) {
        this.passaporte06 = passaporte06;
    }

    public String getPassaporte07() {
        return passaporte07;
    }

    public void setPassaporte07(String passaporte07) {
        this.passaporte07 = passaporte07;
    }

    public String getPassaporte08() {
        return passaporte08;
    }

    public void setPassaporte08(String passaporte08) {
        this.passaporte08 = passaporte08;
    }

    public String getPassaporte09() {
        return passaporte09;
    }

    public void setPassaporte09(String passaporte09) {
        this.passaporte09 = passaporte09;
    }

    public String getPassaporte10() {
        return passaporte10;
    }

    public void setPassaporte10(String passaporte10) {
        this.passaporte10 = passaporte10;
    }

    public Float getTotalTaxaEmbarqueEstrangeira() {
        return totalTaxaEmbarqueEstrangeira;
    }

    public void setTotalTaxaEmbarqueEstrangeira(Float totalTaxaEmbarqueEstrangeira) {
        this.totalTaxaEmbarqueEstrangeira = totalTaxaEmbarqueEstrangeira;
    }

    public Float getTotalTaxaEmbarqueNacionall() {
        return totalTaxaEmbarqueNacionall;
    }

    public void setTotalTaxaEmbarqueNacionall(Float totalTaxaEmbarqueNacionall) {
        this.totalTaxaEmbarqueNacionall = totalTaxaEmbarqueNacionall;
    }

    public Float getTotalOPTaxas() {
        return totalOPTaxas;
    }

    public void setTotalOPTaxas(Float totalOPTaxas) {
        this.totalOPTaxas = totalOPTaxas;
    }

    public Float getValornet() {
        return valornet;
    }

    public void setValornet(Float valornet) {
        this.valornet = valornet;
    }

    public Float getValorgross() {
        return valorgross;
    }

    public void setValorgross(Float valorgross) {
        this.valorgross = valorgross;
    }

    public Float getValormoedanacional() {
        return valormoedanacional;
    }

    public void setValormoedanacional(Float valormoedanacional) {
        this.valormoedanacional = valormoedanacional;
    }

    public Float getComissao() {
        return comissao;
    }

    public void setComissao(Float comissao) {
        this.comissao = comissao;
    }

    public Float getComissaoloja() {
        return comissaoloja;
    }

    public void setComissaoloja(Float comissaoloja) {
        this.comissaoloja = comissaoloja;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public Pacotetrecho getPacotetrecho() {
        return pacotetrecho;
    }

    public void setPacotetrecho(Pacotetrecho pacotetrecho) {
        this.pacotetrecho = pacotetrecho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpacotepassagem != null ? idpacotepassagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacotepassagem)) {
            return false;
        }
        Pacotepassagem other = (Pacotepassagem) object;
        if ((this.idpacotepassagem == null && other.idpacotepassagem != null) || (this.idpacotepassagem != null && !this.idpacotepassagem.equals(other.idpacotepassagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Pacotepassagem[ idpacotepassagem=" + idpacotepassagem + " ]";
    }
    
}
