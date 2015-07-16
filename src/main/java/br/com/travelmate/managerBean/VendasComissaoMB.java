/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.VendasComissaoFacade;
import br.com.travelmate.model.Vendascomissao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class VendasComissaoMB implements Serializable{
    
    private Vendascomissao vendascomissao;
    private List<Vendascomissao> listaVendasComissao;

    public VendasComissaoMB() {
        
    }
    
    @PostConstruct
    public void init(){
        this.vendascomissao = new Vendascomissao();
        String sql = "Select v from Vendascomissao v order by v.vendas.dataVenda";
        gerarListaVendaComissao(sql);
    }
    
    public List<Vendascomissao> getListaVendasComissao() {
        return listaVendasComissao;
    }

    public void setListaVendasComissao(List<Vendascomissao> listaVendasComissao) {
        this.listaVendasComissao = listaVendasComissao;
    }
    
    public void gerarListaVendaComissao(String sql){
        VendasComissaoFacade vendasComissaoFacade = new VendasComissaoFacade();
        listaVendasComissao = vendasComissaoFacade.listar(sql);
        if (listaVendasComissao==null){
            listaVendasComissao = new ArrayList<Vendascomissao>();
        }
    }
    public String editar(){
        return "editarComissaovendas";
    }
}
