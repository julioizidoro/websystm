package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.ProdutoFacade;
import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.facade.VendasFacade;
import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Produtos;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class VendasFinanceiroMB  implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Vendas> listaVendas;
    private String id;
    private String nomeCliente;
    private Date dataInical;
    private Date dataFinal;
    private Unidadenegocio unidadenegocio;
    private Produtos produto;
    private List<Unidadenegocio> listaUnidadeNegocio;
    private List<Produtos> listaProdutos;
    
    
    @PostConstruct
    public void inti(){
        gerarListaUnidadeNegocio();
        gerarListaProdutos();
        gerarListaVendas(null);
    }

    public List<Vendas> getListaVendas() {
        return listaVendas;
    }

    public void setListaVendas(List<Vendas> listaVendas) {
        this.listaVendas = listaVendas;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataInical() {
        return dataInical;
    }

    public void setDataInical(Date dataInical) {
        this.dataInical = dataInical;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public List<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produtos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
    
    public String informacoesAdicionais(Vendas venda){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("venda", venda);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",570);
        RequestContext.getCurrentInstance().openDialog("informacoesAdicionais", options, null);
        return "";
    }
    
    public String produtoVendas(Vendas venda){
        if ((venda.getOrcamentoList()!=null) && (venda.getOrcamentoList().size()>0)){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("venda", venda);
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth",570);
            RequestContext.getCurrentInstance().openDialog("produtoVendas", options, null);
        }
        FacesMessage msg = new FacesMessage("Venda não Possui produtos! ", " ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public String visualizarContasReceber(Vendas venda){
        if ((venda.getOrcamentoList() != null) && (venda.getCobrancaList().size() > 0)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("venda", venda);
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 620);
            RequestContext.getCurrentInstance().openDialog("visualizarContasReceber", options, null);
        }
        FacesMessage msg = new FacesMessage("Venda não Possui Contas a Receber! ", " ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public String editarComissao(Vendas venda){
        if ((venda.getVendascomissaoList() != null) && (venda.getVendascomissaoList().size() > 0)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("venda", venda);
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth",850);
            RequestContext.getCurrentInstance().openDialog("editarComissoes", options, null);
        }
        FacesMessage msg = new FacesMessage("Venda não Possui Comissão! ", " ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public String editarInformacoes(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",800);
        RequestContext.getCurrentInstance().openDialog("editarInformacoes", options, null);
        return "";
    }
    
    public String fecharEditarInformacoes(){
        RequestContext.getCurrentInstance().openDialog("editarInformacoes");
        return "";
    }
    
    public void gerarListaVendas(String sql){
        VendasFacade vendasFacade = new VendasFacade();
        if (sql==null){
            String sData = Formatacao.SubtarirDatas(new Date(), 90, "yyyy-MM-dd");
            sql = "Select v From Vendas v where v.dataVenda>='" + sData + "' order by v.dataVenda, v.idvendas";
        }
        listaVendas = vendasFacade.lista(sql);
        if (listaVendas==null){
            listaVendas = new ArrayList<Vendas>();
        }
    }
    
    public String filtarVendas(){
        String sql = "Select v From Vendas v where ";
        String usouAnd = "";
        boolean executarFiltro = false;
        if ((id!=null) && (id.length()>0)){
            sql = sql + " v.idvendas=" + id;
            usouAnd = " and";
            executarFiltro = true;
        }
        if ((nomeCliente!=null) && (nomeCliente.length()>0)){
            sql = sql + usouAnd + " v.cliente.nome like '%" + nomeCliente + "%'"; 
            usouAnd = " and";
            executarFiltro = true;
        }
        if ((dataInical!=null) && (dataFinal!=null)){
            sql = sql + usouAnd +  " v.dataVenda>='" + Formatacao.ConvercaoDataSql(dataInical) +
                    "' and v.dataVenda<='" + Formatacao.ConvercaoDataSql(dataFinal) + "'";
            usouAnd = " and";
            executarFiltro = true;
        }
        if (unidadenegocio!=null){
            sql = sql + usouAnd + " v.unidadenegocio.idunidadeNegocio=" + unidadenegocio.getIdunidadeNegocio();
            usouAnd = " and";
            executarFiltro = true;
        }
        if (produto!=null){
            sql = sql + usouAnd + " v.produtos.idprodutos=" + produto.getIdprodutos();
            executarFiltro = true;
        }
        sql = sql + " order by v.dataVenda, v.idvendas";
        if (executarFiltro){
            gerarListaVendas(sql);
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Nenhum filtro selecionado"));
        }
        return "";
    }
    
    public String limparFiltroVendas(){
        gerarListaVendas(null);
        unidadenegocio = null;
        produto = null;
        id="";
        nomeCliente="";
        dataFinal=null;
        dataInical=null;
        return "";
    }
    
    private void gerarListaUnidadeNegocio(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        listaUnidadeNegocio = unidadeNegocioFacade.listar();
        if (listaUnidadeNegocio==null){
            listaUnidadeNegocio = new ArrayList<Unidadenegocio>();
        }
    }
    
    private void gerarListaProdutos(){
        ProdutoFacade produtoFacade = new ProdutoFacade();
        listaProdutos = produtoFacade.listarProdutos("");
        if (listaProdutos==null){
            listaProdutos = new ArrayList<Produtos>();
        }
    }
}
