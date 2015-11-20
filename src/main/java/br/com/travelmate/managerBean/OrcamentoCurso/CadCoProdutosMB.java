package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.CoProdutosFacade;
import br.com.travelmate.facade.FiltroOrcamentoProdutoFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Filtroorcamentoproduto;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Produtosorcamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadCoProdutosMB implements Serializable{
    
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Coprodutos coprodutos;
     private Fornecedorcidade fornecedorcidade;
    private List<Filtroorcamentoproduto> listaFiltroorcamentoproduto;
    private Produtosorcamento prdutoOrcamento;
    

    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        fornecedorcidade =  (Fornecedorcidade) session.getAttribute("fornecedorCidade");
        session.removeAttribute("fornecedorCidade");
        getUsuarioLogadoMB();
        coprodutos = new Coprodutos();
        prdutoOrcamento = new Produtosorcamento();
     }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Coprodutos getCoprodutos() {
        return coprodutos;
    }

    public void setCoprodutos(Coprodutos coprodutos) {
        this.coprodutos = coprodutos;
    }

    public List<Filtroorcamentoproduto> getListaFiltroorcamentoproduto() {
        return listaFiltroorcamentoproduto;
    }

    public void setListaFiltroorcamentoproduto(List<Filtroorcamentoproduto> listaFiltroorcamentoproduto) {
        this.listaFiltroorcamentoproduto = listaFiltroorcamentoproduto;
    }

    public Produtosorcamento getPrdutoOrcamento() {
        return prdutoOrcamento;
    }

    public void setPrdutoOrcamento(Produtosorcamento prdutoOrcamento) {
        this.prdutoOrcamento = prdutoOrcamento;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    
    
    public void gerarListaProdutosOrcamento(){
        FiltroOrcamentoProdutoFacade filtroOrcamentoProdutoFacade = new FiltroOrcamentoProdutoFacade();
        String sql="";
        if(coprodutos.getTipo().equalsIgnoreCase("Acomodacao")){
            sql = "select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
                usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.tipo='A' order by f.produtosorcamento.descricao";
        }else if(coprodutos.getTipo().equalsIgnoreCase("Transfer")){
            sql = "select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
                usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.tipo='T' order by f.produtosorcamento.descricao";
        }else if(coprodutos.getTipo().equalsIgnoreCase("Transfer")){
            sql = "select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
                usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.tipo='T' order by f.produtosorcamento.descricao";
        }else if(coprodutos.getTipo().equalsIgnoreCase("Opcional")){
            sql = "select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
                usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.tipo='D' order by f.produtosorcamento.descricao";
        }else if(coprodutos.getTipo().equalsIgnoreCase("Obrigatorio")){
            sql = "select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
                usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.tipo='O' order by f.produtosorcamento.descricao";
        }
        listaFiltroorcamentoproduto = filtroOrcamentoProdutoFacade.pesquisar(sql);
        if (listaFiltroorcamentoproduto==null){
            listaFiltroorcamentoproduto = new ArrayList<Filtroorcamentoproduto>();
        }
    }
    
    public String salvarCoProduto(){
        coprodutos.setFornecedorcidade(fornecedorcidade);
        coprodutos.setProdutosorcamento(prdutoOrcamento);
        CoProdutosFacade coProdutosFacade = new CoProdutosFacade();
        coProdutosFacade.salvar(coprodutos);
        RequestContext.getCurrentInstance().closeDialog(coprodutos);
        return "orcamentocurso";
    }
    
    public String cancelarCoProduto(){
        RequestContext.getCurrentInstance().closeDialog(null);
        coprodutos = new Coprodutos();
        return "";
    }
    
}
