/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ClienteFacade;
import br.com.travelmate.model.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ConsultaClienteMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cliente> listaCliente;
    private String nome;
    
    public ConsultaClienteMB() {
        carregarListaCliente();
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void carregarListaCliente(){
        if (nome==null){
            nome = "";
        }
        ClienteFacade clienteFacade = new ClienteFacade();
        listaCliente = clienteFacade.consultarNome(nome);
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
    }
    
    public void selecionarCliente(Cliente cliente){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("cliente", cliente);
        RequestContext.getCurrentInstance().closeDialog(cliente);
    }
}
