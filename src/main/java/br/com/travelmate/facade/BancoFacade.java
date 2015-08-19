/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.BancoDao;
import br.com.travelmate.model.Banco;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class BancoFacade {
    
    BancoDao bancoDao;
    
    public List<Banco> listar() {
        bancoDao = new BancoDao();
        try {
            return bancoDao.listar();
        } catch (SQLException ex) {
            Logger.getLogger(BancoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
