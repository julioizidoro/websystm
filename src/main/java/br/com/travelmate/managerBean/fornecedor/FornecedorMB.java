/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.fornecedor;

import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.model.Produtos;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


@Named
@ViewScoped
public class FornecedorMB implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Paisproduto> listaPais;
    private Pais pais;
    private Produtos produto;
    private List<Produtos> listaProdutos;
    private Cidade cidade;
    private Fornecedorcidade fornecedorCidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    
    @PostConstruct
    public void init() {
        getUsuarioLogadoMB();
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        listaPais = paisProdutoFacade.listar();
        listaProdutos = GerarListas.listarProdutos("");
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

    
    
    public String consFornecedor(Cidade cidade, Produtos produtos){
        if(cidade!=null & produto!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("produtos", produtos);    
            session.setAttribute("cidade", cidade); 
            RequestContext.getCurrentInstance().openDialog("consFornecedores");
            return "";
        }else{
            FacesMessage mensagem = new FacesMessage("Atenção! ", "campos obrigatorios não preenchidos.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }
    
    public String consPais(){
        return "consPais";
    }
    
    public String cadFornecedorCidade(){
        return "cadFornecedorCidade";
    }
    
    public String cadFornecedorComissao(){
        RequestContext.getCurrentInstance().openDialog("cadFornecedorComissao");
        return "";
    }
    
    public void listarFornecedorCidade(){
        if ((produto!=null) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(produto.getIdprodutos(), cidade.getIdcidade());
        }
    }
    
    public void retornoDialogNovo(SelectEvent event){
       Fornecedorcidade fornecedorcidade = (Fornecedorcidade) event.getObject();
       listaFornecedorCidade.add(fornecedorcidade);
   }
}
