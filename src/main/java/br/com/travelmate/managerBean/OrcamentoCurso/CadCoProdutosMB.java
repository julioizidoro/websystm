package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.CoProdutosFacade;
import br.com.travelmate.facade.FiltroOrcamentoProdutoFacade;
import br.com.travelmate.facade.GrupoObrigatorioFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Filtroorcamentoproduto;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Fornecedorcidadeidioma;
import br.com.travelmate.model.Grupoobrigatorio;
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
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadCoProdutosMB implements Serializable{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Coprodutos coprodutos;
    private Fornecedorcidade fornecedorcidade;
    private List<Filtroorcamentoproduto> listaFiltroorcamentoproduto;
    private Produtosorcamento prdutoOrcamento;
    private Produtosorcamento produtoVincular;
    private List<Filtroorcamentoproduto> listaProdutosVincular;
    private Grupoobrigatorio grupoobrigatorio;

    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Fornecedorcidadeidioma fornecedorcidadeidioma = (Fornecedorcidadeidioma) session.getAttribute("fornecedorcidadeidioma");
        coprodutos = (Coprodutos) session.getAttribute("coprodutos");
        session.removeAttribute("coprodutos");
        fornecedorcidade =  fornecedorcidadeidioma.getFornecedorcidade();
        getUsuarioLogadoMB();
        if(coprodutos==null){
            coprodutos = new Coprodutos();
            prdutoOrcamento = new Produtosorcamento();
        }else{
           gerarListaProdutosOrcamento();
           prdutoOrcamento = coprodutos.getProdutosorcamento();
           GrupoObrigatorioFacade grupoObrigatorioFacade = new GrupoObrigatorioFacade();
           grupoobrigatorio = grupoObrigatorioFacade.consultar(coprodutos.getIdcoprodutos());
           if(grupoobrigatorio!=null){
                produtoVincular = grupoobrigatorio.getProdutosorcamento();
           }
        }
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
    
    public Produtosorcamento getProdutoVincular() {
		return produtoVincular;
	}

	public void setProdutoVincular(Produtosorcamento produtoVincular) {
		this.produtoVincular = produtoVincular;
	}

	public List<Filtroorcamentoproduto> getListaProdutosVincular() {
		return listaProdutosVincular;
	}

	public void setListaProdutosVincular(List<Filtroorcamentoproduto> listaProdutosVincular) {
		this.listaProdutosVincular = listaProdutosVincular;
	}
	
	public Grupoobrigatorio getGrupoobrigatorio() {
		return grupoobrigatorio;
	}

	public void setGrupoobrigatorio(Grupoobrigatorio grupoobrigatorio) {
		this.grupoobrigatorio = grupoobrigatorio;
	}

    public void gerarListaProdutosVincular(){
    	FiltroOrcamentoProdutoFacade filtroOrcamentoProdutoFacade = new FiltroOrcamentoProdutoFacade();
    	String sql="";
    	sql="select f from Filtroorcamentoproduto f where f.produtos.idprodutos=" + 
        usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.vincular='true' "
        		+ "order by f.produtosorcamento.descricao";
    	listaProdutosVincular= filtroOrcamentoProdutoFacade.pesquisar(sql);
    	if(listaProdutosVincular == null){
    		listaProdutosVincular = new ArrayList<Filtroorcamentoproduto>();
    	}
    }
    
    public void gerarListaProdutosOrcamento(){
    	gerarListaProdutosVincular();
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
                usuarioLogadoMB.getParametrosprodutos().getCursos() + " and f.produtosorcamento.tipo='O' or f.produtosorcamento.tipo='C' order by f.produtosorcamento.descricao";
        }
        listaFiltroorcamentoproduto = filtroOrcamentoProdutoFacade.pesquisar(sql);
        if (listaFiltroorcamentoproduto==null){
            listaFiltroorcamentoproduto = new ArrayList<Filtroorcamentoproduto>();
        }
    }
    
    public String salvarCoProduto(){
        if(coprodutos.getTipo()!=null && prdutoOrcamento!=null && coprodutos.getDescricao()!=null){
            coprodutos.setFornecedorcidade(fornecedorcidade);
            coprodutos.setProdutosorcamento(prdutoOrcamento);
            CoProdutosFacade coProdutosFacade = new CoProdutosFacade();
            coprodutos = coProdutosFacade.salvar(coprodutos);
            if(produtoVincular!=null){
            	grupoobrigatorio.setCoprodutos(coprodutos);
            	grupoobrigatorio.setProdutosorcamento(produtoVincular);
        		GrupoObrigatorioFacade grupoObrigatorioFacade = new GrupoObrigatorioFacade();
        		grupoobrigatorio = grupoObrigatorioFacade.salvar(grupoobrigatorio);
        	}
            RequestContext.getCurrentInstance().closeDialog(coprodutos);
            return "";
        }else{
            FacesMessage mensagem = new FacesMessage("Atencao! ", "Campos obrigatorios nao preenchidos.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }
    
    public String cancelarCoProduto(){
        RequestContext.getCurrentInstance().closeDialog(null);
        coprodutos = new Coprodutos();
        return "";
    }
    
}
