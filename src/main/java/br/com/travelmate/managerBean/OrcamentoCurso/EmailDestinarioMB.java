/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.managerBean.UsuarioLogadoMB;
import br.com.travelmate.model.Ocrusoprodutos;
import br.com.travelmate.model.Ocurso;
import br.com.travelmate.model.Ocursoformapagamento;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class EmailDestinarioMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private String emailDestinario;
    private String emailConsultor;
    private String emailCopiaOculta;
    private Ocurso ocurso;
    private List<Ocrusoprodutos> listaProdutos;
    private Ocursoformapagamento formaPagamento;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        ocurso = (Ocurso) session.getAttribute("ocurso");
        formaPagamento = (Ocursoformapagamento) session.getAttribute("formaPagamento");
        listaProdutos = (List<Ocrusoprodutos>) session.getAttribute("listaProdutos");
        session.removeAttribute("ocurso");
        session.removeAttribute("formaPagamento");
        session.removeAttribute("listaProdutos");
        getUsuarioLogadoMB();
        emailConsultor = usuarioLogadoMB.getUsuario().getEmail();
        emailDestinario = ocurso.getCliente().getEmail();
    }

    public Ocurso getOcurso() {
        return ocurso;
    }

    public void setOcurso(Ocurso ocurso) {
        this.ocurso = ocurso;
    }
    

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public String getEmailDestinario() {
        return emailDestinario;
    }

    public void setEmailDestinario(String emailDestinario) {
        this.emailDestinario = emailDestinario;
    }

    public String getEmailConsultor() {
        return emailConsultor;
    }

    public void setEmailConsultor(String emailConsultor) {
        this.emailConsultor = emailConsultor;
    }

    public String getEmailCopiaOculta() {
        return emailCopiaOculta;
    }

    public void setEmailCopiaOculta(String emailCopiaOculta) {
        this.emailCopiaOculta = emailCopiaOculta;
    }

    public List<Ocrusoprodutos> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Ocrusoprodutos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Ocursoformapagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Ocursoformapagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void enviarEmailDestinario(){
         enviarEmail(ocurso, listaProdutos, formaPagamento);
    }
    
    public void enviarEmail(Ocurso ocurso,  List<Ocrusoprodutos> listaProdutos, Ocursoformapagamento formaPagamento){
        EnviarEmailBean enviarEmailBean = new EnviarEmailBean(ocurso, listaProdutos, formaPagamento);
        enviarEmailBean.enviarEmail();
    }
}
