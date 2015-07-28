/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PacoteIngressoFacade;
import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacoteingresso;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pais;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class PacoteIngressoMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Pacoteingresso pacoteingresso;
     private List<Pacoteingresso> listaPacoteIngresso;
    private Cambio cambio;
    private Fornecedorcidade fornecedorcidade;
    private List<Pais> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;

    public PacoteIngressoMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        PaisFacade paisFacade = new PaisFacade();
        listaPais = paisFacade.listar("");
        Pacotetrecho pacotetrecho = (Pacotetrecho) session.getAttribute("pacoteTrecho");
        session.removeAttribute("pacoteTrecho");
        int idProduto = 0;
        if (pacotetrecho != null) {
            idProduto = pacotetrecho.getPacotes().getVendas().getProdutos().getIdprodutos();
        }
        listaPacoteIngresso = pacotetrecho.getPacoteingressoList();
        pacoteingresso = new Pacoteingresso();
        pacoteingresso.setPacotetrecho(pacotetrecho);
        if (listaPacoteIngresso == null) {
            listaPacoteIngresso = new ArrayList<Pacoteingresso>();
        } else {
            cambio = pacoteingresso.getCambio();
            fornecedorcidade = pacoteingresso.getFornecedorcidade();
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

    public Pacoteingresso getPacoteingresso() {
        return pacoteingresso;
    }

    public void setPacoteingresso(Pacoteingresso pacoteingresso) {
        this.pacoteingresso = pacoteingresso;
    }

    public List<Pacoteingresso> getListaPacoteIngresso() {
        return listaPacoteIngresso;
    }

    public void setListaPacoteIngresso(List<Pacoteingresso> listaPacoteIngresso) {
        this.listaPacoteIngresso = listaPacoteIngresso;
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

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
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
    
    public void listarFornecedorCidade(int idProduto){
        if (usuarioLogadoMB!=null){
            idProduto = usuarioLogadoMB.getParametrosprodutos().getPacotes();
        }
        if ((idProduto>0) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(idProduto, cidade.getIdcidade());
        }
    }
    
    public String salvarIngresso(){
        PacoteIngressoFacade pacoteIngressoFacade = new PacoteIngressoFacade();
        pacoteingresso.setFornecedorcidade(fornecedorcidade);
        pacoteingresso.setCambio(cambio);
        pacoteingresso = pacoteIngressoFacade.salvar(pacoteingresso);
        listaPacoteIngresso.add(pacoteingresso);
        Pacotetrecho pacotetrecho = pacoteingresso.getPacotetrecho();
        pacoteingresso = new Pacoteingresso();
        pacoteingresso.setPacotetrecho(pacotetrecho);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        return "";
    }
    
    
    
}
