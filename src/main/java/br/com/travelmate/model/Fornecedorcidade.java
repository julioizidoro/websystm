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
@Table(name = "fornecedorcidade")
@NamedQueries({
    @NamedQuery(name = "Fornecedorcidade.findAll", query = "SELECT f FROM Fornecedorcidade f")})
public class Fornecedorcidade implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Valoresseguro> valoresseguroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacotepasseio> pacotepasseioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacotecarro> pacotecarroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacotetransfer> pacotetransferList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacotetrem> pacotetremList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacotehotel> pacotehotelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacotecruzeiro> pacotecruzeiroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacoteingresso> pacoteingressoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Pacotepassagem> pacotepassagemList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfornecedorcidade")
    private Integer idfornecedorcidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorcidade")
    private List<Vendas> vendasList;
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    @ManyToOne(optional = false)
    private Cidade cidade;
    @JoinColumn(name = "fornecedor_idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos")
    @ManyToOne(optional = false)
    private Produtos produtos;

    public Fornecedorcidade() {
    }

    public Fornecedorcidade(Integer idfornecedorcidade) {
        this.idfornecedorcidade = idfornecedorcidade;
    }

    public Integer getIdfornecedorcidade() {
        return idfornecedorcidade;
    }

    public void setIdfornecedorcidade(Integer idfornecedorcidade) {
        this.idfornecedorcidade = idfornecedorcidade;
    }

   
    public List<Vendas> getVendasList() {
        return vendasList;
    }

    public void setVendasList(List<Vendas> vendasList) {
        this.vendasList = vendasList;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedorcidade != null ? idfornecedorcidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedorcidade)) {
            return false;
        }
        Fornecedorcidade other = (Fornecedorcidade) object;
        if ((this.idfornecedorcidade == null && other.idfornecedorcidade != null) || (this.idfornecedorcidade != null && !this.idfornecedorcidade.equals(other.idfornecedorcidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Fornecedorcidade[ idfornecedorcidade=" + idfornecedorcidade + " ]";
    }

    public List<Pacotepasseio> getPacotepasseioList() {
        return pacotepasseioList;
    }

    public void setPacotepasseioList(List<Pacotepasseio> pacotepasseioList) {
        this.pacotepasseioList = pacotepasseioList;
    }

    public List<Pacotecarro> getPacotecarroList() {
        return pacotecarroList;
    }

    public void setPacotecarroList(List<Pacotecarro> pacotecarroList) {
        this.pacotecarroList = pacotecarroList;
    }

    public List<Pacotetransfer> getPacotetransferList() {
        return pacotetransferList;
    }

    public void setPacotetransferList(List<Pacotetransfer> pacotetransferList) {
        this.pacotetransferList = pacotetransferList;
    }

    public List<Pacotetrem> getPacotetremList() {
        return pacotetremList;
    }

    public void setPacotetremList(List<Pacotetrem> pacotetremList) {
        this.pacotetremList = pacotetremList;
    }

    public List<Pacotehotel> getPacotehotelList() {
        return pacotehotelList;
    }

    public void setPacotehotelList(List<Pacotehotel> pacotehotelList) {
        this.pacotehotelList = pacotehotelList;
    }

    public List<Pacotecruzeiro> getPacotecruzeiroList() {
        return pacotecruzeiroList;
    }

    public void setPacotecruzeiroList(List<Pacotecruzeiro> pacotecruzeiroList) {
        this.pacotecruzeiroList = pacotecruzeiroList;
    }

    public List<Pacoteingresso> getPacoteingressoList() {
        return pacoteingressoList;
    }

    public void setPacoteingressoList(List<Pacoteingresso> pacoteingressoList) {
        this.pacoteingressoList = pacoteingressoList;
    }

    public List<Pacotepassagem> getPacotepassagemList() {
        return pacotepassagemList;
    }

    public void setPacotepassagemList(List<Pacotepassagem> pacotepassagemList) {
        this.pacotepassagemList = pacotepassagemList;
    }

    public List<Valoresseguro> getValoresseguroList() {
        return valoresseguroList;
    }

    public void setValoresseguroList(List<Valoresseguro> valoresseguroList) {
        this.valoresseguroList = valoresseguroList;
    }

}
