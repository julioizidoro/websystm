/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Cliente;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class ClienteDao {
    
    private EntityManager manager;
    
    public Cliente salvar(Cliente cliente) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        cliente = manager.merge(cliente);
        //fechando uma transação
        manager.getTransaction().commit();
        return cliente;
    }
    
    public Cliente consultar(int idCliente) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Cliente cliente = manager.find(Cliente.class, idCliente);
        //fechando uma transação
        manager.getTransaction().commit();
        return cliente;
    }
    
    public Cliente consultarEmail(String email) throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Cliente c where c.email='" + email + "'" );
          manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Cliente) q.getSingleResult();
        } 
        return null;
    }
    
}
