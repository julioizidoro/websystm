package br.com.travelmate.managerBean.fornecedor;

import br.com.travelmate.facade.FiltroOrcamentoProdutoFacade;
import br.com.travelmate.facade.FornecedorComissaoCursoFacade;
import br.com.travelmate.facade.FornecedorComissaoCursoProdutoFacade;
import br.com.travelmate.facade.FornecedorFacade;
import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Filtroorcamentoproduto;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcomissaocurso;
import br.com.travelmate.model.Fornecedorcomissaocursoproduto;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Produtosorcamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class FornecedorComissaoMB implements Serializable{
    
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Pais pais;
    private List<Pais> listaPais;
    private Produtosorcamento produtoOrcamento;
    private List<Filtroorcamentoproduto> listaProdutos;
    private Fornecedor fornecedor;
    private List<Fornecedor> listaFornecedor;
    private Fornecedorcomissaocurso fornecedorcomissaocurso;
    private Fornecedorcomissaocursoproduto fornecedorcomissaocursoproduto;
    private List<Fornecedorcomissaocursoproduto> listaComissao;
    
    @PostConstruct
    public void init() {
        gerarListaFornecedor();
        gerarListaProdutos();
        fornecedorcomissaocurso = new Fornecedorcomissaocurso();
        fornecedorcomissaocursoproduto = new Fornecedorcomissaocursoproduto();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    public Produtosorcamento getProdutoOrcamento() {
        return produtoOrcamento;
    }

    public void setProdutoOrcamento(Produtosorcamento produtoOrcamento) {
        this.produtoOrcamento = produtoOrcamento;
    }

    public List<Filtroorcamentoproduto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Filtroorcamentoproduto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public Fornecedorcomissaocurso getFornecedorcomissaocurso() {
        return fornecedorcomissaocurso;
    }

    public void setFornecedorcomissaocurso(Fornecedorcomissaocurso fornecedorcomissaocurso) {
        this.fornecedorcomissaocurso = fornecedorcomissaocurso;
    }

    public Fornecedorcomissaocursoproduto getFornecedorcomissaocursoproduto() {
        return fornecedorcomissaocursoproduto;
    }

    public void setFornecedorcomissaocursoproduto(Fornecedorcomissaocursoproduto fornecedorcomissaocursoproduto) {
        this.fornecedorcomissaocursoproduto = fornecedorcomissaocursoproduto;
    }

    public List<Fornecedorcomissaocursoproduto> getListaComissao() {
        return listaComissao;
    }

    public void setListaComissao(List<Fornecedorcomissaocursoproduto> listaComissao) {
        this.listaComissao = listaComissao;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }
    
    
    
    public void gerarListaPais(){
        PaisFacade paisFacade = new PaisFacade();
        listaPais = paisFacade.listar("");
        if(listaPais==null){
            listaPais = new ArrayList<Pais>();
        }
    }
    
    public void gerarListaFornecedor(){
        FornecedorFacade fornecedorFacade = new FornecedorFacade();
        listaFornecedor = fornecedorFacade.listar("select f from Fornecedor f order by f.nome");
        if(listaFornecedor==null){
            listaFornecedor = new ArrayList<Fornecedor>();
        }
    }
    
    public void gerarListaProdutos(){
        FiltroOrcamentoProdutoFacade filtroOrcamentoFacade = new FiltroOrcamentoProdutoFacade();
        listaProdutos = filtroOrcamentoFacade.pesquisar("select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
                usuarioLogadoMB.getParametrosprodutos().getCursos() + "  order by f.produtosorcamento.descricao");
        if(listaProdutos==null){
            listaProdutos = new ArrayList<Filtroorcamentoproduto>();
        }
    }
    
    public String cancelarCadastro(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
    public String salvar(){
        if(pais!=null && produtoOrcamento!=null && fornecedor!=null){    
            
            FornecedorComissaoCursoFacade fornecedorComissaoCursoFacade = new FornecedorComissaoCursoFacade();
            fornecedorcomissaocurso.setFornecedor(fornecedor);
            fornecedorcomissaocurso.setPais(pais);
            fornecedorcomissaocurso = fornecedorComissaoCursoFacade.salvar(fornecedorcomissaocurso);
            FornecedorComissaoCursoProdutoFacade fornecedorComissaoCursoProdutoFacade = new FornecedorComissaoCursoProdutoFacade();
            fornecedorcomissaocursoproduto.setFornecedorcomissaocurso(fornecedorcomissaocurso);
            fornecedorcomissaocursoproduto.setProdutosorcamento(produtoOrcamento);
            fornecedorcomissaocursoproduto = fornecedorComissaoCursoProdutoFacade.salvar(fornecedorcomissaocursoproduto);
            gerarListaComissao();
        } else {
            FacesMessage mensagem = new FacesMessage("Faltam informações! ", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public void gerarListaComissao(){
        FornecedorComissaoCursoProdutoFacade fornecedorComissaoCursoProdutoFacade = new FornecedorComissaoCursoProdutoFacade();
        listaComissao = fornecedorComissaoCursoProdutoFacade.listar(fornecedorcomissaocurso.getIdfornecedorcomissao(), produtoOrcamento.getIdprodutosOrcamento());
        if(listaComissao==null){
            listaComissao = new ArrayList<Fornecedorcomissaocursoproduto>();
        }
    }
}
