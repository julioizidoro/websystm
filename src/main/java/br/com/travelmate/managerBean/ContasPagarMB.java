package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ContasPagarFacade;
import br.com.travelmate.model.Contaspagar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class ContasPagarMB implements Serializable{
    
    private Contaspagar conta;
    private List<Contaspagar> listaContasPagar;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        conta = (Contaspagar) session.getAttribute("contaspagar");
        session.removeAttribute("contaspagar");
        String sql = "Select c from Contaspagar c order by c.datavencimento";
        carregarContasPagar(sql);
        conta = new Contaspagar();
    }

    public Contaspagar getConta() {
        return conta;
    }

    public void setConta(Contaspagar conta) {
        this.conta = conta;
    }

    public List<Contaspagar> getListaContasPagar() {
        return listaContasPagar;
    }

    public void setListaContasPagar(List<Contaspagar> listaContasPagar) {
        this.listaContasPagar = listaContasPagar;
    }
    
    
    
    public String cadastrarContaPagar(){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth",570);
        RequestContext.getCurrentInstance().openDialog("confContasPagar", options, null);
        return "";
    }
    
    
    public void carregarContasPagar(String sql){
        ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
        listaContasPagar = contasPagarFacade.listar(sql);
        if (listaContasPagar==null){
            listaContasPagar = new ArrayList<Contaspagar>();
        }
    }
     
     public String editar(){
        if (this.conta!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("contapagar", conta);       
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth",570);
            RequestContext.getCurrentInstance().openDialog("confContasPagar", options, null);
        }
        return "";
    }
    
     
     public String excluir(){
        ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
        contasPagarFacade.excluir(conta.getIdcontaspagar());
        String sql = "Select c from Contaspagar c where c.contapaga='Pendente' order by c.datavencimento";
        carregarContasPagar(sql);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Excluido com Sucesso", ""));
        return "consContaPagar";
     }
}
