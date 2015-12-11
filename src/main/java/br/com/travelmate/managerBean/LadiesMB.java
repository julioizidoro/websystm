/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.LadiesFacade;
import br.com.travelmate.model.Ladies;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LadiesMB implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Ladies> listaLadies;
    
    public List<Ladies> getListaLadies(){
        if (listaLadies == null) {
            gerarListaLadies();
        }
        return listaLadies;
    }
    
    public void setListaLadies(List<Ladies> listaLadies){
        this.listaLadies = listaLadies;
    }
    
    
    public String cadastrarFicha(){
        return "cadFichaLadies";
    }

    private void gerarListaLadies() {
        LadiesFacade ladiesFacade = new LadiesFacade();
        listaLadies = ladiesFacade.listar("");
        if (listaLadies == null) {
            listaLadies = new ArrayList<Ladies>();
        }
    }
}
