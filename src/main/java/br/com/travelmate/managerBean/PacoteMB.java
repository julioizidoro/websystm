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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class PacoteMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Pacotes> listaPacotes;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;

    public PacoteMB() {
        String sql = "Select p from Pacotes p where p.operacao='Operadora' order by p.vendas.dataVenda";
        listaPacotes = GerarListas.listarPacotes(sql);
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
    
    
    public String novoPacotes(){
        return "cadPacoteOperadora";
    }
    
    public String editarPacote(Pacotes pacote){
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacote);
        return "cadPacoteOperadora";
    }
}

