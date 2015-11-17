/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean.financeiro;

import br.com.travelmate.facade.UnidadeNegocioFacade;
import br.com.travelmate.facade.VendasComissaoFacade;
import br.com.travelmate.facade.VendasFacade;
import br.com.travelmate.model.Unidadenegocio;
import br.com.travelmate.model.Vendas;
import br.com.travelmate.model.Vendascomissao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class FaturaFranquiaMB implements Serializable{
    
    private Unidadenegocio unidadenegocio;
    private List<Unidadenegocio> listaUnidadeNegocio;
    private List<Vendascomissao> listaFatura;
    private Float valorComissionavel;
    
    @PostConstruct()
    public void init(){
        gerarListaUnidadeNegocio();
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public List<Unidadenegocio> getListaUnidadeNegocio() {
        return listaUnidadeNegocio;
    }

    public void setListaUnidadeNegocio(List<Unidadenegocio> listaUnidadeNegocio) {
        this.listaUnidadeNegocio = listaUnidadeNegocio;
    }

    public List<Vendascomissao> getListaFatura() {
        return listaFatura;
    }

    public void setListaFatura(List<Vendascomissao> listaFatura) {
        this.listaFatura = listaFatura;
    }

    public Float getValorComissionavel() {
        return valorComissionavel;
    }

    public void setValorComissionavel(Float valorComissionavel) {
        this.valorComissionavel = valorComissionavel;
    }

    

    
    public void gerarListaUnidadeNegocio(){
        UnidadeNegocioFacade unidadeNegocioFacade = new UnidadeNegocioFacade();
        listaUnidadeNegocio = unidadeNegocioFacade.listar();
        if (listaUnidadeNegocio==null){
            listaUnidadeNegocio = new ArrayList<Unidadenegocio>();
        }
    }
    
    public void gerarListaFaturaFranquia(){
        VendasComissaoFacade vendasFacade = new VendasComissaoFacade();
        if(unidadenegocio!=null){
             listaFatura = vendasFacade.listar("Select v from Vendascomissao v where v.vendas.unidadenegocio.idunidadeNegocio="+ unidadenegocio.getIdunidadeNegocio() +" order by v.vendas.dataVenda");
        }
        if (listaFatura==null){
            listaFatura = new ArrayList<Vendascomissao>();
        }
        valorComissionavel();
    }
    
    public void valorComissionavel(){
        for(int i=0;i<listaFatura.size();i++){
            if(listaFatura.get(i).getVendas().getProdutos().getIdprodutos()==1){
                valorComissionavel = 0f;
            }else{
                valorComissionavel = listaFatura.get(i).getValorbruto();
            }
        }
    }
    
}
