/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.dao.PlanoContaDao;
import br.com.travelmate.dao.ProdutoDao;
import br.com.travelmate.model.Planoconta;
import br.com.travelmate.model.Produtos;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlanoContaFacade {
    
    private PlanoContaDao planoContaDao;
    
    public Planoconta salvar(Planoconta planoconta) {
        planoContaDao = new PlanoContaDao();
        try {
            return planoContaDao.salvar(planoconta);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Planoconta> listar() {
        planoContaDao = new PlanoContaDao();
        try {
            return planoContaDao.listar();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public Planoconta consultar(int idPlanoConta) {
        planoContaDao = new PlanoContaDao();
        try {
            return planoContaDao.consultar(idPlanoConta);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
