package br.com.travelmate.managerBean.OrcamentoCurso;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class OrcamentoCursoMB implements Serializable{
    
    private FornecedorProdutosBean fornecedorProdutosBean;
    private boolean seguroSelecionado;
    private boolean acomodacaoSelecionado;

    public OrcamentoCursoMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        fornecedorProdutosBean = (FornecedorProdutosBean) session.getAttribute("FornecedorProdutosBean");
    }

    public FornecedorProdutosBean getFornecedorProdutosBean() {
        return fornecedorProdutosBean;
    }

    public void setFornecedorProdutosBean(FornecedorProdutosBean fornecedorProdutosBean) {
        this.fornecedorProdutosBean = fornecedorProdutosBean;
        
    }

    public boolean isSeguroSelecionado() {
        return seguroSelecionado;
    }

    public void setSeguroSelecionado(boolean seguroSelecionado) {
        this.seguroSelecionado = seguroSelecionado;
    }

    public boolean isAcomodacaoSelecionado() {
        return acomodacaoSelecionado;
    }

    public void setAcomodacaoSelecionado(boolean acomodacaoSelecionado) {
        this.acomodacaoSelecionado = acomodacaoSelecionado;
    }
    
    public String abilitarSeguro(){
        if(seguroSelecionado){
            return "false";
        }
        return "true";
    }
    
    public String abilitarAcomodacao(){
        if(acomodacaoSelecionado){
            return "false";
        }
        return "true";
    }
}
