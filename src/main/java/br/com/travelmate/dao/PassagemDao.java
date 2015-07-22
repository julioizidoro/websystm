/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Passagemaerea;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class PassagemDao {
    
    private EntityManager manager;
    
    public Passagemaerea salvar(Passagemaerea passageAerea) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        passageAerea = manager.merge(passageAerea);
        //fechando uma transação
        manager.getTransaction().commit();
        return passageAerea;
    }
    
     public void excluir(int idPassagem) throws SQLException{
        manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        Passagemaerea passagem = manager.find(Passagemaerea.class, idPassagem);
        manager.remove(passagem);
        //fechando uma transação
        manager.getTransaction().commit();
    }
     
     public Passagemaerea consultar(int idVenda) throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Passagemaerea p where p.vendas=" + idVenda);
         manager.getTransaction().commit();
        if (q.getResultList().size() > 0) {
            return  (Passagemaerea) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
}
