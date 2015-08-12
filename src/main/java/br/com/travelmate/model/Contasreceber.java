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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "contasreceber")
@NamedQueries({
    @NamedQuery(name = "Contasreceber.findAll", query = "SELECT c FROM Contasreceber c")})
public class Contasreceber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcontasreceber")
    private Integer idcontasreceber;
    @Size(max = 30)
    @Column(name = "numerodocumento")
    private String numerodocumento;
    @Column(name = "numeroparcelas")
    private Integer numeroparcelas;
    @Column(name = "datavencimento")
    @Temporal(TemporalType.DATE)
    private Date datavencimento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorparcela")
    private Float valorparcela;
    @Column(name = "juros")
    private Float juros;
    @Column(name = "desagio")
    private Float desagio;
    @Column(name = "datapagamento")
    @Temporal(TemporalType.DATE)
    private Date datapagamento;
    @Column(name = "valorpago")
    private Float valorpago;
    @Size(max = 50)
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Column(name = "boletogerado")
    private Boolean boletogerado;
    @Size(max = 8)
    @Column(name = "nossonumero")
    private String nossonumero;
    @Column(name = "dataEmissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "dataRetorno")
    @Temporal(TemporalType.DATE)
    private Date dataRetorno;
    @Column(name = "boletoenviado")
    private Boolean boletoenviado;
    @JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "planoconta_idplanoconta", referencedColumnName = "idplanoconta")
    @ManyToOne(optional = false)
    private Planoconta planoconta;
    @JoinColumn(name = "recebimento_idrecebimento", referencedColumnName = "idrecebimento")
    @ManyToOne(optional = false)
    private Recebimento recebimento;
    @JoinColumn(name = "vendas_idvendas", referencedColumnName = "idvendas")
    @ManyToOne(optional = false)
    private Vendas vendas;
    @Transient
    private boolean selecionado;

    public Contasreceber() {
    }

    public Contasreceber(Integer idcontasreceber) {
        this.idcontasreceber = idcontasreceber;
    }

    public Integer getIdcontasreceber() {
        return idcontasreceber;
    }

    public void setIdcontasreceber(Integer idcontasreceber) {
        this.idcontasreceber = idcontasreceber;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public Integer getNumeroparcelas() {
        return numeroparcelas;
    }

    public void setNumeroparcelas(Integer numeroparcelas) {
        this.numeroparcelas = numeroparcelas;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public Float getValorparcela() {
        return valorparcela;
    }

    public void setValorparcela(Float valorparcela) {
        this.valorparcela = valorparcela;
    }

    public Float getJuros() {
        return juros;
    }

    public void setJuros(Float juros) {
        this.juros = juros;
    }

    public Float getDesagio() {
        return desagio;
    }

    public void setDesagio(Float desagio) {
        this.desagio = desagio;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public Float getValorpago() {
        return valorpago;
    }

    public void setValorpago(Float valorpago) {
        this.valorpago = valorpago;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Boolean getBoletogerado() {
        return boletogerado;
    }

    public void setBoletogerado(Boolean boletogerado) {
        this.boletogerado = boletogerado;
    }

    public String getNossonumero() {
        return nossonumero;
    }

    public void setNossonumero(String nossonumero) {
        this.nossonumero = nossonumero;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Boolean getBoletoenviado() {
        return boletoenviado;
    }

    public void setBoletoenviado(Boolean boletoenviado) {
        this.boletoenviado = boletoenviado;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Planoconta getPlanoconta() {
        return planoconta;
    }

    public void setPlanoconta(Planoconta planoconta) {
        this.planoconta = planoconta;
    }

    public Recebimento getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontasreceber != null ? idcontasreceber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contasreceber)) {
            return false;
        }
        Contasreceber other = (Contasreceber) object;
        if ((this.idcontasreceber == null && other.idcontasreceber != null) || (this.idcontasreceber != null && !this.idcontasreceber.equals(other.idcontasreceber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Contasreceber[ idcontasreceber=" + idcontasreceber + " ]";
    }
    
}
