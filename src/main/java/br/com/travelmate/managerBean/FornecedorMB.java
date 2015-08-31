/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.model.Produtos;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */

@Named
@ViewScoped
public class FornecedorMB implements Serializable{
    
    
	private static final long serialVersionUID = 1L;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @Inject
    private ConsultaFornecedorMB consultaFornecedorMB;
    private List<Paisproduto> listaPais;
    private Pais pais;
    private Fornecedorcidade fornecedorCidade;
    private Produtos produto;
    private List<Produtos> listaProdutos;
    private Cidade cidade;
    private Fornecedor fornecedor;
    private List<Fornecedor> listaFornecedor;
    private List<Fornecedorcidade> listaFornecedorCidade;
    
    @PostConstruct
    public void init() {
        int idProduto = 0;
        getUsuarioLogadoMB();
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
        listaPais = paisProdutoFacade.listar(idProduto);
        listaProdutos = GerarListas.listarProdutos("");
        if(listaFornecedor==null){
            listaFornecedor = new ArrayList<Fornecedor>();
        }
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Paisproduto> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Paisproduto> listaPais) {
        this.listaPais = listaPais;
    }

    

    public Fornecedorcidade getFornecedorCidade() {
        return fornecedorCidade;
    }

    public void setFornecedorCidade(Fornecedorcidade fornecedorCidade) {
        this.fornecedorCidade = fornecedorCidade;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public List<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produtos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Fornecedorcidade> getListaFornecedorCidade() {
        return listaFornecedorCidade;
    }

    public void setListaFornecedorCidade(List<Fornecedorcidade> listaFornecedorCidade) {
        this.listaFornecedorCidade = listaFornecedorCidade;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    
    
    
    
    public void listarFornecedorCidade(){
        if ((produto!=null) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(produto.getIdprodutos(), cidade.getIdcidade());
        }
    }
    
    public String selecionaFornecedorCidade(Fornecedorcidade fornecedorcidade){
        consultaFornecedorMB.setFornecedorcidade(fornecedorcidade);
        String retorno = consultaFornecedorMB.getRetorno();
        consultaFornecedorMB.setRetorno("");
        return retorno;
    }
    
    public String consFornecedor(){
        return "consFornecedores";
    }
    
    public String consPais(){
        return "consPais";
    }
    
    public String cadPais(){
        RequestContext.getCurrentInstance().openDialog("cadPais");
        return "";
    }
    
    public String cadCidade(){
        RequestContext.getCurrentInstance().openDialog("cadCidade");
        return "";
    }
    
    public String cancelarCadastro(){
        RequestContext.getCurrentInstance().closeDialog("null");
        return "";
    }
    
    public String cadFornecedorCidade(){
        return "cadFornecedorCidade";
    }
    
    public String cadFornecedorComissao(){
        RequestContext.getCurrentInstance().openDialog("cadFornecedorComissao");
        return "";
    }
    
    public String voltar(){
        return "consFornecedorPais";
    }
}
