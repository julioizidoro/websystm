/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.model.Pacotes;
import br.com.travelmate.util.GerarListas;
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
 * @author Wolverine
 */
@Named
@ViewScoped
public class PacoteMB implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    private List<Pacotes> listaPacotes;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Pacotes> listaPacotesAgencia;
    private Pacotes pacotes;

    @PostConstruct
    public void init() {
        listaPacotes = GerarListas.listarPacotes("Select p from Pacotes p where  p.operacao='Operadora' order by p.vendas.dataVenda");
        listaPacotesAgencia = GerarListas.listarPacotes("Select p from Pacotes p where p.operacao='agencia' and p.unidadenegocio.idunidadeNegocio='"+usuarioLogadoMB.getUsuario().getUnidadenegocio().getIdunidadeNegocio()+"' order by p.vendas.dataVenda");
    }
    
    public List<Pacotes> getListaPacotes() {
        return listaPacotes;
    }

    public void setListaPacotes(List<Pacotes> listaPacotes) {
        this.listaPacotes = listaPacotes;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Pacotes> getListaPacotesAgencia() {
        return listaPacotesAgencia;
    }

    public void setListaPacotesAgencia(List<Pacotes> listaPacotesAgencia) {
        this.listaPacotesAgencia = listaPacotesAgencia;
    }

    public Pacotes getPacotes() {
        return pacotes;
    }

    public void setPacotes(Pacotes pacotes) {
        this.pacotes = pacotes;
    }

    
    
    public String novoPacotes(){
        return "cadPacoteOperadora";
    }
    
    public String editarPacote(Pacotes pacote){
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacote);
        return "cadPacoteOperadora";
    }
    
    
    public String editarPacoteAgencia(Pacotes pacote){
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacote);
        return "cadPacote";
    }
    
    public String retornarPacoteImportado(Pacotes pacote){
        pacotes = new Pacotes();
        pacotes.setPacotetrechoList(pacote.getPacotetrechoList());
        pacotes.setDescricao(pacote.getDescricao());
        pacotes.setCliente(pacote.getCliente());
        pacotes.setDatainicio(pacote.getDatainicio());
        pacotes.setDatetermino(pacote.getDatetermino());
        pacotes.setCartaovtm(pacote.getCartaovtm());
        pacotes.setNumerocartaovtm(pacote.getNumerocartaovtm());
        pacotes.setMoeda(pacote.getMoeda());
        pacotes.setCambio(pacote.getCambio());
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacote);
        RequestContext.getCurrentInstance().closeDialog(pacote);
        return "cadPacote";
    }
}

