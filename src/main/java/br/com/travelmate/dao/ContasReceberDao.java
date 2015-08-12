/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.travelmate.dao;

import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Contasreceber;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 * 
 *
 * @author Wolverine
 */
public class ContasReceberDao {
    
    private EntityManager manager;
    
    public Contasreceber salvar(Contasreceber conta) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        conta = manager.merge(conta);
        manager.getTransaction().commit();
        return conta;
    }
    
    public Contasreceber consultar(int idConta) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Contasreceber conta = manager.find(Contasreceber.class, idConta);
        manager.getTransaction().commit();
        return conta;
    }
    
    public void excluir(int idConta) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Contasreceber conta = manager.find(Contasreceber.class, idConta);
        manager.remove(conta);
        manager.getTransaction().commit();
    }
    
    public List<Contasreceber> listar(String sql)throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        manager.getTransaction().commit();
        return q.getResultList();
    }
    
    public Contasreceber consultar(String sql)throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        manager.getTransaction().commit();
        Contasreceber conta = null;
        if (q.getResultList().size()>0){
            conta = (Contasreceber) q.getResultList().get(0);
        }
        return conta;
    }
}
