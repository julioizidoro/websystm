/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.model.Banco;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.util.GerarListas;
import java.io.Serializable;
import java.util.List;
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
public class EditarContasReceberMB implements Serializable{
    
    
    private Contasreceber conta;
    private List<Banco> listaBanco;

    public EditarContasReceberMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        conta = (Contasreceber) session.getAttribute("contareceber");
        session.removeAttribute("contareceber");
        listaBanco = GerarListas.listarBancos();
    }

    public Contasreceber getConta() {
        return conta;
    }

    public void setConta(Contasreceber conta) {
        this.conta = conta;
    }

    public List<Banco> getListaBanco() {
        return listaBanco;
    }

    public void setListaBanco(List<Banco> listaBanco) {
        this.listaBanco = listaBanco;
    }
    
    public String salvar(){
        ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
        contasReceberFacade.salvar(conta);
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consultacontasreceber";
    }
    
    public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return null;
    }
    
    
    
}
