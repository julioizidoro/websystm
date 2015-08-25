/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.facade.PaisProdutoFacade;
import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Coprodutos;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pacotetrecho;
import br.com.travelmate.model.Pais;
import br.com.travelmate.model.Valorcoprodutos;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ValorCoProdutosMB implements Serializable{
    
    private Coprodutos coprodutos;
    private Valorcoprodutos valorCoProdutos; 
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        coprodutos = (Coprodutos) session.getAttribute("coProdutos");
        session.removeAttribute("CoProdutos");
    }

    public Coprodutos getCoprodutos() {
        return coprodutos;
    }

    public void setCoprodutos(Coprodutos coprodutos) {
        this.coprodutos = coprodutos;
    }

    public Valorcoprodutos getValorCoProdutos() {
        return valorCoProdutos;
    }

    public void setValorCoProdutos(Valorcoprodutos valorCoProdutos) {
        this.valorCoProdutos = valorCoProdutos;
    }
    
    
    
}
