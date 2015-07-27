/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.facade;

import br.com.travelmate.dao.ClienteDao;
import br.com.travelmate.model.Cliente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Wolverine
 */
public class ClienteFacade {
    
    ClienteDao clienteDao;
    
    public Cliente salvar(Cliente cliente){
        clienteDao = new ClienteDao();
        try {
            return clienteDao.salvar(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Cliente consultar(int idCliente) {
        clienteDao = new ClienteDao();
        try {
            return clienteDao.consultar(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Cliente consultarEmail(String email) {
        clienteDao = new ClienteDao();
        try {
            return clienteDao.consultarEmail(email);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
