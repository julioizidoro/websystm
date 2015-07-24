/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Moedas;
import br.com.travelmate.model.Pacotetrecho;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class PacoteTrechoDao {
    
    private EntityManager manager;
    
    public Pacotetrecho salvar(Pacotetrecho pacotetrecho) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        pacotetrecho = manager.merge(pacotetrecho);
        //fechando uma transação
        manager.getTransaction().commit();
        return pacotetrecho;
    }
    
    public List<Pacotetrecho> listar(String sql) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("sql");
        List<Pacotetrecho> lista = null;
        if (q.getResultList().size()>0){
            lista = q.getResultList();
        }
        manager.getTransaction().commit();
        return lista;
    }
    
}
