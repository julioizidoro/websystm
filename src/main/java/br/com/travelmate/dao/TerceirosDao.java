/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Terceiros;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class TerceirosDao {
    
    private EntityManager manager;
    
    public List<Terceiros> lista() throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select t from Terceiros t order by t.nome");
        manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return q.getResultList();
        }
        return null;
    }
    
}
