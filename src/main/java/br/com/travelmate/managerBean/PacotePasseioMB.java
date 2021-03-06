/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.travelmate.facade.PacoteIngressoFacade;
import br.com.travelmate.facade.PacotePasseioFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotepasseio;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Paisproduto;
import br.com.travelmate.util.GerarListas;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class PacotePasseioMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Pacotepasseio pacotepasseio;
    private Cambio cambio;
    private Fornecedorcidade fornecedorcidade;
    private List<Paisproduto> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;
    private List<Pacotepasseio> listaPacotePasseio;

    public PacotePasseioMB() {
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
        listaPacotePasseio = pacotetrecho.getPacotepasseioList();
        pacotepasseio = new Pacotepasseio();
        pacotepasseio.setPacotetrecho(pacotetrecho);
        if (listaPacotePasseio == null) {
            listaPacotePasseio = new ArrayList<Pacotepasseio>();
        } 
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Pacotepasseio getPacotepasseio() {
        return pacotepasseio;
    }

    public void setPacotepasseio(Pacotepasseio pacotepasseio) {
        this.pacotepasseio = pacotepasseio;
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

    public List<Pacotepasseio> getListaPacotePasseio() {
        return listaPacotePasseio;
    }

    public void setListaPacotePasseio(List<Pacotepasseio> listaPacotePasseio) {
        this.listaPacotePasseio = listaPacotePasseio;
    }
    
    public void listarFornecedorCidade(int idProduto){
        if (usuarioLogadoMB!=null){
            idProduto = usuarioLogadoMB.getParametrosprodutos().getPacotes();
        }
        if ((idProduto>0) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(idProduto, cidade.getIdcidade());
        }
    }
    
    public String salvarPasseio(){
        PacotePasseioFacade pacotePasseioFacade = new PacotePasseioFacade();
        pacotepasseio.setFornecedorcidade(fornecedorcidade);
        pacotepasseio.setCambio(cambio);
        pacotepasseio = pacotePasseioFacade.salvar(pacotepasseio);
        listaPacotePasseio.add(pacotepasseio);
        Pacotetrecho pacotetrecho = pacotepasseio.getPacotetrecho();
        pacotepasseio = new Pacotepasseio();
        pacotepasseio.setPacotetrecho(pacotetrecho);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacoteTrecho", pacotetrecho);
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "pacotepasseio";
    }
    
    public void calcularValorGross(){
        float valorNet = pacotepasseio.getValornet();
        float comissao = pacotepasseio.getComissao();
        float valorGross = 0.0f;
        if ((valorNet>0) && (comissao>0)){
            comissao = comissao /100;
            comissao = comissao + 1;
            valorGross = valorNet * comissao;
        }
        pacotepasseio.setValorgross(valorGross);
        pacotepasseio.setValormoedanacional(pacotepasseio.getValorgross() * cambio.getValor());
    }
    
    public void calcularComissao(){
        float valorNet = pacotepasseio.getValornet();
        float comissao = pacotepasseio.getComissao();
        float valorGross = pacotepasseio.getValorgross();
        if ((valorNet>0) && (valorGross>0)){
            comissao = valorGross / valorNet;
            comissao = comissao - 1;
            comissao = comissao * 100;
        }
        pacotepasseio.setComissao(comissao);
        pacotepasseio.setValormoedanacional(pacotepasseio.getValorgross() * cambio.getValor());
    }
    
    public String cancelar(){
        Pacotetrecho pacotetrecho = pacotepasseio.getPacotetrecho();
        pacotepasseio = new Pacotepasseio();
        pacotepasseio.setPacotetrecho(pacotetrecho);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacoteTrecho", pacotetrecho);
        context.addMessage(null, new FacesMessage("Cancelado", ""));
        return "pacotepasseio";
    }
    
    public String finalizar(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacotepasseio.getPacotetrecho().getPacotes());
        if (pacotepasseio.getPacotetrecho().getPacotes().getOperacao().equalsIgnoreCase("Operadora")){
            RequestContext.getCurrentInstance().closeDialog("cadpacotesoperadora");
            return "";
        }else{
           RequestContext.getCurrentInstance().closeDialog("cadPacote");
           return "";
        }
    }
    
    public String excluirItem(String linha){
        int n = Integer.parseInt(linha);
        if (n>=0){
            Pacotepasseio pi = listaPacotePasseio.get(n);
            PacoteIngressoFacade pacoteIngressoFacade = new PacoteIngressoFacade();
            pacoteIngressoFacade.excluir(pi.getIdpacotepasseio());
            listaPacotePasseio.remove(n);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Ingresso Excluído", ""));
        }
        return "";
    }
    
    
    
    
}
