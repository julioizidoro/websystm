/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.ProgramasTeensDao;
import br.com.travelmate.model.Programasteens;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greicieli
 */
public class ProgramasTeensFacede {
    ProgramasTeensDao programasTeensDao;
    
     public Programasteens salvar(Programasteens programasteens){
        programasTeensDao = new ProgramasTeensDao();
        try {
            return programasTeensDao.salvar(programasteens);
        } catch (SQLException ex) {
            Logger.getLogger(HighSchoolFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public List<Programasteens> listar(String nome) {
        programasTeensDao = new ProgramasTeensDao();
        try {
            return programasTeensDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ProgramasTeensFacede.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
