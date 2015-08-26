/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ListaEscolasMB implements Serializable{
    
    private List<FornecedorProdutosBean> listaFornecedorProdutosBean;

    public ListaEscolasMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        listaFornecedorProdutosBean = (List<FornecedorProdutosBean>) session.getAttribute("listaFornecedorProdutosBean");
    }
}
