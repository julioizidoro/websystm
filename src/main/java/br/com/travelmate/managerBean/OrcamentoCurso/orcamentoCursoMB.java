package br.com.travelmate.managerBean.OrcamentoCurso;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class orcamentoCursoMB implements Serializable{
    
    private boolean seguroSelecionado;
    private boolean acomodacaoSelecionado;

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
