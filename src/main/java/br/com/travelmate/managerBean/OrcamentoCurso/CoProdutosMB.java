package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.CoProdutosFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.model.Valorcoprodutos;
import br.com.travelmate.util.Formatacao;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class CoProdutosMB implements Serializable{
    
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Coprodutos coprodutos;
    private Valorcoprodutos valorcoproduto;
    private List<Coprodutos> listaCoProdutos;
     private Fornecedorcidade fornecedorcidade;
    private List<Paisproduto> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;

    @PostConstruct
    public void init(){
        int idProduto = 0;
        getUsuarioLogadoMB();
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
        listaPais = paisProdutoFacade.listar(idProduto);
        fornecedorcidade = new Fornecedorcidade();
        pais = new Pais();
        cidade = new Cidade();   
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

    public Valorcoprodutos getValorcoproduto() {
        return valorcoproduto;
    }

    public void setValorcoproduto(Valorcoprodutos valorcoproduto) {
        this.valorcoproduto = valorcoproduto;
    }
    
    
    

    

    public List<Coprodutos> getListaCoProdutos() {
        return listaCoProdutos;
    }

    public void setListaCoProdutos(List<Coprodutos> listaCoProdutos) {
        this.listaCoProdutos = listaCoProdutos;
    }

    

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public List<Paisproduto> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Paisproduto> listaPais) {
        this.listaPais = listaPais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    
    public void listarFornecedorCidade(String id){
        int idProduto = Integer.parseInt(id);
        if (usuarioLogadoMB!=null){
            idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
        }
        if ((idProduto>0) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(idProduto, cidade.getIdcidade());
        }
    }
    
    public void listarCoProdutos(){
        if (fornecedorcidade!=null){
            String sql = "Select c from Coprodutos c where c.fornecedorcidade.idfornecedorcidade=" + 
                    fornecedorcidade.getIdfornecedorcidade() + " order by c.produtosorcamento.descricao"; 
            CoProdutosFacade coProdutosFacade = new CoProdutosFacade();
            listaCoProdutos = coProdutosFacade.listar(sql);
            if (listaCoProdutos==null){
                listaCoProdutos = new ArrayList<Coprodutos>();
            }
        }
    }
    
    public List<Coprodutos> gerarListaCoObrigatorio(String sql){
        CoProdutosFacade coObrigatorioFacade = new CoProdutosFacade();
        List<Coprodutos> listaObrigatorio = coObrigatorioFacade.listar(sql);
        if (listaObrigatorio==null){
           listaObrigatorio = new ArrayList<Coprodutos>();
        }
        return listaObrigatorio;
    }
    
    public String cadProduto(){
         RequestContext.getCurrentInstance().openDialog("cadProdutos");
        return "";
    }
    
    public String cadValoresProdutos(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("CoProdutos", coprodutos);
        return "";
    }
    
    public String consValorProduto(){
        return "consValorProdutos";
    }
    
     public String cadValorProduto(){
         RequestContext.getCurrentInstance().openDialog("cadValorProdutos");
        return "";
    }
    
}
