package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ContasPagarFacade;
import br.com.travelmate.facade.ContasReceberFacade;
import br.com.travelmate.facade.PlanoContaFacade;
import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.model.Contaspagar;
import br.com.travelmate.model.Contasreceber;
import br.com.travelmate.model.Planoconta;
import br.com.travelmate.model.Unidadenegocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class ContasPagarMB implements Serializable{
    
    private Contaspagar conta;
    private List<Unidadenegocio> listaUnidadeNegocio;
    private List<Planoconta> listaPlanoConta;
    
    public ContasPagarMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        conta = (Contaspagar) session.getAttribute("contapagar");
        session.removeAttribute("contapagar");
        carregarUnidadeNegocio();
        carregarPlanoConta();
    }

    public Contaspagar getConta() {
        return conta;
    }

    public void setConta(Contaspagar conta) {
        this.conta = conta;
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public List<Planoconta> getListaPlanoConta() {
        return listaPlanoConta;
    }

    public void setListaPlanoConta(List<Planoconta> listaPlanoConta) {
        this.listaPlanoConta = listaPlanoConta;
    }
    
   
    
    public String cadastrarContaPagar(){
        return "cadContasPagar";
    }
    
    public String confirmarContaPagar(){
        conta = new Contaspagar();
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",800);
        RequestContext.getCurrentInstance().openDialog("confContasPagar", options, null);
        return "";
    }
    
    
     public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return null;
    }
     
     public String salvar(){
        ContasPagarFacade contasReceberFacade = new ContasPagarFacade();
        contasReceberFacade.salvar(conta);
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consContasPagar";
    }
     
     public void carregarUnidadeNegocio(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        listaUnidadeNegocio = unidadeNegocioFacade.listar();
        if (listaUnidadeNegocio==null){
            listaUnidadeNegocio = new ArrayList<Unidadenegocio>();
        }
    }
     
    public void carregarPlanoConta(){
        PlanoContaFacade planoContaFacade = new PlanoContaFacade();
        listaPlanoConta = planoContaFacade.listar();
        if (listaPlanoConta==null){
            listaPlanoConta = new ArrayList<Planoconta>();
        }
    }
     
}
