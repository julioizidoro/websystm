/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.ValorCoProdutosFacade;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Valorcoprodutos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ValorCoProdutosMB implements Serializable{
    
    private Coprodutos coprodutos;
    private List<Valorcoprodutos> listaValores;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        coprodutos = (Coprodutos) session.getAttribute("coprodutos");
        if (coprodutos!=null){
            gerarListaValores();
        }else{
            listaValores = new ArrayList<Valorcoprodutos>();
        }
    }

    public Coprodutos getCoprodutos() {
        return coprodutos;
    }

    public void setCoprodutos(Coprodutos coprodutos) {
        this.coprodutos = coprodutos;
    }
    
    public List<Valorcoprodutos> getListaValores() {
        return listaValores;
    }

    public void setListaValores(List<Valorcoprodutos> listaValores) {
        this.listaValores = listaValores;
    }
    
    public void gerarListaValores(){
        String sql = "Select v from Valorcoprodutos v where v.coprodutos.idcoprodutos=" + coprodutos.getIdcoprodutos();
        ValorCoProdutosFacade valorCoProdutosFacade = new ValorCoProdutosFacade();
        listaValores = valorCoProdutosFacade.listar(sql);
        if (listaValores==null){
            listaValores = new ArrayList<Valorcoprodutos>();
        }
    }
    
    public String cadValoresProdutos(){
        RequestContext.getCurrentInstance().openDialog("cadValorCoProdutos");
        return "";
    }
    
    public String voltar(){
        return "consProdutos";
    }
    
    public String editar(Valorcoprodutos valorcoprodutos){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("valorcoprodutos", valorcoprodutos);
        RequestContext.getCurrentInstance().openDialog("cadValorCoProdutos");
        return "";
    }
    
    
    public void retornoDialogoNovo(SelectEvent event){
        Valorcoprodutos valorcoprodutos = (Valorcoprodutos) event.getObject();
        listaValores.add(valorcoprodutos);
    }
}
