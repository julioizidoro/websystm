package br.com.travelmate.managerBean;

import br.com.travelmate.facade.CobrancaFacade;
import br.com.travelmate.facade.HistoricoCobrancaFacade;
import br.com.travelmate.model.Cobranca;
import br.com.travelmate.model.Historicocobranca;
import br.com.travelmate.model.Vendas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class HistoricoCobrancaMB implements Serializable{
    
    
    @Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    private Historicocobranca historico;  
    
    public HistoricoCobrancaMB() {
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        historico =  (Historicocobranca) session.getAttribute("historico");
        session.removeAttribute("historico");
        Cobranca cobranca =   (Cobranca) session.getAttribute("cobranca");
        if (historico==null){
            historico = new Historicocobranca();
            historico.setCobranca(cobranca);
             session.removeAttribute("cobranca");
        }
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }
    
    public Historicocobranca getHistorico() {
        return historico;
    }

    public void setHistorico(Historicocobranca historico) {
        this.historico = historico;
    }
    
    
    public String voltar(){
        return "cobranca";
    }
    
    public String salvarEdicao(){ 
        HistoricoCobrancaFacade historicoCobrancaFacade = new HistoricoCobrancaFacade();
        historicoCobrancaFacade.salvar(historico);
        RequestContext.getCurrentInstance().closeDialog(null);
        FacesMessage mensagem = new FacesMessage("Alterado com Sucesso! ", "Historico de Cobrança alterado.");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        return "consContasReceber";
    }
    
    
   
}
