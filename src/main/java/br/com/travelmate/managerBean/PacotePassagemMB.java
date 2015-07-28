/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PacotesPassagemFacade;
import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotepassagem;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pais;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.List;
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
public class PacotePassagemMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Pacotepassagem pacotepassagem;
    private Cambio cambio;
    private Fornecedorcidade fornecedorcidade;
    private List<Pais> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;

    public PacotePassagemMB() {
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
        PacotesPassagemFacade pacotesPassagemFacade = new PacotesPassagemFacade();
        pacotepassagem = pacotesPassagemFacade.consultar(pacotetrecho.getIdpacotetrecho());
        if (pacotepassagem == null) {
            pacotepassagem = new Pacotepassagem();
            pacotepassagem.setPacotetrecho(pacotetrecho);
            fornecedorcidade = new Fornecedorcidade();
            pais = new Pais();
            cidade = new Cidade();
        } else {
            cambio = pacotepassagem.getCambio();
            fornecedorcidade = pacotepassagem.getFornecedorcidade();
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

    public Pacotepassagem getPacotepassagem() {
        return pacotepassagem;
    }

    public void setPacotepassagem(Pacotepassagem pacotepassagem) {
        this.pacotepassagem = pacotepassagem;
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
}
