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
@Table(name = "produtosorcamento")
@NamedQueries({
    @NamedQuery(name = "Produtosorcamento.findAll", query = "SELECT p FROM Produtosorcamento p")})
public class Produtosorcamento implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtosorcamento")
    private List<Ocursodesconto> ocursodescontoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtosorcamento")
    private List<Fornecedorcomissaocursoproduto> fornecedorcomissaocursoprodutoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtosorcamento")
    private List<Ocurso> ocursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtosorcamento")
    private List<Coprodutos> coprodutosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtosorcamento")
    private List<Orcamentoprodutosorcamento> orcamentoprodutosorcamentoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprodutosOrcamento")
    private Integer idprodutosOrcamento;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 50)
    @Column(name = "descricaooutralingua")
    private String descricaooutralingua;
    @Column(name = "vincular")
    private Boolean vincular;

    public Produtosorcamento() {
    }

    public Produtosorcamento(Integer idprodutosOrcamento) {
        this.idprodutosOrcamento = idprodutosOrcamento;
    }

    public Integer getIdprodutosOrcamento() {
        return idprodutosOrcamento;
    }

    public void setIdprodutosOrcamento(Integer idprodutosOrcamento) {
        this.idprodutosOrcamento = idprodutosOrcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricaooutralingua() {
        return descricaooutralingua;
    }

    public void setDescricaooutralingua(String descricaooutralingua) {
        this.descricaooutralingua = descricaooutralingua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprodutosOrcamento != null ? idprodutosOrcamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produtosorcamento)) {
            return false;
        }
        Produtosorcamento other = (Produtosorcamento) object;
        if ((this.idprodutosOrcamento == null && other.idprodutosOrcamento != null) || (this.idprodutosOrcamento != null && !this.idprodutosOrcamento.equals(other.idprodutosOrcamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Produtosorcamento[ idprodutosOrcamento=" + idprodutosOrcamento + " ]";
    }

    public List<Orcamentoprodutosorcamento> getOrcamentoprodutosorcamentoList() {
        return orcamentoprodutosorcamentoList;
    }

    public void setOrcamentoprodutosorcamentoList(List<Orcamentoprodutosorcamento> orcamentoprodutosorcamentoList) {
        this.orcamentoprodutosorcamentoList = orcamentoprodutosorcamentoList;
    }

    public List<Coprodutos> getCoprodutosList() {
        return coprodutosList;
    }

    public void setCoprodutosList(List<Coprodutos> coprodutosList) {
        this.coprodutosList = coprodutosList;
    }

    public List<Ocurso> getOcursoList() {
        return ocursoList;
    }

    public void setOcursoList(List<Ocurso> ocursoList) {
        this.ocursoList = ocursoList;
    }

    public List<Fornecedorcomissaocursoproduto> getFornecedorcomissaocursoprodutoList() {
        return fornecedorcomissaocursoprodutoList;
    }

    public void setFornecedorcomissaocursoprodutoList(List<Fornecedorcomissaocursoproduto> fornecedorcomissaocursoprodutoList) {
        this.fornecedorcomissaocursoprodutoList = fornecedorcomissaocursoprodutoList;
    }

    public List<Ocursodesconto> getOcursodescontoList() {
        return ocursodescontoList;
    }

    public void setOcursodescontoList(List<Ocursodesconto> ocursodescontoList) {
        this.ocursodescontoList = ocursodescontoList;
    }

	public Boolean getVincular() {
		return vincular;
	}

	public void setVincular(Boolean vincular) {
		this.vincular = vincular;
	}
    
    
    
}
