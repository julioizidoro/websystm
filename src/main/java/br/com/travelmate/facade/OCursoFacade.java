/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.OCursoDao;
import br.com.travelmate.model.Ocurso;
import java.sql.SQLException;

/**
 *
 * @author Wolverine
 */
public class OCursoFacade {
    
    private OCursoDao oCursoDao;
    
    public Ocurso salvar(Ocurso  ocurso) throws SQLException{
        oCursoDao = new OCursoDao();
        return oCursoDao.salvar(ocurso);
    }
    
}
