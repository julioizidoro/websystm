/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ClienteFacade;
import br.com.travelmate.facade.FornecedorCidadeFacade;
import br.com.travelmate.facade.PacoteTrechoFacade;
import br.com.travelmate.facade.PacotesFacade;
import br.com.travelmate.facade.ProdutoFacade;
import br.com.travelmate.facade.VendasFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cliente;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Produtos;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class CadPacoteOperadoraMB  implements Serializable{
    
     private Pacotes pacotes;
     private List<Unidadenegocio> listaUnidadeNegocio;
     private List<Pacotetrecho> listaTrecho;
     private Pacotetrecho pacotetrecho;
     @Inject
     private UsuarioLogadoMB usuarioLogadoMB;
     private Cambio cambio;
    
    
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        pacotes = (Pacotes) session.getAttribute("pacote");
        session.removeAttribute("pacote");
        if (this.pacotes == null) {
            pacotetrecho = new Pacotetrecho();
            listaTrecho = new ArrayList<Pacotetrecho>();
        } else {
            cambio = pacotes.getCambio();
            listaTrecho = pacotes.getPacotetrechoList();
        }
        pacotetrecho = new Pacotetrecho();
        listaUnidadeNegocio = GerarListas.listarUnidade();
    }

    public Pacotes getPacotes() {
        return pacotes;
    }

    public void setPacotes(Pacotes pacotes) {
        this.pacotes = pacotes;
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
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
    
    public String iniciarPacote() {
        if (pacotes.getVendas() == null) {
            FornecedorCidadeFacade fornecedorCidadeFacade = new FornecedorCidadeFacade();
            Fornecedorcidade fornecedorcidade = fornecedorCidadeFacade.getFornecedorCidade(usuarioLogadoMB.getParametrosprodutos().getFornecedorpacote());
            ProdutoFacade produtoFacade = new ProdutoFacade();
            Produtos produto = produtoFacade.consultar(usuarioLogadoMB.getParametrosprodutos().getPacotes());
            ClienteFacade clienteFacade = new ClienteFacade();
            Cliente cliente = clienteFacade.consultar(usuarioLogadoMB.getParametrosprodutos().getClientepacote());
            Vendas venda = new Vendas();
            venda.setCliente(cliente);
            venda.setDataVenda(new Date());
            venda.setFornecedor(fornecedorcidade.getFornecedor());
            venda.setFornecedorcidade(fornecedorcidade);
            venda.setProdutos(produto);
            venda.setSituacao("Processo");
            venda.setUnidadenegocio(usuarioLogadoMB.getUsuario().getUnidadenegocio());
            venda.setUsuario(usuarioLogadoMB.getUsuario());
            venda.setValor(0.0f);
            venda.setVendasMatriz("S");
            venda.setVendaimportada(0);
            venda.setObsCancelar("");
            venda.setUsuariocancelamento(0);
            venda.setObstm("");
            VendasFacade vendasFacade = new VendasFacade();
            venda = vendasFacade.salvar(venda);
            pacotes.setVendas(venda);
        }
        pacotes.setOperacao("Operadora");
        pacotes.setUsuario(usuarioLogadoMB.getUsuario());
        pacotes.setCambio(cambio);
        pacotes.setCliente(pacotes.getVendas().getCliente());
        PacotesFacade pacotesFacade = new PacotesFacade();
        pacotes = pacotesFacade.salvar(pacotes);
        return "";
    }
    
    
    public String adicionarTrecho(){
        pacotetrecho.setPacotes(pacotes);
        PacoteTrechoFacade pacoteTrechoFacade = new PacoteTrechoFacade();
        pacoteTrechoFacade.salvar(pacotetrecho);
        String sql = "Select p from Pacotetrecho p where p.pacotes.idpacotes=" + pacotes.getIdpacotes();
        listaTrecho = GerarListas.listarPacoteTrecho(sql);
        pacotetrecho = new Pacotetrecho();
        return "";
    }
    
    public String finalizar(){
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.removeAttribute("tipoOepracaoPacote");
        session.removeAttribute("pacoteTrecho");
        System.out.println("teste");
        return null;
    }
    
    
    
    public String novoCarro(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("tipoOepracaoPacote", "carro");
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacotecarro";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoCruzeiro(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("tipoOepracaoPacote", "cruzeiro");
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacotecruzeiro";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoHotel(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("tipoOepracaoPacote", "hotel");
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacotehotel";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoTrem(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("tipoOepracaoPacote", "trem");
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacotetrem";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoTransfer(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("tipoOepracaoPacote", "transfer");
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacotetransfer";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoIngresso(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("tipoOepracaoPacote", "ingresso");
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacoteIngresso";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoPasseio(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("tipoOepracaoPacote", "passeio");
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacotePasseio";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
}
