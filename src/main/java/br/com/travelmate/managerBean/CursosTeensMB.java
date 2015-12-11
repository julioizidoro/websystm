/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.facade.ProgramasTeensFacede;
import br.com.travelmate.model.Programasteens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CursosTeensMB implements Serializable {
 
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Programasteens> listaCursosTeens;
    
    
    public List<Programasteens> getListaCursosTeens() {
        if(listaCursosTeens==null){
            gerarListaCursosTeens();
        }
        return listaCursosTeens;
    }

    public void setListaCursosTeens(List<Programasteens> listaCursosTeens) {
        this.listaCursosTeens = listaCursosTeens;
    }
    
    
    public String cadastrarFicha(){
        return "cadProgramasTeens";
    }
    
     public void gerarListaCursosTeens(){
        ProgramasTeensFacede programasTeensFacede = new ProgramasTeensFacede();
        listaCursosTeens= programasTeensFacede.listar("");
        if (listaCursosTeens==null){
            listaCursosTeens = new ArrayList<Programasteens>();
        }
    }
}
