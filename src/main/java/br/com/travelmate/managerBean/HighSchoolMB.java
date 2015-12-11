package br.com.travelmate.managerBean;

import br.com.travelmate.facade.HighSchoolFacade;
import br.com.travelmate.model.Highschool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class HighSchoolMB implements Serializable{

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Highschool> listaHighSchool;
    
    
    public List<Highschool> getListaHighSchool() {
        if(listaHighSchool==null){
            gerarListaHighSchool();
        }
        return listaHighSchool;
    }

    public void setListaHighSchool(List<Highschool> listaHighSchool) {
        this.listaHighSchool = listaHighSchool;
    }
    
    
    public String cadastrarFicha(){
        return "cadHighSchool";
    }
    
    public void gerarListaHighSchool(){
        HighSchoolFacade highSchoolFacade = new HighSchoolFacade();
        listaHighSchool = highSchoolFacade.listar("");
        if (listaHighSchool==null){
            listaHighSchool = new ArrayList<Highschool>();
        }
    }
}
