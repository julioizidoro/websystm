package br.com.travelmate.facade;

import br.com.travelmate.dao.HighSchoolDao;
import br.com.travelmate.model.Highschool;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HighSchoolFacade {
    
    HighSchoolDao highSchoolDao;
    
    
    public Highschool salvar(Highschool highschool){
        highSchoolDao = new HighSchoolDao();
        try {
            return highSchoolDao.salvar(highschool);
        } catch (SQLException ex) {
            Logger.getLogger(HighSchoolFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public List<Highschool> listar(String nome) {
        highSchoolDao = new HighSchoolDao();
        try {
            return highSchoolDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(HighSchoolFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
