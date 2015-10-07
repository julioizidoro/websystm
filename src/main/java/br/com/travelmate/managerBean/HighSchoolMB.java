package br.com.travelmate.managerBean;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class HighSchoolMB implements Serializable{
    
    
    public String cadastrarFicha(){
        return "cadHighSchool";
    }
}
