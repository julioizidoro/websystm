/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Parametrosprodutos;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Wolverine
 */
public class ParametrosProdutosDao {
    
    private EntityManager manager;
    
    public Parametrosprodutos salvar(Parametrosprodutos parametrosprodutos) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        parametrosprodutos = manager.merge(parametrosprodutos);
        //fechando uma transação
        manager.getTransaction().commit();
        return parametrosprodutos;
    }
    
    public Parametrosprodutos consultar() throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
         manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Parametrosprodutos  p");
        //fechando uma transação
         manager.getTransaction().commit();
        if (q.getResultList().size()>0){
            return (Parametrosprodutos) q.getSingleResult();
        }
        return null;
    }
}
