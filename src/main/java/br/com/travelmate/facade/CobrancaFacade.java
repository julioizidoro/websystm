/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.CobrancaDao;
import br.com.travelmate.model.Cobranca;
import br.com.travelmate.model.Historicocobranca;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class CobrancaFacade {
    
    private CobrancaDao cobrancaDao;
    
    public Cobranca salvar(Cobranca cobranca) {
        cobrancaDao = new CobrancaDao();
        try {
            return cobrancaDao.salvar(cobranca);
        } catch (SQLException ex) {
            Logger.getLogger(CobrancaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Historicocobranca salvar(Historicocobranca historicocobranca) {
        cobrancaDao = new CobrancaDao();
        try {
            return cobrancaDao.salvar(historicocobranca);
        } catch (SQLException ex) {
            Logger.getLogger(CobrancaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
}
