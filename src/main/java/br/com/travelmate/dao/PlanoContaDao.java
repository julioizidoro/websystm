/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;
import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Planoconta;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class PlanoContaDao {
    
    private EntityManager manager;
    
    public Planoconta salvar(Planoconta planoconta) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        planoconta = manager.merge(planoconta);
        //fechando uma transação
        manager.getTransaction().commit();
        return planoconta;
    }
    
    public List<Planoconta> listar() throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Planoconta p order by p.descricao" );
        manager.getTransaction().commit();
        return q.getResultList();
    }
    
    public Planoconta consultar(int idPlanoConta) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Planoconta planoconta = manager.find(Planoconta.class, idPlanoConta);
        //fechando uma transação
        manager.getTransaction().commit();
        return planoconta;
    }
}
