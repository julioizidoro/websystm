/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.PacoteCruzeiroFacade;
import br.com.travelmate.facade.PaisFacade;
import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotecruzeiro;
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

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class PacoteCruzeiroMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Pacotecruzeiro pacotecruzeiro;
    private Cambio cambio;
    private Fornecedorcidade fornecedorcidade;
    private List<Paisproduto> listaPais;
    private Cidade cidade;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Pais pais;

    public PacoteCruzeiroMB() {
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
        PacoteCruzeiroFacade pacoteCruzeiroFacade = new PacoteCruzeiroFacade();
        pacotecruzeiro = pacoteCruzeiroFacade.consultar(pacotetrecho.getIdpacotetrecho());
        if (pacotecruzeiro == null) {
            pacotecruzeiro = new Pacotecruzeiro();
            pacotecruzeiro.setPacotetrecho(pacotetrecho);
            fornecedorcidade = new Fornecedorcidade();
            pais = new Pais();
            cidade = new Cidade();
        } else {
            cambio = pacotecruzeiro.getCambio();
            fornecedorcidade = pacotecruzeiro.getFornecedorcidade();
            pais = fornecedorcidade.getCidade().getPais();
            cidade = fornecedorcidade.getCidade();
            listarFornecedorCidade(String.valueOf(idProduto));
        }
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Pacotecruzeiro getPacotecruzeiro() {
        return pacotecruzeiro;
    }

    public void setPacotecruzeiro(Pacotecruzeiro pacotecruzeiro) {
        this.pacotecruzeiro = pacotecruzeiro;
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
    
    public void listarFornecedorCidade(String id){
        int idProduto = Integer.parseInt(id);
        if (usuarioLogadoMB!=null){
            idProduto = usuarioLogadoMB.getParametrosprodutos().getPacotes();
        }
        if ((idProduto>0) && (cidade!=null)){
            listaFornecedorCidade = GerarListas.listarFornecedorCidade(idProduto, cidade.getIdcidade());
        }
    }
    
    public String salvarCruzeiro(){
        PacoteCruzeiroFacade pacoteCruzeiroFacade = new PacoteCruzeiroFacade();
        pacotecruzeiro.setFornecedorcidade(fornecedorcidade);
        pacotecruzeiro.setCambio(cambio);
        pacotecruzeiro = pacoteCruzeiroFacade.salvar(pacotecruzeiro);
        fornecedorcidade = new Fornecedorcidade();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacotecruzeiro.getPacotetrecho().getPacotes());
        if (pacotecruzeiro.getPacotetrecho().getPacotes().getOperacao().equalsIgnoreCase("Operadora")){
            return "cadpacotesoperadora";
        }else return "cadPacote";
    }
    
    public void calcularValorMoedaNcional(){
        pacotecruzeiro.setValormoedanacional(pacotecruzeiro.getValorgross() * cambio.getValor());
    }
    
    public void calcularValorGross(){
        float valorNet = pacotecruzeiro.getValornet();
        float comissao = pacotecruzeiro.getComissao();
        float valorGross = 0.0f;
        if ((valorNet>0) && (comissao>0)){
            comissao = comissao /100;
            comissao = comissao + 1;
            valorGross = valorNet * comissao;
        }
        pacotecruzeiro.setValorgross(valorGross);
        pacotecruzeiro.setValormoedanacional(pacotecruzeiro.getValorgross() * cambio.getValor());
    }
    
    public void calcularComissao(){
        float valorNet = pacotecruzeiro.getValornet();
        float comissao = pacotecruzeiro.getComissao();
        float valorGross = pacotecruzeiro.getValorgross();
        if ((valorNet>0) && (valorGross>0)){
            comissao = valorGross / valorNet;
            comissao = comissao - 1;
            comissao = comissao * 100;
        }
        pacotecruzeiro.setComissao(comissao);
        pacotecruzeiro.setValormoedanacional(pacotecruzeiro.getValorgross() * cambio.getValor());
    }
}
