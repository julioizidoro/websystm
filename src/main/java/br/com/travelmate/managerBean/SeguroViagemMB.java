package br.com.travelmate.managerBean;


import br.com.travelmate.facade.SeguroViagemFacade;
import br.com.travelmate.model.Cambio;
import br.com.travelmate.model.Pacotes;
import br.com.travelmate.model.Seguroviagem;
import java.io.Serializable;
import java.sql.SQLException;
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
public class SeguroViagemMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Cambio cambio;
    private Seguroviagem seguroviagem;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Pacotes pacotes = (Pacotes) session.getAttribute("pacote");
        session.removeAttribute("pacote");
        if (seguroviagem == null) {
            seguroviagem = new Seguroviagem();
        } 
    }
    
    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Seguroviagem getSeguroviagem() {
        return seguroviagem;
    }

    public void setSeguroviagem(Seguroviagem seguroviagem) {
        this.seguroviagem = seguroviagem;
    }
    
    
    
    
    public String salvarSeguro() throws SQLException{
        SeguroViagemFacade seguroViagemFacade = new SeguroViagemFacade();
        seguroViagemFacade.salvar(seguroviagem);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Salvo com Sucesso", ""));
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        RequestContext.getCurrentInstance().closeDialog("cadpacotesoperadora");
        return "";
    }
    
}
