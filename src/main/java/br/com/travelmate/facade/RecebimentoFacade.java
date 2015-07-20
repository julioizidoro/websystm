/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.RecebimentoDao;
import br.com.travelmate.model.Recebimento;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class RecebimentoFacade {
    
    RecebimentoDao recebimentoDao;
    
    public Recebimento salvar(Recebimento recebimento) {
        recebimentoDao = new RecebimentoDao();
        try {
            return recebimentoDao.salvar(recebimento);
        } catch (SQLException ex) {
            Logger.getLogger(RecebimentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
