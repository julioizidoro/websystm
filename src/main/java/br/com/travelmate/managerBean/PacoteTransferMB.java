/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PacoteTransferFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotetransfer;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class PacoteTransferMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Pacotetransfer pacotetransfer;
    private Cambio cambio;
    private Fornecedorcidade fornecedorcidade;
    private List<Paisproduto> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;

    public PacoteTransferMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Pacotetrecho pacotetrecho = (Pacotetrecho) session.getAttribute("pacoteTrecho");
        session.removeAttribute("pacoteTrecho");
        int idProduto = 0;
        if (pacotetrecho != null) {
            PaisProdutoFacade paisProdutoFacade = new PaisProdutoFacade();
            idProduto = pacotetrecho.getPacotes().getVendas().getProdutos().getIdprodutos();
            listaPais = paisProdutoFacade.listar(idProduto);
        }
        PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
        pacotetransfer = pacoteTransferFacade.consultar(pacotetrecho.getIdpacotetrecho());
        if (pacotetransfer == null) {
            pacotetransfer = new Pacotetransfer();
            pacotetransfer.setPacotetrecho(pacotetrecho);
            fornecedorcidade = new Fornecedorcidade();
            pais = new Pais();
            cidade = new Cidade();
        } else {
            cambio = pacotetransfer.getCambio();
            fornecedorcidade = pacotetransfer.getFornecedorcidade();
            pais = fornecedorcidade.getCidade().getPais();
            cidade = fornecedorcidade.getCidade();
            listarFornecedorCidade(idProduto);
        }
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Pacotetransfer getPacotetransfer() {
        return pacotetransfer;
    }

    public void setPacotetransfer(Pacotetransfer pacotetransfer) {
        this.pacotetransfer = pacotetransfer;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
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

    public Cidade getCidade() {
        return cidade;
    }

    
    public void setListaPais(List<Paisproduto> listaPais) {
        this.listaPais = listaPais;
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
    
    public void listarFornecedorCidade(int idProduto){
        if (usuarioLogadoMB!=null){
            idProduto = usuarioLogadoMB.getParametrosprodutos().getPacotes();
        }
        if ((idProduto>0) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(idProduto, cidade.getIdcidade());
        }
    }
    
    public String salvarTransfer(){
        PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
        pacotetransfer.setFornecedorcidade(fornecedorcidade);
        pacotetransfer.setCambio(cambio);
        pacotetransfer = pacoteTransferFacade.salvar(pacotetransfer);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacotetransfer.getPacotetrecho().getPacotes());
        RequestContext.getCurrentInstance().closeDialog(pacotetransfer);
        return "";
    }
    
    public void calcularValorGross(){
        float valorNet = pacotetransfer.getValornet();
        float comissao = pacotetransfer.getComissao();
        float valorGross = 0.0f;
        if ((valorNet>0) && (comissao>0)){
            comissao = comissao /100;
            comissao = comissao + 1;
            valorGross = valorNet * comissao;
        }
        pacotetransfer.setValorgross(valorGross);
        pacotetransfer.setValormoedanacional(pacotetransfer.getValorgross() * cambio.getValor());
    }
    
    public void calcularComissao(){
        float valorNet = pacotetransfer.getValornet();
        float comissao = pacotetransfer.getComissao();
        float valorGross = pacotetransfer.getValorgross();
        if ((valorNet>0) && (valorGross>0)){
            comissao = valorGross / valorNet;
            comissao = comissao - 1;
            comissao = comissao * 100;
        }
        pacotetransfer.setComissao(comissao);
        pacotetransfer.setValormoedanacional(pacotetransfer.getValorgross() * cambio.getValor());
    }
    
    public String cancelar(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacotetransfer.getPacotetrecho().getPacotes());
        if (pacotetransfer.getPacotetrecho().getPacotes().getOperacao().equalsIgnoreCase("Operadora")){
            RequestContext.getCurrentInstance().closeDialog("cadpacotesoperadora");
            return "";
        }else{
            RequestContext.getCurrentInstance().closeDialog("cadPacote");
            return "";
        }
    }
    
    public String excluir(){
        PacoteTransferFacade pacoteTransferFacade = new PacoteTransferFacade();
        pacoteTransferFacade.excluir(pacotetransfer.getIdpacotetransfer());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Excluído com Sucesso", ""));
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacotetransfer.getPacotetrecho().getPacotes());
        if (pacotetransfer.getPacotetrecho().getPacotes().getOperacao().equalsIgnoreCase("Operadora")){
            //return "cadpacotesoperadora";
            RequestContext.getCurrentInstance().closeDialog("cadpacotesoperadora");
            return "";
        }else{
            RequestContext.getCurrentInstance().closeDialog("cadPacote");
            return "";
        }
    }
    
    
}
