/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.OrcamentoCurso;

import br.com.travelmate.model.Valorcoprodutos;

/**
 *
 * @author Wolverine
 */
public class ProdutosOrcamentoBean {
    
    private Valorcoprodutos valorcoprodutos;
    private Float valorOrigianl;
    private Float valorOriginalRS;
    private Float valorPromocional;
    private Float valorPromocionalRS;
    private boolean selecionado;

    public Valorcoprodutos getValorcoprodutos() {
        return valorcoprodutos;
    }

    public void setValorcoprodutos(Valorcoprodutos valorcoprodutos) {
        this.valorcoprodutos = valorcoprodutos;
    }

    public Float getValorOrigianl() {
        return valorOrigianl;
    }

    public void setValorOrigianl(Float valorOrigianl) {
        this.valorOrigianl = valorOrigianl;
    }

    public Float getValorOriginalRS() {
        return valorOriginalRS;
    }

    public void setValorOriginalRS(Float valorOriginalRS) {
        this.valorOriginalRS = valorOriginalRS;
    }

    public Float getValorPromocionalRS() {
        return valorPromocionalRS;
    }

    public void setValorPromocionalRS(Float valorPromocionalRS) {
        this.valorPromocionalRS = valorPromocionalRS;
    }

    

    public Float getValorPromocional() {
        return valorPromocional;
    }

    public void setValorPromocional(Float valorPromocional) {
        this.valorPromocional = valorPromocional;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
    
    
    
    
        
    
}
