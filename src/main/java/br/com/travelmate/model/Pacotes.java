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
@Table(name = "pacotes")
@NamedQueries({
    @NamedQuery(name = "Pacotes.findAll", query = "SELECT p FROM Pacotes p")})
public class Pacotes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpacotes")
    private Integer idpacotes;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "datainicio")
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Column(name = "datetermino")
    @Temporal(TemporalType.DATE)
    private Date datetermino;
    @Size(max = 50)
    @Column(name = "controle")
    private String controle;
    @Size(max = 10)
    @Column(name = "operacao")
    private String operacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorgross")
    private Float valorgross;
    @Column(name = "valornet")
    private Float valornet;
    @Column(name = "comissao")
    private Double comissao;
    @Column(name = "valormoedanacional")
    private Float valormoedanacional;
    @Column(name = "comissaoloja")
    private Float comissaoloja;
    @Size(max = 3)
    @Column(name = "cartaovtm")
    private String cartaovtm;
    @Size(max = 50)
    @Column(name = "numerocartaovtm")
    private String numerocartaovtm;
    @Size(max = 10)
    @Column(name = "moeda")
    private String moeda;
    @JoinColumn(name = "cambio_idcambio", referencedColumnName = "idcambio")
    @ManyToOne(optional = false)
    private Cambio cambio;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "unidadeNegocio_idunidadeNegocio", referencedColumnName = "idunidadeNegocio")
    @ManyToOne(optional = false)
    private Unidadenegocio unidadenegocio;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "vendas_idvendas", referencedColumnName = "idvendas")
    @ManyToOne(optional = false)
    private Vendas vendas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacotes")
    private List<Pacotetrecho> pacotetrechoList;

    public Pacotes() {
    }

    public Pacotes(Integer idpacotes) {
        this.idpacotes = idpacotes;
    }

    public Integer getIdpacotes() {
        return idpacotes;
    }

    public void setIdpacotes(Integer idpacotes) {
        this.idpacotes = idpacotes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatetermino() {
        return datetermino;
    }

    public void setDatetermino(Date datetermino) {
        this.datetermino = datetermino;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Float getValorgross() {
        return valorgross;
    }

    public void setValorgross(Float valorgross) {
        this.valorgross = valorgross;
    }

    public Float getValornet() {
        return valornet;
    }

    public void setValornet(Float valornet) {
        this.valornet = valornet;
    }

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public Float getValormoedanacional() {
        return valormoedanacional;
    }

    public void setValormoedanacional(Float valormoedanacional) {
        this.valormoedanacional = valormoedanacional;
    }

    public Float getComissaoloja() {
        return comissaoloja;
    }

    public void setComissaoloja(Float comissaoloja) {
        this.comissaoloja = comissaoloja;
    }

    public String getCartaovtm() {
        return cartaovtm;
    }

    public void setCartaovtm(String cartaovtm) {
        this.cartaovtm = cartaovtm;
    }

    public String getNumerocartaovtm() {
        return numerocartaovtm;
    }

    public void setNumerocartaovtm(String numerocartaovtm) {
        this.numerocartaovtm = numerocartaovtm;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
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

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }

    public List<Pacotetrecho> getPacotetrechoList() {
        return pacotetrechoList;
    }

    public void setPacotetrechoList(List<Pacotetrecho> pacotetrechoList) {
        this.pacotetrechoList = pacotetrechoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpacotes != null ? idpacotes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pacotes)) {
            return false;
        }
        Pacotes other = (Pacotes) object;
        if ((this.idpacotes == null && other.idpacotes != null) || (this.idpacotes != null && !this.idpacotes.equals(other.idpacotes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Pacotes[ idpacotes=" + idpacotes + " ]";
    }
    
}
