/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Fornecedor;
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

    public List<FornecedorProdutosBean> getListaFornecedorProdutosBean() {
        return listaFornecedorProdutosBean;
    }

    public void setListaFornecedorProdutosBean(List<FornecedorProdutosBean> listaFornecedorProdutosBean) {
        this.listaFornecedorProdutosBean = listaFornecedorProdutosBean;
    }
    
    
    public String srcLogo(){
        Fornecedor fornecedor = new Fornecedor();
        String logo = "http://www.travelmate.com.br/logoescola/" + fornecedor.getLogo() + ".png";
        return logo;
    }
    
}
