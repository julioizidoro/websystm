/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Parametrosprodutos;
import br.com.travelmate.util.Formatacao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
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
    
    private Parametrosprodutos parametrosprodutos;
    private List<FornecedorProdutosBean> listaFornecedorProdutosBean;

    public ListaEscolasMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        listaFornecedorProdutosBean = (List<FornecedorProdutosBean>) session.getAttribute("listaFornecedorProdutosBean");
    }

    public Parametrosprodutos getParametrosprodutos() {
        return parametrosprodutos;
    }

    public void setParametrosprodutos(Parametrosprodutos parametrosprodutos) {
        this.parametrosprodutos = parametrosprodutos;
    }

    
    public List<FornecedorProdutosBean> getListaFornecedorProdutosBean() {
        return listaFornecedorProdutosBean;
    }

    public void setListaFornecedorProdutosBean(List<FornecedorProdutosBean> listaFornecedorProdutosBean) {
        this.listaFornecedorProdutosBean = listaFornecedorProdutosBean;
    }
    
    
    public String srcLogo(Fornecedor fornecedor){
        String logo ="";
        if (fornecedor!=null){
            logo = "http://www.travelmate.com.br/logoescola/" + fornecedor.getLogo();
        }
        return logo;
    }
    
     public String orcamentoResultado(FornecedorProdutosBean fornecedorProdutosBean){
        if(fornecedorProdutosBean!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("fornecedorProdutosBean", fornecedorProdutosBean);
            return "orcamentoCurso";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Fornecedor não encontrado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
        
    }
    
}
