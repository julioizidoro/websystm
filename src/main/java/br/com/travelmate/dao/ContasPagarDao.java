/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.dao;
import br.com.travelmate.connection.ConectionFactory;
import br.com.travelmate.model.Contaspagar;
import br.com.travelmate.model.Planoconta;
import br.com.travelmate.model.Produtos;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Julio
 */
public class ContasPagarDao {
    
    private EntityManager manager;
    
    public Contaspagar salvar(Contaspagar conta) throws SQLException{
        manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        conta = manager.merge(conta);
        manager.getTransaction().commit();
        return conta;
    }
    
    public List<Contaspagar> listar(String sql)throws SQLException{
        manager = ConectionFactory.getConnection();
         manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        manager.getTransaction().commit();
        return q.getResultList();
    }
    
    public void excluir(int idContasPagar) throws SQLException {
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select c from Contaspagar c where c.idcontaspagar=" + idContasPagar);
        if (q.getResultList().size()>0){
            Contaspagar contaspagar = (Contaspagar) q.getResultList().get(0);
            manager.remove(contaspagar);
        }
        manager.getTransaction().commit();
    }
}
