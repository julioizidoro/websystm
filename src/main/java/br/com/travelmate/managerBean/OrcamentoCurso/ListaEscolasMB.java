/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Parametrosprodutos;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ListaEscolasMB implements Serializable{
    
    @Inject
    private FiltrarEscolaMB filtrarEscolaMB;
    private Parametrosprodutos parametrosprodutos;
    
    @PostConstruct
    public void init() {
    }

    public FiltrarEscolaMB getFiltrarEscolaMB() {
        return filtrarEscolaMB;
    }

    public void setFiltrarEscolaMB(FiltrarEscolaMB filtrarEscolaMB) {
        this.filtrarEscolaMB = filtrarEscolaMB;
    }

    public Parametrosprodutos getParametrosprodutos() {
        return parametrosprodutos;
    }

    public void setParametrosprodutos(Parametrosprodutos parametrosprodutos) {
        this.parametrosprodutos = parametrosprodutos;
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
            filtrarEscolaMB.setFornecedorProdutosBean(fornecedorProdutosBean);
            return "orcamentoCurso";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Fornecedor não encontrado.");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
        
    }
     
     public String voltar(){
         return "filtrarOrcamentoCurso";
     }
    
}
