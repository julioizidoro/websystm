package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.CoProdutosFacade;
import br.com.travelmate.facade.FornecedorCidadeIdiomaFacade;
import br.com.travelmate.facade.IdiomaFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Fornecedorcidadeidioma;
import br.com.travelmate.model.Idioma;
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
    private List<Paisproduto> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;
    private Idioma idioma;
    private Fornecedorcidadeidioma fornecedorCidadeIdioma;
    private List<Fornecedorcidadeidioma> listaFornecedorIdioma;
    private List<Idioma> listaIdiomas;

    @PostConstruct
    public void init(){
        int idProduto = 0;
        getUsuarioLogadoMB();
        listarIdiomas();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        coprodutos = (Coprodutos) session.getAttribute("coprodutos");
        session.removeAttribute("coprodutos");
        fornecedorCidadeIdioma =  (Fornecedorcidadeidioma) session.getAttribute("fornecedorcidadeidioma");
        session.removeAttribute("coprodutos");
        PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
        idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
        listaPais = paisProdutoFacade.listar(idProduto);
        if(coprodutos==null){
            fornecedorCidadeIdioma = new Fornecedorcidadeidioma();
            pais = new Pais();
            cidade = new Cidade();   
        }else{
            //fornecedorCidadeIdioma = coprodutos.getFornecedorcidade().get
            pais = coprodutos.getFornecedorcidade().getCidade().getPais();
            cidade = coprodutos.getFornecedorcidade().getCidade();
           // listarFornecedorCidade("0");
            listarCoProdutos();
            listarForCidadeIdioma();
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

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Fornecedorcidadeidioma getFornecedorCidadeIdioma() {
        return fornecedorCidadeIdioma;
    }

    public void setFornecedorCidadeIdioma(Fornecedorcidadeidioma fornecedorCidadeIdioma) {
        this.fornecedorCidadeIdioma = fornecedorCidadeIdioma;
    }
    
    

    public List<Fornecedorcidadeidioma> getListaFornecedorIdioma() {
        return listaFornecedorIdioma;
    }

    public void setListaFornecedorIdioma(List<Fornecedorcidadeidioma> listaFornecedorIdioma) {
        this.listaFornecedorIdioma = listaFornecedorIdioma;
    }

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    public void setListaIdiomas(List<Idioma> listaIdiomas) {
        this.listaIdiomas = listaIdiomas;
    }
    
    
    
    
   /*/ public void listarFornecedorCidade(String id){
        int idProduto = Integer.parseInt(id);
        if (usuarioLogadoMB!=null){
            idProduto = usuarioLogadoMB.getParametrosprodutos().getCursos();
        }
        if ((idProduto>0) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(idProduto, cidade.getIdcidade());
        }
    }/*/
    
    public void listarCoProdutos(){
        if (fornecedorCidadeIdioma!=null){
            String sql = "Select c from Coprodutos c where c.fornecedorcidade.idfornecedorcidade=" + 
                    fornecedorCidadeIdioma.getFornecedorcidade().getIdfornecedorcidade() + " order by c.produtosorcamento.descricao"; 
            CoProdutosFacade coProdutosFacade = new CoProdutosFacade();
            listaCoProdutos = coProdutosFacade.listar(sql);
            if (listaCoProdutos==null){
                listaCoProdutos = new ArrayList<Coprodutos>();
            }
        }
    }
    
    public void listarIdiomas(){
        IdiomaFacade  idiomaFacade = new IdiomaFacade();
        listaIdiomas = idiomaFacade.listar("Select i from Idioma i order by i.descricao");
        if (listaIdiomas==null){
            listaIdiomas = new ArrayList<Idioma>();
        }
    }
    
    public void listarForCidadeIdioma(){
        
        if(cidade !=null && idioma != null){
            String sql = "select f from Fornecedorcidadeidioma f where f.fornecedorcidade.cidade.idcidade=" + 
                    cidade.getIdcidade() + " and f.idioma.ididioma =" 
                    + idioma.getIdidioma() + " order by f.fornecedorcidade.fornecedor.nome"; 
            FornecedorCidadeIdiomaFacade fornecedorCidadeIdiomaFacade = new FornecedorCidadeIdiomaFacade();
            listaFornecedorIdioma = fornecedorCidadeIdiomaFacade.listar(sql);
            if (listaFornecedorIdioma == null){
                listaFornecedorIdioma = new ArrayList<Fornecedorcidadeidioma>();
            }
           listaCoProdutos = new ArrayList<Coprodutos>();
        }
    }
    
   
    
    public String cadProduto() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("fornecedorcidadeidioma", fornecedorCidadeIdioma);
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
