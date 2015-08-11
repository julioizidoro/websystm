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
public class PacoteAgenciaMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Pacotes> listaPacotes;

    public PacoteAgenciaMB() {
        String sql = "Select p from Pacotes p where p.operacao='Agencia' and p.unidadenegocio.idunidadeNegocio=" + usuarioLogadoMB.getUsuario().getUnidadenegocio().getIdunidadeNegocio() + "   order by p.vendas.dataVenda";
        listaPacotes = GerarListas.listarPacotes(sql);
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public List<Pacotes> getListaPacotes() {
        return listaPacotes;
    }

    public void setListaPacotes(List<Pacotes> listaPacotes) {
        this.listaPacotes = listaPacotes;
    }
    
     public String novoPacotes(){
        return "cadpacote";
    }
    
    public String editarPacote(Pacotes pacote){
        FacesContext fc = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);  
        session.setAttribute("pacote", pacote);
        return "cadpacote";
    }
    
    
}
