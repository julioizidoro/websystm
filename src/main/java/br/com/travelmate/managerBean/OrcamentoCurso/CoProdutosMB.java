package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.CoProdutosFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.facade.ValorCoProdutosFacade;
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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CoProdutosMB implements Serializable{
    
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Valorcoprodutos> listaCoProdutos;
    private Coprodutos coProdutos;
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

   
    public List<Valorcoprodutos> getListaCoProdutos() {
        return listaCoProdutos;
    }

    public void setListaCoProdutos(List<Valorcoprodutos> listaCoProdutos) {
        this.listaCoProdutos = listaCoProdutos;
    }

    public Coprodutos getCoProdutos() {
        return coProdutos;
    }

    public void setCoProdutos(Coprodutos coProdutos) {
        this.coProdutos = coProdutos;
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
    
    public void listarProdutos(){
        if (fornecedorcidade!=null){
            String dataSql = Formatacao.ConvercaoDataSql(new Date());
            String sql = "Select v from Valorcoprodutos v where v.coprodutos.fornecedorcidade.idfornecedorcidade=" + 
                    fornecedorcidade.getIdfornecedorcidade() + " and v.datainicial>='" + dataSql +
                    "' and v.datafinal<='" + dataSql + "' order by v.coprodutos.produtosorcamento.descricao"; 
            ValorCoProdutosFacade valorCoProdutosFacade = new ValorCoProdutosFacade();
            listaCoProdutos = valorCoProdutosFacade.listar(sql);
            if (listaCoProdutos==null){
                listaCoProdutos = new ArrayList<Valorcoprodutos>();
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
         RequestContext.getCurrentInstance().openDialog("cadProduto");
        return "";
    }
    
    
    
}
