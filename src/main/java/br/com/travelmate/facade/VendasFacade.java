/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.travelmate.dao.VendasDao;
import br.com.travelmate.model.Vendas;




/**
 *
 * @author Wolverine
 */
public class VendasFacade {
    
    VendasDao vendasDao;
    
    public Vendas salvar(Vendas venda) {
        vendasDao = new  VendasDao();
        try {
            return vendasDao.salvar(venda);
        } catch (SQLException ex) {
            Logger.getLogger(VendasFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Vendas consultarVendas(int idVenda) {
        vendasDao = new VendasDao();
        try {
            return vendasDao.consultarVendas(idVenda);
        } catch (SQLException ex) {
            Logger.getLogger(VendasFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
   
    public List<Vendas> lista() {
        vendasDao = new VendasDao();
        try {
            return vendasDao.lista();
        } catch (SQLException ex) {
            Logger.getLogger(VendasFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
