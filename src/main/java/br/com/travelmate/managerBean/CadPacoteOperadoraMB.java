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
        return null;
    }
    
    
    
    public String novoCarro(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
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
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacoteingresso";
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
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacotepasseio";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
     public String novoSeguro(Pacotetrecho pacotetrecho){
        if (pacotetrecho!=null){
            FacesContext fc = FacesContext.getCurrentInstance();  
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("pacoteTrecho", pacotetrecho);
            return "pacoteseguro";
        }else {
            FacesMessage mensagem = new FacesMessage("Atenção! ", "Trecho Não Localizado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String imagemAereo(Pacotetrecho pacotetrecho) {
//        if(pacoteaereo!=null){
//            return "../../resources/img/aereoverde.png";
//        }else{
            return "../../resources/img/aereovermelho.png";
    //    }
    }
    
    public String imagemCarro(Pacotetrecho pacotetrecho) {
         boolean verdade = true;
        if (pacotetrecho.getPacotecarroList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotecarroList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/carroverde.png";
        }else{
             return "../../resources/img/carrovermelho.png";
        }
    }
    
    public String imagemCruzeiro(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotecruzeiroList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotecruzeiroList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/cruzeiroverde.png";
        }else{
            return "../../resources/img/cruzeirovermelho.png";
        }
    }
    
    public String imagemHotel(Pacotetrecho pacotetrecho) {
         boolean verdade = true;
        if (pacotetrecho.getPacotehotelList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotehotelList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/hotelverde.png";
        } else {
            return "../../resources/img/hotelvermelho.png";
        }

    }
    
    public String imagemIngresso(Pacotetrecho pacotetrecho) {
         boolean verdade = true;
        if (pacotetrecho.getPacoteingressoList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacoteingressoList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/ingressoverde.png";
        }else{
            return "../../resources/img/ingressovermelho.png";
        }
    }
    
    public String imagemPasseio(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotepasseioList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotepasseioList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/passeioverdeb.png";
        }else{
             return "../../resources/img/passeiovermelhob.png";
         }
    }
    
    public String imagemTransfer(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotetransferList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotetransferList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/tranferverde.png";
        }else{
             return "../../resources/img/transfervermelho.png";
        }
    }
    
    public String imagemTrem(Pacotetrecho pacotetrecho) {
        boolean verdade = true;
        if (pacotetrecho.getPacotetremList()==null){
            verdade = false;
        }else if (pacotetrecho.getPacotetremList().size()==0){
            verdade=false;
        }
        if(verdade){
            return "../../resources/img/tremverde.png";
        }else{
              return "../../resources/img/tremvermelho.png";
         }
    }
    public String imagemSeguro(Pacotetrecho pacotetrecho) {
        
            return "../../resources/img/segurovermelho.png";
    }
}
