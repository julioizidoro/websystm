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
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        coprodutos = (Coprodutos) session.getAttribute("coprodutos");
        session.removeAttribute("coprodutos");
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
        listaPais = paisProdutoFacade.listar(idProduto);
        if(coprodutos==null){
            fornecedorcidade = new Fornecedorcidade();
            pais = new Pais();
            cidade = new Cidade();   
        }else{
            fornecedorcidade = coprodutos.getFornecedorcidade();
            pais = coprodutos.getFornecedorcidade().getCidade().getPais();
            cidade = coprodutos.getFornecedorcidade().getCidade();
            listarFornecedorCidade("0");
            listarCoProdutos();
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
    
    public String cadProduto() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("fornecedorCidade", fornecedorcidade);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadProdutos", options, null);
        return "";
    }
    
    
    
    public String consValorProduto(Coprodutos coprodutos){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("coprodutos", coprodutos);
        return "consValorCoProdutos";
    }
    
    
    public void retornoDialogoNovo(SelectEvent event){
        Coprodutos coproduto = (Coprodutos) event.getObject();
        listaCoProdutos.add(coproduto);
    }
}
