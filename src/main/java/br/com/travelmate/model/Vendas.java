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
import javax.persistence.Lob;
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
@Table(name = "vendas")
@NamedQueries({
    @NamedQuery(name = "Vendas.findAll", query = "SELECT v FROM Vendas v")})
public class Vendas implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Programasteens> programasteensList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Ladies> ladiesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Highschool> highschoolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Contasreceber> contasreceberList;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Pacotes> pacotesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Passagemaerea> passagemaereaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvendas")
    private Integer idvendas;
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Size(max = 20)
    @Column(name = "situacao")
    private String situacao;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Obstm")
    private String obstm;
    @Size(max = 1)
    @Column(name = "vendasMatriz")
    private String vendasMatriz;
    @Column(name = "vendaimportada")
    private Integer vendaimportada;
    @Size(max = 200)
    @Column(name = "obsCancelar")
    private String obsCancelar;
    @Column(name = "datacancelamento")
    @Temporal(TemporalType.DATE)
    private Date datacancelamento;
    @Column(name = "usuariocancelamento")
    private Integer usuariocancelamento;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "fornecedor_idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos")
    @ManyToOne(optional = false)
    private Produtos produtos;
    @JoinColumn(name = "unidadeNegocio_idunidadeNegocio", referencedColumnName = "idunidadeNegocio")
    @ManyToOne(optional = false)
    private Unidadenegocio unidadenegocio;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Cobranca> cobrancaList;
    

    public Vendas() {
    }

    public Vendas(Integer idvendas) {
        this.idvendas = idvendas;
    }

    public Integer getIdvendas() {
        return idvendas;
    }

    public void setIdvendas(Integer idvendas) {
        this.idvendas = idvendas;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getObstm() {
        return obstm;
    }

    public void setObstm(String obstm) {
        this.obstm = obstm;
    }

    public String getVendasMatriz() {
        return vendasMatriz;
    }

    public void setVendasMatriz(String vendasMatriz) {
        this.vendasMatriz = vendasMatriz;
    }

    public Integer getVendaimportada() {
        return vendaimportada;
    }

    public void setVendaimportada(Integer vendaimportada) {
        this.vendaimportada = vendaimportada;
    }

    public String getObsCancelar() {
        return obsCancelar;
    }

    public void setObsCancelar(String obsCancelar) {
        this.obsCancelar = obsCancelar;
    }

    public Date getDatacancelamento() {
        return datacancelamento;
    }

    public void setDatacancelamento(Date datacancelamento) {
        this.datacancelamento = datacancelamento;
    }

    public Integer getUsuariocancelamento() {
        return usuariocancelamento;
    }

    public void setUsuariocancelamento(Integer usuariocancelamento) {
        this.usuariocancelamento = usuariocancelamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvendas != null ? idvendas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendas)) {
            return false;
        }
        Vendas other = (Vendas) object;
        if ((this.idvendas == null && other.idvendas != null) || (this.idvendas != null && !this.idvendas.equals(other.idvendas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Vendas[ idvendas=" + idvendas + " ]";
    }

    public List<Cobranca> getCobrancaList() {
        return cobrancaList;
    }

    public void setCobrancaList(List<Cobranca> cobrancaList) {
        this.cobrancaList = cobrancaList;
    }

    public List<Pacotes> getPacotesList() {
        return pacotesList;
    }

    public void setPacotesList(List<Pacotes> pacotesList) {
        this.pacotesList = pacotesList;
    }

    public List<Passagemaerea> getPassagemaereaList() {
        return passagemaereaList;
    }

    public void setPassagemaereaList(List<Passagemaerea> passagemaereaList) {
        this.passagemaereaList = passagemaereaList;
    }

    public List<Contasreceber> getContasreceberList() {
        return contasreceberList;
    }

    public void setContasreceberList(List<Contasreceber> contasreceberList) {
        this.contasreceberList = contasreceberList;
    }

    public List<Highschool> getHighschoolList() {
        return highschoolList;
    }

    public void setHighschoolList(List<Highschool> highschoolList) {
        this.highschoolList = highschoolList;
    }

    public List<Programasteens> getProgramasteensList() {
        return programasteensList;
    }

    public void setProgramasteensList(List<Programasteens> programasteensList) {
        this.programasteensList = programasteensList;
    }

    public List<Ladies> getLadiesList() {
        return ladiesList;
    }

    public void setLadiesList(List<Ladies> ladiesList) {
        this.ladiesList = ladiesList;
    }

    

}
