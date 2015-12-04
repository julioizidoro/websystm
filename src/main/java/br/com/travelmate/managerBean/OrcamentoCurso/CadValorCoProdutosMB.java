/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.ValorCoProdutosFacade;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Valorcoprodutos;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadValorCoProdutosMB implements Serializable{
    
    private Valorcoprodutos valorcoprodutos;

    public CadValorCoProdutosMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        valorcoprodutos = (Valorcoprodutos) session.getAttribute("valorcoprodutos");
        Coprodutos coprodutos = (Coprodutos) session.getAttribute("coprodutos");
        session.removeAttribute("valorcoprodutos");
        if (valorcoprodutos==null){
            valorcoprodutos = new Valorcoprodutos();
            valorcoprodutos.setDatainicial(Formatacao.ConvercaoStringData("01/01/2016"));
            valorcoprodutos.setDatafinal(Formatacao.ConvercaoStringData("31/12/2016"));
            valorcoprodutos.setTipodata("DM");
            valorcoprodutos.setValorpromocional(0.0f);
            valorcoprodutos.setCoprodutos(coprodutos);
        }
        
    }

    public Valorcoprodutos getValorcoprodutos() {
        return valorcoprodutos;
    }

    public void setValorcoprodutos(Valorcoprodutos valorcoprodutos) {
        this.valorcoprodutos = valorcoprodutos;
    }
    
    public String salvar(){
        if (valorcoprodutos.getValorpromocional()>0){
            valorcoprodutos.setPromocional(false);
        }else valorcoprodutos.setPromocional(true);
        ValorCoProdutosFacade valorCoProdutosFacade = new ValorCoProdutosFacade();  
        valorcoprodutos = valorCoProdutosFacade.salvar(valorcoprodutos);
        RequestContext.getCurrentInstance().closeDialog(valorcoprodutos);
        return "";
    }
    
    public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
    
    
    
    
    
}
