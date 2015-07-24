/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.FornecedorCidadeFacade;
import br.com.travelmate.facade.PacoteTrechoFacade;
import br.com.travelmate.facade.PacotesFacade;
import br.com.travelmate.facade.ProdutoFacade;
import br.com.travelmate.facade.VendasFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotecarro;
import br.com.travelmate.model.Pacotecruzeiro;
import br.com.travelmate.model.Pacotehotel;
import br.com.travelmate.model.Pacoteingresso;
import br.com.travelmate.model.Pacotepassagem;
import br.com.travelmate.model.Pacotepasseio;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Pacotetransfer;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pacotetrem;
import br.com.travelmate.model.Produtos;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.model.Vendas;
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

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class PacoteMB implements Serializable{
    
    private List<Pacotes> listaPacotes;
    private Pacotes pacotes;
    private String tipoPacote;
    private Pacotecarro pacotecarro;
    private Pacotecruzeiro pacotecruzeiro;
    private Pacotepassagem pacotepassagem;
    private Pacotehotel pacotehotel;
    private Pacoteingresso pacoteingresso;
    private Pacotepasseio pacotepasseio;
    private Pacotetransfer pacotetransfer;
    private List<Pacotetrecho> listaTrecho;
    private Pacotetrecho pacotetrecho;
    private Pacotetrem pacotetrem;
    private List<Unidadenegocio> listaUnidadeNegocio;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Cambio cambio;
   
    
    
    
   @PostConstruct
   public void init(){
       pacotes = new Pacotes();
       pacotetrecho = new Pacotetrecho();
       String sql = "Select p from Pacotes p where p.operacao='" + tipoPacote + "' order by p.vendas.dataVenda, p.unidadenegocio.nomeFantasia";
       listaPacotes = GerarListas.listarPacotes(sql);
       listaUnidadeNegocio = GerarListas.listarUnidade();
       listaTrecho = new ArrayList<Pacotetrecho>();
   }

    public List<Pacotes> getListaPacotes() {
        return listaPacotes;
    }

    public void setListaPacotes(List<Pacotes> listaPacotes) {
        this.listaPacotes = listaPacotes;
    }

    public Pacotes getPacotes() {
        return pacotes;
    }

    public void setPacotes(Pacotes pacotes) {
        this.pacotes = pacotes;
    }

    public String getTipoPacote() {
        return tipoPacote;
    }

    public void setTipoPacote(String tipoPacote) {
        this.tipoPacote = tipoPacote;
    }

    public Pacotecarro getPacotecarro() {
        return pacotecarro;
    }

    public void setPacotecarro(Pacotecarro pacotecarro) {
        this.pacotecarro = pacotecarro;
    }

    public Pacotecruzeiro getPacotecruzeiro() {
        return pacotecruzeiro;
    }

    public void setPacotecruzeiro(Pacotecruzeiro pacotecruzeiro) {
        this.pacotecruzeiro = pacotecruzeiro;
    }

    public Pacotepassagem getPacotepassagem() {
        return pacotepassagem;
    }

    public void setPacotepassagem(Pacotepassagem pacotepassagem) {
        this.pacotepassagem = pacotepassagem;
    }

    public Pacotehotel getPacotehotel() {
        return pacotehotel;
    }

    public void setPacotehotel(Pacotehotel pacotehotel) {
        this.pacotehotel = pacotehotel;
    }

    public Pacoteingresso getPacoteingresso() {
        return pacoteingresso;
    }

    public void setPacoteingresso(Pacoteingresso pacoteingresso) {
        this.pacoteingresso = pacoteingresso;
    }

    public Pacotepasseio getPacotepasseio() {
        return pacotepasseio;
    }

    public void setPacotepasseio(Pacotepasseio pacotepasseio) {
        this.pacotepasseio = pacotepasseio;
    }

    public Pacotetransfer getPacotetransfer() {
        return pacotetransfer;
    }

    public void setPacotetransfer(Pacotetransfer pacotetransfer) {
        this.pacotetransfer = pacotetransfer;
    }

    public List<Pacotetrecho> getListaTrecho() {
        return listaTrecho;
    }

    public void setListaTrecho(List<Pacotetrecho> listaTrecho) {
        this.listaTrecho = listaTrecho;
    }

    public Pacotetrecho getPacotetrecho() {
        return pacotetrecho;
    }

    public void setPacotetrecho(Pacotetrecho pacotetrecho) {
        this.pacotetrecho = pacotetrecho;
    }

    public Pacotetrem getPacotetrem() {
        return pacotetrem;
    }

    public void setPacotetrem(Pacotetrem pacotetrem) {
        this.pacotetrem = pacotetrem;
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    
    
    public String novoPacotes(){
        pacotes = new Pacotes();
        pacotetrecho = new Pacotetrecho();
        listaTrecho = new ArrayList<Pacotetrecho>();
        return "cadPacoteOperadora";
    }
    
    public String iniciarPacote() {
        if (pacotes.getVendas() == null) {
            FornecedorCidadeFacade fornecedorCidadeFacade = new FornecedorCidadeFacade();
            Fornecedorcidade fornecedorcidade = fornecedorCidadeFacade.getFornecedorCidade(usuarioLogadoMB.getParametrosprodutos().getFornecedorpacote());
            ProdutoFacade produtoFacade = new ProdutoFacade();
            Produtos produto = produtoFacade.consultar(usuarioLogadoMB.getParametrosprodutos().getPacotes());
           
            Vendas venda = new Vendas();
            venda.setCliente(null);
            venda.setDataVenda(new Date());
            venda.setFornecedor(fornecedorcidade.getFornecedor());
            venda.setFornecedorcidade(fornecedorcidade);
            venda.setProdutos(produto);
            venda.setSituacao("Finalizada");
            venda.setUnidadenegocio(usuarioLogadoMB.getUsuario().getUnidadenegocio());
            venda.setUsuario(usuarioLogadoMB.getUsuario());
            venda.setValor(0.0f);
            venda.setVendasMatriz("S");
            venda.setVendaimportada(0);
            VendasFacade vendasFacade = new VendasFacade();
            venda = vendasFacade.salvar(venda);
            pacotes.setVendas(venda);
        }
        pacotes.setOperacao("Operadora");
        pacotes.setUsuario(usuarioLogadoMB.getUsuario());
        pacotes.setCambio(cambio);
        PacotesFacade pacotesFacade = new PacotesFacade();
        pacotes = pacotesFacade.salvar(pacotes);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacotes);
        return "";
    }
    
    
    public String adicionarTrecho(){
        pacotetrecho.setPacotes(pacotes);
        PacoteTrechoFacade pacoteTrechoFacade = new PacoteTrechoFacade();
        pacoteTrechoFacade.salvar(pacotetrecho);
        String sql = "Select p from Pacotetrecho p where p.pacotes.idpacotees=" + pacotetrecho.getPacotes().getIdpacotes();
        listaTrecho = GerarListas.listarPacoteTrecho(sql);
        pacotetrecho = new Pacotetrecho();
        return "";
    }
    
    public String finalizar(){
        System.out.println("teste");
        return null;
    }
    
}

